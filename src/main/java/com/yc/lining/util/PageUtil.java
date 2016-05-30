package com.yc.lining.util;

//��ҳ
public class PageUtil {
	private int pageNo =1;
	private int pageSize =8;
	private int totalSize;  //�ܼ���
	private int totalPages; //��ҳ��  
	
	public int getPageNo() {
		return pageNo;
	}
	
	
	public PageUtil() {
	}


	public PageUtil(int pageNo, int pageSize) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}



	@Override
	public String toString() {
		return "PageUtil [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalSize=" + totalSize + ", totalPages=" + totalPages
				+ "]";
	}

	//��һҳ
	public int getNextPageNo(){
		int nextPageNo =1;
		if(this.pageNo<this.getTotalPages()){//�����ǰҳС����ҳ��
			nextPageNo =pageNo+1;
		}else{
			nextPageNo=this.getTotalPages();
		}
		return nextPageNo;
	}
	
	//��һҳ
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
			pageSize=3;//Ĭ��ÿҳ��ʾ3��
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
	
	//��ҳ��
	public int getTotalPages() {
		return this.getTotalSize()%this.getPageSize()==0?this.getTotalSize()/this.getPageSize():this.getTotalSize()/this.getPageSize()+1;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}
