/**
 * Mark Kuczmarski
 * CSE 383-f14
 * Dr. Campbell
 * HW2
 * Handler.java
 * 
 * Used code from Dr Campbell's handouts
 */

import java.util.Timer;
import java.util.Map;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import java.net.*;
import java.io.*;

public class Handler extends Thread{
    private Socket client;
    private DataInputStream is;
    private byte[] data;
    private DataOutputStream dos;
    private final int numItemsInMap = 3;
    private final int lengthOfGreeting = 30;
    private String uid; // client uid
    private int length;
    private byte clientCmd;

    public Handler(Socket client){
	this.client = client;
    }

    @Override
    public void run(){
	handleData();
    }


    private void handleData(){
	clientAuth();
	// if the serverResponse is OK
	// keep going
	if(serverResponse()){
	    handleClientCommands();
	}
	closeStreams();
    }
    
    private void clientAuth()
    {
	createInputStream();
	getClientUID();
	receiveGreeting();
    }

    private void createInputStream(){
	try {
	    is = new DataInputStream(client.getInputStream());
	} catch(IOException e){
	    //write to log
	    LogWriter.writeToLog("Could not get client input stream",client);
	}
    }

    private void createOutputStream(){
	try {
	    dos = new DataOutputStream(client.getOutputStream());
	} catch(IOException e){
	    //write to log
	    LogWriter.writeToLog("Could not create a client output stream",client);
	}
    }
    
    private void closeStreams(){
	try {
	    dos.close();
	    is.close();
	    client.close();
	}catch(IOException e){
	    //write to log
	    LogWriter.writeToLog("Error closing streams",client);
	}
    }

    //receive the greeting from the client
    private void receiveGreeting(){
	try {
	    //get length of greeting
            length = is.readByte();
	    System.out.println("recieved greeting of length " + length);
	    data = new byte[length];
	    //loop to the end of the greeting
	    for(int i = 0;(i < length); i++){
		data[i] = is.readByte();
	    }
	} catch(IOException e) {
	    LogWriter.writeToLog("Error reading in greeting from client", client);
	}
    }

    private void getClientUID() {
	try {
	    //read client user id
	    uid = is.readUTF();
	    System.out.println("got greeting from " + uid);
	}
	catch(IOException e){
	    LogWriter.writeToLog("Error reading in client uid", client);
	}
    }

    

    /**
     * Checks if the data sent by the client is valid
     * returns false if data is not valid
     */
    private boolean isValidData(byte[] b){
	try {
	    if(b.length > 0){               
		int sum = 0;
		//obtain the sum of all of the numbers
		for(int i = 0; (i < b.length); i++){     
		    sum += b[i];
		}
		//check that the sum mod 13 equals 0
		return ((sum % 13) == 0);
	    } else {
		//send error response to client
		return false;
	    }
	} catch (Exception e) {
	    LogWriter.writeToLog("Error validating greeting",client);
	    //send error response to client
	    return false;
	}                                              
    }       

    /**
     * Send a response to the client.
     * 0 - OK
     * 1 - Error
     */
    private void sendServerResponse(int response,String message) {
	try{
	    dos.writeByte((byte)response);
	    dos.writeUTF(message);
	} catch(IOException e){
	    LogWriter.writeToLog("Error sending server response",client);
	}
    }

    /**
     * Get command from client
     * 0 - query number records
     * 1 - int int Get Records ( startand end)
     * 2 - Disconnect
     */
    private void getClientCommand(){
	try{
	    clientCmd = is.readByte();
	    System.out.println("Got Client command " + clientCmd);
	} catch (IOException e){
	    LogWriter.writeToLog("Error receiving client command", client);
	}
    }

    /**
     * Loops infinitely until there is an error
     * or until the client decides to disconnect
     * If the command is 
     * 0 - query the num records
     * 1 - return records in a range
     * 2 - disconnect
     * default - disconnect with an error message
     */
    private void handleClientCommands() {
	getClientCommand();
	while(clientCmd == 0 || clientCmd == 1 || clientCmd == 2){
	    switch(clientCmd){
	    case 0:
		if(queryNumRecords()) return;
		break;
	    case 1:
		if(getRecordsInRange()) return;
		break;
	    case 2:
		//Disconnect with the client
		sendCommandResponseHeader(0,"OK");
		return;
	    default:
		//Error disconnet with the client
		sendCommandResponseHeader(1,"Error disconecting");
		return;
	    }
	    getClientCommand();
	}//end while
     
    }
    
    /**
     * Sends the command response back to the client
     * with a byte and a UTF
     */
    private void sendCommandResponseHeader(int b,String utf) {
	try {
	    dos.writeByte((byte)b);
	    dos.writeUTF(utf);
	} catch (IOException e) {
	    LogWriter.writeToLog("Error sending command response to client",client);
	}
    }

    /**
     * Get the number of records and send that data
     * back to the client returns true if there is an error, false
     * otherwise
     */
    private boolean queryNumRecords(){
	try {
	    sendCommandResponseHeader(0,"OK");
	    dos.writeInt(getAmazonWebServerData().size());
	    return false;
	} catch(Exception e){
	    //sendCommandResponseHeader(1,"Error sending number of records to client");
	    LogWriter.writeToLog("Error sending number of query records to client",client);
	    return true;
	}
    }

    /**
     * Get start and end value of records and send back to client
     * returns true if there is an error, false otherwise
     */
    private boolean getRecordsInRange(){
	try {
	    int start = is.readInt();
	    int end = is.readInt();
	    System.out.printf("Getting records in range start: %d end: %d\n",start,end);
	    sendCommandResponseHeader(0,"OK");
	    sendClientAmazonWebServerData(start,end);
	    return false;
	} catch(IOException e){
	    sendCommandResponseHeader(1,"Could not get valid record data");
	    LogWriter.writeToLog("Error sending records to client",client);
	    return true;
	}
    }

    /**
     * If the data is valid, send an OK response to
     * the client, send an error response otherwise
     * Returns true if the data is valid
     */
    private boolean serverResponse(){
	createOutputStream();
	if(isValidData(data)){
	    System.out.println("Sending a server response of 0");
	    // sends OK response to the client
	    sendServerResponse(0,"OK");
	    return true;
	} else {
	    // sends error response to the client
	    System.out.println("Sending a server response of 0");
	    sendServerResponse(1,"Error");
	    return false;
	}
    }

    private java.util.List<Map<String,AttributeValue>> getAmazonWebServerData(){
	return new AwsScan1().Main();
    }
    
    private void sendClientAmazonWebServerData(){
	java.util.List<Map<String,AttributeValue>> allItems = getAmazonWebServerData();
	try {
	    dos.writeInt(allItems.size()*numItemsInMap);
	    for (Map<String,AttributeValue> itemSet: allItems) {
		sendItem(itemSet);
	    }
	    //tell the client you are done
	    dos.writeInt(0);
	} catch(IOException e){
	    //write to log
	    LogWriter.writeToLog("Error sending AWS data to client",client);
	}
    }

    /**
     *Send the data back to the client in range start to end
     */
    private void sendClientAmazonWebServerData(int start, int end){
	java.util.List<Map<String,AttributeValue>> allItems = getAmazonWebServerData();
	System.out.println("Sending amazon web server data");
	try {
	    if(start < end 
	       && (start >= 0) 
	       && (end <= allItems.size()) 
	       && (allItems.size() > 0)){
		System.out.println("Sent some data");
		dos.writeInt((end-start)*numItemsInMap);
		for (int i = start; (i < end); i++) {
		    sendItem(allItems.get(i));
		    System.out.println("Sending item " + i);
		}
	    }
	    //tell the client you are done
	    dos.writeInt(0);
	} catch(IOException e){
	    //write to log
	    LogWriter.writeToLog("Error sending AWS data to client",client);
	}
    }  

    private void sendItem(Map<String,AttributeValue> itemSet) throws IOException{
	for (Map.Entry<String, AttributeValue> item : itemSet.entrySet()) {
	    String attributeName = item.getKey();
	    AttributeValue value = item.getValue();
	    dos.writeUTF(attributeName);
	    dos.writeUTF(attributeName + " "
			 + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
			 + (value.getN() == null ? "" : "N=[" + value.getN() + "]")
			 + (value.getB() == null ? "" : "B=[" + value.getB() + "]")
			 + (value.getSS() == null ? "" : "SS=[" + value.getSS() + "]")
			 + (value.getNS() == null ? "" : "NS=[" + value.getNS() + "]")
			 + (value.getBS() == null ? "" : "BS=[" + value.getBS() + "] \n"));
	}
    }
}