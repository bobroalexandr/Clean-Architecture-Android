package demo.datasource.client.parse;

/**
 * Created on 29.10.2015.
 */
public interface ObjectConverter<T, S> {

	T convert(S src);

	S convertBack(T target);
}
