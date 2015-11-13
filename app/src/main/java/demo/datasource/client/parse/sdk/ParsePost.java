package demo.datasource.client.parse.sdk;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created on 29.10.2015.
 */
@ParseClassName("Post")
public class ParsePost extends ParseObject {

	private static final String AUTHOR = "author";
	private static final String TITLE = "title";
	private static final String BODY = "bodyText";
	private static final String PICTURE_URL = "bodyPictureUrl";

	public void setAuthor(String authorId) {
		ParseUser author = new ParseUser();
		author.setObjectId(authorId);
		super.put(AUTHOR, author);
	}

	public void setTitle(String title) {
		super.put(TITLE, title);
	}

	public void setBody(String body) {
		super.put(BODY, body);
	}

	public void setPictureUrl(String pictureUrl) {
		super.put(PICTURE_URL, pictureUrl);
	}

	public String getAuthorId() {
		ParseUser author = getParseUser(AUTHOR);
		return author == null ? null : author.getObjectId();
	}

	public String getTitle() {
		return getString(TITLE);
	}

	public String getBody() {
		return getString(BODY);
	}

	public String getPictureUrl() {
		return getString(PICTURE_URL);
	}

	@Override
	final public void put(String key, Object value) {
		//avoid using this method
	}
}
