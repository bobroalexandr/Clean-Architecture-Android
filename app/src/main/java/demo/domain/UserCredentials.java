package demo.domain;

import demo.util.Copyable;
import demo.util.TextUtils;

/**
 * Created on 25.10.2015.
 */
public class UserCredentials extends DomainObject implements Copyable {

	public static final UserCredentials EMPTY = new UserCredentials();


	private String password;

	public String getPassword() {
		return password;
	}


	private UserCredentials() {
	/* Nothing to do */
	}

	public UserCredentials(String id, String password) {
		if (TextUtils.isEmpty(id) || TextUtils.isEmpty(password)) {
			throw new IllegalArgumentException("Both, id and password must NOT be empty!");
		}
		this.id = id;
		this.password = password;
	}


	@Override
	public UserCredentials copy(boolean deep) {
		if (deep) {
			throw new UnsupportedOperationException("Deep copy not supported.");
		}
		UserCredentials result = new UserCredentials();
		{
			result.id = this.id;
			result.password = this.password;
		}
		return result;
	}

	@Override
	public UserCredentials copy() {
		return copy(false);
	}


	@Override
	public int hashCode() {
		int hash = super.hashCode();
		{
			hash = calcHash(hash, password);
		}
		return hash;
	}

	@Override
	protected boolean equalsImpl(Object o) {
		if (super.equalsImpl(o) && (o instanceof UserCredentials)) {
			UserCredentials other = (UserCredentials)o;

			return TextUtils.equals(this.password, other.password);
		}
		return false;
	}
}
