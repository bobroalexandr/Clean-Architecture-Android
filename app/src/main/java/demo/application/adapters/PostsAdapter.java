package demo.application.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.R;
import demo.domain.Post;
import demo.domain.User;

/**
 * Created on 12.11.2015.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostHolder> {

	private Context           context;
	private List<Post>        posts;
	private OnPostsItemAction onPostsItemAction;

	public PostsAdapter(Context context, List<Post> posts, OnPostsItemAction onPostsItemAction) {
		this.context = context;
		this.posts = posts;
		this.onPostsItemAction = onPostsItemAction;
	}

	public void swapPosts(List<Post> posts) {
		this.posts = posts;
		notifyDataSetChanged();
	}

	@Override
	public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new PostHolder(LayoutInflater.from(context).inflate(R.layout.item_post, parent, false));
	}

	@Override
	public void onBindViewHolder(PostHolder holder, int position) {
		final Post post = getPost(position);
		holder.tvPostBodyText.setText(post.getBody());
		Picasso.with(context)
			.load(post.getPictureUrl())
			.into(holder.ivPostImage);
		holder.rvPost.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(onPostsItemAction != null) {
					onPostsItemAction.onPostSelected(post);
				}
			}
		});

		//TODO change when user repository functionality will be ready
		holder.tvUsername.setText("noname");
		Picasso.with(context)
			.load("http://pablo-el-diablo.narod.ru/11.jpg")
			.into(holder.ivAvatar);
		holder.rvAuthor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO implement when user repository functionality will be ready
			}
		});
	}

	@Override
	public int getItemCount() {
		return posts == null ? 0 : posts.size();
	}

	protected Post getPost(int position) {
		return posts.get(position);
	}

	public interface OnPostsItemAction {

		void onUserSelected(User user);

		void onPostSelected(Post post);
	}

	static class PostHolder extends RecyclerView.ViewHolder {

		@Bind(R.id.author)
		RelativeLayout rvAuthor;

		@Bind(R.id.avatar)
		ImageView ivAvatar;

		@Bind(R.id.username)
		TextView tvUsername;

		@Bind(R.id.post)
		RelativeLayout rvPost;

		@Bind(R.id.postBodyText)
		TextView tvPostBodyText;

		@Bind(R.id.postImage)
		ImageView ivPostImage;


		public PostHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
