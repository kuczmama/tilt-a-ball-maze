/**
 * Mark Kuczmarski
 * CSE 383-f14
 * Dr. Campbell
 * HW2
 * Server.java
 * 
 * Used code from Dr Campbell's handouts
 */
import java.net.*;
import java.io.*;

public class Server{
    private final int SERVER_PORT = 3028;
    private ServerSocket serverSocket;
    private final int maxConnections = 10;

    public Server(){
	init();
	run();
    }

    private void init(){
	createServer();
    }

    private void createServer(){
	try {
	    serverSocket = new ServerSocket(SERVER_PORT);
	} catch(Exception e) {
	    e.printStackTrace();
	    LogWriter.writeToLog("Could not create server",null);
	}
    }

    /**
     * Handle a client
     */ 
    public void run() {
	try {
	    //handle clients
	    new ConnectionManager(serverSocket,maxConnections);
	} catch (Exception err) {
	    LogWriter.writeToLog("Could not handle client",null);
	}
    }
   
}