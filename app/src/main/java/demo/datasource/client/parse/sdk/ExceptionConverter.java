package demo.datasource.client.parse.sdk;

import com.parse.ParseException;

import demo.datasource.client.RESTException;
import demo.datasource.client.parse.ObjectConverter;

/**
 * Created on 08.11.2015.
 */
class ExceptionConverter implements ObjectConverter<RESTException, ParseException> {

	@Override
	public RESTException convert(ParseException src) {
		return new RESTException(src.getMessage(), src.getCause(), src.getCode());
	}

	@Override
	public ParseException convertBack(RESTException target) {
		return new ParseException(target.getCode(), target.getMessage(), target.getCause());
	}
}
