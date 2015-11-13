package demo.datasource.client.parse.bare.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 28.10.2015.
 */
public class ParseCounterIncrementOp {
	@SerializedName("__op")
	public final String operation = "Increment";

	public int amount;
}
