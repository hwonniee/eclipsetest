package util;

public class PageHandler {
	private int totalCnt;		// 총 게시물 갯수
	private int pageSize;		// 한 페이지의 게시글 수
	private int naviSize = 10;	// 페이지 내비게이션의 크기(표시될 페이징 크기)
	private int totalPage;		// 전체 페이지의 갯수
	private int currPage;		// 현재 페이지
	private int beginPage;		// 내비게이션의 첫번째 페이지
	private int endPage;		// 내비게이션의 마지막 페이지
	private boolean showPrev;	// 이전 페이지로 이동하는 링크를 보여줄 것인지의 여부
	private boolean showNext;	// 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부
	
	public PageHandler(int totalCnt, int currPage) {
		this(totalCnt, currPage, 10);
	}
	
	public PageHandler(int totalCnt, int currPage, int pageSize) {
		this.totalCnt = totalCnt;
		this.currPage = currPage;
		this.pageSize = pageSize;
		
		totalPage = (int)Math.ceil(totalCnt / (double)pageSize);
		/*
		 *   1 2 3 4 5 6 7 8 9 10
		 *   11 12 13 14 15 16 17 18 19 20
		 *   21 22 23 24 25 26 27 28 29 30
		 *   31 32 33 
		 *   currPage 	 beginPage    beginPage는 현재 페이지에서 1의 자리를 없애고 + 1  
		 *   	5  	  |  	1   
		 *   	11	  |    11
		 *      15    |    11
		 *      25    |    21
		 */
		beginPage = (currPage - 1) / naviSize * naviSize + 1; 
		endPage = Math.min(beginPage + naviSize - 1, totalPage);
		showPrev = beginPage != 1;
		showNext = endPage != totalPage;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getNaviSize() {
		return naviSize;
	}

	public void setNaviSize(int naviSize) {
		this.naviSize = naviSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isShowPrev() {
		return showPrev;
	}

	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}

	public boolean isShowNext() {
		return showNext;
	}

	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}
}
