package demo.datasource.client;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import demo.domain.Post;
import demo.domain.User;
import demo.util.TextUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created on 31.10.2015.
 */
public class RESTClientTest {

	@Test
	public void classesTest() {
		RESTClient<Post> client = RESTClientFactory.resolve(Post.class);

		List<Post> postsExpected = new ArrayList<>();
		{
			Post post = new Post();

			for (int i = 5; i > 0; --i) {
				post.setTitle(String.format("Test Post #%d", i));
				postsExpected.add(post.copy());
			}
		}
		Collection<Post> response;
		List<Post> postsActual;

		try {
			response = client.post(postsExpected);
		} catch (RESTException e) {
			fail(e.getMessage());

			throw new RuntimeException(e);
		}
		assertNotNull(response);

		postsActual = new ArrayList<>(response);
		assertTrue(postsExpected.size() == postsActual.size());

		for (int i = 0, imax = postsExpected.size(); i < imax; ++i) {
			assertNotNull(postsActual.get(i).getId());
			assertEquals(
				postsExpected.get(i).getTitle(),
				postsActual.get(i).getTitle()
			);
		}
		try {
			response = client.get(null);
		} catch (RESTException e) {
			fail(e.getMessage());

			throw new RuntimeException(e);
		}
		assertNotNull(response);
		assertTrue(response.size() >= postsExpected.size());

		try {
			response = client.delete(postsActual);
		} catch (RESTException e) {
			fail(e.getMessage());

			throw new RuntimeException(e);
		}
		assertNotNull(response);

		postsActual = new ArrayList<>(response);
		assertTrue(0 < postsActual.size()
			&& postsActual.size() <= postsExpected.size());

		for (int i = 0, imax = postsExpected.size(); i < imax; ++i) {
			assertNotNull(postsActual.get(i).getId());
		}
	}


	@Test
	public void usersTest() {
		// TODO: need to replace this with create-read-remove approach in future.
		RESTClientReader<User> client = RESTClientFactory.resolveReader(User.class);
		Collection<User> response;

		try {
			response = client.get(null);
		} catch (RESTException e) {
			fail(e.getMessage());

			throw new RuntimeException(e);
		}
		assertNotNull(response);
		assertTrue(response.size() > 0);

		boolean contains = false;
		for (User responseUser : response) {
			contains |= TextUtils.equals(responseUser.getId(), "VWjzO4OUmL");
		}
		assertTrue(contains);
	}
}
