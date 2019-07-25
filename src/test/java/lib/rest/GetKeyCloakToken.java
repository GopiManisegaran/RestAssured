package lib.rest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class GetKeyCloakToken extends PreAndTest{
	static String  tokenURL = "https://keycloak.sandbox.adf.kp.org/auth/realms/master/protocol/openid-connect/token";
	
	public static String Token() throws ClientProtocolException, IOException {

	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost post = new HttpPost(tokenURL);
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("client_id", "svc-spark-job"));
	    params.add(new BasicNameValuePair("client_secret", "4ee03369-7c67-445d-9d1a-7e9de1e44136"));
	    params.add(new BasicNameValuePair("grant_type", "client_credentials"));
	   post.setEntity(new UrlEncodedFormEntity(params));
	    HttpResponse response = httpclient.execute(post);
	    String body = EntityUtils.toString(response.getEntity());

	System.out.println(body);
	  String [] token = body.split("[\"]");
	  String access_token = token[3];
	 // //  System.out.println(access_token);
	    
	   return access_token;  
	}

}
