package tests.rest;

import java.lang.reflect.InvocationTargetException;

import java.sql.SQLException;

import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class TC007_ValidateCustomers_AfterUpdate extends RESTAssuredBase{
	
	
	
	@BeforeTest
	public void setValues() {

		testCaseName = "TC007_ValidateCustomers_AfterUpdate";
		testDescription = "";
		nodes = "Service";
		authors = "";
		category = "smoke";
		dataFileName = "TC007_ValidateCustomers_AfterUpdate";
		dataFileType = "Excel";
	}
	
	
	
	@Test//(dataProvider = "fetchData")
	public void getCustomers() throws ClassNotFoundException, SQLException, JSONException, ArithmeticException, InvocationTargetException {		
		String endPoint = "customers";
		deleteDataFromDB("DeleteCustomerData");
		insertDataInDB("InsertCustomerData");
		updateDataInDB("UpdateCustomerData");
		
		Response response =get(RestAssured.baseURI+endPoint);
		String actual = response.asString();
		compareTwoJsonObject("GetCustomerList", actual);
		
		

		// Verify the response status code
		verifyResponseCode(response, 200);	
		
		// Verify the response time
	//	verifyResponseTime(response, 5000);
		
		
		
	}


}
