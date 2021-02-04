package board;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.dto.BoardDTO;
import board.dto.CommentDTO;
import board.dto.FileDTO;
import board.dto.QnaDTO;
import board.service.BoardService;
import board.service.MemberService;
import board.service.QnaService;
import board.vo.MemberVO;
import board.vo.PagingVO;
import exception.BoardException;

@Controller
public class MainController {
	
	private MemberService memberService;
	private BoardService boardService;
	private QnaService qnaService;

	public MainController(MemberService memberService, BoardService boardService, QnaService qnaService) {
		super();
		this.memberService = memberService;
		this.boardService = boardService;
		this.qnaService = qnaService;
	}
	
	@RequestMapping("/")
	public String index() {
		System.out.println("main");
		return "main";
	}
	@RequestMapping("main.do")
	public String main(HttpServletRequest request,HttpSession session) {
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		String sort = request.getParameter("sort");

		List<BoardDTO> board_list = boardService.searchAllBoardDTO(currPage,sort);
		HashMap<Integer, Integer> map = boardService.countAllComment();
		int count = boardService.countAllBoard();
		PagingVO pageVO = new PagingVO(count,currPage);
		
		request.setAttribute("board_list", board_list);
		request.setAttribute("map", map);
		request.setAttribute("pageVO", pageVO);
		request.setAttribute("currPage", currPage);
		request.setAttribute("sort", sort);
		
		String param = "";
		if(request.getQueryString()!=null) {
			param += "?"+request.getQueryString();
		}
		session.setAttribute("last", request.getRequestURI()+param);
		session.setAttribute("lastBoard", request.getRequestURI()+param);
		System.out.println("last : " + request.getRequestURI()+param);
		
		session.setMaxInactiveInterval(60*30);
		return "main";
	}
	
	@RequestMapping("login_view.do")
	public String loginView() {
		return "member/login";
	}
	
	@RequestMapping("login.do")
	public String login(HttpServletRequest request,HttpServletResponse response ,HttpSession session) {
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		MemberVO vo = memberService.loginMember(id,pass);
		
		if(vo != null) {
			String last = (String) session.getAttribute("last");
			last = last.split("/")[1];
			session.setAttribute("login", true);
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("grade", vo.getGrade());
			session.setAttribute("grade_name", vo.getGrade_name());
			try {
				response.getWriter().write("<script>location.href='"+last+"';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			session.setAttribute("login", false);
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('일치하는 계정이 없습니다.');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String last = (String) session.getAttribute("last");
		last = last.split("/")[1];
		String lastBoard = (String) session.getAttribute("lastBoard");
		lastBoard = lastBoard.split("/")[1];
		session.invalidate();
		session = request.getSession();
		session.setAttribute("last", last);
		session.setAttribute("lastBoard", lastBoard);
		try {
			response.getWriter().write("<script>location.href='"+last+"';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("update_member_view.do")
	public String updateMemberView(HttpSession session,HttpServletRequest request ) {
		String id = (String) session.getAttribute("id");
		MemberVO vo = memberService.searchMember(id);
		request.setAttribute("vo", vo);
		session.setMaxInactiveInterval(60*30);
		return "member/update_member_view";
	}
	
	@RequestMapping("update_member.do")
	public String updateMember(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		
		String id = (String)session.getAttribute("id");
		if(id == null) {
			try {
				response.setContentType("type/html; charset=utf-8");
				response.getWriter().write("<script>alert('로그인 세션이 만료되었습니다');location.href='main.do';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		memberService.updateMember(id,pass,name,age);
		session.setMaxInactiveInterval(60*30);
		
		return "main";
	}
	
	@RequestMapping("member_manage.do")
	public String MemberManagee(HttpSession session,HttpServletResponse response, HttpServletRequest request) {
		if(session.getAttribute("login") == null || !session.getAttribute("grade_name").equals("master")) {
			try {
				response.getWriter().write("<script>alert('권한이 없습니다.');history.back();</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "member/member_manage";
	}
	
	@RequestMapping("member_manage_search.do")
	public void MemberManageSearch(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		if(search.equals(null)) search="";
		
		List<MemberVO> list =  memberService.memberManageSearch(kind,search);
		
		JSONObject jsonObject = new JSONObject();
    	jsonObject.put("kind",kind);
    	jsonObject.put("search",search);
    	JSONArray jsonArray = new JSONArray(list);
    	
    	jsonObject.put("result",jsonArray);
    	if(jsonArray.length() > 0) {
    		jsonObject.put("responseCode", 200);
    	}
    	else {
    		jsonObject.put("responseCode", 500);
    	}
    	try {
			response.getWriter().write((jsonObject.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("member_manage_update.do")
	public void MemberManageUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		int grade = Integer.parseInt(request.getParameter("grade"));
		int count = memberService.memberManageUpdate(new MemberVO(id, null, name, age, grade));
		if(count == 0) {
			try {
				response.sendError(1000);
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			response.getWriter().write(""+count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("member_manage_delete.do")
	public void MemberManageDelete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int count = memberService.memberManageDelete(id);
		if(count == 0) {
			try {
				response.sendError(1000);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			response.getWriter().write(""+count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("sendLog.do")
	public void sendLog(HttpServletRequest request, HttpServletResponse response) {
		String log_date = request.getParameter("log_date");
		int code_number = Integer.parseInt(request.getParameter("code_number"));
		String message = request.getParameter("message");
		
		System.out.println(log_date + "," + code_number + "," + message);
		int count = memberService.insertLog(log_date, code_number ,message);
		System.out.println(count);
		try {
			response.getWriter().write("count : " + count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("board_view.do")
	public String boardView(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		if(request.getAttribute("bno") != null) {
			request.removeAttribute("bno");
		}
		BoardDTO dto = null;
		int bno = 0;
		
		bno = Integer.parseInt(request.getParameter("bno"));
		try {
			dto =  boardService.searchBoardDTO(bno);
		} catch (BoardException e) {
			try {
				response.getWriter().write("<script>alert('"+e.getMessage()+"');history.back();</script>");
				return null;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		List<CommentDTO> comment_list = boardService.searchAllCommentDTO(bno);
		List<FileDTO> fList = boardService.searchFileList(dto.getBno(),dto.getWriter());
		
		request.setAttribute("dto", dto);
		request.setAttribute("comment_list", comment_list);
		request.setAttribute("file_list", fList);
		
		String param = "";
		if(request.getQueryString()!=null) {
			param += "?"+request.getQueryString();
		}
		session.setAttribute("last", request.getRequestURI()+param);
		System.out.println("last : " + request.getRequestURI()+param);
		return "board/board_view";
	}
	
	@RequestMapping("insert_comment.do")
	public void insertComment(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		int count = boardService.insertBoardComment(new CommentDTO(bno,content,writer));
		
		try {
			response.getWriter().write(""+count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("comment_view_sort.do")
	public void commentViewSort(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		String sort = request.getParameter("sort");
		int bno = Integer.parseInt(request.getParameter("bno"));
		System.out.println(sort + " " + bno);
		
		List<CommentDTO> comment_list = boardService.searchSortCommentDTO(bno,sort);
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray(comment_list);
		obj.put("comment_list",comment_list);
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("update_like_hate.do")
	public void updateLikeHate(HttpServletRequest request, HttpServletResponse response) {
		String lh = request.getParameter("lh");
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		int count = boardService.addLikeHateBoardDTO(bno,lh);
		if(count == 0)
			try {
				response.sendError(1000);
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		try {
			response.getWriter().write(""+boardService.searchLikeHateCount(bno,lh));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("update_comment_like_hate.do")
	public void updateCommentLikehate(HttpServletRequest request, HttpServletResponse response) {
		String lh = request.getParameter("lh");
		int cno = Integer.parseInt(request.getParameter("cno"));
		
		int count = boardService.addLikeHateCommentDTO(cno,lh);
		if(count == 0)
			try {
				response.sendError(1000);
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		try {
			response.getWriter().write(""+boardService.searchCommentLikeHateCount(cno,lh));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("write_board_view.do")
	public String writeBoardView(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		if(session.getAttribute("login") == null || !(boolean)session.getAttribute("login")) {
			try {
				response.getWriter().write("<script>alert('로그인이 필요합니다');location.href='"+session.getAttribute("lastBoard")+"';</script>");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "board/board_write_view";
	}
	
	@RequestMapping("insert_board.do")
	public String insertBoard(MultipartHttpServletRequest mprequest, HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		List<MultipartFile> fileList = mprequest.getFiles("file");
		System.out.println(fileList.size());
		String path = "C:\\fileupload\\"+writer+"\\";
		ArrayList<FileDTO> fList = new ArrayList<FileDTO>();
		
		BoardDTO board = new BoardDTO(title,writer,content);
		int bno = 0;
		try {
			bno = boardService.nextBno();
			board.setBno(bno);
			boardService.insertBoardDTO(board);
			System.out.println("file bno :"+bno);
			if(bno == 0) throw new BoardException("BNO 생성 에러");
			
			for(MultipartFile mf : fileList) {
				String originalFileName = mf.getOriginalFilename();
				long fileSize = mf.getSize();
				if(fileSize == 0) continue;
				System.out.println("originalFileName : " + originalFileName);
				System.out.println("fileSize : " + fileSize);
				File parentPath = new File(path);
				if(!parentPath.exists()) parentPath.mkdirs();
				
				//파일 업로드
				fList.add(new FileDTO(bno, writer, originalFileName));
				File pathFile = new File(path + originalFileName);
				mf.transferTo(pathFile);
			}
			boardService.insertFileList(fList);
		} catch (BoardException e) {
			try {
				response.getWriter().write("<script>alert('"+e.getMessage()+"');history.back();</script>");
				return null;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("bno", bno);
		return "main";
		
	}
	
	@RequestMapping("file_download.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//다운로드 할 파일명
		String fileName = request.getParameter("fileName");
		String writer = request.getParameter("writer");
		//다운로드할 파일 전체 경로
		String path = "C:\\fileupload\\"+writer+"\\"+fileName;
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		
		fileName = URLEncoder.encode(fileName,"utf-8");
		
		//다운로드시 나타낼 기본파일명
		response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLengthLong(file.length());
		
		//사용자에게 파일을 전송
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		//버퍼 생성
		byte[] buffer = new byte[1024*1024];
		
		while(true) {
			int size = fis.read(buffer); // 읽어온 바이트수
			if(size == -1) {
				break;
			}
			bos.write(buffer,0,size);
			bos.flush();
		}
		
		fis.close();
		bos.close();
	}
	
	@RequestMapping("image_load.do")
	public String imageLoad(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String fileName = request.getParameter("fileName");
		String type = fileName.substring(fileName.lastIndexOf(".")+1);
		
		response.setContentType("image/"+type);
		File path = new File("C:\\fileupload\\"+writer+"\\"+fileName);
		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = response.getOutputStream();
			
			byte[] buffer = new byte[1024*1024];
			while(true) {
				int size = fis.read(buffer);
				if(size == -1) break;
				sos.write(buffer,0,size);
				sos.flush();
			}
			sos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("prev_board.do")
	public String prevBoard(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardDTO dto = boardService.prevBoardDTO(bno);
		if(dto == null) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('더 이상 이전글이 없습니다');history.back();</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("bno", dto.getBno());
		return "main";
	}
	
	@RequestMapping("next_board.do")
	public String nextBoard(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardDTO dto = boardService.nextBoardDTO(bno);
		if(dto == null) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('더 이상 다음글이 없습니다');history.back();</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("bno", dto.getBno());
		return "main";
	}
	
	@RequestMapping("qna_view.do")
	public String qnaView(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		//관리자인 경우에는 모든 사용자의 문의목록 읽어옴.
		if(session.getAttribute("login") == null || !(boolean)session.getAttribute("login")) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('로그인이 필요합니다');location.href='"+session.getAttribute("lastBoard")+"';</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String grade_name = (String) session.getAttribute("grade_name");
		if(grade_name.equals("master")) {
			return "qna/qna_master";
		}
		else {
			return "qna/qna";
		}

	}
	
	@RequestMapping("qna_nextList.do")
	public void qnaNextList(HttpServletRequest request, HttpServletResponse response) {
		//1.조회할 페이지 번호 읽어옴
		String writer = (String)request.getSession().getAttribute("id");
		int nextPage = Integer.parseInt(request.getParameter("nextPage"));
		String grade_name = (String)request.getSession().getAttribute("grade_name");
		//2.해당 페이지 목록을 읽어옴 - list
		List<QnaDTO> list = qnaService.searchQnaList(writer, nextPage, grade_name);
		//3.다음페이지 번호 / 다음페이지 없으면 0
		if(qnaService.searchQnaList(writer, nextPage+1, grade_name).size()==0) {
			nextPage = 0;
		}
		else {
			nextPage++;
		}
		//4. JSON으로 변환(QnaDTO, 다음페이지 번호)
		JSONObject jsonObject = new JSONObject();
    	jsonObject.put("nextPage",nextPage);
    	JSONArray jsonArray = new JSONArray(list);
    	jsonObject.put("array",jsonArray);
		//5. writer로 출력
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("register_qna.do")
	public String registerQna(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = (String)request.getSession().getAttribute("id");
		
		QnaDTO dto = new QnaDTO(title, content, writer);
		try {
			qnaService.insertQnaDTO(dto);
		} catch (Exception e) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<script>alert('"+e.getMessage()+"');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		
		return "qna/qna";
	}
	
	@RequestMapping("qna_master_view.do")
	public String qnaMasterView(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setContentType("text/html;charset=utf-8");
		if(session.getAttribute("login") == null || !(boolean)session.getAttribute("login")) {
			try {
				response.getWriter().write("<script>alert('로그인이 필요합니다');location.href='"+session.getAttribute("lastBoard")+"';</script>");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int qid = Integer.parseInt(request.getParameter("qid"));
		int status = Integer.parseInt(request.getParameter("status"));
		if(status == 0) status = 1;
		QnaDTO dto = qnaService.searchQnaDTO(qid,status);
		
		if(dto != null) {
			request.setAttribute("dto", dto);
			return "qna/qna_master_view";
		}
		else {
			try {
				response.getWriter().write("<script>alert('해당 문의글을 찾을 수 없습니다.');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	@RequestMapping("update_qna_response.do")
	public String updateQnaResponse(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		int qid = Integer.parseInt(request.getParameter("qid"));
		String answer = request.getParameter("response");
		
		int count = qnaService.updateQnaResponse(qid,answer,2);
		if(count == 0) {
			try {
				response.getWriter().write("<script>alert('답변 등록에 실패했습니다');location.href='qna_master_view.do';");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		else {
			QnaDTO dto = qnaService.searchQnaDTO(qid,2);
			request.setAttribute("dto", dto);
			return "qna/qna_master_view";
		}
		
	}
}
