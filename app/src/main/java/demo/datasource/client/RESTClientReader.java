package demo.datasource.client;

import java.util.Collection;

import demo.datasource.Criteria;

/**
 * Created on 12.11.2015.
 */
public interface RESTClientReader<T> {

	/**
	 * Performs an HTTP GET method over this REST client.
	 *
	 * @param criteria used to filter items against when fetching pages of data.
	 * @return Returns a collection of data that provides a window, to perform data iteration with.
	 *
	 * Note, that paging is supposed to be controlled with {@code criteria} object as well.
	 */
	Collection<T> get(Criteria criteria) throws RESTException;
}
