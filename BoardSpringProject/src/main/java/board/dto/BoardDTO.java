package board.dto;

import org.apache.ibatis.type.Alias;

@Alias("board")
public class BoardDTO {
	private int bno;
	private String title;
	private String date;
	private int count;
	private String writer;
	private String content;
	private int like;
	private int hate;
	
	
	
	
	public BoardDTO(String title, String writer, String content) {
		super();
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	
	public BoardDTO(int bno, String title, String date, int count, String writer, String content, int like, int hate) {
		super();
		this.bno = bno;
		this.title = title;
		this.date = date;
		this.count = count;
		this.writer = writer;
		this.content = content;
		this.like = like;
		this.hate = hate;
	}



	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "BoardDTO [bno=" + bno + ", title=" + title + ", date=" + date + ", count=" + count + ", writer="
				+ writer + ", content=" + content + ", like=" + like + ", hate=" + hate + "]";
	}

	
	
}
