package board.dto;

public class QnaDTO {
	private int qid;
	private String title;
	private String content;
	private String writer;
	private String qdate;
	private int status;
	private String response;
	
	
	public QnaDTO(String title, String content, String writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	public QnaDTO(int qid, String title, String content, String writer, String qdate, int status, String response) {
		super();
		this.qid = qid;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.qdate = qdate;
		this.status = status;
		this.response = response;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getQid() {
		return qid;
	}

	public String getWriter() {
		return writer;
	}

	public String getQdate() {
		return qdate;
	}

	@Override
	public String toString() {
		return "QnaDTO [qid=" + qid + ", title=" + title + ", content=" + content + ", writer=" + writer + ", qdate="
				+ qdate + ", status=" + status + ", response=" + response + "]";
	}
	
}
