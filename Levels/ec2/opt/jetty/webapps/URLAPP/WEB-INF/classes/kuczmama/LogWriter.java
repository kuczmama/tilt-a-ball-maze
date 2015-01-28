/*
 * CSE 383-f14
 * Dr. Campbell
 * LogWriter.java
 *
 * static class to handle writing to the log file
 */
package kuczmama;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.net.*;

public class LogWriter {
    static PrintWriter write = null;
    //static String logFileName = "/opt/jetty/webapps/URLAPP/WEB-INF/classes/kuczmama/log.log";
     static String logFileName = "/opt/jetty/logs/URLAPP.log";

    private static void initializePrintWriter(){
	try {
	    write = new PrintWriter(new FileOutputStream(logFileName,true));
	} catch(IOException e){
	    System.err.println(logFileName + " not found");
	}
    }

    private static String getTimeStamp(){
	java.util.Date date= new java.util.Date();
	return (new Timestamp(date.getTime())).toString();
    }

    private static String getHostName(){
	try {
	    return InetAddress.getLocalHost().getHostName();
	} catch (UnknownHostException e) {
	    //System.err.println("Unknown host exception");
	}
	return "unable to find hostname";
    }
 
    // write to log file
    public static void writeToLog(String message){
	if(write == null)
	    initializePrintWriter();
	if(message != null)
	    write.println(getTimeStamp() + " " + getHostName() + " " + message);
	/*write.printf("%s %s\n",
		     getTimeStamp(),
		     message);*/
	write.flush();
    }

}
