package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendLogJob implements Job{
	public void execute(JobExecutionContext context) throws JobExecutionException {
			
		try {
			File file = new File("error.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				sendLog(str.split("\t"));
			}
			br.close();
			fr.close();
			System.out.println("파일 삭제 : " +file.delete());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void sendLog(String[] split) throws IOException {
		String queryString = "";
		String[] paramArr = {"log_date","error_code","content"};
		
		for(int i=0;i<split.length;i++) {
			queryString += paramArr[i] + "=" + URLEncoder.encode(split[i],"utf-8") + "&";
		}
		String apiUrl = "http://localhost:9999/send_log.do?" + queryString;
		URL url = new URL(apiUrl);
		//API 요청 -> 데이터 전송
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//결과를 버퍼로 받음
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		System.out.println(br.readLine());
		br.close();
		conn.disconnect();
		
	}
	
}
