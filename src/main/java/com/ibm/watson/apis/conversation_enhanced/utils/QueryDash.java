package com.ibm.watson.apis.conversation_enhanced.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ibm.nosql.json.api.*; 
import com.ibm.nosql.json.util.*;

public class QueryDash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueryDash qd = new QueryDash() ;
		qd.fetchData("*","Transactions","TransType='ATM'",123,2) ;
	}
	private Connection getConnection()
	{
		Connection con = null;
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			
			BasicDBObject bludb = getCredentials() ;
			
			String jdbcurl =  (String) bludb.get("jdbcurl"); // use ssljdbcurl to connect via SSL
			String user = (String) bludb.get("username");
			String password = (String) bludb.get("password");
			

			con = DriverManager.getConnection(jdbcurl, user,password);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e);

		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound Exception: " + e);

		}
		return con ;
	}
public ResultSet fetchData(String selParams,String table,String where,int custId,int noOfRows)
{
	Connection con = getConnection() ;
	ResultSet rs=null ;
	Statement stmt = null;
	String sqlStatement = "";
	try {
		stmt = con.createStatement();
		sqlStatement = "SELECT "+selParams+" FROM "+table+ " where +\"CustomerId\"="+custId+" and "+where+" FETCH FIRST "+ noOfRows + " ROWS ONLY" ;
		rs = stmt.executeQuery(sqlStatement);
		
	} catch (SQLException e) {
		System.out.println("Error creating statement: " + e);
	}

	return rs;
}
public BasicDBObject getCredentials()
{
	BasicDBObject bludb = null ;
	String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
	if (VCAP_SERVICES != null) {
	   BasicDBObject obj = (BasicDBObject) JSON.parse(VCAP_SERVICES);
	   String thekey = null;
	   Set<String> keys = obj.keySet();
	   for (String eachkey : keys)
	      if (eachkey.contains("dashDB"))
	         thekey = eachkey;
	   BasicDBList list = (BasicDBList) obj.get(thekey);
	   bludb = (BasicDBObject) list.get("0");
	   bludb = (BasicDBObject) bludb.get("credentials");
	}
	return bludb ;
}
}
