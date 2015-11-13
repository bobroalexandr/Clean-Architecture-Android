package demo.datasource.client.parse.bare;

import com.google.gson.GsonBuilder;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import demo.domain.UserCredentials;
import demo.util.CommonUtils;
import demo.util.TextUtils;

import static org.junit.Assert.*;

/**
 * Created on 27.10.2015.
 */
public class ObjectMapperTest {

	static final class Entity {

		public double fieldPrimitive;

		public Nested fieldReference;

		public Collection<String> fieldCollection;


		@Override
		public boolean equals(Object o) {
			boolean equals = super.equals(o);

			if (!equals && o instanceof Entity) {
				Entity other = (Entity)o;

				boolean collectionEquals = CommonUtils.equals(this.fieldCollection, other.fieldReference)
					|| (this.fieldCollection != null && other.fieldCollection != null
						&& this.fieldCollection.size() == other.fieldCollection.size());

				if (collectionEquals) {
					for (Iterator<String> iterA = this.fieldCollection.iterator(),
						 iterB = other.fieldCollection.iterator();
						 iterA.hasNext();
						) {

						if (!TextUtils.equals(iterA.next(), iterB.next())) {
							return false;
						}
					}
				}
				return collectionEquals
					&& this.fieldPrimitive == other.fieldPrimitive
					&& CommonUtils.equals(this.fieldReference, other.fieldReference);
			}
			return equals;
		}
	}

	static final class Nested {

		public String fieldString;

		public char fieldChar;


		@Override
		public boolean equals(Object o) {
			boolean equals = super.equals(o);

			if (!equals && o instanceof Nested) {
				Nested other = (Nested)o;

				return TextUtils.equals(this.fieldString, other.fieldString)
					&& this.fieldChar == other.fieldChar;
			}
			return equals;
		}
	}

	private static ObjectMapper.JsonSerializer mapper;

	@BeforeClass
	public static void SetUpClass() {
		mapper = new GsonBasedJsonSerializer(new GsonBuilder().create());
	}

	@Test
	public void validationTest() {
		try {
			ObjectMapper.<UserCredentials>toMap(null, mapper);

			fail();
		} catch (Throwable t) {
			/* Good to go */
		}

		try {
			ObjectMapper.fromMap(null, UserCredentials.class, mapper);

			fail();
		} catch (Throwable t) {
			/* Good to go */
		}
	}

	@Test
	public void defaultMapperTest() {
		Entity entityExpected = new Entity();
		{
			Nested nested = new Nested();
			{
				nested.fieldChar = '@';
				nested.fieldString = "Nested object's string field.";
			}
			entityExpected.fieldCollection = Arrays.asList("One", "Two", "Three", "Four", "Five");
			entityExpected.fieldPrimitive = Math.PI;
			entityExpected.fieldReference = nested;
		}
		Map<String, Object> serial = ObjectMapper.toMap(entityExpected);

		assertNotNull(serial);
		assertTrue(serial.size() == Entity.class.getFields().length);

		Entity entityCurrent = ObjectMapper.fromMap(serial, Entity.class);

		assertEquals(entityExpected, entityCurrent);
	}

	@Test
	public void jsonMapperTest() {
		Entity entityExpected = new Entity();
		{
			Nested nested = new Nested();
			{
				nested.fieldChar = '@';
				nested.fieldString = "Nested object's string field.";
			}
			entityExpected.fieldCollection = Arrays.asList("One", "Two", "Three", "Four", "Five");
			entityExpected.fieldPrimitive = Math.PI;
			entityExpected.fieldReference = nested;
		}
		Map<String, String> serial = ObjectMapper.toMap(entityExpected, mapper);

		assertNotNull(serial);
		assertTrue(serial.size() == Entity.class.getFields().length);

		Entity entityCurrent = ObjectMapper.fromMap(serial, Entity.class, mapper);

		assertEquals(entityExpected, entityCurrent);
	}
}
