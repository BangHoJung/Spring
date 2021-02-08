package book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	@RequestMapping("search_book.do")
	public void searchBook(HttpServletRequest request, HttpServletResponse response) {
		String search = request.getParameter("search");
		
		String clientId = "EKHaO1yXy6n6_NKCUkoB"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "8wVTPUCmmE"; //애플리케이션 클라이언트 시크릿값"
        try {
            search = URLEncoder.encode(search, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        
		String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + search;    // json 결과
		
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
			JSONObject json = new JSONObject(result);
			System.out.println(json.toString());
			
			JSONArray arr = json.getJSONArray("items");
			JSONArray resultArr = new JSONArray();
			for(int i=0;i<arr.length();i++) {
				JSONObject tmp = new JSONObject();
				tmp.put("title", arr.getJSONObject(i).getString("title"));
				tmp.put("author", arr.getJSONObject(i).getString("author"));
				tmp.put("publisher", arr.getJSONObject(i).getString("publisher"));
				tmp.put("description", arr.getJSONObject(i).getString("description"));
				tmp.put("image", arr.getJSONObject(i).getString("image"));
				tmp.put("link", arr.getJSONObject(i).getString("link"));
				resultArr.put(tmp);
//				downloadImage(arr.getJSONObject(i).getString("image"), arr.getJSONObject(i).getString("link").split("bid=")[1]);
			}
			
			JSONObject resultObj = new JSONObject();
			resultObj.put("resultArr", resultArr);
			
			response.setContentType("html/text;charset=utf-8");
			response.getWriter().write(""+resultObj);
			
			br.close();
			con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
