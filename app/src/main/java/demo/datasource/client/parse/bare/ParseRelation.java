package demo.datasource.client.parse.bare;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 29.10.2015.
 */
public class ParseRelation {

	@SerializedName("__type")
	public String type;

	public String className;

	public String objectId;
}
