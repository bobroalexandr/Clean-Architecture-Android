package demo.application;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.test.ApplicationTestCase;
import android.util.Log;

import demo.application.DemoApplication;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<DemoApplication> {

	private static final String TAG = DemoApplication.class.getSimpleName();


	public ApplicationTest() {
		super(DemoApplication.class);
	}


	@Override
	protected void setUp() throws Exception {
		super.setUp();

		createApplication();
	}

	public void testAccountManagerSandbox() {
		AccountManager accountManager = AccountManager.get(getApplication());
		Account accounts[] = accountManager.getAccounts();

		if (accounts.length > 0) {
			for (Account account : accounts) {
				Log.d(TAG, account.toString());
			}
		} else {
			PackageManager pm = getContext().getPackageManager();
			{
				int result = pm.checkPermission(Manifest.permission.GET_ACCOUNTS, getContext().getPackageName());

				Log.d(TAG, String.format("%s permission is: %s", Manifest.permission.GET_ACCOUNTS,
					result == PackageManager.PERMISSION_GRANTED ? "granted" : "denied"));
			}
			Log.d(TAG, "There are no accounts on this device.");
		}
	}
}