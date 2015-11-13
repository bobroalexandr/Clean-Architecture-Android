package demo.datasource.client.parse.bare.retrofit;

import java.util.List;

import demo.datasource.client.parse.ParseConstants;
import demo.datasource.client.parse.bare.request.ParseBatchedRequest;
import demo.datasource.client.parse.bare.response.ParseBatchedResponse;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created on 31.10.2015.
 */
public interface ParseBatchAPI {

	@Headers(value = {
		"X-Parse-Application-Id: " + ParseConstants.PARSE_APPLICATION_ID,
		"X-Parse-REST-API-Key: " + ParseConstants.PARSE_KEY_REST,
		"Content-Type: application/json"
	})
	@POST("/batch")
	List<ParseBatchedResponse> batchObjects(@Body ParseBatchedRequest _request);
}
