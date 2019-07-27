package tests.rest;

import java.sql.SQLException;

import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lib.rest.RESTAssuredBase;

public class TC005_DeleteOrder extends RESTAssuredBase{
	
	
	
	@BeforeTest
	public void setValues() {

		testCaseName = "TC005_DeleteOrder";
		testDescription = "";
		nodes = "Service";
		authors = "";
		category = "smoke";
		dataFileName = "TC005_DeleteOrder";
		dataFileType = "Excel";
	}
	
	
	
	@Test(dataProvider = "fetchData")
	public void deleteOrder(String QueryKey) throws ClassNotFoundException, SQLException, JSONException {		

		deleteDataFromDB(QueryKey);
		
		
	}


}
