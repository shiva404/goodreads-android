package se.janlindblom.android.goodreads;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;

public class GoodreadsImageGetter implements ImageGetter {

	@Override
	public Drawable getDrawable(String source) {
		InputStream is;
		try {
			is = (InputStream) this.fetch(source);
			Drawable d = Drawable.createFromStream(is, "src");						
			return d;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	private Object fetch(String address) throws MalformedURLException, IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}
}
