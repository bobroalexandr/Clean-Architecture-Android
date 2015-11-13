package demo.application.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import demo.R;
import demo.application.fragments.UserPostsFragment;

/**
 * Created by Alex on 12.11.2015.
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
				.add(R.id.container, UserPostsFragment.newInstance(null))
				.commit();
		}
	}
}
