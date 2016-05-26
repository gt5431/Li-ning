package com.yc.lining.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.entity.Usersinfo;
import com.yc.lining.service.GoodsinfoService;
import com.yc.lining.service.UserService;


@Controller("goodsinfoAction")
public class GoodsinfoAction implements ModelDriven<Goodsinfo>,SessionAware{

	@Autowired
	private GoodsinfoService goodsinfoService;
	private Goodsinfo goodsinfo;
	private Map<String, Object> session;

	public String FindGoods(){
		//LogManager.getLogger().debug("取到的vsid值=" + item.getVsId());
		goodsinfo = goodsinfoService.FindGoodsById(15);
		String [] color =goodsinfo.getColor().split(",");
		String [] img =goodsinfo.getPro_img().split(",");
		System.out.println(goodsinfo);
		session.put("goodsinfo", goodsinfo);
		session.put("goodsinfocolor", color);
		System.out.println(color[0]);
		session.put("goodsinfoimg", img);
		return "findgoods";
	}

	@Override
	public Goodsinfo getModel() {
		goodsinfo = new Goodsinfo();
		return goodsinfo;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
