package demo.datasource.client.parse.bare;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import demo.BuildConfig;
import demo.datasource.Criteria;
import demo.datasource.client.RESTClientReader;
import demo.datasource.client.RESTException;
import demo.datasource.client.parse.ObjectConverter;
import demo.datasource.client.parse.ObjectConverterFactory;
import demo.datasource.client.parse.ParseConstants;
import demo.datasource.client.parse.bare.response.ParseQueryResponse;
import demo.datasource.client.parse.bare.retrofit.ParseBatchAPI;
import demo.datasource.client.parse.bare.retrofit.ParseObjectsAPI;
import demo.util.TextUtils;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import solid.stream.Stream;

/**
 * Created on 12.11.2015.
 */
public class ParseClientReaderBare<T, P extends ParseObject> implements RESTClientReader<T>, ObjectConverter<RESTException, Throwable> {

	protected final String                  classEndpoint;
	protected final ParseObjectsAPI         apiObjects;
	protected final ParseBatchAPI           apiBatch;
	protected final Class<P>                parseClass;
	protected final GsonBasedJsonSerializer jsonSerializer;
	protected final ObjectConverter<T, P>   objectConverter;

	public ParseClientReaderBare(Class<P> parseClass, String classEndpoint) {
		Gson gson = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
				// TODO: perform additional JSON conversion configuration here.
			.create();
		RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
			// TODO: perform additional configuration here.
			.setConverter(new GsonConverter(gson));

		if (BuildConfig.DEBUG) {
			restAdapterBuilder
				.setLog(System.out::println)
				.setLogLevel(RestAdapter.LogLevel.FULL);
		}
		if (TextUtils.isEmpty(classEndpoint)) {
			classEndpoint = "classes/" + parseClass.getSimpleName();
		}
		this.classEndpoint = ParseConstants.URL_REST_BASE + classEndpoint;
		this.apiObjects = restAdapterBuilder
			.setEndpoint(this.classEndpoint)
			.build()
			.create(ParseObjectsAPI.class);
		this.apiBatch = restAdapterBuilder
			.setEndpoint(ParseConstants.URL_REST_BASE)
			.build()
			.create(ParseBatchAPI.class);
		this.parseClass = parseClass;
		this.jsonSerializer = new GsonBasedJsonSerializer(gson);
		this.objectConverter = ObjectConverterFactory
			.getInstance()
			.resolve(parseClass);
	}

	@Override
	public Collection<T> get(Criteria criteria) throws RESTException {
		try {
			return getImpl(criteria);
		} catch (Throwable throwable) {
			throw convert(throwable);
		}
	}

	private Collection<T> getImpl(Criteria criteria) {
		Iterable<P> rawData;
		{
			ParseCommand<ParseObjectsAPI, Map<String, JsonElement>> command = ParseCommandFactory
				.getInstance()
				.resolve(criteria, ParseObjectsAPI.class);

			Map<String, JsonElement> response = command.execute(apiObjects);

			if (response != null) {
				ParseQueryResponse queryResponse = mapResponse(
					response, ParseQueryResponse.class, jsonSerializer.getMapper());

				if (queryResponse.results != null) {
					rawData = Stream.stream(queryResponse.results)
						.map(value -> mapResponse(value, parseClass, jsonSerializer.getMapper()));
				} else if (!response.isEmpty()) {
					rawData = Collections.singleton(mapResponse(response, parseClass, jsonSerializer.getMapper()));
				} else {
					rawData = Collections.emptyList();
				}
			} else {
				rawData = Collections.emptyList();
			}
		}
		return Stream.stream(rawData)
			.map(objectConverter::convert)
			.toList();
	}

	protected static <E> E mapResponse(Map<String, JsonElement> src, Class<E> target, Gson mapper) {
		return ObjectMapper.fromMap(src, target, mapper::fromJson);
	}


	///////////////////////////////////////////////////////////////////////////////////////////////
	// Exceptions translation
	///////////////////////////////////////////////////////////////////////////////////////////////

	// TODO: define full-scale exception translation in here.

	@Override
	public RESTException convert(Throwable src) {
		return new RESTException(src.getMessage(), src, -1);
	}

	@Override
	public Throwable convertBack(RESTException target) {
		return new Throwable(target.getMessage(), target);
	}
}
