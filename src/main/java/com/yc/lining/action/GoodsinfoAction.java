package com.yc.lining.action;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.entity.Item;
import com.yc.lining.service.GoodsinfoService;


@Controller("goodsinfoAction")
public class GoodsinfoAction implements SessionAware,ModelDriven<Item>{
	private Item item;
	@Autowired
	private GoodsinfoService goodsinfoService;
	private Map<String, Object> session;
	
	
	public String FindGoods(){
		System.out.println("查找商品");
		//LogManager.getLogger().debug("查找商品");
		//LogManager.getLogger().debug("取到的pro_number值=" + item.getPro_number());
		System.out.println("pro_number的值"+item.getPro_number());
		Goodsinfo goodsinfo = goodsinfoService.FindGoodsById(item.getPro_number());
		session.put("goodsinfo", goodsinfo);
		System.out.println(goodsinfo);
		System.out.println("查找商品方法结束");
		return "findgoods";
	}
	@Override
	public Item getModel() {
		item = new Item();
		return item;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
