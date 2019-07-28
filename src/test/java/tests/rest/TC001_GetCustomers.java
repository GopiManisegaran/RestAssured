package tests.rest;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class TC001_GetCustomers extends RESTAssuredBase{
		
	@BeforeTest
	public void setValues() {

		testCaseName = "TC001_Get Customers";
		testDescription = "";
		nodes = "Service";
		authors = "";
		category = "smoke";
		dataFileName = "TC001_Get Customers";
		dataFileType = "Excel";
	}
	
	
	@Test//(dataProvider = "fetchData")
	
	public void getCustomers() throws ClassNotFoundException, SQLException, JSONException, AssertionError {		
		String endPoint = "customers";
		
		Response response =get(RestAssured.baseURI+endPoint);
		String actual = response.asString();
		// Verify the response status code
		verifyResponseCode(response, 200);	
		compareTwoJsonObject("GetCustomerList", actual);
	
	
	
		
		// Verify the response time
	//	verifyResponseTime(response, 5000);
		
	}

}
