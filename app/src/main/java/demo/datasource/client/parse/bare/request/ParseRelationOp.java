package demo.datasource.client.parse.bare.request;

import com.google.gson.annotations.SerializedName;

import demo.datasource.client.parse.bare.ParseRelation;

/**
 * Created on 29.10.2015.
 */
public class ParseRelationOp {

	public static final String OP_RELATION_ADD    = "AddRelation";
	public static final String OP_RELATION_REMOVE = "RemoveRelation";

	@SerializedName("__op")
	public String operation;

	public ParseRelation objects[];
}
