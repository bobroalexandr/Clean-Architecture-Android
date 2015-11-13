package demo.datasource.client.parse.bare.request;

import java.util.Map;

/**
 * Created on 28.10.2015.
 */
public class ParseBatchedOp {
	/**
	 * A field, that represents HTTP method.
	 *
	 * Should be one of: GET, POST, PUT, DELETE
	 */
	public String method;

	/**
	 * A relative path, to class to perform operation with.
	 * Consists of /%API_VERSION%/classes/%CLASS_NAME%.
	 *
	 * Example: /1/classes/GameScore
	 */
	public String path;

	/**
	 * Body represents a body of an object of certain class.
	 */
	public Map<String, Object> body;
}
