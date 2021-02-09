import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class searchBookMain {
	
	public static void main(String[] args) {
		
		String clientId = "EKHaO1yXy6n6_NKCUkoB"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "8wVTPUCmmE"; //애플리케이션 클라이언트 시크릿값"
        Scanner sc = new Scanner(System.in);
        String text = null;
        try {
        	System.out.print("책 검색 입력 : ");
            text = URLEncoder.encode(sc.nextLine(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        
		String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text;    // json 결과
		
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
			for(int i=0;i<arr.length();i++) {
				System.out.println(arr.getJSONObject(i).getString("title"));
				System.out.println(arr.getJSONObject(i).getString("link"));
				System.out.println(arr.getJSONObject(i).getString("description"));
				downloadImage(arr.getJSONObject(i).getString("image"), arr.getJSONObject(i).getString("link").split("bid=")[1]);
			}
			
			br.close();
			con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//웹에 있는 이미지 다운로드
	public static void downloadImage(String url, String title) {
		try {
			URL imgUrl = new URL(url);
			URLConnection conn = imgUrl.openConnection();//이미지 파일과 연결
			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(title+".jpg");
			byte[] arr = new byte[1024];
			while(true) {
				int count = is.read(arr);
				if(count == -1) break;
				fos.write(arr,0,count);
			}
			
			fos.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
