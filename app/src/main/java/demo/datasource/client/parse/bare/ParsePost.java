package demo.datasource.client.parse.bare;

import demo.datasource.client.parse.ObjectConverter;
import demo.domain.Post;
import demo.util.TextUtils;

/**
 * Created on 31.10.2015.
 */
class ParsePost extends ParseObject {

	public ParseRelation author;
	public String title;
	public String bodyText;
	public String bodyPictureUrl;


	public static final class Converter implements ObjectConverter<Post, ParsePost> {

		@Override
		public Post convert(ParsePost src) {
			Post result = new Post(src.id);
			{
				ParseRelation author = src.author;

				if (author != null) {
					result.setAuthorId(author.objectId);
				}
				result.setTitle(src.title);
				result.setBody(src.bodyText);
				result.setPictureUrl(src.bodyPictureUrl);
			}
			return result;
		}

		@Override
		public ParsePost convertBack(Post target) {
			ParsePost result = new ParsePost();
			{
				result.id = target.getId();

				String authorId = target.getAuthorId();
				if (!TextUtils.isEmpty(authorId)) {
					result.author = new ParseRelation();
					result.author.objectId = authorId;
					result.author.className = "_User";
					result.author.type = "Relation";
				}
				result.title = target.getTitle();
				result.bodyText = target.getBody();
				result.bodyPictureUrl = target.getPictureUrl();
			}
			return result;
		}
	}
}
