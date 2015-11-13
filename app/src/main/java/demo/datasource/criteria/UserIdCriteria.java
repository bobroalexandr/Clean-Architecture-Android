package demo.datasource.criteria;

import demo.datasource.Criteria;

/**
 * Created on 12.11.2015.
 */
public class UserIdCriteria implements Criteria {

	private final String userId;

	public UserIdCriteria(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
}
