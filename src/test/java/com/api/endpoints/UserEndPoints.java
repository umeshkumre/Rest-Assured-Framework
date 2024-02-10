package com.api.endpoints;
import static io.restassured.RestAssured.given;
import com.api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUSer(User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when().post(Routes.post_url);
		return response;
		
	}
	
	public static Response getUSer(String userName)
	{
		Response response=given()
				.pathParam("username",userName)
		.when().get(Routes.get_url);
		return response;
		
	}
	
	public static Response updateUSer(String userName,User payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username",userName)
				.body(payload)
		.when().put(Routes.put_url);
		return response;
		
	}
	
	public static Response deleteUSer(String userName)
	{
		Response response=given()
				.pathParam("username",userName)
		.when().delete(Routes.delete_url);
		return response;
		
	}


}
