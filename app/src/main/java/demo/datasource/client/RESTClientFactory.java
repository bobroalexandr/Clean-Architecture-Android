package demo.datasource.client;

import demo.BuildConfig;
import demo.datasource.client.parse.bare.BareResolver;
import demo.datasource.client.parse.sdk.SdkResolver;

/**
 * Created on 31.10.2015.
 */
public class RESTClientFactory {

	public interface Resolver {

		RESTClientReader resolve(Class domainObjectClass);
	}


	@SuppressWarnings("unchecked")
	public static <T> RESTClient<T> resolve(Class<T> domainObjectClass) {
		return (RESTClient<T>)Singleton.INSTANCE.resolver.resolve(domainObjectClass);
	}

	@SuppressWarnings("unchecked")
	public static <T> RESTClientReader<T> resolveReader(Class<T> domainObjectClass) {
		return (RESTClientReader<T>)Singleton.INSTANCE.resolver.resolve(domainObjectClass);
	}


	private static final class Singleton {
		public static final RESTClientFactory INSTANCE = buildFactory();
	}

	private final Resolver resolver;

	private RESTClientFactory(Resolver resolver) {
		this.resolver = resolver;
	}

	private static RESTClientFactory buildFactory() {
		if (BuildConfig.PARSE_CLIENT_IMPL_TYPE_ACTIVE.equals(BuildConfig.PARSE_CLIENT_IMPL_TYPE_BARE)) {
			return new RESTClientFactory(new BareResolver());
		} else if (BuildConfig.PARSE_CLIENT_IMPL_TYPE_ACTIVE.equals(BuildConfig.PARSE_CLIENT_IMPL_TYPE_SDK)) {
			return new RESTClientFactory(new SdkResolver());
		}
		throw new Error(String.format("Oops! Resolver that supports this type is not defined yet: %s", BuildConfig.PARSE_CLIENT_IMPL_TYPE_ACTIVE));
	}
}
