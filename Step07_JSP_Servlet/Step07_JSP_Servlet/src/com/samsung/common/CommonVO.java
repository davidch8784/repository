package com.samsung.common;

public class CommonVO {
	private String searchCondition;
	private String searchKeyword;
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	// 부모클래스로 사용할 예정
	// 자식의 toString에서 출력할 예정
}
