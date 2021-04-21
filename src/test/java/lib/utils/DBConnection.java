package lib.utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.JsonArray;

import lib.rest.BaseTest;
import lib.rest.GenericFunctions;
import net.javacrumbs.jsonunit.JsonAssert;
import restAssured.restAssured;

@SuppressWarnings("unchecked")
@Service
public class DBConnection extends GenericFunctions{
	
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	public  String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=TestDB;integratedSecurity=true";				
	public String username = "DIR\\xxxx";	
	
	public String password = "none";	
	
	
	/*
	 * public String getConnection(){ String sql="SELECT * FROM ORDERS";
	 * List<Map<String,Object>> results = jdbcTemplate.queryForList(sql); return
	 * results.toString(); }
	 */
	
		public void deleteTableData(String deleteQuery) throws ClassNotFoundException, SQLException{
			//final String query="DELETE FROM "+ tableName + " WHERE "+ column+ "= "+ value;
            
     	    //Load mysql jdbc driver		
       	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
       
       		//Create Connection to DB		
        	Connection con = DriverManager.getConnection(dbUrl,username,password);

			
			try
			{
			      
      		//Create Statement Object		
    	   Statement stmt = con.createStatement();					
   
   			// Execute the SQL Query. Store results in ResultSet
    	   stmt.addBatch(deleteQuery);
    	   stmt.executeBatch();
   	
			}
			catch(Exception ex)
			{
				reportRequest("Data not Deleted :Execption - "+ex.getMessage() ,"FAIL");
				con.rollback();
			}
			finally
			{
       			con.close();			
			}
	
		
	}
		
	/*
	 * public String DataInTable(String tableName ,String column,String value,
	 * String query){ query="DELETE FROM"+ tableName + "WHERE"+ column+ "="+ value;
	 * List<Map<String,Object>> results = jdbcTemplate.queryForList(query); return
	 * results.toString(); }
	
	 */
		
		public String GetDataFromTable(String selectQuery) throws ClassNotFoundException, SQLException{
		//	final String query="SELECT * FROM CUSTOMERS";
			String data =null;
     	    //Load mysql jdbc driver		
       	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
       
       		//Create Connection to DB		
        	Connection con = DriverManager.getConnection(dbUrl,username,password);

			
			try
			{
			      
      		//Create Statement Object		
    	   //tatement stmt = con.createStatement();
				Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=stmt.executeQuery(selectQuery);
    	   
    	   while (rs.next()) {
    	
    	    data = rs.getString("forname");
    	   }
   			// Execute the SQL Query. Store results in ResultSet
    	   //stmt.addBatch(query);
    	   //stmt.executeBatch();
     	//	ResultSet rs= stmt.executeQuery(query);	
			}
			catch(Exception ex)
			{
				con.rollback();
			}
			finally
			{
       			con.close();			
			}
		// jdbcTemplate.execute(query);
	//	 jdbcTemplate.execute(query);
			return data;
		
	}
		@SuppressWarnings("unchecked")
		public String getorderitems() throws ClassNotFoundException, SQLException{
		
			  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
		       
	       		//Create Connection to DB		
	        	Connection con = DriverManager.getConnection(dbUrl,username,password);

				
			final String query="SELECT * FROM CUSTOMERS";
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    	   ResultSet rs=stmt.executeQuery(query);
			ResultSetMetaData metadata = rs.getMetaData();
		    int columnCount = metadata.getColumnCount();   
		    ArrayList<String> columnNames = new ArrayList<String>();
		    JSONObject obj1 = new JSONObject();
	        JSONArray jsonArray = new JSONArray();
		    for (int i = 1; i <= columnCount; i++) {
		    	columnNames.add(metadata.getColumnName(i));
			/*
			 * writeToFile(metadata.getColumnName(i) + ", "); } System.out.println(); while
			 * (rs.next()) { String row = ""; for (int i = 1; i <= columnCount; i++) { row
			 * += rs.getString(i) + ", "; } System.out.println(); writeToFile(row);
			 */}
		    	
		    	  while (rs.next()) {
		    		  
		    		  ArrayList<String> data = new ArrayList<String>();
		    	  
	                    JSONObject obj = new JSONObject();
	                    for (int i = 0; i < columnNames.size(); i++) {
	                        data.add(rs.getString(columnNames.get(i)));
	                        {
	                            for (int j = 0; j < data.size(); j++) {
	                                if (data.get(j) != null) {
	                                    obj.put(columnNames.get(i), data.get(j));
	                                }else {
	                                    obj.put(columnNames.get(i), "");
	                                }
	                            }
	                        }
	                    }
	              
	               
	                   jsonArray.add(obj);
	//                   System.out.printl//n(jsonArray.add(obj));
	                    obj1.put("", jsonArray);

		    }
			

	
		    return obj1.toString();}
		

		public String getTableData(String sqlQuery) throws ClassNotFoundException, SQLException, JSONException {
			 PreparedStatement pst = null;
			 ResultSet rset = null;
			ArrayList<Object> data = new ArrayList<Object>();
			JSONObject obj1 = new JSONObject();
	        JSONArray jsonArray = new JSONArray();
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
		       
       		//Create Connection to DB		
        	Connection con = DriverManager.getConnection(dbUrl,username,password);

			
	//	final String query="SELECT * FROM logins";

		   try {
	            pst = con.prepareStatement(sqlQuery);
	            rset = pst.executeQuery();
	            ArrayList<String> columnNames = new ArrayList<String>();
	            if (rset != null) {
	                ResultSetMetaData columns = rset.getMetaData();
	                int i = 0;
	                while (i < columns.getColumnCount()) {
	                    i++;
	                    columnNames.add(columns.getColumnName(i));
	                }
	           //    System.out.println(columnNames);
	                while (rset.next()) {
	        //        	org.json.JSONObject ob = new org.json.JSONObject();
	           		 JSONObject obj = new JSONObject();
	                   
	                    for (i = 0; i < columnNames.size(); i++) {
	                        data.add(rset.getObject(columnNames.get(i)));
	                        {
	                            for (int j = 0; j < data.size(); j++) {
	                                if (data.get(j) != null) {
	                                    obj.put(columnNames.get(i), data.get(j));
	                     //               ob.put(columnNames.get(i), data.get(j));
	                                }else {
	                                    obj.put(columnNames.get(i), "");
	                        //            ob.put(columnNames.get(i), "");
	                                }
	                            }
	                        }
	                    }
	                //   System.out.println(obj);
	                    jsonArray.add(obj);
	                 
	                 
	                    
	            //        obj1.put("header", jsonArray);
	              
	                }
	                pst.close();
	                
	                
	                
	            } else {
	                JSONObject obj2 = new JSONObject();
	                obj2.put(null, null);
	                jsonArray.add(obj2);
	                obj1.put("header", jsonArray);
	            }
	            
	          //  System.out.println(jsonArray.toJSONString());
	  
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	        	reportRequest("Unable to fetch data :Execption - "+e.getMessage() ,"FAIL");
	       
	        } finally {
	            if (con != null) {
	                try {
	                    con.close();
	                    rset.close();
	                    pst.close();
	                } catch (SQLException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	        }
		return jsonArray.toString();
		}
			
		
		public void insertDataInTable(String InsertQuery) throws ClassNotFoundException, SQLException{
	
            
     	    //Load mysql jdbc driver		
       	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
       
       		//Create Connection to DB		
        	Connection con = DriverManager.getConnection(dbUrl,username,password);

			
			try
			{
			      
      		//Create Statement Object		
    	   Statement stmt = con.createStatement();					
   
   			// Execute the SQL Query. Store results in ResultSet
    	   stmt.addBatch(InsertQuery);
    	   stmt.executeBatch();
   	
			}
			catch(Exception ex)
			{
				reportRequest("Data not inserted :Execption - "+ex.getMessage() ,"FAIL");
				con.rollback();
			}
			finally
			{
       			con.close();			
			}
	
		
	}
	public void updateDataInTable(String updateQuery) throws ClassNotFoundException, SQLException{
	
            
     	    //Load mysql jdbc driver		
       	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
       
       		//Create Connection to DB		
        	Connection con = DriverManager.getConnection(dbUrl,username,password);

			
			try
			{
			      
      		//Create Statement Object		
    	   Statement stmt = con.createStatement();					
   
   			// Execute the SQL Query. Store results in ResultSet
    	   stmt.addBatch(updateQuery);
    	   stmt.executeBatch();
   	
			}
			catch(Exception ex)
			{
				reportRequest("Data not updated :Execption - "+ex.getMessage() ,"FAIL");
				con.rollback();
			}
			finally
			{
       			con.close();			
			}
	
		
	}

			
		

}

	



