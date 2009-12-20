package se.janlindblom.android.goodreads;

import android.text.Html;
import android.text.Spanned;

public class Update {
	private String title;
	private String description;
	private String link;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public String toString() {
		if ((this.description != null) && (this.link != null))
			return "<a href=\"" + this.link + "\">" + this.title + "</a><br>" + this.description;
		else
			return this.title;
	}
	
	public Spanned toSpanned() {
		if ((this.description != null) && (this.link != null)) {
			return Html.fromHtml("<b><a href=\"" + this.link + "\">" + this.title + "</a></b><br>" + this.description);
		} else {
			return Html.fromHtml("<b>" + this.title + "</b>");
		}
	}
}
