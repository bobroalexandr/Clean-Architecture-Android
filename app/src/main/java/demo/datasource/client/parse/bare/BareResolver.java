package demo.datasource.client.parse.bare;

import java.util.HashMap;
import java.util.Map;

import demo.datasource.client.RESTClient;
import demo.datasource.client.RESTClientFactory;
import demo.datasource.client.RESTClientReader;
import demo.datasource.client.parse.ObjectConverterFactory;
import demo.datasource.client.parse.bare.retrofit.ParseObjectsAPI;
import demo.domain.Post;
import demo.domain.User;

/**
 * Created on 31.10.2015.
 */
public class BareResolver implements RESTClientFactory.Resolver {

	static {
		ObjectConverterFactory objectConverterFactory = ObjectConverterFactory.getInstance();
		{
			objectConverterFactory.register(ParsePost.class, ParsePost.Converter.class);
			objectConverterFactory.register(ParseUser.class, ParseUser.Converter.class);
		}
		ParseCommandFactory parseCommandFactory = ParseCommandFactory.getInstance();
		{
			parseCommandFactory.register(ParseObjectsAPI.class, ParseObjectAPICriteriaMapper.class);
		}
	}


	private final Map<Class, RESTClientReader> restClientMap = new HashMap<>();

	public BareResolver() {
		restClientMap.put(Post.class, new ParseClientBare<>(ParsePost.class, "classes/Post"));
		restClientMap.put(User.class, new ParseClientReaderBare<>(ParseUser.class, "users"));
	}

	@Override
	public RESTClientReader resolve(Class domainObjectClass) {
		return restClientMap.get(domainObjectClass);
	}
}
