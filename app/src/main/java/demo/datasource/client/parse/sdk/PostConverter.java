package demo.datasource.client.parse.sdk;

import demo.datasource.client.parse.ObjectConverter;
import demo.domain.Post;

/**
 * Created on 29.10.2015.
 */
public class PostConverter implements ObjectConverter<Post, ParsePost> {

	@Override
	public Post convert(ParsePost parsePost) {
		Post post = new Post(parsePost.getObjectId());
		post.setAuthorId(parsePost.getAuthorId());
		post.setTitle(parsePost.getTitle());
		post.setBody(parsePost.getBody());
		post.setPictureUrl(parsePost.getPictureUrl());
		return post;
	}

	@Override
	public ParsePost convertBack(Post post) {
		ParsePost parsePost = new ParsePost();
		parsePost.setObjectId(post.getId());
		parsePost.setAuthor(post.getAuthorId());
		parsePost.setTitle(post.getTitle());
		parsePost.setBody(post.getBody());
		parsePost.setPictureUrl(post.getPictureUrl());
		return null;
	}
}
