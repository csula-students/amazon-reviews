package edu.csula.datascience.acquisition;

import java.util.Arrays;
import java.util.List;

public class Review {

	private String reviewerID;
	private String asin;
	private String reviewerName;
	private Integer[] helpful;
	private float helpfulFeedbackCount;
	private float feedbackCount;
	private String reviewText;
	private float overall;
	private String summary;
	private Long unixReviewTime;
	private String reviewTime;
	private long datetime;
	
	private long reviewLength;
	private long summaryLength;

	private float numericRatio;
	private float capitalRatio;
	private long hash;
	private float fake;
	
	private float reviewerCount;
	private float duplicateCount;
	private float asinCount;
	private float overallMean;
	private float ratingDeviation;
	private float reviewerAverageRating;

	public Review() {
	}


	public Review(String reviewerID, String asin, String reviewerName, Integer[] helpful, 
			float helpfulFeedbackCount, float feedbackCount, String reviewText,
			float overall, String summary, Long unixReviewTime, String reviewTime, long datetime,
			long reviewLength, long summaryLength, float numericRatio, float capitalRatio, long hash, float fake,
			float reviewerCount, float duplicateCount, float asinCount, float overallMean, float reviewerAverageRating,
			float ratingDeviation) {
		this.reviewerID = reviewerID;
		this.asin = asin;
		this.reviewerName = reviewerName;
		this.helpful = helpful;
		this.helpfulFeedbackCount = helpfulFeedbackCount;
		this.feedbackCount = feedbackCount;
		this.reviewText = reviewText;
		this.overall = overall;
		this.summary = summary;
		this.unixReviewTime = unixReviewTime;
		this.reviewTime = reviewTime;
		this.datetime = datetime;
		
		this.reviewLength = reviewLength;
		this.summaryLength = summaryLength;
		this.numericRatio = numericRatio;
		this.capitalRatio = capitalRatio;
		this.hash= hash;
		this.fake = fake;
		
		this.reviewerCount = reviewerCount;
		this.duplicateCount = duplicateCount;
		this.asinCount = asinCount;
		this.overallMean = overallMean;
		this.reviewerAverageRating = reviewerAverageRating;
		this.ratingDeviation = ratingDeviation;
	}

	@Override
	public String toString() {
		return "Reviewer Id: " + reviewerID + " Text: " + reviewText + " Time: " + reviewTime;
	}

	public String getReviewerID() {
		return reviewerID;
	}

	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public List<Integer> getHelpful() {
		return Arrays.asList(helpful);
	}

	public void setHelpful(Integer[] helpful) {
		this.helpful = helpful;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public float getOverall() {
		return overall;
	}

	public void setOverall(float overall) {
		this.overall = overall;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getUnixReviewTime() {
		return unixReviewTime;
	}

	public void setUnixReviewTime(Long unixReviewTime) {
		this.unixReviewTime = unixReviewTime;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public long getDatetime() {
		return datetime;
	}

	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}
	
	public float getHelpfulFeedbackCount() {
		return helpfulFeedbackCount;
	}

	public void setHelpfulFeedbackCount(float helpfulFeedbackCount) {
		this.helpfulFeedbackCount = helpfulFeedbackCount;
	}

	public float getFeedbackCount() {
		return feedbackCount;
	}

	public void setFeedbackCount(float feedbackCount) {
		this.feedbackCount = feedbackCount;
	}

	public long getReviewLength() {
		return reviewLength;
	}

	public void setReviewLength(long reviewLength) {
		this.reviewLength = reviewLength;
	}

	public long getSummaryLength() {
		return summaryLength;
	}

	public void setSummaryLength(long summaryLength) {
		this.summaryLength = summaryLength;
	}

	public float getNumericRatio() {
		return numericRatio;
	}

	public void setNumericRatio(float numericRatio) {
		this.numericRatio = numericRatio;
	}

	public float getCapitalRatio() {
		return capitalRatio;
	}

	public void setCapitalRatio(long capitalRatio) {
		this.capitalRatio = capitalRatio;
	}

	public long getHash() {
		return hash;
	}

	public void setHash(long hash) {
		this.hash = hash;
	}

	public float getFake() {
		return fake;
	}

	public void setFake(float fake) {
		this.fake = fake;
	}

	public float getReviewerCount() {
		return reviewerCount;
	}

	public void setReviewerCount(float reviewerCount) {
		this.reviewerCount = reviewerCount;
	}

	public float getDuplicateCount() {
		return duplicateCount;
	}

	public void setDuplicateCount(float duplicateCount) {
		this.duplicateCount = duplicateCount;
	}

	public float getAsinCount() {
		return asinCount;
	}

	public void setAsinCount(float asinCount) {
		this.asinCount = asinCount;
	}

	public float getOverallMean() {
		return overallMean;
	}

	public void setOverallMean(float overallMean) {
		this.overallMean = overallMean;
	}

	public float getRatingDeviation() {
		return ratingDeviation;
	}

	public void setRatingDeviation(float ratingDeviation) {
		this.ratingDeviation = ratingDeviation;
	}

	public float getReviewerAverageRating() {
		return reviewerAverageRating;
	}

	public void setReviewerAverageRating(float reviewerAverageRating) {
		this.reviewerAverageRating = reviewerAverageRating;
	}
}
