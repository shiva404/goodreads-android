package se.janlindblom.android.goodreads.request;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

import se.janlindblom.android.goodreads.BookShelf;
import se.janlindblom.android.goodreads.Update;

public class UserHandler extends DefaultHandler2 {
	
	private boolean inGoodreadsResponse = false;
    
    private boolean inRequest = false;
    /* Request fields */
    private boolean inAuthentication = false;
    private boolean inKey = false;
    private boolean inKeyCDATA = false;
    private boolean inMethod = false;
    private boolean inMethodCDATA = false;
    
    private boolean inUser = false;
    /* User fields */
    private boolean inName = false;
	private boolean inNameCDATA = false;
    private boolean inUserName = false;
    private boolean inUserNameCDATA = false;
    private boolean inLink = false;
    private boolean inUpdatesRssUrl = false;
    private boolean inReviewsRssUrl = false;
    private boolean inFriendsCount = false;
    private boolean inReviewsCount = false;
    private boolean inUserShelves = false;
    private boolean inUserShelf = false;
    /* User shelf fields */
    private boolean inBookCount = false;
    private boolean inDescription = false;
    private boolean inExclusiveFlag = false;
    private boolean inId = false;
    private boolean inShelfName = false;
    
    private boolean inUpdates = false;
    private boolean inUpdate = false;
    /* Update fields */
    private boolean inTitle = false;
    private boolean inUpdateLink = false;
    private boolean inUpdateDescription = false;
    
    private BookShelf currentShelf = null;
    private Update currentUpdate = null;

    private ParsedUserDataSet puds = new ParsedUserDataSet();

	private boolean inTitleCDATA = false;
	
	private String currentString;
    
	public ParsedUserDataSet getParsedData() { 
		return this.puds;
	}
    
    @Override 
    public void startDocument() throws SAXException {
    	this.puds = new ParsedUserDataSet();
    }
    
    @Override 
    public void endDocument() throws SAXException {}
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    	currentString = "";
    	if (localName.equals("GoodreadsResponse")) {
    		this.setInGoodreadsResponse(true);
    	} else if (this.inGoodreadsResponse && localName.equals("Request")) {
    		this.setInRequest(true);
   		} else if (this.inRequest && localName.equals("authentication")) {
   			this.inAuthentication = true;
   		} else if (this.inRequest && localName.equals("key")) {
   			this.inKey = true;
   		} else if (this.inRequest && localName.equals("method")) {
   			this.inMethod = true;
   		} else if (localName.equals("user")) {
   			int userid = Integer.parseInt(atts.getValue("id"));
   			puds.setExtractedUserId(userid);
   			this.setInUser(true);
   		} else if (this.inUser && localName.equals("name") && !this.inUserShelves) {
        	 this.inName = true;
         } else if (this.inUser && localName.equals("user-name")) {
        	 this.inUserName = true;
         } else if (this.inUser && localName.equals("friends-count")) {
        	 this.inFriendsCount = true;
         } else if (this.inUser && localName.equals("reviews-count")) {
        	 this.inReviewsCount = true;
         } else if (this.inUser && !this.inUpdate && localName.equals("link")) {
        	 this.inLink = true;
         } else if (this.inUser && localName.equals("user_shelves")) {
        	 this.inUserShelves = true;
         } else if (this.inUser && this.inUserShelves && localName.equals("user_shelf")) {
        	 this.inUserShelf = true;
        	 currentShelf = new BookShelf();
         } else if (this.inUser && this.inUserShelves && this.inUserShelf && localName.equals("book_count")) {
        	 this.inBookCount = true;
         } else if (this.inUser && this.inUserShelves && this.inUserShelf && localName.equals("description")) {
        	 this.inDescription = true;
         } else if (this.inUser && this.inUserShelves && this.inUserShelf && localName.equals("exclusive_flag")) {
        	 this.inExclusiveFlag = true;
         } else if (this.inUser && this.inUserShelves && this.inUserShelf && localName.equals("id")) {
        	 this.inId = true;
         } else if (this.inUser && this.inUserShelves && this.inUserShelf && localName.equals("name")) {
        	 this.inShelfName = true;
         } else if (this.inUser && localName.equals("updates")) {
        	 this.inUpdates = true;
         } else if (this.inUser && this.inUpdates && localName.equals("update")) {
        	 this.inUpdate = true;
        	 currentUpdate = new Update();
         } else if (this.inUser && this.inUpdates && this.inUpdate && localName.equals("title")) {
        	 this.inTitle = true;
         } else if (this.inUser && this.inUpdates && this.inUpdate && localName.equals("link")) {
        	 this.inUpdateLink = true;
         } else if (this.inUser && this.inUpdates && this.inUpdate && localName.equals("description")) {
        	 this.inUpdateDescription = true;
         } 
    }
    
    @Override
    public void startCDATA() {
    	if (this.inKey) {
    		this.inKeyCDATA = true;
    	} else if (this.inMethod) {
    		this.inMethodCDATA = true;
    	} else if (this.inName) {
    		this.inNameCDATA = true;
    	} else if (this.inUserName) {
    		this.inUserNameCDATA = true;
    	} else if (this.inTitle) {
    		this.inTitleCDATA = true;
    	}
    }
    
    @Override
    public void endCDATA() {
    	if (this.inKey) {
    		this.inKeyCDATA = false;
    	} else if (this.inMethod) {
    		this.inMethodCDATA = false;
    	} else if (this.inName) {
    		this.inNameCDATA = false;
    	} else if (this.inUserName) {
    		this.inUserNameCDATA = false;
    	} else if (this.inTitle) {
    		this.inTitleCDATA = false;
    	}
    }
    
    @Override 
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    	if (localName.equals("GoodreadsResponse")) { 
            this.setInGoodreadsResponse(false); 
       } else if (this.inGoodreadsResponse && localName.equals("Request")) {
           this.setInRequest(false);
       } else if (this.inRequest && localName.equals("authentication")) {
    	   this.inAuthentication = false;
       } else if (this.inRequest && localName.equals("key")) {
    	   this.inKey = false;
       } else if (this.inRequest && localName.equals("method")) {
    	   this.inMethod = false;
       } else if (localName.equals("user")) {
    	   this.setInUser(false);
       } else if (this.inUser && localName.equals("name") && !this.inUserShelves) {
    	   this.inName = false;
       } else if (this.inUser && localName.equals("user-name")) {
    	   this.inUserName = false;
       } else if (this.inUser && localName.equals("friends-count")) {
    	   this.inFriendsCount = false;
       } else if (this.inUser && localName.equals("reviews-count")) {
    	   this.inReviewsCount = false;
       } else if (this.inUser && !this.inUpdate && localName.equals("link")) {
      	 this.inLink = false;
       } else if (this.inUser && localName.equals("user_shelves")) {
    	   this.inUserShelves = false;
       } else if (this.inUser && this.inUserShelves && localName.equals("user_shelf")) {
    	   puds.addShelf(currentShelf);
    	   this.inUserShelf = false;
       } else if (this.inUser && this.inUserShelves && this.inUserShelf && localName.equals("book_count")) {
    	   this.inBookCount = false;
       } else if (this.inUser && this.inUserShelves && this.inUserShelf && localName.equals("description")) {
    	   this.inDescription = false;
       } else if (this.inUser && this.inUserShelves && this.inUserShelf && localName.equals("exclusive_flag")) {
    	   this.inExclusiveFlag = false;
       } else if (this.inUser && this.inUserShelves && this.inUserShelf && localName.equals("id")) {
    	   this.inId = false;
       } else if (this.inUser && this.inUserShelves && this.inUserShelf && localName.equals("name")) {
    	   this.inShelfName = false;
       } else if (this.inUser && localName.equals("updates")) {
      	 this.inUpdates = false;
       } else if (this.inUser && this.inUpdates && localName.equals("update")) {
    	   if (!currentUpdate.getTitle().equals("New Update::UpdateArray update"))
    		   puds.addUpdate(currentUpdate);
    	   this.inUpdate = false;
       } else if (this.inUser && this.inUpdates && this.inUpdate && localName.equals("title")) {
    	   this.inTitle = false;
       } else if (this.inUser && this.inUpdates && this.inUpdate && localName.equals("link")) {
    	   this.inUpdateLink = false;
       } else if (this.inUser && this.inUpdates && this.inUpdate && localName.equals("description")) {
    	   this.inUpdateDescription = false;
       }
    }
    
    @Override 
    public void characters(char ch[], int start, int length) {
    	if (this.inAuthentication) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		puds.setExtractedAuthentication(currentString);
    	} else if (this.inKey) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		puds.setExtractedKey(currentString);
    	} else if (this.inMethod) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		puds.setExtractedMethod(currentString);
    	} else if (this.inName) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		puds.setExtractedName(currentString);
    	} else if (this.inUserName) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		puds.setExtractedUserName(currentString);
    	} else if (this.inLink) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		puds.setExtractedLink(currentString);
    	} else if (this.inFriendsCount) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		puds.setExtractedFriendsCount(Integer.parseInt(currentString));
    	} else if (this.inReviewsCount) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		puds.setExtractedReviews(Integer.parseInt(currentString));
    	} else if (this.inBookCount) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentShelf.setCount(Integer.parseInt(currentString));
    	} else if (this.inDescription) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentShelf.setDescription(new String(ch, start, length));
    	} else if (this.inExclusiveFlag) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		boolean exclusive = false;
    		if (currentString.equals("true"))
    			exclusive = true;
    		currentShelf.setExclusive(exclusive);
    	} else if (this.inId) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentShelf.setId(Integer.parseInt(currentString));
    	} else if (this.inShelfName) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentShelf.setName(currentString);
    	} else if (this.inUpdateDescription) {
    		String newString = new String(ch, start, length);
    		newString = newString.trim();
    		currentString = currentString.concat(newString + " ");
//    		currentString = currentString.trim();
    		currentUpdate.setDescription(currentString);
    	} else if (this.inTitle) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentUpdate.setTitle(currentString);
    	} else if (this.inUpdateLink) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentUpdate.setLink(currentString);
    	}
    }

	public void setInGoodreadsResponse(boolean inGoodreadsResponse) {
		this.inGoodreadsResponse = inGoodreadsResponse;
	}

	public boolean isInGoodreadsResponse() {
		return inGoodreadsResponse;
	}

	public void setInRequest(boolean inRequest) {
		this.inRequest = inRequest;
	}

	public boolean isInRequest() {
		return inRequest;
	}

	public void setInUser(boolean inUser) {
		this.inUser = inUser;
	}

	public boolean isInUser() {
		return inUser;
	}
}
