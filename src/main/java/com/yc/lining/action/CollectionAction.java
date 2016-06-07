package com.yc.lining.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Collection02;
import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.service.CollectionService;

@Controller("collectionAction")
public class CollectionAction implements ModelDriven<Collection02>{
	@Autowired
	private CollectionService collectionService;

	private Collection02 collection;
	private List<Goodsinfo> collectList;
	
	public List<Goodsinfo> getCollectList() {
		return collectList;
	}

	public void setCollectList(List<Goodsinfo> collectList) {
		this.collectList = collectList;
	}

	public void Collections(){
		System.out.println("收藏的信息为==>"+collection);
		boolean flag;
		flag = collectionService.CollectionGoods(collection);
		System.out.println("插入结果为==>"+flag);
	}
	
	public String findCollectionById(){
		//收藏
		collectList = collectionService.CollectionById(collection.getU_id());
		System.out.println("收藏id为==>"+collectList);
		return "success";
	}
	
	public String list(){
		return "listAll";
	}

	@Override
	public Collection02 getModel() {
		collection = new Collection02();
		return collection;
	}
}
