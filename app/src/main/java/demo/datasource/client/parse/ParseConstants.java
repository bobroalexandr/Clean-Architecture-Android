package demo.datasource.client.parse;

/**
 * This class defines constants for Parse service implementation.
 */
public final class ParseConstants {

	public static final int PARSE_BATCHED_OPERATIONS_COUNT_MAX = 50;


	/**
	 * Application ID. This is the main identifier that uniquely specifies your application.
	 * This is paired with a key to provide your clients access to your application's data.
	 */
	public static final String PARSE_APPLICATION_ID = "I83DLobjMb0kofLwo1I25a2NTJ75AFOHPj2RsYIl";

	/**
	 * Client Key. This key should be used in consumer clients, like the iOS or Android SDK.
	 * It adheres to object level permissions.
	 */
	public static final String PARSE_KEY_CLIENT = "VKfNt9zseR2mgDbSywBYSEgO4LWOzw7RnUqMLdeQ";

	/**
	 * REST API Key. This key should be used when making requests to the REST API.
	 * It also adheres to object level permissions.
	 */
	public static final String PARSE_KEY_REST = "NdBXDYUppPadRcI7Ld3CAAp8Je4C9sBSko5NBqJG";


	public static final String URL_REST_BASE = "https://api.parse.com/1/";


	private ParseConstants() {
		/* Prevent instantiating */
	}
}
