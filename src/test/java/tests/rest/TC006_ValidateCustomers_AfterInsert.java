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

public class TC006_ValidateCustomers_AfterInsert extends RESTAssuredBase{
	
	
	
	@BeforeTest
	public void setValues() {

		testCaseName = "TC006_ValidateCustomers_AfterInsert";
		testDescription = "";
		nodes = "Service";
		authors = "";
		category = "smoke";
		dataFileName = "TC006_ValidateCustomers_AfterInsert";
		dataFileType = "Excel";
	}
	
	
	
	@Test//(dataProvider = "fetchData")
	public void getCustomers() throws ClassNotFoundException, SQLException, JSONException {		
		String endPoint = "customers";
		deleteDataFromDB("DeleteCustomerData");
		insertDataInDB("InsertCustomerData");
		
		Response response =get(RestAssured.baseURI+endPoint);
		String actual = response.asString();
		compareTwoJsonObject("GetCustomerList", actual);
		
		

		// Verify the response status code
		verifyResponseCode(response, 200);	
		
		// Verify the response time
		verifyResponseTime(response, 5000);
		
		
		
	}


}
