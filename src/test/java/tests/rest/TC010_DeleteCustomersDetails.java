package tests.rest;

import java.sql.SQLException;

import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.GenericFunctions;

public class TC010_DeleteCustomersDetails extends GenericFunctions{
		
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
		
		//Inserting the record if the expected record not found in DB to delete.
		String customer = getDataFromDB("SelectDeletedFirstName");
		if (customer==null) 
		{
			postWithJsonAsBody(insertJsonData, RestAssured.baseURI+endPoint);
		}
		
		Response response = DeleteWithJsonAsBody(deleteJsonData, RestAssured.baseURI+endPoint);
		verifyResponseCode(response, 200);
		verifyContentWithKey(response, "message", "SUCCESS");
		//	verifyResponseTime(response, 5000);
		
		//validating whether the record deleted in DB or not
		customer = getDataFromDB("SelectDeletedFirstName");
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
