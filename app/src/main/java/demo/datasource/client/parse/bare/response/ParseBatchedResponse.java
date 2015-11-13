package demo.datasource.client.parse.bare.response;

import com.google.gson.JsonElement;

import java.util.Map;

/**
 * Created on 28.10.2015.
 */
public class ParseBatchedResponse {
	/**
	 * POST
	 * {
	 *   "createdAt": "2011-08-20T02:06:57.931Z",
	 *   "objectId": "Ed1nuqPvcm"
	 * }
	 *
	 * PUT
	 * {
	 *   "updatedAt": "2011-08-21T18:02:52.248Z"
	 * }
	 *
	 * DELETE
	 * {
	 *   // Empty object
	 * }
	 *
	 * GET
	 * {
	 *   // Class object
	 * }
	 */
	public Map<String, JsonElement> success;

	/**
	 * {
	 *   "code": 101,
	 *   "error": "object not found for delete"
	 * }
	 */
	public Map<String, JsonElement> error;
}
