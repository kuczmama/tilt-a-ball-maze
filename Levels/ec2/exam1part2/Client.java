/*
  Scott Campbell

  lab 911 client

  write url's to amazondb
*/

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import java.net.*;
import java.io.*;

public class Client {
    XmlRpcClient xmlRPCclient = null;
    String token = null;
    int port = -1;

    //arg0: port    arg1: host    arg2: str
    public static void main(String args[]) {
	int port = -1;
	String host;
	try {
	    port = Integer.parseInt(args[0]);
	} catch (Exception err) {
	    System.out.println("specify port");
	    return;
	}

	try {
	    Client client = new Client(args[1],port);
	    System.out.println("Client created");
	    String str = "";
	    if(args.length >= 2){
		str = args[2];
	    } else {
		System.out.println("Enter in a string to find the length of");
	    }
	    int length = client.getLength(str);
	    System.out.println("The length of " + str + " is : " + length);
	}
	catch (Exception err) {
	    System.err.println( err);
	    return;
	}
    }

    /*
      constructor
    */
    public Client(String host,int p) throws IOException {
	port = p;
	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	config.setServerURL(new URL("http://" + host+":"+port));
	xmlRPCclient = new XmlRpcClient();
	xmlRPCclient.setConfig(config);
    }

    public int getLength(String str) throws IOException,org.apache.xmlrpc.XmlRpcException{
	Object[] params = new Object[]{str};
	return (int) xmlRPCclient.execute("lab.getLength",params);
    }

}
