package demo.util;

/**
 * An interface that provides copying operators for a class.
 *
 * For convenience, please use return type covariance in order to provide
 * more concrete return type for copy methods.
 */
public interface Copyable {

	/**
	 * Provides copy operator.
	 *
	 * @param deep selects whether an operator makes a deep or shallow copy of current object.
	 * @return A copy of this current object, but may throw {@link java.lang.UnsupportedOperationException}
	 * if either 'deep' or 'shallow' copying method is not supported.
	 * Implementation should support at least one method to be correct. This is a MUST.
	 */
	Object copy(boolean deep);

	/**
	 * Provides a default copying operator.
	 *
	 * This may be either 'deep' or 'shallow' copy of an object,
	 * which should be documented appropriately by implementor.
	 *
	 * @return A copy of this current object, guarantees to obtain a copied object instance.
	 */
	Object copy();
}
