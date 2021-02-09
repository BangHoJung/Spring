package book;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	private NaverBookSearch bookSearch;
	
	public MainController(NaverBookSearch bookSearch) {
		super();
		this.bookSearch = bookSearch;
	}

	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	@RequestMapping("search_book.do")
	public void searchBook(HttpServletRequest request, HttpServletResponse response) {
		String search = request.getParameter("search");
		int start = Integer.parseInt(request.getParameter("start"));
		try {
			search = URLEncoder.encode(search, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패",e);
		}
		
		try {
			response.setContentType("html/text;charset=utf-8");
			response.getWriter().write(""+bookSearch.searchBook(search,start));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
