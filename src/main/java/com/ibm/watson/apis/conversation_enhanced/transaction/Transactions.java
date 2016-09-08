package com.ibm.watson.apis.conversation_enhanced.transaction;

import com.ibm.watson.apis.conversation_enhanced.payload.TransactionPayload ;
import com.ibm.watson.developer_cloud.conversation.v1_experimental.model.MessageResponse ;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.ibm.watson.apis.conversation_enhanced.utils.QueryDash;

public class Transactions {
	  public List<TransactionPayload> getTransactions(int noTrans, String RTT,String cusId) throws Exception {			
		  int customerId = Integer.parseInt(cusId) ;
		  QueryDash qd = new QueryDash() ;
		  ResultSet rs = qd.fetchData("\"Date\",\"Desc\",\"Amount\"","\"Transactions\"","\"TransType\"="+"'"+RTT+"'",customerId,noTrans) ;
		  List<TransactionPayload> docs = null ;
		  if (rs!=null)
			{
			  	docs = new ArrayList<TransactionPayload>();	
				
				int i=0;
				while (rs.next()) {
					TransactionPayload transactionPayload = new TransactionPayload();
				    transactionPayload.setType(RTT) ;					
					transactionPayload.setDate(rs.getString("Date")) ;
					transactionPayload.setDesc(rs.getString("Desc")) ;
					transactionPayload.setAmount(rs.getString("Amount"));
					docs.add(i++,transactionPayload);					
				}
				
			}
		  

		     /* 
		      for (int i=0;i<noTrans;i++)
		      {
		    	  TransactionPayload transactionPayload = new TransactionPayload();
			      transactionPayload.setType(RTT) ;
		    	  transactionPayload.setDesc(RTT+" "+i) ;
		    	  docs.add(i,transactionPayload);
		      }
		      */
		    return docs;
		  }


}
