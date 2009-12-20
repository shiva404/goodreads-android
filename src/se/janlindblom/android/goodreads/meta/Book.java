package se.janlindblom.android.goodreads.meta;

public class Book {
	private int id;
	private int isbn;
	private int isbn13;
	private int ratingsCount;
	private int textReviewsCount;
	private String title;
	private double averageRating;
	private String link;
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
	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	/**
	 * @return the isbn
	 */
	public int getIsbn() {
		return isbn;
	}
	/**
	 * @param isbn13 the isbn13 to set
	 */
	public void setIsbn13(int isbn13) {
		this.isbn13 = isbn13;
	}
	/**
	 * @return the isbn13
	 */
	public int getIsbn13() {
		return isbn13;
	}
	/**
	 * @param ratingsCount the ratingsCount to set
	 */
	public void setRatingsCount(int ratingsCount) {
		this.ratingsCount = ratingsCount;
	}
	/**
	 * @return the ratingsCount
	 */
	public int getRatingsCount() {
		return ratingsCount;
	}
	/**
	 * @param textReviewsCount the textReviewsCount to set
	 */
	public void setTextReviewsCount(int textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}
	/**
	 * @return the textReviewsCount
	 */
	public int getTextReviewsCount() {
		return textReviewsCount;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param averageRating the averageRating to set
	 */
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	/**
	 * @return the averageRating
	 */
	public double getAverageRating() {
		return averageRating;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
}
