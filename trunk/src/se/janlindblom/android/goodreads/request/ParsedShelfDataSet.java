package se.janlindblom.android.goodreads.request;

public class ParsedShelfDataSet {
	private boolean extractedAuthentication = false;
	private String extractedKey = null;
	private String extractedMethod = null;

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
}
