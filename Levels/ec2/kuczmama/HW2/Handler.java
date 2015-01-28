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

    public Handler(Socket client){
	this.client = client;
    }

    @Override
    public void run(){
	handleData();
    }

    private void handleData(){
	receiveData();
	sendDataToClient();
	closeStreams();
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
	}catch(IOException e){
	    //write to log
	    LogWriter.writeToLog("Error closing streams",client);
	}
    }

    //receive the greeting from the client
     private void receiveData(){
	 createInputStream();
	 try {
	     //get length of greeting
	     int length = is.readByte();
	     System.out.println("recieved " + length + " of data");
	     data = new byte[length];
	     //loop to the end of the greeting
	     for(int i = 0;(i < length); i++){
		 data[i] = is.readByte();
	     }
	 } catch(IOException e){
	     //write to log
	     LogWriter.writeToLog("Error reading the greeting from client",client);
	 }
     }

    private boolean isValidData(byte[] b){
	try {
	    if(lengthOfGreeting == b.length){                                         
		for(int i = 0; (i < lengthOfGreeting); i++){     
		    if((b[i]/((i % 3)+1)) != 16){              
			return false;                                                 
		    }
		}
		return true;
	    }
	} catch (Exception e) {
	    LogWriter.writeToLog("Error validating greeting",client);
	    return false;
	}
	return false;                                                                 
    }       


    private void sendDataToClient(){
	createOutputStream();
	if(isValidData(data)){
	    sendClientAmazonWebServerData();
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