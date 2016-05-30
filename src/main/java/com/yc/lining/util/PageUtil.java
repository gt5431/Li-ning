package com.yc.lining.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


//分页
public class PageUtil {
	private int pageNo =1;
	private int pageSize =8;
	private int totalSize;  //总计数
	private int totalPages; //总页数  
	public int getPageNo() {
		return pageNo;
	}
	
	@Override
	public String toString() {
		return "\nPageUtil [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalSize=" + totalSize + ", totalPages=" + totalPages
				+ "]";
	}

	//下一页
	public int getNextPageNo(){
		int nextPageNo =1;
		if(this.pageNo<this.getTotalPages()){//如果当前页小于总页数
			nextPageNo =pageNo+1;
		}else{
			nextPageNo=this.getTotalPages();
		}
		return nextPageNo;
	}
	
	//上一页
	public int getProPageNo(){
		int proPageNo=1;
		if(pageNo>1){
			proPageNo =pageNo-1;
		}else{
			proPageNo=1;
		}
		return proPageNo;
	}
	
	public void setPageNo(int pageNo) {
		if(pageNo<1){
			pageNo=1;
		}else{
			this.pageNo = pageNo;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<=0){
			pageSize=3;//默认每页显示3条
		}else{
			this.pageSize = pageSize;
		}
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		if(totalSize<0){
			totalSize=0;
		}else{
			this.totalSize = totalSize;
		}
	}
	
	//总页数
	public int getTotalPages() {
		return this.getTotalSize()%this.getPageSize()==0?this.getTotalSize()/this.getPageSize():this.getTotalSize()/this.getPageSize()+1;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public PageUtil(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public PageUtil() {
		
	}
	
	
}
