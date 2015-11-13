package demo.datasource.client.parse.sdk;

import com.parse.ParseUser;

import demo.datasource.client.parse.ObjectConverter;
import demo.domain.User;

/**
 * Created on 29.10.2015.
 */
public final class UserConverter implements ObjectConverter<User, ParseUser> {

	private static final String NICKNAME = "nickname";
	private static final String FIRST_NAME = "firstName";
	private static final String MIDDLE_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String DATE_OF_BIRTH = "dateOfBirth";
	private static final String PLACE_OF_BIRTH = "placeOfBirth";


	@Override
	public User convert(ParseUser parseUser) {
		User user = new User(parseUser.getObjectId());
		user.setEmail(parseUser.getEmail());
		user.setNickname(parseUser.getString(NICKNAME));
		user.setNameFirst(parseUser.getString(FIRST_NAME));
		user.setNameMiddle(parseUser.getString(MIDDLE_NAME));
		user.setNameLast(parseUser.getString(LAST_NAME));
		user.setDateOfBirth(parseUser.getDate(DATE_OF_BIRTH));
		user.setPlaceOfBirth(GeoPointMapper.fromParseObject(parseUser.getParseGeoPoint(PLACE_OF_BIRTH)));
		return user;
	}

	@Override
	public ParseUser convertBack(User user) {
		ParseUser parseUser = new ParseUser();
		parseUser.setObjectId(user.getId());
		parseUser.setEmail(user.getEmail());
		parseUser.put(NICKNAME, user.getNickname());
		parseUser.put(FIRST_NAME, user.getNameFirst());
		parseUser.put(MIDDLE_NAME, user.getNameMiddle());
		parseUser.put(LAST_NAME, user.getNameLast());
		parseUser.put(DATE_OF_BIRTH, user.getDateOfBirth());
		parseUser.put(PLACE_OF_BIRTH, GeoPointMapper.toParseObject(user.getPlaceOfBirth()));
		return parseUser;
	}
}
