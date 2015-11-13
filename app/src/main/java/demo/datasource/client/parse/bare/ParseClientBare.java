package demo.datasource.client.parse.bare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import demo.datasource.Criteria;
import demo.datasource.client.RESTClient;
import demo.datasource.client.RESTException;
import demo.datasource.client.parse.ParseConstants;
import demo.datasource.client.parse.bare.request.ParseBatchedOp;
import demo.datasource.client.parse.bare.request.ParseBatchedRequest;
import demo.datasource.client.parse.bare.response.ParseBatchedResponse;
import demo.util.CommonUtils;
import demo.util.TextUtils;
import solid.functions.SolidFunc1;
import solid.stream.Stream;

/**
 * Created on 25.10.2015.
 */
class ParseClientBare<T, P extends ParseObject> extends ParseClientReaderBare<T, P> implements RESTClient<T> {

	public ParseClientBare(Class<P> parseClass, String classEndpoint) {
		super(parseClass, classEndpoint);
	}


	@Override
	public Collection<T> post(Iterable<T> items) throws RESTException {
		try {
			return batch(items, this::postImpl,
				ParseConstants.PARSE_BATCHED_OPERATIONS_COUNT_MAX);
		} catch (Throwable throwable) {
			throw convert(throwable);
		}
	}

	private Iterable<T> postImpl(Iterable<T> items) {
		List<P> rawData;
		{
			List<P> parseObjects = Stream.stream(items)
				.map(objectConverter::convertBack)
				.toList();

			List<ParseBatchedResponse> response;
			{
				ParseBatchedRequest request = new ParseBatchedRequest();
				{
					request.requests = Stream.stream(parseObjects)
						.map(value -> {
							ParseBatchedOp result = new ParseBatchedOp();
							{
								result.body = ObjectMapper.toMap(value);
								result.path = classEndpoint;
								result.method = TextUtils.isEmpty(value.id)
									? "POST" : "PUT";
							}
							return result;
						})
						.toList();
				}
				response = apiBatch.batchObjects(request);

				//noinspection UnusedAssignment
				request = null;
			}
			rawData = new ArrayList<>(parseObjects.size());

			//noinspection unchecked
			for (int i = 0, imax = parseObjects.size(); i < imax; ++i) {
				ParseBatchedResponse responseBody = response.get(i);

				if (responseBody.error == null) {
					P source = mapResponse(responseBody.success, parseClass, jsonSerializer.getMapper());
					P target = parseObjects.get(i);
					{
						target.id = source.id;
					}
					rawData.add(target);
				}
			}
		}
		return Stream.stream(rawData)
			.map(objectConverter::convert);
	}


	@Override
	public Collection<T> delete(Criteria criteria) throws RESTException {
		return delete(get(criteria));
	}

	@Override
	public Collection<T> delete(Iterable<T> items) throws RESTException {
		try {
			return batch(items, this::deleteImpl,
				ParseConstants.PARSE_BATCHED_OPERATIONS_COUNT_MAX);
		} catch (Throwable throwable) {
			throw convert(throwable);
		}
	}

	private Iterable<T> deleteImpl(Iterable<T> items) {
		List<T> rawData;
		{
			List<ParseBatchedResponse> response;
			{
				ParseBatchedRequest request = new ParseBatchedRequest();
				{
					request.requests = Stream.stream(items).map(value -> {
						ParseBatchedOp result = new ParseBatchedOp();
						{
							P parseObject = objectConverter.convertBack(value);

							result.path = classEndpoint + "/" + parseObject.id;
							result.method = "DELETE";
						}
						return result;
					}).toList();
				}
				response = apiBatch.batchObjects(request);

				//noinspection UnusedAssignment
				request = null;
			}
			List<T> itemsList = CommonUtils.asImmutableList(items);
			rawData = new ArrayList<>(itemsList.size());

			//noinspection unchecked
			for (int i = 0, imax = response.size(); i < imax; ++i) {
				ParseBatchedResponse responseBody = response.get(i);

				if (responseBody.error == null) {
					rawData.add(itemsList.get(i));
				}
			}
		}
		return rawData;
	}


	private static <T> Collection<T> batch(Iterable<T> items, SolidFunc1<Iterable<T>, Iterable<T>> processorFunc, int windowSize) {
		Stream<T> outputStream = Stream.stream(Collections.<T>emptyList());

		for (Stream<T> inputStream = Stream.stream(items);
			 inputStream.iterator().hasNext(); inputStream = inputStream.skip(windowSize))
		{
			outputStream = outputStream.merge(processorFunc.call(inputStream.take(windowSize)));
		}
		return outputStream.toList();
	}
}
