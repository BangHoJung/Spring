import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class MemberSearchMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 이름 입력 : "); String search = sc.nextLine();
		
		//HTTP로 데이터 요청
		//1. 데이터를 요청할 AP주소를 문자열로 선언
		//2. 전달할 파라미터를 인코딩 작업
		try {
			search = URLEncoder.encode(search,"utf-8");
			//3. url 완성 주소랑 파라미터(쿼리 스트링)을 조합
			String apiUrl = "http://localhost:8000/member_manage_search.do?kind=name&search="+search;
			URL url;
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
			if(json.getInt("responseCode") == 500) {
				throw new Exception(json.getInt("responseCode") + "\t" +"response Error");
			}
			JSONArray arr = json.getJSONArray("result");
			for(int i=0;i<arr.length();i++) {
				JSONObject obj = arr.getJSONObject(i);
				System.out.println(obj.getString("id"));
				System.out.println(obj.getString("name"));
				System.out.println(obj.getInt("age"));
				System.out.println(obj.getString("grade_name"));
			}
			
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			FileOutputStream fos = new FileOutputStream("error.txt",true);
			PrintWriter pw = new PrintWriter(fos);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Calendar now =  Calendar.getInstance();
			String str = sdf.format(now.getTime()) + "\t" + e.getMessage()+"\n";
			System.out.println(str);
			pw.write(str);
			pw.flush();
			pw.close();
		}
	}

}
