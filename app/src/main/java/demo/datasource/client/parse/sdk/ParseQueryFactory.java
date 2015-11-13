package demo.datasource.client.parse.sdk;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import demo.datasource.Criteria;

/**
 * Created on 30.10.2015.
 */
final class ParseQueryFactory {

	public static <P extends ParseObject> ParseQuery<P> fromCriteria(Class<P> parseClass, Criteria criteria) {
		return new ParseQuery<>(parseClass);
	}
}
