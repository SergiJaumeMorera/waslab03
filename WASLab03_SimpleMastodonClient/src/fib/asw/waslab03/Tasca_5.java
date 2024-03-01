package fib.asw.waslab03;

import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

public class Tasca_5 {

	public static void main(String[] args) {

		String URIst = "https://mastodont.cat/api/v1/accounts/109862447110628983/statuses";
		String tokenst = Token.get();
		String idAcc = "";

		try {
			String output = Request.get(URIst).addHeader("Authorization","Bearer "+tokenst).execute().returnContent().asString();
	
			JSONArray result = new JSONArray(output);
	
			JSONObject resultV = result.getJSONObject(0);
	
			idAcc = resultV.getString("id");
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}

		String URI = "https://mastodont.cat/api/v1/statuses/" + idAcc;
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
		}

		String URIBoost = "https://mastodont.cat/api/v1/statuses/" + idAcc + "/reblog";
		String TOKENBoost = Token.get();

		try {
			String outputF = Request.post(URIBoost)
			.addHeader("Authorization","Bearer "+TOKENBoost)
			.execute()
			.returnContent()
			.asString();
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
