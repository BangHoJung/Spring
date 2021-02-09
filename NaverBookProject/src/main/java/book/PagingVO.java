package book;


/*
 * 현재 사용자가 조회하고 있는 페이지 정보
 * 현재 페이지 번호, 전체 글 개수, 한페이지당 출력할 글목록 개수
 * 게시판 하단에 나타낼 페이지 번호 개수
 * */
public class PagingVO {

	//전체 게시글 개수
	private int countAll;
	//한페이지당 출력할 게시글 개수
	private final int countPageContent = 7; 
	//게시판 하단에 나타낼 페이지 번호 개수
	private final int countPageGroup = 4;
	//현재 페이지 번호
	private int currPage;
	
	
	public PagingVO(int countAll, int currPage) {
		super();
		this.countAll = countAll;
		this.currPage = currPage;
	}
	
	public int getCountPageContent() {
		return countPageContent;
	}
	
	public int getCountPageGroup() {
		return countPageGroup;
	}
	
	public int getCurrPage() {
		return currPage;
	}
	
	public int getTotalPage() {
		int totalPage = countAll/countPageContent;
		if(countAll % countPageContent != 0) {
			totalPage++;
		}
		return totalPage;
	}
	
	public int getTotalPageGroup() {
		int totalGroup = getTotalPage()/countPageGroup;
		if(getTotalPage() % countPageGroup != 0) {
			totalGroup++;
		}
		return totalGroup;
	}
	
	public int getCurrPageGroup() {
		int result = currPage/countPageGroup;
		if(currPage % countPageGroup != 0) {
			result++;
		}
		return result;
	}
	
	public int getStartPageOfPageGroup() {
		return countPageGroup*(getCurrPageGroup()-1) + 1;
	}
	
	public int getEndPageOfPageGroup() {
		if(countPageGroup*getCurrPageGroup() <= getTotalPage()) {
			return countPageGroup*getCurrPageGroup();
		}
		else {
			return getTotalPage();
		}
	}
	
	public boolean isPrevPageGroup() {
		return getCurrPageGroup() > 1;
	}
	
	public boolean isNextPageGroup() {
		return getCurrPageGroup() < getTotalPageGroup();
	}

	@Override
	public String toString() {
		return "PagingVO [countAll=" + countAll + ", countPageContent=" + countPageContent + ", countPageGroup="
				+ countPageGroup + ", currPage=" + currPage + "]";
	}
	
	
}
