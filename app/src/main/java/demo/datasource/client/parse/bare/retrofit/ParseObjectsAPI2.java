package demo.datasource.client.parse.bare.retrofit;

import java.util.Collection;
import java.util.Map;

import demo.datasource.client.parse.ParseConstants;
import demo.datasource.client.parse.bare.request.ParseBatchedRequest;
import demo.datasource.client.parse.bare.response.ParseBatchedResponse;
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
public interface ParseObjectsAPI2 {

	/// Objects

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST,
		"Content-Type: application/json"
	})
	@POST(ParseConstants.URL_REST_BASE + "classes/{className}")
	Map<String, String> postObject(@Path("className") String _classname, @Body Map<String, Object> _object);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST
	})
	@GET(ParseConstants.URL_REST_BASE + "classes/{className}/{id}")
	Map<String, String> getObject(@Path("className") String _classname, @Path("id") String _objectId, @QueryMap Map<String, Object> query);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST,
		"Content-Type: application/json"
	})
	@PUT(ParseConstants.URL_REST_BASE + "classes/{className}/{id}")
	Map<String, String> putObject(@Path("className") String _classname, @Path("id") String _objectId, @Body Map<String, Object> _object);

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST
	})
	@PUT(ParseConstants.URL_REST_BASE + "classes/{className}/{id}")
	Map<String, String> deleteObject(@Path("className") String _classname, @Path("id") String _objectId);


	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST,
		"Content-Type: application/json"
	})
	@POST(ParseConstants.URL_REST_BASE + "batch")
	Collection<ParseBatchedResponse> batchObjects(@Body ParseBatchedRequest _request);
}
