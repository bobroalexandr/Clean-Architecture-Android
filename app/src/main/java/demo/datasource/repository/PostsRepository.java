package demo.datasource.repository;

import java.util.Collection;
import java.util.Enumeration;

import demo.datasource.Criteria;
import demo.datasource.client.RESTClient;
import demo.datasource.client.RESTClientFactory;
import demo.datasource.client.RESTException;
import demo.domain.Post;

/**
 * Created on 12.11.2015.
 */
//TODO implement 3 datasource layers (memory cache, database cache and rest server cache)
public class PostsRepository implements RepositoryBase<Post> {

	RESTClient<Post>             postClient;
	DataSourceExceptionConverter dataSourceExceptionConverter;

	public PostsRepository() {
		this.postClient = RESTClientFactory.resolve(Post.class);
		dataSourceExceptionConverter = new DataSourceExceptionConverter();
	}

	@Override
	public Collection<Post> read(Criteria criteria) throws DataSourceException {
		try {
			return postClient.get(criteria);
		} catch (RESTException e) {
			throw dataSourceExceptionConverter.convert(e);
		}
	}

	@Override
	public Collection<Post> readAll() throws DataSourceException {
		return null;
	}

	@Override
	public Collection<Post> write(Enumeration<Post> data) throws DataSourceException {
		return null;
	}

	@Override
	public Collection<Post> insert(Enumeration<Post> data) throws DataSourceException {
		return null;
	}

	@Override
	public Collection<Post> delete(Criteria criteria) throws DataSourceException {
		return null;
	}

	@Override
	public Collection<Post> deleteAll(Enumeration<Post> data) throws DataSourceException {
		return null;
	}
}
