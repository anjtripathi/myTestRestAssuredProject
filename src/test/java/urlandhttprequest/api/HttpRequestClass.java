package urlandhttprequest.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.api.TestData;

public class HttpRequestClass {

	public static Response createUser(TestData data)
	{
		Response r = RestAssured.given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(data)
		.when().post(URLClassAPI.posturl);
		return r;
		
	}
	
	public static Response getUser(String username)
	{
		Response r = RestAssured.given()
		.accept(ContentType.JSON)
		.pathParam("username", username)
	    .when().get(URLClassAPI.geturl);
		return r;
		
	}
	
	public static Response putUser(String username, TestData data)
	{
		Response r = RestAssured.given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", username)
		.body(data)
	    .when().put(URLClassAPI.puturl);
		return r;
		
	}

	public static Response deleteUser(String username, TestData data)
	{
		Response r = RestAssured.given()
		.accept(ContentType.JSON)
		.pathParam("username", username)
	    .when().delete(URLClassAPI.deleteurl);
		return r;
		
	}
	
}
