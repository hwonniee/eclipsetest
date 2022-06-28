package com.board.vo;

public class SearchVO {
	private String searchType;	// 검색타입(제목/내용/제목+내용/작성자)
	private String searchText;	// 검색어
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	
}
