package demo.datasource.client.parse.bare;

/**
 * Created on 31.10.2015.
 */
interface ParseCommand<A, R> {

	R execute(A api);
}
