/**
 * Mark Kuczmarski
 * CSE 383-f14
 * Dr. Campbell
 * HW2
 * LogWriter.java
 *
 * static class to handle writing to the log file
 */

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.net.*;

public class LogWriter {
    private static PrintWriter write;
    private static final String logFileName = "HW2.log";
    private static final String hostName = "easlnx01";
    private static Socket client;

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
	return hostName;
    }

    private static String getRemoteHostIp(){
	try {
	    return client.getRemoteSocketAddress().toString();
	} catch(Exception e){
	    //System.err.println("Error getting remote host IP");
	}
	return "NULL";
    }
    
    // write to log file
    public static void writeToLog(String message,Socket iclient){
	client = iclient;
	initializePrintWriter();
	write.printf("%s %s %s %s\n",
		     getTimeStamp(),
		     getHostName(),
		     getRemoteHostIp(),
		     message);
	write.flush();
	closePrintWriter();
    }
    
}