package edu.csula.datascience.elasticsearch;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import edu.csula.datascience.acquisition.Review;

import org.bson.Document;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/* gradle command to run this app `gradle esApp` */
public class ElasticSearchApp {

	static MongoClient mongoClient;
	static MongoDatabase database;
	static MongoCollection<Document> collection;
	static final int batchSize = 10000;

	private final static String indexName = "amazon-data-new";
	private final static String typeName = "reviews";

	public static void main(String[] args) throws URISyntaxException, IOException {
		Node node = nodeBuilder().settings(
				Settings.builder().put("cluster.name", "mahdiye-jamali-1368").put("path.home", "elasticsearch-data"))
				.node();
		Client client = node.client();

		mongoClient = new MongoClient();
		database = mongoClient.getDatabase("amazon");
		collection = database.getCollection("reviews");

		BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
			@Override
			public void beforeBulk(long executionId, BulkRequest request) {
			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
				System.out.println("Facing error while importing data to elastic search");
				failure.printStackTrace();
			}
		}).setBulkActions(10000).setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
				.setFlushInterval(TimeValue.timeValueSeconds(5)).setConcurrentRequests(1)
				.setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)).build();

		// Gson library for sending json to elastic search
		Gson gson = new Gson();

		int i = 0;

		try (MongoCursor<Document> cursor = collection.find().iterator()) {
			while (cursor.hasNext() && i++ < 200 * batchSize) {
				String json = cursor.next().toJson();
				Review review = jsonToReview(json);

				/*
				 * json = gson.toJson(review); JSONObject jsonObj = new
				 * JSONObject(json); jsonObj.put("date", "5-12-2016");
				 */

				bulkProcessor.add(new IndexRequest(indexName, typeName).source(gson.toJson(review)));
			}
			cursor.close();
		}

		// simple search by field name "state" and find Washington
		// SearchResponse response = client.prepareSearch(indexName)
		// .setTypes(typeName)
		// .setSearchType(SearchType.DEFAULT)
		// .setQuery(QueryBuilders.matchQuery("state", "Washington")) // Query
		// .setScroll(new TimeValue(60000))
		// .setSize(60).setExplain(true)
		// .execute()
		// .actionGet();
		//
		// //Scroll until no hits are returned
		// while (true) {
		//
		// for (SearchHit hit : response.getHits().getHits()) {
		// System.out.println(hit.sourceAsString());
		// }
		// response = client
		// .prepareSearchScroll(response.getScrollId())
		// .setScroll(new TimeValue(60000))
		// .execute()
		// .actionGet();
		// //Break condition: No hits are returned
		// if (response.getHits().getHits().length == 0) {
		// break;
		// }
		// }
		//

		/*
		 * SearchResponse sr =
		 * node.client().prepareSearch(indexName).setTypes(typeName)
		 * .setQuery(QueryBuilders.matchAllQuery())
		 * .addAggregation(AggregationBuilders.terms("stateAgg").field("state").
		 * size(Integer.MAX_VALUE)).execute() .actionGet();
		 * 
		 * // Get your facet results Terms agg1 =
		 * sr.getAggregations().get("stateAgg");
		 * 
		 * for (Terms.Bucket bucket : agg1.getBuckets()) {
		 * System.out.println(bucket.getKey() + ": " + bucket.getDocCount()); }
		 */
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

	public static JSONObject replaceNullString(JSONObject jsonObject, String key, String value) {
		if (jsonObject.get(key) == null)
			jsonObject.put(key, value);
		return jsonObject;
	}

	public static JSONObject replaceNullLong(JSONObject jsonObject, String key, long value) {
		if (jsonObject.get(key) == null)
			jsonObject.put(key, value);
		return jsonObject;
	}
}
