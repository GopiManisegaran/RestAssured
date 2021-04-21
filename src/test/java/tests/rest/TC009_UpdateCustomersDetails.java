package tests.rest;

import java.sql.SQLException;

import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.GenericFunctions;

public class TC009_UpdateCustomersDetails extends GenericFunctions{
	
	
	
	@BeforeTest
	public void setValues() {

		testCaseName = "TC009_UpdateCustomersDetails";
		testDescription = "";
		nodes = "Service";
		authors = "";
		category = "smoke";
		dataFileName = "TC009_UpdateCustomersDetails";
		dataFileType = "Excel";
	}
	
	
	
	@Test(dataProvider = "fetchData")
	public void updateCustomersDetails(String insertJsonData , String updateJsonData, String revertJsonData,String FirstName) throws ClassNotFoundException, SQLException, JSONException {		
		String endPoint = "customers";
			String customer = getDataFromDB("SelectFirstNameBeforeUpdate");
		
			if (customer==null) {
				//inserting a record
				postWithJsonAsBody(insertJsonData, RestAssured.baseURI+endPoint);
				
				//calling put service to validate
				Response response =	putWithJsonAsBody(updateJsonData, RestAssured.baseURI+endPoint);
				verifyResponseCode(response, 200);	
			//	verifyResponseTime(response, 5000);
				verifyContentWithKey(response, "message", "SUCCESS");
				
				//validating updated record
				String FirstNameDB = getDataFromDB("SelectUpdatedFirstName");
				compareTwoData(FirstNameDB,FirstName);
				
				//revert the changes
				putWithJsonAsBody(revertJsonData, RestAssured.baseURI+endPoint);
				
			}
			else {// if the expected record already exist
				
				//calling put service to validate
				Response response =	putWithJsonAsBody(updateJsonData, RestAssured.baseURI+endPoint);
				verifyResponseCode(response, 200);	
				verifyResponseTime(response, 5000);
				verifyContentWithKey(response, "message", "SUCCESS");
				
				//validating updated record
				String FirstNameDB = getDataFromDB("SelectUpdatedFirstName");
				compareTwoData(FirstNameDB,FirstName);
				
				//revert the changes
				putWithJsonAsBody(revertJsonData, RestAssured.baseURI+endPoint);
				
			}
		
	}

}
