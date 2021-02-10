import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
public class WeatherTestMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String serviceKey,pageNo,numOfRows,dataType,baseDate,baseTime,nx,ny;
		serviceKey = "Iu2FcMrXODOm6ILvnOSYSEVxtdhLg3xIqgpJtRmdE6b02EoyADD2wB2Apdyw0xMFy4uY4bYeAe1sLkxIRZC%2F5w%3D%3D";
		nx = args[3]; ny = args[4];
		int time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		int[] arr = new int[] {2,5,8,11,14,17,20,23};
		int i;
		for(i=0;i<arr.length;i++) {
			if(arr[i] > time) break;
		}
		if(i == 0) { // 02시 미만 -> 전날
			i = arr.length-1;
			cal.add(Calendar.DATE, -1);
		}
		else { // 02시 이후 -> 오늘날 
			i--;
		}
		baseDate = sdf.format(cal.getTime());
		baseTime = arr[i]+"00";
		pageNo = "1";
		numOfRows = "100";
		dataType = "json";
		
		
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("Iu2FcMrXODOm6ILvnOSYSEVxtdhLg3xIqgpJtRmdE6b02EoyADD2wB2Apdyw0xMFy4uY4bYeAe1sLkxIRZC%2F5w%3D%3D", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*15년 12월 1일발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*05시 발표*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());
        JSONObject json = new JSONObject(sb.toString());
        JSONObject items = json.getJSONObject("response").getJSONObject("body").getJSONObject("items");
        JSONArray item = new JSONArray(items.getJSONArray("item"));
//        System.out.println(items.toString());
        String result = "",fTime="",fDate="",TMN="",TMX="";
        for(i=0;i<item.length();i++) {
        	if(!fDate.equals(item.getJSONObject(i).getString("fcstDate"))) {
        		System.out.println(result+"\n");
        		fDate = item.getJSONObject(i).getString("fcstDate");
        		System.out.println(fDate+" "+args[0]+" "+args[1]+" "+args[2]+" 날씨정보");
        	}
        	String category = item.getJSONObject(i).getString("category");
        	if(!fTime.equals(item.getJSONObject(i).getString("fcstTime"))) {
        		result += "\n" + item.getJSONObject(i).getString("fcstTime") +" //\t ";
        		fTime = item.getJSONObject(i).getString("fcstTime");
        	}
        	switch(category) {
        	case "POP" :
        		result += "강수확률:"+item.getJSONObject(i).getString("fcstValue")+"% , ";
        		break;
        	case "T3H" :
        		result += "현재온도:"+item.getJSONObject(i).getString("fcstValue")+"C";
        		break;
        	case "TMN" :
        		result = "최저온도:"+item.getJSONObject(i).getString("fcstValue")+"C" +" / "+ result;
        		break;
        	case "TMX" :
        		result = "최고온도:"+item.getJSONObject(i).getString("fcstValue")+"C" + result;
        		break;
        	}
        }
        System.out.println(result);
	}
	
	public static void weather(String nx, String ny) {
		
	}

}
