package kuczmama;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.sql.*;

public class DBConnector{
    Connection conn = null;
    final String host = "localhost";
    final String user = "root";
    String pwd = "Cleveland6";
    final String db = "urlappdb";
    
    public DBConnector(){	
	try { 
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    conn = DriverManager.getConnection("jdbc:mysql://"+ host +  "/" + db + "?user="+ user + "&password=" + pwd);
	}
	catch (Exception err) {
	    err.printStackTrace();
	    //System.err.println("Unable to connect to database " + db);
	    //handle the error in intelligent fashion. Retry, use alternate database if possible, inform user via useful message
	}
    }

    /*
     * execute a database update command
     */
    public void executeUpdate(String updateCmd){
	try {
	    conn.createStatement().executeUpdate(updateCmd);
	} catch (Exception e){
	    e.printStackTrace();
	}
    }

    /*
     * Execute a query and get a result set as a result
     */
    public ResultSet executeQuery(String query){
	try{
	    return conn.createStatement().executeQuery(query);
	} catch(Exception e){
	    e.printStackTrace();
	}
	return null;
    }
}
