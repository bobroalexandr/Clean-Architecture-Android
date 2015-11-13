package demo.datasource.client.parse.bare;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created on 14/08/15.
 */
final class ObjectMapper {

	public interface JsonSerializer {

		String toJson(Object value, Type type);

		Object fromJson(String json, Type type);
	}


	public interface To<T> {

		T map(Object field, Type type);
	}

	public static Map<String, Object> toMap(Object src) {
		return toMapImpl(src, (field, type) -> field);
	}

	public static Map<String, String> toMap(Object src, final JsonSerializer mapper) {
		if (mapper == null) {
			throw new IllegalArgumentException("mapper can't be 'null'.");
		}
		return toMap(src, mapper::toJson);
	}

	public static <T> Map<String, T> toMap(Object src, To<T> mapper) {
		if (mapper == null) {
			throw new IllegalArgumentException("mapper can't be 'null'");
		}
		return toMapImpl(src, mapper);
	}


	public interface From<T> {

		Object map(T value, Type type);
	}

	public static <R> R fromMap(Map<String, Object> map, Class<R> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("clazz can't be 'null'.");
		}
		try {
			return fromMapImpl(map, clazz.newInstance(), (value, type) -> value);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static <R> R fromMap(Map<String, String> map, Class<R> clazz, final JsonSerializer mapper) {
		if (clazz == null) {
			throw new IllegalArgumentException("clazz can't be 'null'.");
		}
		if (mapper == null) {
			throw new IllegalArgumentException("mapper can't be 'null'.");
		}
		try {
			return fromMapImpl(map, clazz.newInstance(), mapper::fromJson);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T, R> R fromMap(Map<String, T> map, Class<R> clazz, From<T> mapper) {
		if (clazz == null) {
			throw new IllegalArgumentException("clazz can't be 'null'.");
		}
		if (mapper == null) {
			throw new IllegalArgumentException("mapper can't be 'null'.");
		}
		try {
			return fromMapImpl(map, clazz.newInstance(), mapper);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static <R> R fromMap(Map<String, Object> map, R instance) {
		if (instance == null) {
			throw new IllegalArgumentException("instance can't be 'null'.");
		}
		return fromMapImpl(map, instance, (value, type) -> value);
	}

	public static <R> R fromMap(Map<String, String> map, R instance, final JsonSerializer mapper) {
		if (instance == null) {
			throw new IllegalArgumentException("instance can't be 'null'.");
		}
		if (mapper == null) {
			throw new IllegalArgumentException("mapper can't be 'null'.");
		}
		return fromMapImpl(map, instance, mapper::fromJson);
	}

	public static <T, R> R fromMap(Map<String, T> map, R instance, From<T> mapper) {
		if (instance == null) {
			throw new IllegalArgumentException("instance can't be 'null'.");
		}
		if (mapper == null) {
			throw new IllegalArgumentException("mapper can't be 'null'.");
		}
		return fromMapImpl(map, instance, mapper);
	}


	private static <T> Map<String, T> toMapImpl(Object src, To<T> mapper) {
		if (src != null) {
			List<FieldInfo> fields = getFields(src.getClass());

			if (!fields.isEmpty()) {
				Map<String, T> result = new HashMap<>(fields.size());

				for (FieldInfo field : fields) {
					try {
						Object value = field.getValue(src);

						result.put(field.getName(), mapper.map(value, field.getType()));
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
				return result;
			}
		}
		return new HashMap<>(0);
	}

	private static <T, R> R fromMapImpl(Map<String, T> map, R instance, From<T> mapper) {
		if (map != null) {
			List<FieldInfo> fields = getFields(instance.getClass());

			for (FieldInfo field : fields) {
				try {
					field.setValue(instance, mapper.map(map.get(field.getName()), field.getType()));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return instance;
	}


	private static final Map<Class, List<FieldInfo>> FIELDS_CACHE = new HashMap<>();

	private static List<FieldInfo> getFields(Class src) {
		List<FieldInfo> result = FIELDS_CACHE.get(src);

		if (result == null) {
			synchronized (FIELDS_CACHE) {
				result = FIELDS_CACHE.get(src);

				if (result == null) {
					FIELDS_CACHE.put(src, result = extractFields(src));
				}
			}
		}
		return result;
	}

	private static List<FieldInfo> extractFields(Class src) {
		List<FieldInfo> result = new LinkedList<>();

		for (Field field : src.getFields()) {
			final int modifiers = field.getModifiers();

			if (!(Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers))) {
				SerializedName name = field.getAnnotation(SerializedName.class);

				if (!Modifier.isPublic(modifiers)) {
					if (name != null) {
						field.setAccessible(true);
					} else {
						continue;
					}
				}
				result.add(new FieldInfo(field, name != null ? name.value() : null));
			}
		}
		return result;
	}

	private static final class FieldInfo {

		private final Field field;
		private final String name;

		public FieldInfo(Field field, String name) {
			this.field = field;
			this.name = name;
		}


		public String getName() {
			return name != null ? name : field.getName();
		}

		public Type getType() {
			return field.getGenericType();
		}


		public void setValue(Object receiver, Object value) throws IllegalAccessException {
			field.set(receiver, value);
		}

		public Object getValue(Object receiver) throws IllegalAccessException {
			return field.get(receiver);
		}
	}


	private ObjectMapper() {
		/* Prevent instantiating */
	}
}
