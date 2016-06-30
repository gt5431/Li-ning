package com.yc.lining.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Orderform;
import com.yc.lining.entity.Place;
import com.yc.lining.entity.Place1;
import com.yc.lining.service.PlaceService;

@Controller("place1Action")
public class Place1Action implements ModelDriven<Place1>,SessionAware {
	private Place1 place;
	private Map<String, Object> session;
	@Autowired
	private PlaceService placeService;
	private Object places;
	public Object getPlaces() {
		return places;
	}
	public void setPlaces(Object places) {
		this.places = places;
	}
	public String listAllPlace(){
		places=placeService.listAllPlace();
		return "placeJson";
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	@Override
	public Place1 getModel() {
		place=new Place1();
		return place;
	}

	
}
