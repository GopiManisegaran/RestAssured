package lib.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import net.javacrumbs.jsonunit.JsonAssert;
import net.minidev.json.JSONObject;
import lib.utils.DBConnection;

public class RESTAssuredBase extends PreAndTest {
	
	public String Query;
	//public static String getCustomer;
	//protected static Properties prop;
	
	  public String  getValueFromPropertyFile(String SQLQuery){
		 
		  Properties prop = new Properties(); 
		  
		  try
	  { 
			  
		  prop.load(new FileInputStream(new File("./src/test/resources/SQLQueries.properties"))); // String strQuery =
		   Query = prop.getProperty(SQLQuery);
		  
	  } catch (IOException e) { // TODO Auto-generated catch block
	  e.printStackTrace(); 
	  }
		return Query; 
	  
	  }
	 
	
	/*
	 * public static void unloadObjects() { prop = null; }
	 */
	
	
	public static RequestSpecification setLogs() {

		RequestLogSpecification log = RestAssured
				.given()
				.auth().none()
				//.auth().oauth2(keyCloakToken)
				//.auth.(keyCloakToken)
				//.basic("admin", "Tuna@123")
				.log();
		return log.all().contentType(getContentType());
	}

	public static Response get(String URL) {
		return setLogs()
				.when().get(URL);
	}


	public static Response get() {
		return setLogs()
				.get();
	}

	public static Response getWithHeader(Map<String, String> headers, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.get(URL);
	}

	public static Response post() {

		return setLogs()
				.post();
	}

	public static Response post(String URL) {

		return setLogs()
				.post(URL);
	}

	public static Response postWithBodyAsFile(File bodyFile) {

		return setLogs()
				.body(bodyFile)
				.post();
	}
	public static Response postWithUrlandBodyAsFile(String URL,File bodyFile) {

		return setLogs()
			
				.body(bodyFile)
				.post(URL);
	}
	
	
	public static Response putWithUrlandBodyAsFile(String URL,File bodyFile) {

		return setLogs()
			
				.body(bodyFile)
				.put(URL);
	}
	public static Response putWithJsonAsBody(String jsonObject, String URL) {

		return setLogs()
				.body(jsonObject)
				.put(URL);
	}
	
	public static Response postWithHeaderAndForm(Map<String, String> headers,
			JSONObject jsonObject, String URL) {

		return setLogs()
				.headers(headers)
				.body(jsonObject)
				.post(URL);
	}

	public static Response postWithJsonAsBody(String jsonObject, String URL) {

		return setLogs()
				.body(jsonObject)
				.post(URL);
	}


	public static Response postWithHeaderAndJsonBody(Map<String, String> headers,
			String jsonObject, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.body(jsonObject)
				.post(URL);
	}


	public static Response postWithHeaderParam(Map<String, String> headers, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.post(URL);
	}
	
	public static Response delete(String URL) {
		return setLogs()
				.when()
				.delete(URL);
	}

	public static Response deleteWithHeaderAndPathParam(Map<String, String> headers,
			JSONObject jsonObject, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.body(jsonObject)
				.delete(URL);
	}

	public static Response deleteWithHeaderAndPathParamWithoutRequestBody(
			Map<String, String> headers, String URL) throws Exception {
		return setLogs()
				.when()
				.headers(headers)
				.delete(URL);
	}
	public static Response DeleteWithJsonAsBody(String jsonObject, String URL) {

		return setLogs()
				.body(jsonObject)
				.delete(URL);
	}

	public static Response putWithHeaderAndBodyParam(Map<String, String> headers, File bodyFile, String URL) {
	//public static Response putWithHeaderAndBodyParam(JSONObject jsonObject, String URL) {

		return RestAssured.given().headers(headers).contentType(getContentType()).request()
				.body(bodyFile).when().put(URL);
	}

	private static ContentType getContentType() {

		return ContentType.JSON;

	}

	public static void verifyContentType(Response response, String type) {
		if(response.getContentType().toLowerCase().contains(type.toLowerCase())) {
			reportRequest("The Content type "+type+" matches the expected content type", "PASS");
		}else {
			reportRequest("The Content type "+type+" does not match the expected content type "+response.getContentType(), "FAIL");	
		}
	}

	public static void verifyResponseCode(Response response, int code) {
		System.out.println(response.statusCode()); 
		if(response.statusCode() == code) {
			reportRequest("The status code "+code+" matches the expected code", "PASS");
		}else {
			reportRequest("The status code "+response.statusCode()+" does not match the expected code "+code, "FAIL");	
		}
	}
	
	public static void verifyResponseTime(Response response, long time) {
		if(response.time() <= time) {
			reportRequest("The time taken "+response.time() +" with in the expected time", "PASS");
		}else {
			reportRequest("The time taken "+response.time() +" is greater than expected SLA time "+time,"FAIL");		
		}
	}

	public static void verifyContentWithKey(Response response, String key, String expVal) {
		if(response.getContentType().contains("json")) {
			
			JsonPath jsonPath = response.jsonPath();
			String actValue = jsonPath.get(key);
			if(actValue.equalsIgnoreCase(expVal)) {
				reportRequest("The JSON response has value "+expVal+" as expected. ", "PASS");
			}else {
				reportRequest("The JSON response :"+actValue+" does not have the value "+expVal+" as expected. ", "FAIL");		
			}
		}
	}
	
	public static void verifyContentsWithKey(Response response, String key, String expVal) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			List<String> actValue = jsonPath.getList(key);
			
			String str = (String) actValue.get(0);
			if(actValue.get(0).equalsIgnoreCase(expVal)) {
				reportRequest("The JSON response has value "+expVal+" as expected. ", "PASS");
			}else {
				reportRequest("The JSON response :"+actValue+" does not have the value "+expVal+" as expected. ", "FAIL");		
			}
		}
	}
	
	public static List<String> getContentsWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return jsonPath.getList(key);			
		}else {
			return null;
		}
	}
	
	public static String getContentWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return (String) jsonPath.get(key);			
		}else {
			return null;
		}
	}
	
	@SuppressWarnings("static-access")
//	@Test(expectedExceptions = ArithmeticException.class)
	public void compareTwoJsonObject (String QueryKey,String actual) throws AssertionError
	{
		try {
			//String strQuery = "";
		//	Properties prop = new Properties();
		//	prop.load(new FileInputStream(new File("./src/test/resources/SQLQueries.properties")));
		//	Query = prop.getProperty(Query);
			String QueryFromPropFile = getValueFromPropertyFile(QueryKey);
			DBConnection dbConn = new DBConnection();
		
			String	expected= dbConn.getTableData(QueryFromPropFile);
			
			JsonAssert js = null;
	
			System.out.println("expected:"+expected);
				System.out.println("actual:"+actual);
			js.assertJsonEquals(expected, actual);
	
		
			reportRequest("<html><font color=\"orange\"> The actual JSON response : </font></html>"+actual+"<html><font color=\"orange\"> matches with expected Json :</font></html>"+expected+".", "PASS");
	
		} 
		
		catch (AssertionError e) {
			// TODO Auto-generated catch block
			reportRequest(e.toString(), "FAIL");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			reportRequest(e.toString(), "FAIL");
		}
	
	}
	
	public void deleteDataFromDB (String deleteKey) throws ClassNotFoundException, SQLException, JSONException {
		try {
			String deleteQeryFromProp = getValueFromPropertyFile(deleteKey);
			DBConnection dbConn = new DBConnection();
			
			dbConn.deleteTableData(deleteQeryFromProp);
			reportRequest("Data Deleted Successfully","PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			reportRequest("Data not Deleted","FAIL");
		}
	}
	
	public void insertDataInDB (String insertKey) throws ClassNotFoundException, SQLException, JSONException {
		try {
			String insertQeryFromProp = getValueFromPropertyFile(insertKey);
			DBConnection dbConn = new DBConnection();
			
			dbConn.insertDataInTable(insertQeryFromProp);
			reportRequest("Data inserted Successfully","PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportRequest("Data not inserted","FAIL");
		}
	}
	
	public void updateDataInDB (String updateKey) throws ClassNotFoundException, SQLException, JSONException {
		try {
			String updateQeryFromProp = getValueFromPropertyFile(updateKey);
			DBConnection dbConn = new DBConnection();
			
			dbConn.updateDataInTable(updateQeryFromProp);
			reportRequest("Data updated Successfully","PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportRequest("Data not updated","FAIL");
		}
	}
	public String getDataFromDB (String SelectDataKey) throws ClassNotFoundException, SQLException, JSONException {
		String data = null;
		try {
			String SelectQeryFromProp = getValueFromPropertyFile(SelectDataKey);
			DBConnection dbConn = new DBConnection();
			
		 data =    	dbConn.GetDataFromTable(SelectQeryFromProp);
			reportRequest("Data retrieved Successfully","PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			reportRequest("Data not retrieved","FAIL");
		}
		return data;
	}
	
	public void compareTwoData (String actual, String expected) {
		
		try {
			if (actual.equalsIgnoreCase(expected)) {
				reportRequest("Actual data: "+actual+" matches with expected data: "+expected,"PASS");
			}
			else {
				reportRequest("Actual data: "+actual+" does not matches with expected data: "+expected,"FAIL");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//	reportRequest("Unable to compare data: "+e,"FAIL");
		}
	}
	
}
