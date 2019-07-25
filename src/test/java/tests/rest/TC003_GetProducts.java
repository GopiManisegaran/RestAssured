package tests.rest;

import java.io.File;
import net.javacrumbs.*;
import net.javacrumbs.jsonunit.JsonAssert;

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

public class TC003_GetProducts extends RESTAssuredBase{
	
	
	
	@BeforeTest
	public void setValues() {

		testCaseName = "TC003_GetProducts";
		testDescription = "";
		nodes = "Service";
		authors = "";
		category = "smoke";
		dataFileName = "TC003_GetProducts";
		dataFileType = "Excel";
	}
	
	
	
	@Test(dataProvider = "fetchData")
	public void getProducts(String URL,String QueryKey) throws ClassNotFoundException, SQLException, JSONException {		

		Response response =get(URL);
		String actual = response.asString();
		compareTwoJsonObject(QueryKey, actual);

		// Verify the response status code
		verifyResponseCode(response, 200);	
		
		// Verify the response time
		verifyResponseTime(response, 5000);
		
		
		
	}


}
