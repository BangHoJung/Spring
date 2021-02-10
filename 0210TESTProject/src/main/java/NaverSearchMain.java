import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class NaverSearchMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어 입력 : "); String text = sc.nextLine();
		try {
			JSONObject json = NaverSearchRun.getInstance().run(text);
//			System.out.println(json.toString());
			JSONArray arr = json.getJSONArray("items");
			String result = "검색어 : "+text+"\n";
			for(int i=0;i<arr.length();i++) {
				result += "title : " + (arr.getJSONObject(i)).get("title")+"\n";
				result += "link : " + (arr.getJSONObject(i)).get("link")+"\n";
				result += "bloggername : " + (arr.getJSONObject(i)).get("bloggername")+"\n";
			}
			result += "\n";
			System.out.println(result);
			FileWriter fw = new FileWriter("blog_search.txt",true);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(result);
			pw.flush();
			
			pw.close();
			fw.close();
		} catch (Exception e) {
//			e.printStackTrace();
			try { //에러메시지가 json형태가 맞을때
				JSONObject json = new JSONObject(e.getMessage());
//				System.out.println(json.toString());
				FileWriter fw = new FileWriter("exception.txt",true);
				PrintWriter pw = new PrintWriter(fw);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				Calendar now =  Calendar.getInstance();
				String error = sdf.format(now.getTime()) + "\t" + json.getInt("responseCode") + "\t" + json.getString("errorCode") + "\t" + json.getString("errorMessage") +"\n";
				System.out.println(error);
				
				pw.write(error);
				pw.flush();
				pw.close();
				fw.close();
			} catch(Exception e1) {
				e1.printStackTrace();
			}

		}
	}

}
