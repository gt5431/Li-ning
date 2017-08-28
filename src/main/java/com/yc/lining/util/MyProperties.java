package com.yc.lining.util;

import java.io.IOException;
import java.util.Properties;

public class MyProperties extends Properties {
	private static final long serialVersionUID = 1687847228656343770L;

	private static MyProperties mp;
	
	private MyProperties(){
		try {
			this.load(  this.getClass().getResourceAsStream("redis.properties")   );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MyProperties getInstance(){
		if(mp==null){
			mp=new MyProperties();
		}
		return mp;
	}
}
