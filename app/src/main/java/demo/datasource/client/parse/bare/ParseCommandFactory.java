package demo.datasource.client.parse.bare;

import java.util.HashMap;
import java.util.Map;

import demo.datasource.Criteria;

/**
 * Created on 31.10.2015.
 */
final class ParseCommandFactory {

	public interface CriteriaMapper<A> {

		ParseCommand<A, ?> resolve(Criteria criteria);
	}

	static {
		// TODO: register all the mappers here.
	}


	private final Map<Class, CriteriaMapper> mapperRegistry = new HashMap<>();

	public <A> void register(Class<A> apiClass, Class<? extends CriteriaMapper<A>> criteriaMapperClass) {
		try {
			register(apiClass, criteriaMapperClass.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public <A> void register(Class<A> apiClass, CriteriaMapper<A> criteriaMapper) {
		mapperRegistry.put(apiClass, criteriaMapper);
	}

	public <A, R> ParseCommand<A, R> resolve(Criteria criteria, Class<A> apiClass) {
		//noinspection unchecked
		return (ParseCommand<A, R>)((CriteriaMapper<A>)mapperRegistry.get(apiClass)).resolve(criteria);
	}


	public static ParseCommandFactory getInstance() {
		return Singleton.INSTANCE;
	}


	private static final class Singleton {
		public static final ParseCommandFactory INSTANCE = new ParseCommandFactory();
	}

	private ParseCommandFactory() {
		/* Prevent instantiating */
	}
}
