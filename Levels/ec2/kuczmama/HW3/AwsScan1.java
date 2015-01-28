/*
  Scott Campbell
  CSE383
  Fall 2012

  Sample query program used to access amazon aws db - modified from program in aws sdk documentation
*/

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;

public class AwsScan1 {

    static AmazonDynamoDBClient client;

    public static void main(String[] args) {
	new AwsScan1().Main();
    }

    public java.util.List<Map<String,AttributeValue>> Main() {
	//connect to aws
	try {
	    createClient();
	} catch (Exception err) {
	    LogWriter.writeToLog("Can't connect to aws",null);
	    return null;
	}

	try {
	    java.util.List<Map<java.lang.String,AttributeValue>> allItems;
	    //get list of Items
	    return GetAllItems();
	}  catch (AmazonServiceException ase) {
	    LogWriter.writeToLog(ase.getMessage(),null);
	}
	return null;
    }

    /**
       client used to connect to aws
    **/
    private void createClient() throws IOException {

	AWSCredentials credentials = new PropertiesCredentials(
							       AwsScan1.class.getResourceAsStream("AwsCredentials.properties"));

	client = new AmazonDynamoDBClient(credentials);
	client.setEndpoint("dynamodb.us-east-1.amazonaws.com");
    }

    /**
       get list .
       Return as a list of values
    */
    private java.util.List<Map<String,AttributeValue>> GetAllItems() throws AmazonServiceException {
	ScanRequest scan = new ScanRequest();	//create a scan to get ALL results
	scan.setTableName("cse383-f14-hw2");	//set name of table

	ScanResult result =  client.scan(scan);

	return result.getItems();

    }

    /**
       iterate over list - each list item is itself a mapping of attribute name and value. value is also a Map
    */
    private void printAll(java.util.List<Map<String,AttributeValue>> allItems) throws AmazonServiceException{
	for (Map<String,AttributeValue> itemSet: allItems) {
	    printItem(itemSet);
	}

    }

    /**
       print out trip item - finally
    */
    private void printItem(java.util.Map<java.lang.String,AttributeValue> itemSet) throws AmazonServiceException {
	for (Map.Entry<String, AttributeValue> item : itemSet.entrySet()) {
	    String attributeName = item.getKey();
	    AttributeValue value = item.getValue();
	    System.out.println(attributeName + " "
			       + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
			       + (value.getN() == null ? "" : "N=[" + value.getN() + "]")
			       + (value.getB() == null ? "" : "B=[" + value.getB() + "]")
			       + (value.getSS() == null ? "" : "SS=[" + value.getSS() + "]")
			       + (value.getNS() == null ? "" : "NS=[" + value.getNS() + "]")
			       + (value.getBS() == null ? "" : "BS=[" + value.getBS() + "] \n"));
	}

    }
}
