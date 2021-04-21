package tests.rest;

import java.lang.reflect.InvocationTargetException;

import java.sql.SQLException;

import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.GenericFunctions;

public class TC004_GetCategories extends GenericFunctions{
	
	
	
	@BeforeTest
	public void setValues() {

		testCaseName = "TC004_GetCategories";
		testDescription = "";
		nodes = "Service";
		authors = "";
		category = "smoke";
		dataFileName = "TC004_GetCategories";
		dataFileType = "Excel";
	}
	
	
	
	@Test(dataProvider = "fetchData")
	public void getCategories(String URL,String QueryKey) throws ClassNotFoundException, SQLException, JSONException, ArithmeticException, InvocationTargetException {		

		Response response =get(URL);
		String actual = response.asString();
		compareTwoJsonObject(QueryKey, actual);

		// Verify the response status code
		verifyResponseCode(response, 200);	
		
		// Verify the response time
		verifyResponseTime(response, 5000);
		
		
		
	}


}
