package demo.util;

import java.util.regex.Pattern;

public final class TextUtils {

	/**
	 * Email address validation expression.
	 *
	 * Useful capturing group indices:
	 * 1 - local part  (usually up to 64 symbols)
	 * 3 - domain part (usually up to 256 symbols)
	 */
	public static final Pattern PATTERN_EMAIL_ADDRESS = Pattern.compile(
		"^(\\w+?(\\.[\\w\\-\\+]+)*)@([\\w&&[^_]]+?(\\.[.\\w&&[^_]]+)*(\\.[.\\w&&[^_]]{2,})|\\[\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}\\])$"
	);


	/**
	 * E.164 ITU Phone number validation expression.
	 *
	 * Useful capturing group indices:
	 * 1 - Country code (CC ITU-T) 	(usually 1 to 3 digits)
	 * 2 - National number			(usually 7 to 14 digits)
	 */
	public static final Pattern PATTERN_E164_PHONE_NUMBER = Pattern.compile(
		"^\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)([1-9]\\d{6,13})$"
	);


	public static int safeLength(CharSequence s) {
		return s != null ? s.length() : 0;
	}

	public static boolean isEmpty(CharSequence s) {
		return s == null || s.length() <= 0;
	}

	public static boolean equals(CharSequence a, CharSequence b) {
		final boolean referenceEquals = a == b;

		if (!referenceEquals) {
			int length;
			if (a != null && b != null && (length = a.length()) == b.length()) {
				if (a instanceof String && b instanceof String) {
					return a.equals(b);
				} else {
					for (int i = 0; i < length; i++) {
						if (a.charAt(i) != b.charAt(i)) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return referenceEquals;
	}

	private TextUtils() {
		/* Prevent instantiating */
	}
}

