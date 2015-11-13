package demo.datasource.client.parse.bare.retrofit;

import com.google.gson.JsonElement;

import java.util.Map;

import demo.datasource.client.parse.ParseConstants;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created on 25.10.2015.
 */
public interface ParseObjectsAPI {

	/// Objects

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST,
		"Content-Type: application/json"
	})
	@POST("/")
	Map<String, String> postObject(@Body Map<String, Object> _object);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST
	})
	@GET("/{id}")
	Map<String, JsonElement> getObject(@Path("id") String _objectId, @QueryMap Map<String, Object> query);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST,
		"Content-Type: application/json"
	})
	@PUT("/{id}")
	Map<String, String> putObject(@Path("id") String _objectId, @Body Map<String, Object> _object);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST
	})
	@PUT("/{id}")
	Map<String, String> deleteObject(@Path("id") String _objectId);
}
