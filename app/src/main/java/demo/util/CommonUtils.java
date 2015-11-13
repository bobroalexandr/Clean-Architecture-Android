package demo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created on 25.10.2015.
 */
public final class CommonUtils {

	public static boolean equals(Object a, Object b) {
		return a == b || a != null && b != null && a.equals(b);
	}


	public static <T> ImmutableList<T> asImmutableList(Iterable<T> items) {
		if (items instanceof List) {
			return new ImmutableList<>((List<T>)items);
		} else {
			List<T> list = new ArrayList<T>();

			for (T item : items) {
				list.add(item);
			}
			return new ImmutableList<>(list);
		}
	}

	@SafeVarargs
	public static <T> ImmutableList<T> asImmutableList(T... items) {
		return asImmutableList(Arrays.asList(items));
	}


	public static <K, V> Map<K, V> composeMap(Iterable<Map.Entry<K, V>> entrySet) {
		return entrySet != null
			? composeMapImpl(entrySet, new HashMap<K,V>()) : null;
	}

	public static <K, V> Map<K, V> composeMap(Iterable<Map.Entry<K, V>> entrySet, Map<K, V> target) {
		if (target == null) {
			throw new IllegalArgumentException("target shouldn't be 'null'.");
		}
		return composeMapImpl(entrySet, target);
	}

	private static <K, V> Map<K, V> composeMapImpl(Iterable<Map.Entry<K, V>> entrySet, Map<K, V> target) {
		target.clear();

		for(Map.Entry<K, V> entry : entrySet) {
			target.put(entry.getKey(), entry.getValue());
		}
		return target;
	}


	private CommonUtils() {
		/* Prevent instantiating */
	}
}
