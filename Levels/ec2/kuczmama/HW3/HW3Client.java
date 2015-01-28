import java.io.*;
import java.net.*;
import java.util.*;

/*
   scott Campbell
   hw3-client
   cse383 - f14

   UNTESTED
*/

public class HW3Client {
    Socket sock;
    DataInputStream dis;
    DataOutputStream dos;
    int port=-1;
    String host = "ceclnx01.cec.miamioh.edu";
    int cmd=0;
    final static String uid = "campbest";


    /*
         psvm - jre entry point
    */
    public static void main(String a[]) {
	int port=0;
	int cmd = 0;
	try {
	    port = Integer.parseInt(a[0]);
	    if (a.length>1)
		cmd = Integer.parseInt(a[1]);
	} catch (Exception err) {
	    System.out.println("Error - invalid port - please specify which port to connect");
	    System.exit(-1);
	}

	new HW3Client(port,cmd).Main();
    }

    /*constructor*/
    public HW3Client(int port,int c) {
	this.port = port;
	cmd = c;
    }

    /*
         class entry point
    */
    public void Main() {


	if (cmd==9) //check multi
	    {
		try {
		    System.out.println("opening first socket for checking multi");
		    Socket s = new Socket(host,port);
		} catch (IOException err) {
		    System.out.println("Could not connect to server on port " + port);
		    return;
		}
	    }

	try {
	    System.out.println("Opening");
	    sock = new Socket(host,port);
	    dos=new DataOutputStream(sock.getOutputStream());
	    dis = new DataInputStream(sock.getInputStream());
	    
	    sock.setSoTimeout(5000);
	} catch (IOException err) {
	    System.out.println("Could not connect to server on port " + port);
	    return;
	}

	try {
	    sendGreeting();
	    int i = getNumRecords();
	    System.out.println("There are " + i + " records");
	    //get record0
	    ArrayList<Map<String,String>> results = getRecords(0,0);
	    printRecords(results);
	    results = getRecords(0,i-1);
	    printRecords(results);
	    disconnect();
	}
	catch (IOException err) {
	    System.out.println("Error receiving data");
 	}

    }


    /*
         send greeting
	    first BYTE is length of greeting
	       then greeting is 10 sets of 0x10, 0x20 and 0x30
    */
    public void sendGreeting() throws IOException {
	if (cmd==5) {//long sleep
	    try {
		java.lang.Thread.sleep(10000);
	    } catch (Exception err) {}
	}
	if (cmd==1) {
	    System.out.println("Sending invalid greeting");
	    dos.write(0);
	} else {
	    System.out.println("Sending greeting");
	    dos.writeUTF(uid);
	    byte b[] = new byte[]{10,3,0,0};
	    dos.write(b.length);
	    if (cmd==4) 
		b[2] = 0;
	    for (int i=0;i<b.length;i++)
		{
		    sock.getOutputStream().write((int)b[i]);
		    if (cmd==3) //delay
			try {
			    java.lang.Thread.sleep(100);
			} catch (Exception err) {
			}
		}
	}
	sock.getOutputStream().flush();

	//get Response
	int i = dis.read();
	String response = dis.readUTF();
	if (i!=0)
	    throw new IOException("Greeting rejected");
    }


    /*
         receive data from server
    */
    public ArrayList<Map<String,String>> getRecords(int start, int end)throws IOException {
	ArrayList<Map<String,String>> records = new ArrayList<Map<String,String>>();
	dos.write(1);//send get num records
	dos.writeInt(start);
	dos.writeInt(end);
	int response = dis.read();
	String responseString = dis.readUTF();
	if (response != 0) 
	    throw new IOException("getRecords failed " + responseString);

	int i = dis.readInt();//get num records
	HashMap<String,String> record=null;
	while(i>0) {
	    record = new HashMap<String,String>();
	    for (int j=0;j<i;j++) {
		String name = dis.readUTF();
		String value = dis.readUTF();
		record.put(name,value);
		System.out.println("Client: " + j +" " +  name + "  " +  value);
		
	    }
	    records.add(record);
	    
	    i = dis.readInt();
	}
	return records;

    }

    public int getNumRecords() throws IOException {
	dos.write(0);//get num records
	int response = dis.read();
	String responseString = dis.readUTF();
	if (response != 0)
	    throw new IOException("getNumRecords failed " + responseString);

	int i = dis.readInt();
	return i;
    }

    public void printRecords(ArrayList<Map<String,String>> records) {
	System.out.print("Record: ");
	for(Map<String,String> r:records) {
	    for (String k: r.keySet()) {
		System.out.print(k);
		System.out.print(":");
		System.out.print(r.get(k));
		System.out.print(" ");
	    }
	    System.out.println();
	}
    }

    public void disconnect() throws IOException{
	System.out.println("Client: Attempting to disconnect");
	dos.write(2);
	int response = dis.read();
	String responseString = dis.readUTF();
	if (response != 0)
	    throw new IOException("disconnect failed " + responseString);


    }

}