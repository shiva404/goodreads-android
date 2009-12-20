package se.janlindblom.android.goodreads.request;

/**
 * $Id:$
 * 
 * Copyright (c) 2009, Jan Lindblom
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the project nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

import java.util.Vector;

import se.janlindblom.android.goodreads.Update;
import se.janlindblom.android.goodreads.meta.Shelf;

/**
 * 
 * @author Jan Lindblom (lindblom.jan@gmail.com)
 * @version $Rev:$
 */
public class ParsedUserDataSet {
	private boolean extractedAuthentication = false;
	private String extractedKey = null;
	private String extractedMethod = null;
	private int extractedUserId = 0;
	private String extractedName = null;
	private String extractedUserName = null;
	private int extractedFriendsCount = 0;
	private int extractedReviews = 0;
	private Vector<Shelf> bookShelves = new Vector<Shelf>();
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

	public void addShelf(Shelf currentShelf) {
		bookShelves.add(currentShelf);
	}

	public Vector<Shelf> getBookShelves() {
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
