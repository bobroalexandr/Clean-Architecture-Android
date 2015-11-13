package demo.datasource.client;

import java.util.Collection;

import demo.datasource.Criteria;

/**
 * Created on 25.10.2015.
 */
public interface RESTClient<T> extends RESTClientReader<T> {

	/**
	 * Performs an HTTP POST (or PUT, internally) method over this REST client.
	 *
	 * Insert-or-Update idiom is a plus here.
	 *
	 * @param items to perform this operation against.
	 * @return Returns the whole bunch of items that were successfully saved;
	 * make sure, these items have their object id updated appropriately.
	 */
	Collection<T> post(Iterable<T> items) throws RESTException;


	/**
	 * Performs an HTTP DELETE method over this REST client.
	 *
	 * @param criteria used to filter items against when deleting items.
	 * @return Returns the whole bunch of items that were successfully removed;
	 * make sure, these items have their object id updated appropriately.
	 *
	 * Note, that paging is supposed to be controlled with {@code criteria} object as well.
	 */
	Collection<T> delete(Criteria criteria) throws RESTException;

	/**
	 * Performs an HTTP DELETE method over this REST client.
	 *
	 * @param items items to delete, please note, all items should consider to have their IDs set.
	 * @return Returns the whole bunch of items that were successfully removed;
	 * make sure, these items have their object id updated appropriately.
	 */
	Collection<T> delete(Iterable<T> items) throws RESTException;
}
