/*
   Scott Campbell
   Java REST Client for storing data

 */

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

class Put {
	String host="";
	int port=-1;

	public static void main(String args[]) {
		String host="";
		int port = -1;
		String name = "";
		String url = "";

		try {
			host=args[0];
			port=Integer.parseInt(args[1]);
			name = args[2];
			url = args[3];
		}
		catch (Exception err) {
			System.out.println("usage:  host port name url");
			return;
		}

		new Put(host,port).put(name,url);
	}

	public Put(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void put(String name, String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost("http://" + host + ":" + port + "/awsREST/url/");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("name", name));
		nvps.add(new BasicNameValuePair("url", url));
		nvps.add(new BasicNameValuePair("password", "cse383"));
		CloseableHttpResponse response2=null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			response2 = httpclient.execute(httpPost);
		} catch (Exception err) {
			System.out.println("Error sending request " + err);
			return;
		}

		try {
			System.out.println(response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			EntityUtils.consume(entity2);
		}
		catch (Exception err1) {
			System.err.println("Error on using data " + err1);
		} finally {
			try {
				response2.close();
			} catch (Exception err) {}
		}
	}
}
