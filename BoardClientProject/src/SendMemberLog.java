import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SendMemberLog {

	public static void main(String[] args) {
		
		try {
			FileReader fr = new FileReader("error.txt");
			BufferedReader br = new BufferedReader(fr);
			
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				sendLog(str.split("\t"));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void sendLog(String[] arr) {
		
		String queryString = "";
		String[] paramArr = {"log_date","code_number","message"};
		try {
			for(int i=0; i<arr.length;i++) {
				queryString += paramArr[i] + "=" + URLEncoder.encode(arr[i],"utf-8") + "&";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		try {
			String apiUrl = "http://localhost:8000/sendLog.do?" + queryString;
			URL url;
			url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
