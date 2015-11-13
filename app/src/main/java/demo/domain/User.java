package demo.domain;

import java.util.Date;

import demo.util.CommonUtils;
import demo.util.Copyable;
import demo.util.TextUtils;

/**
 * Created on 25.10.2015.
 */
public class User extends DomainObject implements Copyable {

	String email;

	String nameFirst;

	String nameMiddle;

	String nameLast;

	String nickname;

	String avatarPictureUrl;

	Date dateOfBirth;

	GeoPoint placeOfBirth;

	public User() {
		//nothing to do
	}

	public User(String id) {
		super(id);
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameFirst() {
		return nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public String getNameMiddle() {
		return nameMiddle;
	}

	public void setNameMiddle(String nameMiddle) {
		this.nameMiddle = nameMiddle;
	}

	public String getNameLast() {
		return nameLast;
	}

	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatarPictureUrl() {
		return avatarPictureUrl;
	}

	public void setAvatarPictureUrl(String avatarPictureUrl) {
		this.avatarPictureUrl = avatarPictureUrl;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public GeoPoint getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(GeoPoint placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	@Override
	public User copy(boolean deep) {
		if (deep) {
			throw new UnsupportedOperationException("Deep copy not supported.");
		}
		User result = new User();
		{
			fillCopy(result);
		}
		return result;
	}

	@Override
	public User copy() {
		return copy(false);
	}

	protected void fillCopy(User copy) {
		copy.id = this.id;
		copy.email = this.email;
		copy.nameFirst = this.nameFirst;
		copy.nameMiddle = this.nameMiddle;
		copy.nameLast = this.nameLast;
		copy.nickname = this.nickname;
		copy.avatarPictureUrl = this.avatarPictureUrl;
		copy.dateOfBirth = this.dateOfBirth;
		copy.placeOfBirth = this.placeOfBirth;
	}


	@Override
	public int hashCode() {
		int hash = super.hashCode();
		{
			hash = calcHash(hash, email);
			hash = calcHash(hash, nameFirst);
			hash = calcHash(hash, nameMiddle);
			hash = calcHash(hash, nameLast);
			hash = calcHash(hash, nickname);
			hash = calcHash(hash, avatarPictureUrl);
			hash = calcHash(hash, dateOfBirth);
			hash = calcHash(hash, placeOfBirth);
		}
		return hash;
	}

	@Override
	protected boolean equalsImpl(Object o) {
		if (super.equalsImpl(o) && (o instanceof User)) {
			User other = (User)o;

			return TextUtils.equals(this.email, other.email)
				&& TextUtils.equals(this.nameFirst, other.nameFirst)
				&& TextUtils.equals(this.nameMiddle, other.nameMiddle)
				&& TextUtils.equals(this.nameLast, other.nameLast)
				&& TextUtils.equals(this.nickname, other.nickname)
				&& TextUtils.equals(this.avatarPictureUrl, other.avatarPictureUrl)
				&& CommonUtils.equals(this.dateOfBirth, other.dateOfBirth)
				&& CommonUtils.equals(this.placeOfBirth, other.placeOfBirth);
		}
		return false;
	}
}
