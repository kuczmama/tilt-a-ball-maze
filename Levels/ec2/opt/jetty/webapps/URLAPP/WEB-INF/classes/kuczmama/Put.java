/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: Put.java
 * Description:  Allows a user to add and retrieve things from a database
 */
package kuczmama;
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
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

public class Put{
    String name;
    String key;
    String url;

    static AmazonDynamoDBClient client=null;
    static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    static String tableName = "cse383-f14-hw2";
    static String endpoint = "dynamodb.us-east-1.amazonaws.com";

    public Put(String name, String url) throws Exception {
	this.name = name;
	key="";
	this.url = url;
	createClient();
    }

    private void createClient() throws Exception {
	if (client ==null) {
	    AWSCredentials credentials = new PropertiesCredentials(
								   Put.class.getResourceAsStream("AwsCredentials.properties"));

	    client = new AmazonDynamoDBClient(credentials);
	    client.setEndpoint(endpoint);
	}
    }

    public void store() throws AmazonServiceException{
	Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
	item.put("Name", new AttributeValue().withS(Sanitize.forHTML(name)));
	Date now = new Date();
	item.put("key", new AttributeValue().withS(dateFormatter.getInstance().format(now)));
	item.put("url", new AttributeValue().withS(Sanitize.forHTML(url)));

	PutItemRequest itemRequest = new PutItemRequest().withTableName(tableName).withItem(item);
	client.putItem(itemRequest);
	item.clear();
    }

    public void setName(String n ){
	name = n;
    }

    public String getName() {
	return name;
    }

    public void setURL(String u) {
	url = u;
    }
    public String getURL() {
	return url;
    }
}
