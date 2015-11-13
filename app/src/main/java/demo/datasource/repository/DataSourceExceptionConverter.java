package demo.datasource.repository;

import demo.datasource.client.RESTException;

/**
 * Created on 12.11.2015.
 */
public class DataSourceExceptionConverter {

	DataSourceException convert(RESTException exception) {
		return new DataSourceException(exception.getMessage(), exception.getCause(), exception.getCode());
	}
}
