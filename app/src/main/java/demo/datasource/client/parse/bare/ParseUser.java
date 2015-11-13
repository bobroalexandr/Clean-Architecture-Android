package demo.datasource.client.parse.bare;

import java.util.Date;

import demo.datasource.client.parse.ObjectConverter;
import demo.domain.GeoPoint;
import demo.domain.User;

/**
 * Created on 31.10.2015.
 */
class ParseUser extends ParseObject {

	String email;
	String firstName;
	String middleName;
	String lastName;
	String nickname;
	Date dateOfBirth;
	ParseGeoPoint placeOfBirth;


	public static class Converter implements ObjectConverter<User, ParseUser> {

		@Override
		public User convert(ParseUser src) {
			User result = new User(src.id);
			{
				result.setEmail(src.email);
				result.setNameFirst(src.firstName);
				result.setNameMiddle(src.middleName);
				result.setNameLast(src.lastName);
				result.setNickname(src.nickname);
				result.setDateOfBirth(src.dateOfBirth);
				{
					ParseGeoPoint parseGeoPoint = src.placeOfBirth;

					if (parseGeoPoint != null) {
						result.setPlaceOfBirth(new GeoPoint(
							parseGeoPoint.latitude,
							parseGeoPoint.longitude
						));
					}
				}
			}
			return result;
		}

		@Override
		public ParseUser convertBack(User target) {
			ParseUser result = new ParseUser();
			{
				result.id = target.getId();
				result.email = target.getEmail();
				result.firstName = target.getNameFirst();
				result.middleName = target.getNameMiddle();
				result.lastName = target.getNameLast();
				result.nickname = target.getNickname();
				result.dateOfBirth = target.getDateOfBirth();
				{
					GeoPoint geoPoint = target.getPlaceOfBirth();

					if (geoPoint != null) {
						ParseGeoPoint parseGeoPoint = new ParseGeoPoint();
						{
							parseGeoPoint.latitude = target.getPlaceOfBirth().getLatitude();
							parseGeoPoint.longitude = target.getPlaceOfBirth().getLongitude();
						}
						result.placeOfBirth = parseGeoPoint;
					}
				}
			}
			return result;
		}
	}
}
