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
		return valid ? data : null;
	}

	@Override
	public void save(Review data) {
		if (data == null)
			return;
		Document document = new Document().append("reviewerID", data.getReviewerID()).append("asin", data.getAsin())
				.append("reviewerName", data.getReviewerName()).append("helpful", data.getHelpful())
				.append("reviewText", data.getReviewText()).append("overall", data.getOverall())
				.append("summary", data.getSummary()).append("unixReviewTime", data.getUnixReviewTime())
				.append("reviewTime", data.getReviewTime());

		collection.insertOne(document);
	}

}
