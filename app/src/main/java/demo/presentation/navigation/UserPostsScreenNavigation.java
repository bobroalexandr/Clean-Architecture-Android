package demo.presentation.navigation;

import demo.domain.Post;
import demo.domain.User;

/**
 * Created on 13.11.2015.
 */
public interface UserPostsScreenNavigation {

	void navigateToUserDetailsScreen(User user);

	void navigateToPostDetailsScreen(Post post);
}
