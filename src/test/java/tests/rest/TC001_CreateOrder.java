package tests.rest;

import java.lang.reflect.InvocationTargetException;

import java.sql.SQLException;

import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.GenericFunctions;

public class TC001_CreateOrder extends GenericFunctions{
	
	
	
	@BeforeTest
	public void setValues() {

		testCaseName = "TC001_Create Order";
		testDescription = "";
		nodes = "Incident";
		authors = "";
		category = "smoke";
		dataFileName = "TC001_Create_Order";
		dataFileType = "Excel";
	}
	
	
	
	@Test(dataProvider = "fetchData")
	public void createOrder(String URL, String order , String tableName , String column,String value , String QueryKey) throws ClassNotFoundException, SQLException, JSONException, ArithmeticException, InvocationTargetException {		
						
		// Map<String, String> header = new HashMap<String, String>();
		//header.put("Content-Type", "application/json");
		// Post the request
		//Response response = putWithHeaderAndBodyParam(header,file, RestAssured.baseURI); 
	
		
		 // DBConnection con = new DBConnection();
	//	  System.out.println(con.getorderitems());
	//	 con.DeleteDataInTable(tableName,column, value);
		// System.out.println(con.getTableData().replace('"',' ' ));
	//	 System.out.println(con.getTableData());
		
		
		 
		// String j = con.getTableData();
		
	//	Response response = postWithJsonAsBody(order,URL);
		// RequestSpecification httpRequest = RestAssured.given();
		//  Response response = httpRequest.get(URL);
	      
	  //      System.out.println("Response Body is =>  " + response.getBody().asString());
Response response =get(URL);
//System.out.println(response);
//System.out.println(response.asString());

String actual = response.asString();
compareTwoJsonObject(QueryKey, actual);

//String s = response.asString();
//String p = "[{\"password\":\"parvez123\",\"id\":1,\"customer_id\":1,\"username\":\"parvez\"},{\"password\":\"galib123\",\"id\":2,\"customer_id\":2,\"username\":\"swathi\"},{\"password\":\"suga123\",\"id\":3,\"customer_id\":12,\"username\":\"suga123\"},{\"password\":\"fjdf123\",\"id\":4,\"customer_id\":11,\"username\":\"fjdf123\"},{\"password\":\"fjdf123\",\"id\":5,\"customer_id\":11,\"username\":\"fjdf123\"},{\"password\":\"fjdf123\",\"id\":7,\"customer_id\":7,\"username\":\"fjdf123\"},{\"password\":\"dgh123\",\"id\":8,\"customer_id\":9,\"username\":\"dgh123\"},{\"password\":\"fjdf123\",\"id\":9,\"customer_id\":5,\"username\":\"fjdf123\"},{\"password\":\"fjdf123\",\"id\":11,\"customer_id\":5,\"username\":\"fjdf123\"},{\"password\":\"dgh123\",\"id\":12,\"customer_id\":6,\"username\":\"dgh123\"},{\"password\":\"heloo123\",\"id\":13,\"customer_id\":3,\"username\":\"heloo123\"},{\"password\":\"heloo123\",\"id\":14,\"customer_id\":3,\"username\":\"heloo123\"},{\"password\":\"heloo123\",\"id\":15,\"customer_id\":3,\"username\":\"heloo123\"},{\"password\":\"heloo123\",\"id\":16,\"customer_id\":3,\"username\":\"heloo123\"},{\"password\":\"jjj123\",\"id\":17,\"customer_id\":3,\"username\":\"jjj123\"},{\"password\":\"jjj123\",\"id\":18,\"customer_id\":3,\"username\":\"hello78\"}]";
//con.compareTwoJsonObject(j, s);

		//Response response =   putWithUrlandBodyAsFile(RestAssured.baseURI,file); 
		
		//Verify the Content by Specific Key
	//	verifyContentWithKey(response, "statusMessage", "Tenant is successfully updated");
		
		// Verify the Content type	
	//	verifyContentType(response, "JSON");
		
		// Verify the response status code
		verifyResponseCode(response, 200);	
		
		// Verify the response time
		verifyResponseTime(response, 5000);
		
		
		
	}


}
