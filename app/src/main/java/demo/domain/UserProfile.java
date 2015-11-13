package demo.domain;

import demo.util.Copyable;
import demo.util.TextUtils;

/**
 * Created on 10.11.2015.
 */
public class UserProfile extends User implements Copyable {

	String username;

	String password;

	public UserProfile() {
	}

	public UserProfile(String id) {
		super(id);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public UserProfile copy(boolean deep) {
		if (deep) {
			throw new UnsupportedOperationException("Deep copy not supported.");
		}
		UserProfile result = new UserProfile();
		{
			fillCopy(result);

			result.username = this.username;
			result.password = this.password;
		}
		return result;
	}

	@Override
	public UserProfile copy() {
		return copy(false);
	}


	@Override
	public int hashCode() {
		int hash = super.hashCode();
		{
			hash = calcHash(hash, username);
			hash = calcHash(hash, password);
		}
		return hash;
	}

	@Override
	protected boolean equalsImpl(Object o) {
		if (super.equalsImpl(o) && (o instanceof UserProfile)) {
			UserProfile other = (UserProfile)o;

			return TextUtils.equals(this.username, other.username)
				&& TextUtils.equals(this.password, other.password);
		}
		return false;
	}
}
