package demo.datasource.client.parse.sdk;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Collection;
import java.util.List;

import demo.datasource.Criteria;
import demo.datasource.client.RESTClient;
import demo.datasource.client.RESTException;
import demo.datasource.client.parse.ObjectConverter;
import demo.datasource.client.parse.ObjectConverterFactory;
import solid.collections.SolidList;
import solid.stream.Stream;

/**
 * Created on 29.10.2015.
 */
class ParseClientSdk<T, P extends ParseObject> extends ParseClientReaderSdk<T, P> implements RESTClient<T> {


	public ParseClientSdk(Class<P> parseClass) {
		super(parseClass);
	}


	@Override
	public Collection<T> post(Iterable<T> items) throws RESTException {
		List<P> objects = Stream.stream(items).map(objectConverter::convertBack).toList();
		try {
			ParseObject.saveAll(objects);
			return Stream.stream(objects).map(objectConverter::convert).toList();
		} catch (ParseException e) {
			throw exceptionConverter.convert(e);
		}
	}

	@Override
	public Collection<T> delete(Criteria criteria) throws RESTException {
		ParseQuery<P> parseQuery = ParseQueryFactory.fromCriteria(parseClass, criteria);
		try {
			List<P> parseObjects = parseQuery.find();
			ParseObject.deleteAll(parseObjects);
			return Stream.stream(parseObjects).map(objectConverter::convert).toList();
		} catch (ParseException e) {
			throw exceptionConverter.convert(e);
		}
	}

	@Override
	public Collection<T> delete(Iterable<T> items) throws RESTException {
		try {
			ParseObject.deleteAll(Stream.stream(items)
				.map(objectConverter::convertBack)
				.toList());
			return new SolidList<>(items);
		} catch (ParseException e) {
			throw exceptionConverter.convert(e);
		}
	}
}
