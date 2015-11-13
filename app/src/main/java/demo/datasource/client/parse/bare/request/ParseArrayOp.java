package demo.datasource.client.parse.bare.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 29.10.2015.
 */
public class ParseArrayOp {

	public static final String OP_ADD = "Add";
	public static final String OP_ADD_UNIQUE = "AddUnique";
	public static final String OP_REMOVE = "Remove";

	@SerializedName("__op")
	public String operation;

	public Object objects[];
}
