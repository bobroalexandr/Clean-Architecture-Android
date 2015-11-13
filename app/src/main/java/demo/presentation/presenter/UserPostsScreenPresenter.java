package demo.presentation.presenter;

import java.util.List;

import demo.application.adapters.PostsAdapter;
import demo.datasource.repository.PostsRepository;
import demo.domain.Post;
import demo.domain.User;
import demo.presentation.navigation.UserPostsScreenNavigation;
import demo.presentation.view.UserPostsView;
import demo.usecase.GetPostsByUserIdUseCase;

/**
 * Created on 12.11.2015.
 */
public class UserPostsScreenPresenter extends DataPresenterBase<List<Post>,List<Post>,String,UserPostsView> implements PostsAdapter.OnPostsItemAction {

	String userId;
	UserPostsScreenNavigation navigation;

	public UserPostsScreenPresenter(UserPostsScreenNavigation navigation, String userId) {
		super(new GetPostsByUserIdUseCase(new PostsRepository()));
		this.userId = userId;
		this.navigation = navigation;
	}

	@Override
	public void attachToView(UserPostsView view) {
		super.attachToView(view);
		loadData();
	}

	@Override
	protected String getParams() {
		return userId;
	}

	@Override
	public void onUserSelected(User user) {
		navigation.navigateToUserDetailsScreen(user);
	}

	@Override
	public void onPostSelected(Post post) {
		navigation.navigateToPostDetailsScreen(post);
	}
}
