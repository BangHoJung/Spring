package papago;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	Papago papago;
	public MainController(Papago papago) {
		super();
		this.papago = papago;
	}

	@RequestMapping("/")
	public String inputText() {
		return "input_text";
	}
	
	@RequestMapping("translate.do")
	public void translate(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("html/text;charset=utf-8");
		String param = request.getQueryString();
		System.out.println("param : " + param);
		try {
			JSONObject obj = papago.transalte(param);
		    if(obj.getInt("responseCode") != 200) {
		        FileWriter fw = new FileWriter("error.txt",true);
				PrintWriter pw = new PrintWriter(fw);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				Calendar now =  Calendar.getInstance();
				String error = sdf.format(now.getTime()) + "\t" + obj.getInt("responseCode") + "\t" + obj.getString("errorCode") + "\t" + obj.getString("errorMessage") +"\n";
				System.out.println(error);
				
				pw.write(error);
				pw.flush();
				pw.close();
				fw.close();
				
				response.getWriter().write(request.getParameter("text"));
		    }
		    else {
		    	String result = obj.getJSONObject("message").getJSONObject("result").getString("translatedText");
		        System.out.println(result);
		        response.getWriter().write(""+result);
		    }
		} catch (IOException e) {
			try {
				System.out.println(e.getMessage());
				response.sendError(1000);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
