package se.janlindblom.android.goodreads.request;

import java.util.Vector;

import se.janlindblom.android.goodreads.BookShelf;
import se.janlindblom.android.goodreads.Update;

public class ParsedUserDataSet {
	private boolean extractedAuthentication = false;
	private String extractedKey = null;
	private String extractedMethod = null;
	private int extractedUserId = 0;
	private String extractedName = null;
	private String extractedUserName = null;
	private int extractedFriendsCount = 0;
	private int extractedReviews = 0;
	private Vector<BookShelf> bookShelves = new Vector<BookShelf>();
	private Vector<Update> updates = new Vector<Update>();
	private String extractedLink = null;

	public String toString() {
		return "Authentication: " + extractedAuthentication + ", Key: " + 
			extractedKey + ", Method: " + extractedMethod + ", UserId: " +
			extractedUserId + ", Name: " + extractedName + ", UserName: " + extractedUserName;
	}
	
	public void setExtractedAuthentication(String string) {
		if (string.equals("true")) {
			this.extractedAuthentication = true;
		} else {
			this.extractedAuthentication = false;
		}
	}
	
	public boolean getExtractedAuthentication() {
		return this.extractedAuthentication;
	}

	public void setExtractedKey(String extractedKey) {
		this.extractedKey = extractedKey;
	}

	public String getExtractedKey() {
		return extractedKey;
	}

	public void setExtractedMethod(String extractedMethod) {
		this.extractedMethod = extractedMethod;
	}

	public String getExtractedMethod() {
		return extractedMethod;
	}

	public void setExtractedUserId(int extractedUserId) {
		this.extractedUserId = extractedUserId;
	}

	public int getExtractedUserId() {
		return extractedUserId;
	}

	public void setExtractedName(String extractedName) {
		this.extractedName = extractedName;
	}

	public String getExtractedName() {
		return extractedName;
	}

	public void setExtractedUserName(String extractedUserName) {
		this.extractedUserName = extractedUserName;
	}

	public String getExtractedUserName() {
		return extractedUserName;
	}

	public void setExtractedFriendsCount(int extractedFriendsCount) {
		this.extractedFriendsCount = extractedFriendsCount;
	}

	public int getExtractedFriendsCount() {
		return extractedFriendsCount;
	}

	public void addShelf(BookShelf currentShelf) {
		bookShelves.add(currentShelf);
	}

	public Vector<BookShelf> getBookShelves() {
		return bookShelves;
	}

	public void setExtractedReviews(int extractedReviews) {
		this.extractedReviews = extractedReviews;
	}

	public int getExtractedReviews() {
		return extractedReviews;
	}

	public void addUpdate(Update update) {
		updates.add(update);
		System.err.println("ParsedUserDataSet.addUpdate(): " + update.toString());
	}
	
	public void setUpdates(Vector<Update> updates) {
		this.updates = updates;
	}

	public Vector<Update> getUpdates() {
		return updates;
	}

	public void setExtractedLink(String extractedLink) {
		this.extractedLink = extractedLink;
	}

	public String getExtractedLink() {
		return extractedLink;
	}
}
