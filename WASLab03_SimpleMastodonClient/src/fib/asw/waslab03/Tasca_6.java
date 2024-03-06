package fib.asw.waslab03;

import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

public class Tasca_6 {

	public static void main(String[] args) {

		String URIst = "https://mastodont.cat/api/v1/trends/tags";
		String tokenst = Token.get();
		String idAcc = "";

		try {
			String output = Request.get(URIst).addHeader("Authorization","Bearer "+tokenst).execute().returnContent().asString();
	
			JSONArray result = new JSONArray(output);
			
			for (int i = 0; i < 10; ++i) {
				JSONObject resultObj = result.getJSONObject(i);
				String nameT = resultObj.getString("name");
				System.out.println(nameT);
			}
				
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
/*
		String URI = "https://mastodont.cat/api/v1/trends/tags" + nameTag;
		String TOKEN = Token.get();

		try {
			String output = Request.get(URI)
			.addHeader("Authorization","Bearer "+TOKEN)
			.execute()
			.returnContent()
			.asString();
	
			JSONObject result = new JSONObject(output);
			String text = result.getString("content");
			System.out.format(text);
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}*/
	}

}
