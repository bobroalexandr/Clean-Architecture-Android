package demo.datasource.client.parse.bare;

import com.google.gson.JsonElement;

import java.util.Map;

import demo.datasource.Criteria;
import demo.datasource.client.parse.bare.retrofit.ParseObjectsAPI;

/**
 * Created on 31.10.2015.
 */
class ParseObjectAPICriteriaMapper implements ParseCommandFactory.CriteriaMapper<ParseObjectsAPI> {

	@Override
	public ParseCommand<ParseObjectsAPI, ?> resolve(Criteria criteria) {
		if (criteria == null) {
			return new GetAllCommand();
		}
		throw new UnsupportedOperationException("Can't handle such a criteria currently.");
	}

	private static final class GetAllCommand implements ParseCommand<ParseObjectsAPI, Map<String, JsonElement>> {

		@Override
		public Map<String, JsonElement> execute(ParseObjectsAPI api) {
			return api.getObject("", null);
		}
	}
}
