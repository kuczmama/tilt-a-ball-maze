/*
*/

public class Lab {
	public String getAuthToken(String uid, byte code[]) {
		int sum=0;
		if (code.length<=0)
			throw new RuntimeException("Invalid token length");

		for (int i=0;i<code.length;i++) 
			sum += code[i];
		if (sum%13 != 0)
			throw new RuntimeException("Invalid token");


		System.out.println("uid: " + uid);
		String token = uid + " 10011";
		return token;
	}

	public String storeURL(String token, String URL) {
		System.err.println("Store URL: " + URL);
		try{
		    Put put = new Put();
		    put.setURL(URL);
		    put.setName(token.split(" ")[0]);
		    put.store();
		} catch(Exception e){
		    e.printStackTrace();
		    return "ERROR";
		}
		return "OK";
	}
}

