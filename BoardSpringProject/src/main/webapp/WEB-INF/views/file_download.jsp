<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//다운로드 할 파일명
	String fileName = request.getParameter("fileName");
	String writer = request.getParameter("writer");
	//다운로드할 파일 전체 경로
	String path = "C:\\fileupload\\"+writer+"\\"+fileName;
	System.out.println("다운로드할 파일 전체 경로 : "+path);
	File file = new File(path);
	FileInputStream fis = new FileInputStream(file);
	
	fileName = URLEncoder.encode(fileName,"utf-8");
	String encodingName = URLEncoder.encode(path,"utf-8");
	
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
	
%>