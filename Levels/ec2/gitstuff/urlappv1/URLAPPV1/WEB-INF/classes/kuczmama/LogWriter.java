/**
 * Mark Kuczmarski
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
    private static PrintWriter write;
    private static final String logFileName = "log.txt";

    private static void initializePrintWriter(){
	try {
  	    write = new PrintWriter(new BufferedWriter(new FileWriter(logFileName, true)));
   	} catch(IOException e){
 	    //System.err.println(logFileName + " not found");
	}
    }

    private static void closePrintWriter(){
	write.close();
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
	initializePrintWriter();
	write.printf("%s %s %s\n",
		     getTimeStamp(),
		     getHostName(),
		     message);
	write.flush();
	closePrintWriter();
    }

}

 

 
