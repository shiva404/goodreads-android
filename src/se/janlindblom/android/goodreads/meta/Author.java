package se.janlindblom.android.goodreads.meta;

public class Author {
	private int id;
	private String name;
	private int ratings_count;
	private int text_reviews_count;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRatings_count() {
		return ratings_count;
	}
	public void setRatings_count(int ratingsCount) {
		ratings_count = ratingsCount;
	}
	public int getText_reviews_count() {
		return text_reviews_count;
	}
	public void setText_reviews_count(int textReviewsCount) {
		text_reviews_count = textReviewsCount;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
}
