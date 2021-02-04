package board.dto;

import java.io.File;

import org.apache.ibatis.type.Alias;

@Alias("file")
public class FileDTO {
	private int bno;
	private String path;
	private String writer;
	private String fileName;
	private String type;
	
	public FileDTO(int bno,String writer,File file) {
		super();
		this.bno = bno;
		this.path = file.getAbsolutePath();
		this.writer = writer;
		this.fileName = file.getName();
		//error.png 
		switch(fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase()) {
		case "png":
		case "bmp":
		case "jpg":
		case "gif":
			type="image";
			break;
		case "mp4":
		case "mkv":
			type="video";
			break;
		default:
			type="normal";
		}
	}
	
	public FileDTO(int bno, String writer, String path) {
		super();
		this.bno = bno;
		this.path = path;
		this.writer = writer;
		this.fileName = path.substring(path.lastIndexOf("\\")+1);
		switch(fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase()) {
		case "png":
		case "bmp":
		case "jpg":
		case "gif":
			type="image";
			break;
		case "mp4":
		case "mkv":
			type="video";
			break;
		default:
			type="normal";
		}
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public String getType() {
		return type;
	}
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
