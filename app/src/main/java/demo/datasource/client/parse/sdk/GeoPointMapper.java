package demo.datasource.client.parse.sdk;

import com.parse.ParseGeoPoint;

import demo.domain.GeoPoint;

/**
 * Created on 29.10.2015.
 */
class GeoPointMapper {

	public static GeoPoint fromParseObject(ParseGeoPoint parseGeoPoint) {
		double latitude = parseGeoPoint.getLatitude();
		double longitude = parseGeoPoint.getLongitude();
		return new GeoPoint(latitude, longitude);
	}

	public static ParseGeoPoint toParseObject(GeoPoint geoPoint) {
		double latitude = geoPoint.getLatitude();
		double longitude = geoPoint.getLongitude();
		return new ParseGeoPoint(latitude, longitude);
	}
}
