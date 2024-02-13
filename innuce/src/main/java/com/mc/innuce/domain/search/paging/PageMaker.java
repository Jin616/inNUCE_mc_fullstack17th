package com.mc.innuce.domain.search.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.oracle.wls.shaded.org.apache.bcel.generic.NEW;

public class PageMaker {
	private int pageNum;
	private final int PAGECOUNT = 10;
	private int totalCount; // 게시글 총 갯수
	private int startPage; // 10개의 페이지중 첫번째
	private int endPage; // 10개의 페이지중 마지막
	private boolean prev; // 페이지 이전 버튼
	private boolean next; // 페이지 다음 버튼
	private int tempEndPage;
//	private Criteria cri=new Criteria(); // 페이지 정보 객체
//	private String keyword;

	public int getTotalCount() {
		return totalCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPAGECOUNT() {
		return PAGECOUNT;
	}

	public int getTempEndPage() {
		return tempEndPage;
	}

	public void setTempEndPage(int tempEndPage) {
		this.tempEndPage = tempEndPage;
	}

	public PageMaker() {
		super();
	}

	public PageMaker(int pageNum, int totalCount) {
		super();
		this.pageNum = pageNum;
		this.totalCount = totalCount;
		this.endPage = (int) (Math.ceil(pageNum / (double) PAGECOUNT) * PAGECOUNT);
		this.startPage = (endPage - PAGECOUNT) + 1;
		this.tempEndPage = (int) (Math.ceil(totalCount / (double) PAGECOUNT));
		this.prev = startPage == 1 ? false : true;
		this.next = endPage * PAGECOUNT >= totalCount ? false : true;

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
			// 마지막 게시물이 있는 페이지가 endPage로 다시 할당해준다.
		}
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

}
