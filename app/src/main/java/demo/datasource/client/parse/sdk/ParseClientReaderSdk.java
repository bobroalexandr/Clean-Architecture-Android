package demo.datasource.client.parse.sdk;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collection;
import java.util.List;

import demo.datasource.Criteria;
import demo.datasource.client.RESTClientReader;
import demo.datasource.client.RESTException;
import demo.datasource.client.parse.ObjectConverter;
import demo.datasource.client.parse.ObjectConverterFactory;
import solid.stream.Stream;

/**
 * Created on 12.11.2015.
 */
class ParseClientReaderSdk<T, P extends ParseObject> implements RESTClientReader<T> {

	protected Class<P>              parseClass;
	protected ObjectConverter<T, P> objectConverter;
	protected ExceptionConverter    exceptionConverter;

	public ParseClientReaderSdk(Class<P> parseClass) {
		this.parseClass = parseClass;
		this.objectConverter = ObjectConverterFactory.getInstance().resolve(parseClass);
		this.exceptionConverter = new ExceptionConverter();
	}


	@Override
	public Collection<T> get(Criteria criteria) throws RESTException {
		ParseQuery<P> parseQuery = ParseQueryFactory.fromCriteria(parseClass, criteria);
		try {
			List<P> parseObjects = parseQuery.find();
			return Stream.stream(parseObjects).map(objectConverter::convert).toList();
		} catch (ParseException e) {
			throw exceptionConverter.convert(e);
		}
	}
}
