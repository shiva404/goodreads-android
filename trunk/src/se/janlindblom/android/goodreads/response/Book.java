package se.janlindblom.android.goodreads.response;

import android.graphics.drawable.Drawable;
import se.janlindblom.android.goodreads.Author;
import se.janlindblom.android.goodreads.Review;

public class Book {
	private String title;
	private String isbn;
	private String link;
	private String isbn13;
	private int ratings_count;
	private int text_reviews_count;
	private Author author;
	private double average_rating;
	private String image_url;
	private Drawable cover;
	private Review myReview;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
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
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public double getAverage_rating() {
		return average_rating;
	}
	public void setAverage_rating(double averageRating) {
		average_rating = averageRating;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String imageUrl) {
		image_url = imageUrl;
	}
	public Drawable getCover() {
		return cover;
	}
	public void setCover(Drawable cover) {
		this.cover = cover;
	}
	public Review getMyReview() {
		return myReview;
	}
	public void setMyReview(Review myReview) {
		this.myReview = myReview;
	}
}
