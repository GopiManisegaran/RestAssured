package tests.rest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestGet {


	public Response getData() {
		RestAssured.baseURI = "https://reqres.in";
		return RestAssured.given().when()
		.get("/api/users?page=2");	}		
		
	@Test
	public void ge() {
		String r = getData().getBody().asString();
	System.out.println("ress"+r);
	}
}
