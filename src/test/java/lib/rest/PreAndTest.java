package lib.rest;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import lib.utils.DataInputProvider;
import lib.utils.HTMLReporter;
import restAssured.restAssured;

public class PreAndTest extends HTMLReporter{
	
	public String dataFileName, dataFileType,url ;	
	public static String keyCloakToken;
	
	
	 public String  getEnvUrl(){
		  Properties prop = new Properties();  
		  try
	  { 	  
		  prop.load(new FileInputStream(new File("./src/test/resources/Config.properties"))); // String strQuery =
		   url = prop.getProperty("URL");
		  
	  } catch (IOException e) { // TODO Auto-generated catch block
	  e.printStackTrace(); 
	  }
		return url; 
		
	  
	  }
	
	
	
	@BeforeSuite
	public void beforeSuite() {
		startReport();
	}
	/*
	 * @BeforeTest public void beforeTest(){ RESTAssuredBase.loadProperties(); }
	 */
	
	@BeforeClass
	public void beforeClass() {
		startTestCase(testCaseName, testDescription);		
	}
	
	@BeforeMethod
	public void beforeMethod() throws ClientProtocolException, IOException {
		//for reports		
		svcTest = startTestModule(nodes);
		svcTest.assignAuthor(authors);
		svcTest.assignCategory(category);
	//	keyCloakToken = GetKeyCloakToken.Token();
		//RestAssured.baseURI = "https://scsvc.sandbox.adf.kp.org/api/v1/securitycentral/dataset/create?nuid=M622163";
		//RestAssured.baseURI = "https://scsvc.sandbox.adf.kp.org/api/v1/securitycentral/tenantList";
	//	RestAssured.baseURI = "https://scsvc.sandbox.adf.kp.org/api/v1/securitycentral/tenantDetails?loggedInNuid=G476822";
		RestAssured.baseURI=getEnvUrl();
		

	}

	@AfterMethod
	public void afterMethod() {
	
	}

	/*
	 * @AfterTest public void afterTest(){ RESTAssuredBase.unloadObjects(); }
	 */
	@AfterSuite
	public void afterSuite() {
		endResult();
	}

	@DataProvider(name="fetchData")
	public  Object[][] getData(){
		if(dataFileType.equalsIgnoreCase("Excel"))
			return DataInputProvider.getSheet(dataFileName);	
		else {
			Object[][] data = new Object[1][1];
			data[0][0] = new File("./data/"+dataFileName+"."+dataFileType);
			System.out.println(data[0][0]);
			return data;
		}
			
	}

	@Override
	public long takeSnap() {
		return 0;
	}	

	
	
}
