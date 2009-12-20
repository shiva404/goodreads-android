package se.janlindblom.android.goodreads;

import java.util.Vector;

public class Review {

	private int id;
	private int rating;
	private int votes;
	private boolean sell_flag;
	private boolean spoiler_flag;
	private Vector<Shelf> shelves;
	private String recommended_for;
	private String recommended_by;
	private String read_at;
	private String date_added;
	private String date_updated;
	private int read_count;
	private String body;
	private String url;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public boolean isSell_flag() {
		return sell_flag;
	}
	public void setSell_flag(boolean sellFlag) {
		sell_flag = sellFlag;
	}
	public boolean isSpoiler_flag() {
		return spoiler_flag;
	}
	public void setSpoiler_flag(boolean spoilerFlag) {
		spoiler_flag = spoilerFlag;
	}
	public Vector<Shelf> getShelves() {
		return shelves;
	}
	public void setShelves(Vector<Shelf> shelves) {
		this.shelves = shelves;
	}
	public String getRecommended_for() {
		return recommended_for;
	}
	public void setRecommended_for(String recommendedFor) {
		recommended_for = recommendedFor;
	}
	public String getRecommended_by() {
		return recommended_by;
	}
	public void setRecommended_by(String recommendedBy) {
		recommended_by = recommendedBy;
	}
	public String getRead_at() {
		return read_at;
	}
	public void setRead_at(String readAt) {
		read_at = readAt;
	}
	public String getDate_added() {
		return date_added;
	}
	public void setDate_added(String dateAdded) {
		date_added = dateAdded;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String dateUpdated) {
		date_updated = dateUpdated;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int readCount) {
		read_count = readCount;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
