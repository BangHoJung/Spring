package papago;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Papago {
	
	private String clientId = "bcYyPr43fvUkuiRmicew";//애플리케이션 클라이언트 아이디값";
    private String clientSecret = "bunk0g2Lu_";//애플리케이션 클라이언트 시크릿값";

	public JSONObject transalte(String param) throws IOException {
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(param);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        
        String inputLine;
        StringBuffer resbuf = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
        	resbuf.append(inputLine);
        }
        br.close();
        System.out.println(resbuf.toString());
        JSONObject obj = new JSONObject(resbuf.toString());
        obj.put("responseCode", responseCode);
        return obj;
		
	}

}
