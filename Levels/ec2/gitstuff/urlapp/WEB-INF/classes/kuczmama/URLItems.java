package kuczmama;

/*
  Scott Campbell
  CSE383
  Fall 2014

  Obtain information from AWS Dynamo DB

  Connects to aws
  gets data and stores as arraylist of urlItems
*/

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import java.util.*;

public class URLItems {
    private String AWSEndpoint="dynamodb.us-east-1.amazonaws.com";
    private String AWSTable="cse383-f14-hw2";
    private final String secretKey="cmkOd+2RIwwiQJouxtJxQfUcADZGlPO0+fcId19R";
    private final String accessKey="AKIAJ7UZ5LIGMKYXNX6Q";


    static AmazonDynamoDBClient client;

    public URLItems() {
    }

    public  ArrayList<URLItem> getAllItems() throws IOException{
	//connect to aws
	createClient();

	ArrayList<URLItem> allItems = null;
	try{
	    //get list of Items
	    allItems = GetAllItems();
	    return allItems;

	}  catch (AmazonServiceException ase) {
	    System.err.println(ase.getMessage());
	    throw new IOException("Could not retrieve data");
	}  
    }

    /**
       client used to connect to aws
    **/
    private void createClient() throws IOException {

	AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
	/*
	  AWSCredentials credentials = new PropertiesCredentials(
	  URLItems.class.getResourceAsStream("AwsCredentials.properties"));*/

	client = new AmazonDynamoDBClient(credentials);
	client.setEndpoint(AWSEndpoint);
    }

    /**
       get list .
       Return as a list of values
    */
    private ArrayList<URLItem> GetAllItems() throws AmazonServiceException {
	ScanRequest scan = new ScanRequest();	//create a scan to get ALL results
	scan.setTableName(AWSTable);	//set name of table

	ScanResult result =  client.scan(scan);

	ArrayList<URLItem> itemList = new ArrayList<URLItem>();
	URLItem i;

	for (Map<String,AttributeValue> itemSet:result.getItems()) 
	    {
		i=new URLItem();
		for (Map.Entry<String, AttributeValue> item : itemSet.entrySet()) {
		    String attributeName = item.getKey();
		    AttributeValue aValue = item.getValue();
		    String attributeValue = 
			(aValue.getS() == null ? "" : aValue.getS())
			+ (aValue.getN() == null ? "" : "N=[" + aValue.getN() + "]")
			+ (aValue.getB() == null ? "" : "B=[" + aValue.getB() + "]")
			+ (aValue.getSS() == null ? "" : "SS=[" + aValue.getSS() + "]")
			+ (aValue.getNS() == null ? "" : "NS=[" + aValue.getNS() + "]")
			+ (aValue.getBS() == null ? "" : "BS=[" + aValue.getBS() + "] \n");
		    i.set(attributeName,attributeValue);
		}
		itemList.add(i);
	    }
	return itemList;

    }

    public String deleteItem(String name, String key, String url) throws AmazonServiceException{
	try{
	    createClient();
       	    Map<String, AttributeValue> itemMap = new HashMap<String, AttributeValue>();
	    itemMap.put("Name", new AttributeValue().withS(name));
	    itemMap.put("key", new AttributeValue().withS(key));
	    //itemMap.put("url", new AttributeValue().withS(url));
	    DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
		.withTableName(AWSTable)
		.withKey(itemMap);
	
	    DeleteItemResult deleteItemResult = client.deleteItem(deleteItemRequest);
	    itemMap.clear();

	    return "deleted";
	} catch(Exception e){
	    e.printStackTrace();
	}
	return "error deleting";
    }


    public void updateItem(String name, String key, String url, String newUrl){
	HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
	map.put("Name", new AttributeValue().withS(name));
	map.put("key", new AttributeValue().withS(key));

	Map<String, AttributeValueUpdate> expressionAttributeValues = new HashMap<String, AttributeValueUpdate>();
	expressionAttributeValues.put("url", new AttributeValueUpdate()
				      .withValue(new AttributeValue().withS(newUrl)));
    
            
	UpdateItemRequest updateItemRequest = new UpdateItemRequest()
	    .withTableName(AWSTable)
	    .withKey(map)
	    .withAttributeUpdates(expressionAttributeValues);

	UpdateItemResult result = client.updateItem(updateItemRequest);
    }
}
