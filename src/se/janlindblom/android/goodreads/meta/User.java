package se.janlindblom.android.goodreads.meta;

/**
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
import se.janlindblom.android.goodreads.request.ParsedUserDataSet;

public class User {
	private int id;
	private String name;
	private String userName;
	private Vector<Shelf> bookShelves;
	private String link;
	private int friends;
	private int reviews;
	private Vector<Update> updates;
	
	public User() {}
	
	public User(ParsedUserDataSet p) {
		this();
		this.setId(p.getExtractedUserId());
		this.setName(p.getExtractedName());
		this.setUserName(p.getExtractedUserName());
		this.setBookShelves(p.getBookShelves());
		this.setFriends(p.getExtractedFriendsCount());
		this.setReviews(p.getExtractedReviews());
		this.setUpdates(p.getUpdates());
		this.setLink(p.getExtractedLink());
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Vector<Shelf> getBookShelves() {
		return bookShelves;
	}
	public void setBookShelves(Vector<Shelf> bookShelves) {
		this.bookShelves = bookShelves;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getFriends() {
		return friends;
	}
	public void setFriends(int friends) {
		this.friends = friends;
	}
	public int getReviews() {
		return reviews;
	}
	public void setReviews(int reviews) {
		this.reviews = reviews;
	}

	public void setUpdates(Vector<Update> updates) {
		this.updates = updates;
	}

	public Vector<Update> getUpdates() {
		return updates;
	}
}
