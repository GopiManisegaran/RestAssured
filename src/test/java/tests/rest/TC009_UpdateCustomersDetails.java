package tests.rest;

import java.io.File;
import net.javacrumbs.*;
import net.javacrumbs.jsonunit.JsonAssert;
import restAssured.restAssured;

import java.sql.SQLException;

import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lib.rest.RESTAssuredBase;
import lib.utils.DBConnection;

public class TC009_UpdateCustomersDetails extends RESTAssuredBase{
	
	
	
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
				postWithJsonAsBody(insertJsonData, RestAssured.baseURI+endPoint);
				Response response =	putWithJsonAsBody(updateJsonData, RestAssured.baseURI+endPoint);
				verifyResponseCode(response, 200);	
				
				verifyResponseTime(response, 5000);
				
				verifyContentWithKey(response, "message", "SUCCESS");
				String FirstNameDB = getDataFromDB("SelectUpdatedFirstName");
				compareTwoData(FirstNameDB,FirstName);
				putWithJsonAsBody(revertJsonData, RestAssured.baseURI+endPoint);
				
			}
			else {
				Response response =	putWithJsonAsBody(updateJsonData, RestAssured.baseURI+endPoint);
				verifyResponseCode(response, 200);	
				
				verifyResponseTime(response, 5000);
				
				verifyContentWithKey(response, "message", "SUCCESS");
				String FirstNameDB = getDataFromDB("SelectUpdatedFirstName");
				compareTwoData(FirstNameDB,FirstName);
				putWithJsonAsBody(revertJsonData, RestAssured.baseURI+endPoint);
				
			}
		
		
	}


}
