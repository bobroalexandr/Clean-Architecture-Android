package demo.domain;

import demo.util.Copyable;
import demo.util.TextUtils;

/**
 * Created on 25.10.2015.
 */
public class Post extends DomainObject implements Copyable {

	String authorId;

	String title;

	String body;

	String pictureUrl;

	public Post() {
		//nothing to do
	}

	public Post(String id) {
		super(id);
	}


	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@Override
	public Post copy(boolean deep) {
		if (deep) {
			throw new UnsupportedOperationException("Deep copy not supported.");
		}
		Post result = new Post();
		{
			result.id = this.id;
			result.authorId = this.authorId;
			result.title = this.title;
			result.body = this.body;
			result.pictureUrl = this.pictureUrl;
		}
		return result;
	}

	@Override
	public Post copy() {
		return copy(false);
	}


	@Override
	public int hashCode() {
		int hash =  super.hashCode();
		{
			hash = calcHash(hash, authorId);
			hash = calcHash(hash, title);
			hash = calcHash(hash, body);
			hash = calcHash(hash, pictureUrl);
		}
		return hash;
	}

	@Override
	protected boolean equalsImpl(Object o) {
		if (super.equalsImpl(o) && (o instanceof Post)) {
			Post other = (Post)o;

			return TextUtils.equals(this.authorId, other.authorId)
				&& TextUtils.equals(this.title, other.title)
				&& TextUtils.equals(this.body, other.body)
				&& TextUtils.equals(this.pictureUrl, other.pictureUrl);
		}
		return false;
	}
}
