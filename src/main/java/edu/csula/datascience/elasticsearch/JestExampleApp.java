package edu.csula.datascience.elasticsearch;

import com.google.common.collect.Lists;

import edu.csula.datascience.acquisition.Review;
import io.searchbox.action.BulkableAction;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * A quick example app to send data to elastic search on AWS
 */
public class JestExampleApp {
    public static void main(String[] args) throws URISyntaxException, IOException {
        String indexName = "amazon-data-new";
        String typeName = "reviews";
        String awsAddress = "http://search-amazon-reviews1-aipcs5lx5q5shrxcgcq3lmrfmq.us-east-1.es.amazonaws.com/";
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
            .Builder(awsAddress)
            .multiThreaded(true)
            .build());
        JestClient client = factory.getObject();

        // as usual process to connect to data source, we will need to set up
        // node and client// to read CSV file from the resource folder
        File csv = new File(
            ClassLoader.getSystemResource("output.csv")
                .toURI()
        );

        try {
            // after reading the csv file, we will use CSVParser to parse through
            // the csv files
            CSVParser parser = CSVParser.parse(
                csv,
                Charset.defaultCharset(),
                CSVFormat.EXCEL.withHeader()
            );
            Collection<Review> reviews = Lists.newArrayList();

            int count = 0;

            // for each record, we will insert data into Elastic Search
//            parser.forEach(record -> {
            for (CSVRecord record: parser) {

                Review review = new Review(
                		record.get("reviewerID"),
                		record.get("asin"),
                		record.get("reviewerName"),
                		getHelpful(record.get("helpful")),
                		Float.valueOf(record.get("hepfulFeedbackCount")),
                		Float.valueOf(record.get("feedbackCount")),
                		record.get("reviewText"),
                		Float.valueOf(record.get("overall")),
                		record.get("summary"),
                		Long.valueOf(record.get("unixReviewTime")),
                		record.get("reviewTime"),
                		Long.valueOf(record.get("datetime")),
                		Long.valueOf(record.get("reviewLength")),
                		Long.valueOf(record.get("summaryLength")),
                		Float.valueOf(record.get("numericRatio")),
                		Float.valueOf(record.get("capitalRatio")),
                		Long.valueOf(record.get("hash")),
                		Float.valueOf(record.get("fake")),
                		Float.valueOf(record.get("reviewerCount")),
                		Float.valueOf(record.get("duplicateCount")),
                		Float.valueOf(record.get("asinCount")),
                		Float.valueOf(record.get("overallMean")),
                		Float.valueOf(record.get("reviewerAverageRating")),
                		Float.valueOf(record.get("ratingDeviation"))
                );

                if (count < 500) {
                	reviews.add(review);
                    count ++;
                } else {
                    try {
                        Collection<BulkableAction> actions = Lists.newArrayList();
                        reviews.stream()
                            .forEach(tmp -> {
                                actions.add(new Index.Builder(tmp).build());
                            });
                        Bulk.Builder bulk = new Bulk.Builder()
                            .defaultIndex(indexName)
                            .defaultType(typeName)
                            .addAction(actions);
                        client.execute(bulk.build());
                        count = 0;
                        reviews = Lists.newArrayList();
                        System.out.println("Inserted 500 documents to cloud");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } 
            }

            Collection<BulkableAction> actions = Lists.newArrayList();
            reviews.stream()
                .forEach(tmp -> {
                    actions.add(new Index.Builder(tmp).build());
                });
            Bulk.Builder bulk = new Bulk.Builder()
                .defaultIndex(indexName)
                .defaultType(typeName)
                .addAction(actions);
            client.execute(bulk.build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("We are done! Yay!");
    }

	public static Review jsonToReview(String json) {
		Review review = new Review();
		JSONObject object = null;

		try {
			object = new JSONObject(json);
			review.setReviewerID(object.getString("reviewerID"));
			review.setAsin(object.getString("asin"));
			review.setReviewText(object.getString("reviewText"));

			review.setReviewerName("");

			Integer[] helpful = new Integer[2];
			helpful[0] = object.getJSONArray("helpful").getInt(0);
			helpful[1] = object.getJSONArray("helpful").getInt(1);
			review.setHelpful(helpful);

			review.setOverall((float) object.getDouble("overall"));

			if (object.get("summary") == null)
				review.setSummary("");
			else
				review.setSummary(object.getString("summary"));

			review.setUnixReviewTime(object.getJSONObject("unixReviewTime").getLong("$numberLong"));
			review.setReviewTime(object.getString("reviewTime"));
			review.setDatetime(object.getJSONObject("datetime").getLong("$numberLong"));
			
			review.setReviewLength(object.getLong("reviewLength"));
			review.setSummaryLength(object.getLong("summaryLength"));
			review.setNumericRatio(object.getLong("numericRatio"));
			review.setCapitalRatio(object.getLong("capitalRatio"));
			review.setHash(object.getLong("hash"));

		} catch (JSONException e) {
			System.out.println(object.toString());
		}
		return review;
	}
	
	public static Integer[] getHelpful(String str){
		Integer[] result = new Integer[2];
		
		if(str.length() < 5) {
			result[0] = 0;
			result[1] = 0;
		}else{
			result[0] = Character.getNumericValue(str.charAt(1));
			result[1] = Character.getNumericValue(str.charAt(4));
		}
		return result;
	}
}
