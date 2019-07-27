package tests.rest;

import java.sql.SQLException;

import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class TC010_DeleteCustomersDetails extends RESTAssuredBase{
	
	
	
	@BeforeTest
	public void setValues() {

		testCaseName = "TC010_DeleteCustomersDetails";
		testDescription = "";
		nodes = "Service";
		authors = "";
		category = "smoke";
		dataFileName = "TC010_DeleteCustomersDetails";
		dataFileType = "Excel";
	}
	
	
	
	@Test(dataProvider = "fetchData")
	public void insertCustomersDetails(String deleteJsonData , String insertJsonData) throws ClassNotFoundException, SQLException, JSONException {		
		String endPoint = "customers";
			
		Response response = DeleteWithJsonAsBody(deleteJsonData, RestAssured.baseURI+endPoint);
		verifyResponseCode(response, 200);
		verifyContentWithKey(response, "message", "SUCCESS");
		//	verifyResponseTime(response, 5000);
		
		//validating the whether the record deleted in DB
		String customer = getDataFromDB("SelectDeletedFirstName");
		if (customer==null) 
		{
			postWithJsonAsBody(insertJsonData, RestAssured.baseURI+endPoint);
		}
		else //If customer not deleted
		{
			reportStep("Data Still Exists", "FAIL");
		}
		
		
	}


}
