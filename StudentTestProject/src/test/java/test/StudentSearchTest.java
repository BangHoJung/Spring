package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class StudentSearchTest {

	@Test
	void searchTest() throws FileNotFoundException {
		String kind = "sno";
		String search = "BY";
		try {
			search = URLEncoder.encode(search,"utf-8");
			kind = URLEncoder.encode(kind,"utf-8");
			//3. url 완성 주소랑 파라미터(쿼리 스트링)을 조합
			String apiUrl = "http://localhost:9999/search_all.do?kind="+kind+"&search="+search;
			URL url ;
			url = new URL(apiUrl);
			//4. open connection 요청(데이터 전송)
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//5. inputstream 초기화 해서 받은 데이터 읽기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			//6. json이면 json 파싱해서 원하는 데이터만 추출 후 출력
			String result = "";
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				result += str;
			}
			System.out.println(result);
			JSONObject json = new JSONObject(result);
			if(json.getInt("code") == 500) {
				fail("ERROR");
			}
			JSONArray arr = json.getJSONArray("result");
			for(int i=0;i<arr.length();i++) {
				JSONObject obj = arr.getJSONObject(i);
				System.out.println(obj.getString("sno"));
				System.out.println(obj.getString("name"));
				System.out.println(obj.getString("major"));
				System.out.println(obj.getDouble("score"));
			}
			
			br.close( );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			fail(e.getMessage());
		}
		
	}

}
