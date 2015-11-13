package demo.datasource.client.parse.bare;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import demo.datasource.client.parse.bare.ObjectMapper.JsonSerializer;

/**
 * Created on 29.10.2015.
 */
class GsonBasedJsonSerializer implements JsonSerializer {

	private final Gson mapper;

	public Gson getMapper() {
		return mapper;
	}


	public GsonBasedJsonSerializer(Gson mapper) {
		this.mapper = mapper;
	}

	@Override
	public String toJson(Object value, Type type) {
		return mapper.toJson(value, type);
	}

	@Override
	public Object fromJson(String json, Type type) {
		return mapper.fromJson(json, type);
	}
}
