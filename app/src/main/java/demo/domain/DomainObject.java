package demo.domain;

import demo.util.TextUtils;

/**
 * Created on 25.10.2015.
 */
public class DomainObject {

	String id;

	public String getId() {
		return id;
	}

	public DomainObject() {
		//nothing to do
	}

	public DomainObject(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return calcHash(17, id);
	}

	@Override
	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	public boolean equals(Object o) {
		return this == o || equalsImpl(o);
	}

	/**
	 * Equality check implementation method base. The main purpose is to get rid of reference equality check,
	 * which allows to concentrate on in-domain equality test implementation.
	 *
	 * @param o An object to check whether this instance is equal or not.
	 * @return {@code true} if 'o' object is equal to this one; {@code false} otherwise.
	 */
	protected boolean equalsImpl(Object o) {
		if (/*super.equalsImpl(o) && */o instanceof DomainObject) {
			DomainObject other = (DomainObject)o;

			return TextUtils.equals(this.id, other.id);
		}
		return false;
	}


	/**
	 * Calculates an updated hash value.
	 *
	 * This method is based on Josh Bloch's approach that is described in his
	 * {@link "http://www.amazon.com/dp/0321356683/?tag=stackoverfl08-20" 'Effective Java'}.
	 *
	 * @param hash current hash value, that will be updated.
	 * @param fieldHash an incoming hash value, the current hash value will be updated with.
	 * @return An updated value, that can be sequenced down the hashing process.
	 */
	static int calcHash(int hash, int fieldHash) {
		return (hash * 23) ^ fieldHash;
	}

	/**
	 * Calculates an updated hash value, but for {@code long} input parameter.
	 *
	 * @see demo.domain.DomainObject#calcHash(int, int).
	 */
	static int calcHash(int hash, long fieldId) {
		return (hash * 23) ^ ((int)(fieldId >>> 32) ^ (int)fieldId);
	}

	/**
	 * Calculates an updated hash value, but for an object instance input parameter.
	 *
	 * @see demo.domain.DomainObject#calcHash(int, int).
	 */
	static int calcHash(int hash, Object fieldValue) {
		return fieldValue != null ? calcHash(hash, fieldValue.hashCode()) : hash;
	}
}
