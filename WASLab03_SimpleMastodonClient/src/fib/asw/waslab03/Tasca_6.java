package fib.asw.waslab03;

import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tasca_6 {
	
	private static final String LOCALE = "ca";

	public static void main(String[] args) {

		String URIt = "https://mastodont.cat/api/v1/trends/tags";
		String token = Token.get();
		
		SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd 'a les' HH:mm:ss", new Locale(LOCALE));
		String data = sdf.format(new Date());
		
		System.out.println();
		System.out.println("Els 10 tags més populars a Mastodon [" + data + "]");
		System.out.println();

		try {
			String outputt = Request.get(URIt).addHeader("Authorization","Bearer " + token).execute().returnContent().asString();
	
			JSONArray result = new JSONArray(outputt);
			
			for (int i = 0; i < 10; ++i) {
				//get name tag
				JSONObject resultObj = result.getJSONObject(i);
				String nameT = resultObj.getString("name");
				
				System.out.println("*************************************************");
				System.out.println("* Tag: " + nameT);
				System.out.println("*************************************************");
				
				//get 5 statuses of the tag
				String URIh = "https://mastodont.cat/api/v1/timelines/tag/" + nameT;
				String outputh = Request.get(URIh).addHeader("Authorization","Bearer " + token).execute().returnContent().asString();
				JSONArray hashtag = new JSONArray(outputh);
				
				for (int j = 0; j < 5; ++j) {
					//get status j id
					JSONObject resultObj2 = hashtag.getJSONObject(j);
					String idSt = resultObj2.getString("id");
					
					//get info status
					String URIst = "https://mastodont.cat/api/v1/statuses/" + idSt;
					String outputst = Request.get(URIst).addHeader("Authorization","Bearer " + token).execute().returnContent().asString();
					JSONObject resultObj3 = new JSONObject(outputst);
					
					JSONObject acc = resultObj3.getJSONObject("account");
					String displayName = acc.getString("display_name");
					String username = acc.getString("username");
					String acct = acc.getString("acct");
					String content = resultObj3.get("content").toString().replaceAll("\\<.*?\\>", "");
					
					System.out.print("- " + displayName + " ");
					System.out.print("(@" + username);
					if (acct != "mastodon.cat") System.out.print("@" + acct + "): ");
					System.out.println(content);
					System.out.println("-------------------------------------------------");
				}
				if (i != 9) System.out.println();
			}
				
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
