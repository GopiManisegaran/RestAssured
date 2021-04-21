package tests.rest;

import java.sql.SQLException;

import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.GenericFunctions;

public class TC008_InsertCustomersDetails extends GenericFunctions{
	
	
	
	@BeforeTest
	public void setValues() {

		testCaseName = "TC008_InsertCustomersDetails";
		testDescription = "";
		nodes = "Service";
		authors = "";
		category = "smoke";
		dataFileName = "TC008_InsertCustomersDetails";
		dataFileType = "Excel";
	}
	
	
	
	@Test(dataProvider = "fetchData")
	public void insertCustomersDetails(String jsonObject , String FirstName) throws ClassNotFoundException, SQLException, JSONException {		
		String endPoint = "customers";
		Response response =	postWithJsonAsBody(jsonObject, RestAssured.baseURI+endPoint);
		// Verify the response status code
		verifyResponseCode(response, 200);	
		// Verify the response time
	//	verifyResponseTime(response, 5000);
		
		verifyContentWithKey(response, "message", "SUCCESS");
		String FirstNameDB = getDataFromDB("SelectCustomerData");
		compareTwoData(FirstNameDB,FirstName);
		
	}


}
