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
		
			String customer = getDataFromDB("SelectDeletedFirstName");
		
			if (customer==null) {
				postWithJsonAsBody(insertJsonData, RestAssured.baseURI+endPoint);
			
			}
			else {
				reportStep("Data Still Exists", "FAIL");
				
			}
		
		
	}


}
