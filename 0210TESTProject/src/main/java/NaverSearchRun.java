import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

public class NaverSearchRun {
	private static NaverSearchRun instance = new NaverSearchRun();
	
	private NaverSearchRun() {
		
	}
	public static NaverSearchRun getInstance() {
		if(instance == null) instance = new NaverSearchRun();
		return instance;
	}
	
	public JSONObject run(String text) throws Exception {
		JSONObject json = null;
		String clientId = "EKHaO1yXy6n6_NKCUkoB"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "8wVTPUCmmE"; //애플리케이션 클라이언트 시크릿값"
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // json 결과

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
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String result = "";
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				result += str;
			}
			json = new JSONObject(result);
			json.put("responseCode", responseCode);
			
			br.close();
			con.disconnect();
			
			if(responseCode != 200) {
				throw new Exception(""+json);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
