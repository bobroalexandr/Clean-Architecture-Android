package demo.datasource.client.parse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 30.10.2015.
 */
public final class ObjectConverterFactory {

	final Map<Class, ObjectConverter> mapperRegistry = new HashMap<>();

	public <S> void register(Class<S> key, Class<? extends ObjectConverter<?, S>> mapper) {
		if (key == null) {
			throw new IllegalArgumentException("key shouldn't be 'null'.");
		}
		if (mapper == null) {
			throw new IllegalArgumentException("mapper shouldn't be 'null'.");
		}
		try {
			mapperRegistry.put(key, mapper.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public <S> void register(Class<S> key, ObjectConverter<?, S> mapper) {
		if (key == null) {
			throw new IllegalArgumentException("key shouldn't be 'null'.");
		}
		if (mapper == null) {
			throw new IllegalArgumentException("mapper shouldn't be 'null'.");
		}
		mapperRegistry.put(key, mapper);
	}

	public <T, S> ObjectConverter<T, S> resolve(Class<S> key) {
		if (key == null) {
			throw new IllegalArgumentException("key shouldn't be 'null'.");
		}
		//noinspection unchecked
		return (ObjectConverter<T, S>)mapperRegistry.get(key);
	}


	public static ObjectConverterFactory getInstance() {
		return Singleton.INSTANCE;
	}


	private static final class Singleton {
		public static final ObjectConverterFactory INSTANCE = new ObjectConverterFactory();
	}

	private ObjectConverterFactory() {
		/* Prevent instantiating */
	}
}
