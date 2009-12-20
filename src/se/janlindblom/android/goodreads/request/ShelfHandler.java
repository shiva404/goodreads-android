package se.janlindblom.android.goodreads.request;

/**
 * $Id$
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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

import se.janlindblom.android.goodreads.meta.Author;
import se.janlindblom.android.goodreads.meta.Book;
import se.janlindblom.android.goodreads.meta.Shelf;

/**
 * 
 * @author Jan Lindblom (lindblom.jan@gmail.com)
 * @version $Rev$
 */
public class ShelfHandler extends DefaultHandler2 {
	private boolean inGoodreadsResponse = false;
    private boolean inRequest = false;
    /* Request fields */
    private boolean inAuthentication = false;
    private boolean inKey = false;
    private boolean inKeyCDATA = false;
    private boolean inMethod = false;
    private boolean inMethodCDATA = false;
    /* Books */
    private boolean inBooks = false;
    private int booksStart = 0;
    private int booksEnd = 0;
    private int booksPages = 0;
    private int booksCurrentPage = 0;
    private int booksTotal = 0;
    /* Book */
    private boolean inBook = false;
    private boolean inBookId = false;
    private boolean inIsbn = false;
    private boolean isbnIsNull = false;
    private boolean inIsbn13 = false;
    private boolean isbn13IsNull = false;
    private boolean inBookRatingsCount = false;
    private boolean inBookTextReviewsCount = false;
    private boolean inTitle = false;
    private boolean inAverageRating = false;
    private boolean inLink = false;
    /* Author */
    private boolean inAuthor = false;
    private boolean inAuthorId = false;
    private boolean inAuthorName = false;
    private boolean inAuthorRatingsCount = false;
    private boolean inAuthorTextReviewsCount = false;
    
    private String currentString;
    
    private ParsedShelfDataSet psds = new ParsedShelfDataSet();
    
    private Shelf shelf;
    private Book currentBook;
    private Author currentAuthor;
    
    public ParsedShelfDataSet getParsedData() { 
		return this.psds;
	}
    
    public void startDocument() throws SAXException {
    	this.psds = new ParsedShelfDataSet();
    }
    
    public void endDocument() throws SAXException {}
    
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
   		} else if (!this.inRequest && localName.equals("books")) {
   			this.inBooks = true;
   			this.setBooksStart(Integer.parseInt(atts.getValue("start")));
   			this.setBooksEnd(Integer.parseInt(atts.getValue("end")));
   			this.setBooksCurrentPage(Integer.parseInt(atts.getValue("currentpage")));
   			this.setBooksTotal(Integer.parseInt(atts.getValue("total")));
   			this.setBooksPages(Integer.parseInt(atts.getValue("numpages")));
   			shelf = new Shelf();
   		} else if (this.inBooks && localName.equals("book")) {
   			this.inBook = true;
   			currentBook = new Book();
   		} else if (this.inBook && localName.equals("id")) {
   			this.inBookId = true;
   		}  else if (this.inBook && localName.equals("isbn")) {
   			this.inIsbn = true;
   			if (atts.getValue("nil") != null && atts.getValue("nil").equals("true"))
   				this.isbnIsNull = true;
   			else
   				this.isbnIsNull = false;
        }  else if (this.inBook && localName.equals("isbn13")) {
        	this.inIsbn13 = true;
        	if (atts.getValue("nil") != null && atts.getValue("nil").equals("true"))
   				this.isbn13IsNull = true;
        	else
        		this.isbn13IsNull = false;
        } else if (this.inBook && localName.equals("ratings_count")) {
        	this.inBookRatingsCount = true;
        } else if (this.inBook && localName.equals("text_reviews_count")) {
        	this.inBookTextReviewsCount = true;
        } else if (this.inBook && localName.equals("title")) {
        	this.inTitle = true;
        } else if (this.inBook && localName.equals("average_rating")) {
        	this.inAverageRating = true;
        } else if (this.inBook && localName.equals("link")) {
        	this.inLink = true;
        } else if (this.inBook && localName.equals("author")) {
        	this.inAuthor = true;
        	currentAuthor = new Author();
        } else if (this.inBook && this.inAuthor && localName.equals("id")) {
        	this.inAuthorId = true;
        } else if (this.inBook && this.inAuthor && localName.equals("name")) {
        	this.inAuthorName = true;
        }
    }
    
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
       } else if (!this.inRequest && localName.equals("books")) {
    	   this.inBooks = false;
       } else if (this.inBooks && localName.equals("book")) {
    	   this.inBook = false;
    	   shelf.addBook(currentBook);
       } else if (this.inBook && localName.equals("id")) {
    	   this.inBookId = false;
       } else if (this.inBook && localName.equals("isbn")) {
    	   this.inIsbn = false;
       }  else if (this.inBook && localName.equals("isbn13")) {
    	   this.inIsbn13 = false;
       } else if (this.inBook && localName.equals("ratings_count")) {
    	   this.inBookRatingsCount = false;
       } else if (this.inBook && localName.equals("text_reviews_count")) {
    	   this.inBookTextReviewsCount = false;
       } else if (this.inBook && localName.equals("title")) {
    	   this.inTitle = false;
       } else if (this.inBook && localName.equals("average_rating")) {
    	   this.inAverageRating = false;
       } else if (this.inBook && localName.equals("link")) {
    	   this.inLink = false;
       } else if (this.inBook && localName.equals("author")) {
    	   this.inAuthor = false;
    	   currentBook.setAuthor(currentAuthor);
       } else if (this.inBook && this.inAuthor && localName.equals("id")) {
    	   this.inAuthorId = false;
       } else if (this.inBook && this.inAuthor && localName.equals("name")) {
    	   this.inAuthorName = false;
       }
    }
    
    public void characters(char ch[], int start, int length) {
    	if (this.inAuthentication) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		psds.setExtractedAuthentication(currentString);
    	} else if (this.inKey) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		psds.setExtractedKey(currentString);
    	} else if (this.inMethod) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		psds.setExtractedMethod(currentString);
    	} else if (this.inBookId) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentBook.setId(Integer.parseInt(currentString));
    	} else if (this.inIsbn) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		if (!this.isbnIsNull)
    			currentBook.setIsbn(Integer.parseInt(currentString));
    	} else if (this.inIsbn13) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		if (!this.isbn13IsNull)
    			currentBook.setId(Integer.parseInt(currentString));
    	} else if (this.inBookRatingsCount) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentBook.setRatingsCount(Integer.parseInt(currentString));
    	} else if (this.inBookTextReviewsCount) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentBook.setTextReviewsCount(Integer.parseInt(currentString));
    	} else if (this.inTitle) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentBook.setTitle(currentString);
    	} else if (this.inAverageRating) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentBook.setAverageRating(Double.parseDouble(currentString));
    	} else if (this.inLink) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentBook.setLink(currentString);
    	} else if (this.inAuthorId) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentAuthor.setId(Integer.parseInt(currentString));
    	} else if (this.inAuthorName) {
    		currentString = currentString.concat(new String(ch, start, length));
    		currentString = currentString.trim();
    		currentAuthor.setName(currentString);
    	}
    }

	/**
	 * @return the inGoodreadsResponse
	 */
	public boolean isInGoodreadsResponse() {
		return inGoodreadsResponse;
	}

	/**
	 * @param inGoodreadsResponse the inGoodreadsResponse to set
	 */
	public void setInGoodreadsResponse(boolean inGoodreadsResponse) {
		this.inGoodreadsResponse = inGoodreadsResponse;
	}

	/**
	 * @return the inRequest
	 */
	public boolean isInRequest() {
		return inRequest;
	}

	/**
	 * @param inRequest the inRequest to set
	 */
	public void setInRequest(boolean inRequest) {
		this.inRequest = inRequest;
	}

	/**
	 * @return the inAuthentication
	 */
	public boolean isInAuthentication() {
		return inAuthentication;
	}

	/**
	 * @param inAuthentication the inAuthentication to set
	 */
	public void setInAuthentication(boolean inAuthentication) {
		this.inAuthentication = inAuthentication;
	}

	/**
	 * @return the inKey
	 */
	public boolean isInKey() {
		return inKey;
	}

	/**
	 * @param inKey the inKey to set
	 */
	public void setInKey(boolean inKey) {
		this.inKey = inKey;
	}

	/**
	 * @return the inKeyCDATA
	 */
	public boolean isInKeyCDATA() {
		return inKeyCDATA;
	}

	/**
	 * @param inKeyCDATA the inKeyCDATA to set
	 */
	public void setInKeyCDATA(boolean inKeyCDATA) {
		this.inKeyCDATA = inKeyCDATA;
	}

	/**
	 * @return the inMethod
	 */
	public boolean isInMethod() {
		return inMethod;
	}

	/**
	 * @param inMethod the inMethod to set
	 */
	public void setInMethod(boolean inMethod) {
		this.inMethod = inMethod;
	}

	/**
	 * @return the inMethodCDATA
	 */
	public boolean isInMethodCDATA() {
		return inMethodCDATA;
	}

	/**
	 * @param inMethodCDATA the inMethodCDATA to set
	 */
	public void setInMethodCDATA(boolean inMethodCDATA) {
		this.inMethodCDATA = inMethodCDATA;
	}

	/**
	 * @return the inBooks
	 */
	public boolean isInBooks() {
		return inBooks;
	}

	/**
	 * @param inBooks the inBooks to set
	 */
	public void setInBooks(boolean inBooks) {
		this.inBooks = inBooks;
	}

	/**
	 * @return the inBook
	 */
	public boolean isInBook() {
		return inBook;
	}

	/**
	 * @param inBook the inBook to set
	 */
	public void setInBook(boolean inBook) {
		this.inBook = inBook;
	}

	/**
	 * @return the inBookId
	 */
	public boolean isInBookId() {
		return inBookId;
	}

	/**
	 * @param inBookId the inBookId to set
	 */
	public void setInBookId(boolean inBookId) {
		this.inBookId = inBookId;
	}

	/**
	 * @return the inIsbn
	 */
	public boolean isInIsbn() {
		return inIsbn;
	}

	/**
	 * @param inIsbn the inIsbn to set
	 */
	public void setInIsbn(boolean inIsbn) {
		this.inIsbn = inIsbn;
	}

	/**
	 * @return the inIsbn13
	 */
	public boolean isInIsbn13() {
		return inIsbn13;
	}

	/**
	 * @param inIsbn13 the inIsbn13 to set
	 */
	public void setInIsbn13(boolean inIsbn13) {
		this.inIsbn13 = inIsbn13;
	}

	/**
	 * @return the inBookRatingsCount
	 */
	public boolean isInBookRatingsCount() {
		return inBookRatingsCount;
	}

	/**
	 * @param inBookRatingsCount the inBookRatingsCount to set
	 */
	public void setInBookRatingsCount(boolean inBookRatingsCount) {
		this.inBookRatingsCount = inBookRatingsCount;
	}

	/**
	 * @return the inBookTextReviewsCount
	 */
	public boolean isInBookTextReviewsCount() {
		return inBookTextReviewsCount;
	}

	/**
	 * @param inBookTextReviewsCount the inBookTextReviewsCount to set
	 */
	public void setInBookTextReviewsCount(boolean inBookTextReviewsCount) {
		this.inBookTextReviewsCount = inBookTextReviewsCount;
	}

	/**
	 * @return the inTitle
	 */
	public boolean isInTitle() {
		return inTitle;
	}

	/**
	 * @param inTitle the inTitle to set
	 */
	public void setInTitle(boolean inTitle) {
		this.inTitle = inTitle;
	}

	/**
	 * @return the inAverageRating
	 */
	public boolean isInAverageRating() {
		return inAverageRating;
	}

	/**
	 * @param inAverageRating the inAverageRating to set
	 */
	public void setInAverageRating(boolean inAverageRating) {
		this.inAverageRating = inAverageRating;
	}

	/**
	 * @return the inLink
	 */
	public boolean isInLink() {
		return inLink;
	}

	/**
	 * @param inLink the inLink to set
	 */
	public void setInLink(boolean inLink) {
		this.inLink = inLink;
	}

	/**
	 * @return the inAuthor
	 */
	public boolean isInAuthor() {
		return inAuthor;
	}

	/**
	 * @param inAuthor the inAuthor to set
	 */
	public void setInAuthor(boolean inAuthor) {
		this.inAuthor = inAuthor;
	}

	/**
	 * @return the inAuthorId
	 */
	public boolean isInAuthorId() {
		return inAuthorId;
	}

	/**
	 * @param inAuthorId the inAuthorId to set
	 */
	public void setInAuthorId(boolean inAuthorId) {
		this.inAuthorId = inAuthorId;
	}

	/**
	 * @return the inAuthorName
	 */
	public boolean isInAuthorName() {
		return inAuthorName;
	}

	/**
	 * @param inAuthorName the inAuthorName to set
	 */
	public void setInAuthorName(boolean inAuthorName) {
		this.inAuthorName = inAuthorName;
	}

	/**
	 * @return the inAuthorRatingsCount
	 */
	public boolean isInAuthorRatingsCount() {
		return inAuthorRatingsCount;
	}

	/**
	 * @param inAuthorRatingsCount the inAuthorRatingsCount to set
	 */
	public void setInAuthorRatingsCount(boolean inAuthorRatingsCount) {
		this.inAuthorRatingsCount = inAuthorRatingsCount;
	}

	/**
	 * @return the inAuthorTextReviewsCount
	 */
	public boolean isInAuthorTextReviewsCount() {
		return inAuthorTextReviewsCount;
	}

	/**
	 * @param inAuthorTextReviewsCount the inAuthorTextReviewsCount to set
	 */
	public void setInAuthorTextReviewsCount(boolean inAuthorTextReviewsCount) {
		this.inAuthorTextReviewsCount = inAuthorTextReviewsCount;
	}

	/**
	 * @param booksStart the booksStart to set
	 */
	public void setBooksStart(int booksStart) {
		this.booksStart = booksStart;
	}

	/**
	 * @return the booksStart
	 */
	public int getBooksStart() {
		return booksStart;
	}

	/**
	 * @param booksEnd the booksEnd to set
	 */
	public void setBooksEnd(int booksEnd) {
		this.booksEnd = booksEnd;
	}

	/**
	 * @return the booksEnd
	 */
	public int getBooksEnd() {
		return booksEnd;
	}

	/**
	 * @param booksPages the booksPages to set
	 */
	public void setBooksPages(int booksPages) {
		this.booksPages = booksPages;
	}

	/**
	 * @return the booksPages
	 */
	public int getBooksPages() {
		return booksPages;
	}

	/**
	 * @param booksCurrentPage the booksCurrentPage to set
	 */
	public void setBooksCurrentPage(int booksCurrentPage) {
		this.booksCurrentPage = booksCurrentPage;
	}

	/**
	 * @return the booksCurrentPage
	 */
	public int getBooksCurrentPage() {
		return booksCurrentPage;
	}

	/**
	 * @param booksTotal the booksTotal to set
	 */
	public void setBooksTotal(int booksTotal) {
		this.booksTotal = booksTotal;
	}

	/**
	 * @return the booksTotal
	 */
	public int getBooksTotal() {
		return booksTotal;
	}
}
