/**
 * Mark Kuczmarski
 * CSE 383-f14
 * Dr. Campbell
 * HW2
 * ConnectionManager.java
 * 
 * Used code from Dr Campbell's handouts
 */

import java.net.*;
import java.io.*;

public class ConnectionManager{
    private int connections;
    private int maxConnections;
    private ServerSocket serverSocket;
    private final int SOCKET_TIMEOUT = 5000;
	

    public ConnectionManager(ServerSocket serverSocket,int maxConnections) {
	connections = 0;
	this.serverSocket = serverSocket;
	this.maxConnections = maxConnections;
	run();
    }

    public synchronized void checkMax() {
	while (connections >= maxConnections) {
	    try {
		wait();
	    } catch (Exception err) {}
	}
	connections++;
    }

    public synchronized void handlerDone() {
	connections--;
	if (connections<0)
	    notifyAll();
    }


    public void run(){
	boolean run=true;
	while(run) {
	    try {
		checkMax(); //will block here if the maximum number of handlers reached
		Socket client = serverSocket.accept();
		client.setSoTimeout(SOCKET_TIMEOUT);
		new Handler(client).start();
	    } catch (Exception err) {
		connections=0;
	    }
	}
    }
}