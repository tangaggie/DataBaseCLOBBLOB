package com.microsoft.sqlserver;

/* SqlServerBlobGetBytes.java
- Copyright (c) 2015, HerongYang.com, All Rights Reserved.
*/
import java.io.*;
import java.sql.*;
public class SqlServerBlobGetBytes {
 public static void main(String [] args) {
   Connection con = null;
   try {
     com.microsoft.sqlserver.jdbc.SQLServerDataSource ds 
       = new com.microsoft.sqlserver.jdbc.SQLServerDataSource();
     ds.setServerName("192.168.4.12");
//     ds.setPortNumber(60782);
  //   ds.setInstanceName("SQLEXPRESS");
     ds.setDatabaseName("trinity");
     ds.setUser("trinity");
     ds.setPassword("trinity");
     con = ds.getConnection();

//Retrieving BLOB value with getBytes()
     Statement sta = con.createStatement(); 
     ResultSet res = sta.executeQuery("SELECT * FROM Image"); 
     int i = 0;
     while (res.next() && i<3) {
       i++;
       System.out.println("Record ID: "+res.getInt("ID"));
       System.out.println("   Subject = "+res.getString("Subject"));
       byte[] body = res.getBytes("Body");
       System.out.println("   GetBytes Body  = "+body+"..."); //add by aggie
       String bodyHex = bytesToHex(body, 32);
       System.out.println("   Body in HEX = "+bodyHex+"...");
     }
     res.close();

     sta.close();
     con.close();
   } catch (Exception e) {
     e.printStackTrace();
   }
 }
 public static String bytesToHex(byte[] bytes, int max) {
   StringBuffer buffer = new StringBuffer();
   for (int i=0; i<bytes.length && i<max; i++) {
     buffer.append(Integer.toHexString(bytes[i] & 0xFF)); 
   }
   return buffer.toString().toUpperCase();
 }
}
