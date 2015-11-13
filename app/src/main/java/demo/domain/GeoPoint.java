package demo.domain;

import demo.util.Copyable;

/**
 * Created on 25.10.2015.
 */
public final class GeoPoint implements Copyable {

	final double latitude;

	final double longitude;


	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}


	public GeoPoint(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}



	@Override
	public GeoPoint copy(boolean deep) {
		return new GeoPoint(this.latitude, this.longitude);
	}

	@Override
	public GeoPoint copy() {
		return copy(false);
	}


	@Override
	public int hashCode() {
		int hash = 17;
		{
			hash = DomainObject.calcHash(hash, Double.doubleToLongBits(latitude));
			hash = DomainObject.calcHash(hash, Double.doubleToLongBits(longitude));
		}
		return hash;
	}

	@Override
	public boolean equals(Object o) {
		final boolean referenceEquals = this == o;

		if (!referenceEquals && (o instanceof GeoPoint)) {
			GeoPoint other = (GeoPoint)o;

			return Double.compare(this.latitude, other.latitude) == 0
				&& Double.compare(this.longitude, other.longitude) == 0;
		}
		return referenceEquals;
	}
}
