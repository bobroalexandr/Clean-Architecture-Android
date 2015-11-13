package demo.application.fragments;

import android.os.Bundle;

import demo.R;
import demo.domain.Post;
import demo.domain.User;
import demo.presentation.navigation.UserPostsScreenNavigation;
import demo.presentation.presenter.UserPostsScreenPresenter;
import demo.presentation.view.UserPostsView;

/**
 * Created on 12.11.2015.
 */
public class UserPostsFragment extends FragmentBase<UserPostsView,UserPostsScreenPresenter> implements UserPostsScreenNavigation {

	private static final String BUNDLE_KEY_USER_ID = "bundle_key_user_id";

	UserPostsView userPostsView;
	UserPostsScreenPresenter userPostsScreenPresenter;

	public static UserPostsFragment newInstance(String userId) {
		Bundle args = new Bundle();
		args.putString(BUNDLE_KEY_USER_ID, userId);
		UserPostsFragment userPostsFragment = new UserPostsFragment();
		userPostsFragment.setArguments(args);
		return userPostsFragment;
	}

	@Override
	protected UserPostsView getViewBase() {
		return userPostsView == null ? new UserPostsView()  : userPostsView;
	}

	@Override
	protected UserPostsScreenPresenter getPresenterBase() {
		return userPostsScreenPresenter == null ? new UserPostsScreenPresenter(this, getArguments().getString(BUNDLE_KEY_USER_ID)) : userPostsScreenPresenter;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_posts;
	}

	@Override
	public void navigateToUserDetailsScreen(User user) {
		//TODO implement when user screen will be ready
	}

	@Override
	public void navigateToPostDetailsScreen(Post post) {
		//TODO implement when post screen will be ready
	}
}
