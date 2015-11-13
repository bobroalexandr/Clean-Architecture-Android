package demo.datasource.client.parse.bare.retrofit;

import java.util.Map;

import demo.datasource.client.parse.ParseConstants;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created on 29.10.2015.
 */
public interface ParseUsersAPI {

	/// Users

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST,
		"Content-Type: application/json",
		"X-Parse-Revocable-Session: 1"
	})
	@POST(ParseConstants.URL_REST_BASE + "users")
	Map<String, String> signUp(@Body Map<String, Object> _request);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST,
		"X-Parse-Revocable-Session: 1"
	})
	@GET(ParseConstants.URL_REST_BASE + "login")
	Map<String, String> login(@Query("username") String _username, @Query("password") String _password);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST,
		"Content-Type: application/json",
	})
	@POST(ParseConstants.URL_REST_BASE + "requestPasswordReset")
	Map<String, String> requestPasswordReset(@Body Map<String, String> _request);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST
	})
	@GET(ParseConstants.URL_REST_BASE + "users/me")
	Map<String, String> getMe(@Header("X-Parse-Session-Token") String _token);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST
	})
	@GET(ParseConstants.URL_REST_BASE + "users/{id}")
	Map<String, String> getUser(@Path("id") String _userId);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST
	})
	@POST(ParseConstants.URL_REST_BASE + "users/{id}")
	Map<String, String> postUser(@Header("X-Parse-Session-Token") String _token,
								 @Path("id") String _userId, @Body Map<String, String> _userRequest);
}
