package tests.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import groovyjarjarantlr.collections.List;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC001_EditDataSetName extends RESTAssuredBase {
	@BeforeTest
	public void setValues() {

		testCaseName = "Create a new Incident";
		testDescription = "Login, Create a new Incident and Logout";
		nodes = "Incident";
		authors = "Babu";
		category = "smoke";
		dataFileName = "TC002";
		dataFileType = "JSON";
	}
	
	@Test(dataProvider = "fetchData")
	public void createIncident(File file) {		
		
		
	
		
	   // Map<String, String> header = new HashMap<String, String>();
		//header.put("Content-Type", "application/json");
		// Post the request
		//Response response = putWithHeaderAndBodyParam(header,file, RestAssured.baseURI); 
		
		Response response =  postWithUrlandBodyAsFile(RestAssured.baseURI,file); 
		
		//Verify the Content by Specific Key
		verifyContentWithKey(response, "statusMessage", "SUCCESS");
		
		// Verify the Content type
	//	verifyContentType(response, "JSON");
		
		// Verify the response status code
		verifyResponseCode(response, 200);	
		
		// Verify the response time
	//	verifyResponseTime(response, 5000);
		
		
		
	}

}
