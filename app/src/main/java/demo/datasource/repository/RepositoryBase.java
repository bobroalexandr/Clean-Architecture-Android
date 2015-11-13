package demo.datasource.repository;

import java.util.Collection;
import java.util.Enumeration;

import demo.datasource.Criteria;

/**
 * Created on 23.10.2015.
 */
public interface RepositoryBase<T> {

	/**
	 * Reads a page of data. Pagination is controlled
	 * with {@see demo.datasource.Criteria} object.
	 *
	 * @param criteria to control pagination with.
	 * @return A page of data.
	 */
	Collection<T> read(Criteria criteria) throws DataSourceException;

	/**
	 * Reads all the data items possible.
	 * May throw {@see java.lang.UnsupportedOperationException} if not required.
	 *
	 * @return A collection of whole data items set that is being managed under this repository.
	 */
	Collection<T> readAll() throws DataSourceException;

	/**
	 * Writes data items down to this repository:
	 * All the existing values will be overwritten, but
	 * all the new values will be inserted.
	 *
	 * @param data items enumeration.
	 * @return A collection of successfully processed items.
	 */
	Collection<T> write(Enumeration<T> data) throws DataSourceException;

	/**
	 * Inserts new data items down to this repository.
	 * All the values that exist under this repository will remain untouched.
	 *
	 * @param data items enumeration.
	 * @return A collection of successfully processed items.
	 */
	Collection<T> insert(Enumeration<T> data) throws DataSourceException;

	/**
	 * Removes data items that exist under this repository, given a certain criteria.
	 *
	 * @param criteria to select items for removal with.
	 * @return A page of successfully processed items.
	 */
	Collection<T> delete(Criteria criteria) throws DataSourceException;

	/**
	 * Attempts to remove data items that exist under this repository, given an enumeration.
	 *
	 * @param data items that will be attempted to remove from under this repository.
	 * @return A collection of successfully processed items.
	 */
	Collection<T> deleteAll(Enumeration<T> data) throws DataSourceException;
}
