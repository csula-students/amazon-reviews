package edu.csula.datascience.acquisition;

import java.util.Arrays;
import java.util.List;

public class Review {

	private String reviewerID;
	private String asin;
	private String reviewerName;
	private Integer[] helpful;
	private String reviewText;
	private float overall;
	private String summary;
	private Long unixReviewTime;
	private String reviewTime;
	private long datetime;
	
	private long reviewLength;
	private long summaryLength;
	private long numericRatio;
	private long capitalRatio;
	private long hash;

	public Review() {
	}

	public Review(String reviewerID, String asin, String reviewerName, Integer[] helpful, String reviewText,
			float overall, String summary, Long unixReviewTime, String reviewTime, long datetime) {
		this.reviewerID = reviewerID;
		this.asin = asin;
		this.reviewerName = reviewerName;
		this.helpful = helpful;
		this.reviewText = reviewText;
		this.overall = overall;
		this.summary = summary;
		this.unixReviewTime = unixReviewTime;
		this.reviewTime = reviewTime;
		this.datetime = datetime;
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

	public long getNumericRatio() {
		return numericRatio;
	}

	public void setNumericRatio(long numericRatio) {
		this.numericRatio = numericRatio;
	}

	public long getCapitalRatio() {
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

}
