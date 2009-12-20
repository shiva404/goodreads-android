package se.janlindblom.android.goodreads.meta.operation;

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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import se.janlindblom.android.goodreads.meta.Operation;
import se.janlindblom.android.goodreads.meta.Response;
import se.janlindblom.android.goodreads.meta.response.UserResponse;
import se.janlindblom.android.goodreads.request.ParsedUserDataSet;
import se.janlindblom.android.goodreads.request.UserHandler;

public class UserOperation implements Operation {
	private String showURLBase;
	private String friendsURLBase;
	private URL showURL;
	private String key = null;
	private int id;
	
	public UserOperation() {
		this.setShowURLBase("http://www.goodreads.com/user/show/");
		this.setFriendsURLBase("http://www.goodreads.com/api/friends/");
	}
	
	public UserOperation(String key) {
		this();
		this.setKey(key);
	}
	
	public UserOperation(String key, int userid) {
		this(key);
		this.setId(userid);
	}
	
	public void friends(int id) {
	}

	public void setShowURLBase(String showURLBase) {
		this.showURLBase = showURLBase;
	}

	public String getShowURLBase() {
		return showURLBase;
	}

	public void setFriendsURLBase(String friendsURLBase) {
		this.friendsURLBase = friendsURLBase;
	}

	public String getFriendsURLBase() {
		return friendsURLBase;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setShowURL(URL showURL) {
		this.showURL = showURL;
	}

	public URL getShowURL() {
		return showURL;
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

	@Override
	public Response execute() {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;
		XMLReader xr;
		UserHandler uh;
		try {
			setShowURL(new URL(getShowURLBase() + id + ".xml?key=" + key));
			sp = spf.newSAXParser();
			xr = sp.getXMLReader();
			uh = new UserHandler();
			xr.setContentHandler(uh);
			xr.parse(new InputSource(getShowURL().openStream()));
			ParsedUserDataSet puds = uh.getParsedData();
			UserResponse retval = new UserResponse(puds);
			return retval;
		} catch (MalformedURLException e) {
			return null;
		} catch (ParserConfigurationException e) {
			return null;
		} catch (SAXException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public int getType() {
		return Operation.OPERATION_USER;
	}
}
