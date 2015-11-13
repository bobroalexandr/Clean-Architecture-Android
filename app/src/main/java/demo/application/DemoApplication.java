package demo.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import com.parse.Parse;
import com.parse.ParseObject;

import org.jetbrains.annotations.NotNull;

import demo.datasource.client.parse.ParseConstants;
import demo.datasource.client.parse.sdk.ParsePost;

/**
 * Created on 23.10.2015.
 */
public class DemoApplication extends Application {

	private static final String TAG = DemoApplication.class.getSimpleName();


	// TODO: place your code here.


	public static SharedPreferences getDefaultPreferences() {
		return getInstance().getSharedPreferences(".default_prefs", Context.MODE_PRIVATE);
	}


	@NotNull
	public static ActivityManager.MemoryInfo getMemoryInfo() {
		ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
		{
			ActivityManager am = (ActivityManager) getInstance().getSystemService(Context.ACTIVITY_SERVICE);

			am.getMemoryInfo(mi);
		}
		return mi;
	}


	private static boolean firstRun;
	private static final String KEY_FIRST_RUN = String.format("%s#key_first_run", DemoApplication.class);

	private static void manageFirstLaunch() {
		SharedPreferences preferences = DemoApplication.getDefaultPreferences();

		if (firstRun = preferences.getBoolean(KEY_FIRST_RUN, true)) {
			preferences.edit().putBoolean(KEY_FIRST_RUN, false).apply();
		}
	}

	public static boolean isFirstLaunch() {
		return firstRun;
	}


	private static volatile DemoApplication instance;

	private static void initInstance(DemoApplication context) {
		instance = context;
	}

	@NotNull
	public static DemoApplication getInstance() {
		return instance;
	}


	@Override
	public void onCreate() {
		super.onCreate();

		initInstance(this);
		manageFirstLaunch();

		ParseObject.registerSubclass(ParsePost.class);
		Parse.initialize(this, ParseConstants.PARSE_APPLICATION_ID, ParseConstants.PARSE_KEY_CLIENT);

		// TODO: place all the other initialization calls here.
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);

		MultiDex.install(this);
	}
}
