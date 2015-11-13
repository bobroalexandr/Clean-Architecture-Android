package demo.presentation.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.R;
import demo.application.adapters.PostsAdapter;
import demo.domain.Post;
import demo.domain.User;
import demo.presentation.presenter.UserPostsScreenPresenter;

/**
 * Created on 12.11.2015.
 */
public class UserPostsView extends DemoView<List<Post>, UserPostsScreenPresenter> {

	@Bind(R.id.postsList)
	RecyclerView postsList;

	PostsAdapter postsAdapter;


	@Override
	public void initializeViewComponents(View view) {
		super.initializeViewComponents(view);
		ButterKnife.bind(this, view);
		postsList.setLayoutManager(new LinearLayoutManager(getContext()));
	}

	@Override
	public void displayData(List<Post> data) {
		super.displayData(data);
		if (postsAdapter == null) {
			postsAdapter = new PostsAdapter(getContext(), data, getDataPresenter());
			postsList.setAdapter(postsAdapter);
		} else {
			postsAdapter.swapPosts(data);
		}
	}
}
