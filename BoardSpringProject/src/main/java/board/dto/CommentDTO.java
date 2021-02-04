package board.dto;

import org.apache.ibatis.type.Alias;

@Alias("comment")
public class CommentDTO {
	private int cno;
	private int bno;
	private String content;
	private String date;
	private String writer;
	private int like;
	private int hate;
	
	
	
	public CommentDTO(int bno, String content, String writer) {
		super();
		this.bno = bno;
		this.content = content;
		this.writer = writer;
	}
	
	public CommentDTO(int cno, int bno, String content, String date, String writer, int like, int hate) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.content = content;
		this.date = date;
		this.writer = writer;
		this.like = like;
		this.hate = hate;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getHate() {
		return hate;
	}

	public void setHate(int hate) {
		this.hate = hate;
	}

	@Override
	public String toString() {
		return "CommentDTO [cno=" + cno + ", bno=" + bno + ", content=" + content + ", date=" + date + ", writer="
				+ writer + ", like=" + like + ", hate=" + hate + "]";
	}

	
	
}
