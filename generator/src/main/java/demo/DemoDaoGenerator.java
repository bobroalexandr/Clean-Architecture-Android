package demo;

import java.io.File;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class DemoDaoGenerator {

	public static void main(String[] args) throws Exception {
		Schema schemas[] = new Schema[] {
			makeSchemaPrimary(BuildConfig.INPUT_PACKAGE_ROOT),
//			makeSchemaSecondary(BuildConfig.INPUT_PACKAGE_ROOT + ".secondary")
		};
		DaoGenerator generator = new DaoGenerator();
		{
			for (Schema schema : schemas) {
				generator.generateAll(schema, ".." + File.separator + BuildConfig.OUTPUT_PROJECT_PATH);
			}
		}
	}

	private static Schema makeSchemaPrimary(String targetPackage) {
		Schema schema = new Schema(1, targetPackage);
		{
			// Domain objects (Storage tables)
			Entity entityUser = schema.addEntity("User");
			Entity entityPost = schema.addEntity("Post");
			Entity entityLike = schema.addEntity("Like");

			// Relation proxies
			Entity entityUserToUserJoinData = schema.addEntity("UserToUser");
			Entity entityUserToPostJoinData = schema.addEntity("UserToPost");
			Entity entityUserToLikeJoinData = schema.addEntity("UserToLike");
			Entity entityPostToLikeJoinData = schema.addEntity("PostToLike");

			// User to User join data entity
			Property propertyUserToUserJoinDataIdUser;
			Property propertyUserToUserJoinDataIdFriend;
			{
				entityUserToUserJoinData.addStringProperty("id").primaryKey();
				propertyUserToUserJoinDataIdUser = entityUserToUserJoinData.addStringProperty("idUser").getProperty();
				propertyUserToUserJoinDataIdFriend = entityUserToUserJoinData.addStringProperty("idFriend").getProperty();

				entityUserToUserJoinData.addToOne(entityUser, propertyUserToUserJoinDataIdFriend, "friend");
			}
			// User to Post join data entity
			Property propertyUserToPostJoinDataIdUser;
			{
				entityUserToPostJoinData.addStringProperty("id").primaryKey();
				propertyUserToPostJoinDataIdUser = entityUserToPostJoinData.addStringProperty("idUser").getProperty();
				entityUserToPostJoinData.addStringProperty("idPost").getProperty();
			}
			// User to Like join data entity
			Property propertyUserToLikeJoinDataIdUser;
			{
				entityUserToLikeJoinData.addStringProperty("id").primaryKey();
				propertyUserToLikeJoinDataIdUser = entityUserToLikeJoinData.addStringProperty("idUser").getProperty();
				entityUserToLikeJoinData.addStringProperty("idLike").getProperty();
			}
			// Post to Like join data entity
			Property propertyPostToLikeJoinDataIdPost;
			{
				entityPostToLikeJoinData.addStringProperty("id").primaryKey();
				propertyPostToLikeJoinDataIdPost = entityPostToLikeJoinData.addStringProperty("idPost").getProperty();
				entityPostToLikeJoinData.addStringProperty("idLike").getProperty();
			}

			// User entity.
			{
				entityUser.addStringProperty("id").primaryKey();
				entityUser.addStringProperty("email");
				entityUser.addStringProperty("firstName");
				entityUser.addStringProperty("middleName");
				entityUser.addStringProperty("lastName");
				entityUser.addStringProperty("nickname");
				entityUser.addDateProperty("dateOfBirth");
				entityUser.addStringProperty("placeOfBirth");
				entityUser.addDateProperty("createdAt");
				entityUser.addDateProperty("updatedAt");

				entityUser.addToMany(entityUserToUserJoinData, propertyUserToUserJoinDataIdUser);
				entityUser.addToMany(entityUserToPostJoinData, propertyUserToPostJoinDataIdUser);
				entityUser.addToMany(entityUserToLikeJoinData, propertyUserToLikeJoinDataIdUser);
			}
			// Post entity.
			Property propertyPostIdAuthor;
			{
				entityPost.addStringProperty("id").primaryKey();
				propertyPostIdAuthor = entityPost.addStringProperty("authorId").getProperty();
				entityPost.addStringProperty("title");
				entityPost.addStringProperty("bodyText");
				entityPost.addStringProperty("bodyPictureUrl");
				entityPost.addDateProperty("createdAt");
				entityPost.addDateProperty("updatedAt");

				entityPost.addToOne(entityUser, propertyPostIdAuthor, "author");
				entityPost.addToMany(entityPostToLikeJoinData, propertyPostToLikeJoinDataIdPost);
			}
			// Like entity.
			Property propertyLikeIdUser;
			Property propertyLikeIdPost;
			{
				entityLike.addStringProperty("id").primaryKey();
				propertyLikeIdUser = entityLike.addStringProperty("userId").getProperty();
				propertyLikeIdPost = entityLike.addStringProperty("postId").getProperty();
				entityLike.addDateProperty("createdAt");
				entityLike.addDateProperty("updatedAt");

				entityLike.addToOne(entityUser, propertyLikeIdUser, "user");
				entityLike.addToOne(entityPost, propertyLikeIdPost, "post");
			}
		}
		return schema;
	}

//	private static Schema makeSchemaSecondary(String targetPackage) {
//		Schema schema = new Schema(1, targetPackage);
//		{
//			// TODO: Add secondary schema here.
//		}
//		return schema;
//	}
}
