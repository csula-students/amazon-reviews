package edu.csula.datascience.acquisition;

import java.util.Collection;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ReviewCollector implements Collector<Review, Review> {
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;

	public ReviewCollector() {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase("amazon1");
		collection = database.getCollection("reviews");
	}

	@Override
	public Collection<Review> mungee(Collection<Review> src) {
		return null;
	}

	@Override
	public void save(Collection<Review> data) {
	}

	@Override
	public Review mungee(Review data) {
		boolean valid = data.getReviewerID() != null && data.getAsin() != null && data.getReviewText() != null;
		return (valid && isRecentReview(data)) ? data : null;
	}

	public boolean isRecentReview(Review data) {
		String rTime = data.getReviewTime();
		if(rTime.isEmpty()) return false;
		String[] splits = rTime.split(",");
		int year = Integer.parseInt(splits[splits.length - 1].replaceAll("\\s+", ""));
		return year > 2011;
	}
	
	@Override
	public void save(Review data) {
		if (data == null)
			return;
		String id = data.getReviewerID().hashCode() + "" + data.getAsin().hashCode();
		data.setDatetime(System.currentTimeMillis());
		Document document = new Document().append("_id", id).append("reviewerID", data.getReviewerID()).append("asin", data.getAsin())
				.append("reviewerName", data.getReviewerName()).append("helpful", data.getHelpful())
				.append("reviewText", data.getReviewText()).append("overall", data.getOverall())
				.append("summary", data.getSummary()).append("unixReviewTime", data.getUnixReviewTime())
				.append("reviewTime", data.getReviewTime())
				.append("datetime", data.getDatetime());
		try{
			collection.insertOne(document);
		}catch(Exception e){
		}
	}

}
