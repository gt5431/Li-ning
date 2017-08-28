package com.yc.lining.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Orderform;
import com.yc.lining.entity.Place;
import com.yc.lining.service.PlaceService;

@Controller("placeAction")
public class PlaceAction implements ModelDriven<Place>,SessionAware {
	private Place place;
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
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	@Override
	public Place getModel() {
		place=new Place();
		return place;
	}

	
}
