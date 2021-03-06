import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//HTTP로 데이터 요청
		//1. 데이터를 요청할 AP주소를 문자열로 선언
		//2. 전달할 파라미터를 인코딩 작업
		try {
			//3. url 완성 주소랑 파라미터(쿼리 스트링)을 조합
			String apiUrl = "https://www.naver.com";
			URL url;
			url = new URL(apiUrl);
			//4. open connection 요청(데이터 전송)
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//5. inputstream 초기화 해서 받은 데이터 읽기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = "";
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				result += str +"\n";
			}
			System.out.println(result);
			//6. json이면 json 파싱해서 원하는 데이터만 추출 후 출력
			
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
