package book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class NaverBookSearch {
	
	public JSONObject searchBook(String book, int start) {
		JSONObject json = null;
		String clientId = "EKHaO1yXy6n6_NKCUkoB"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "8wVTPUCmmE"; //애플리케이션 클라이언트 시크릿값"
		String apiURL = "https://openapi.naver.com/v1/search/book.json?query="+book+"&start="+start;    // json 결과
		
		URL url;
		try {
			url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");	
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			BufferedReader br;
			int responseCode = con.getResponseCode();
			if(responseCode == 200) {//정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			else { //에러발생
				System.out.println(responseCode);
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String result = "";
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				result += str;
			}
			json = new JSONObject(result);
			System.out.println(json.toString());
			
			JSONArray arr = json.getJSONArray("items");
			json.put("resultArr", arr);
			json.put("responseCode", responseCode);
			
			br.close();
			con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
