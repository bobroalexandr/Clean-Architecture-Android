package demo.datasource.client.parse.sdk;

import com.parse.ParseUser;

import java.util.HashMap;
import java.util.Map;

import demo.datasource.client.RESTClientFactory;
import demo.datasource.client.RESTClientReader;
import demo.datasource.client.parse.ObjectConverterFactory;
import demo.domain.Post;
import demo.domain.User;

/**
 * Created on 31.10.2015.
 */
public class SdkResolver implements RESTClientFactory.Resolver {

	static {
		ObjectConverterFactory factory = ObjectConverterFactory.getInstance();
		{
			factory.register(ParseUser.class, UserConverter.class);
			factory.register(ParsePost.class, PostConverter.class);
		}
	}


	private final Map<Class, RESTClientReader> restClientMap = new HashMap<>();

	public SdkResolver() {
		restClientMap.put(Post.class, new ParseClientSdk<Post, ParsePost>(ParsePost.class));
		restClientMap.put(User.class, new ParseClientReaderSdk<User, ParseUser>(ParseUser.class));
	}

	@Override
	public RESTClientReader resolve(Class domainObjectClass) {
		return restClientMap.get(domainObjectClass);
	}
}
