package com.yc.lining.action;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Goodsinfo;
import com.yc.lining.entity.Product;
import com.yc.lining.entity.Typesinfo;
import com.yc.lining.entity.Usersinfo;
import com.yc.lining.service.ProductService;
import com.yc.lining.service.TypeInfoService;
import com.yc.lining.service.UserService;

@Controller("userAction")
public class UserAction implements ModelDriven<Usersinfo>,SessionAware{

	private static ShardedJedisPool pool;
	static{
		ResourceBundle bundle=ResourceBundle.getBundle("redis");
		if(bundle==null){
			throw new IllegalAccessError("[redis.properties] is not found");
		}

		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWaitMillis(Integer.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));


		JedisShardInfo jedisShardInfo1=new JedisShardInfo(bundle.getString("redis.ip"),Integer.valueOf(bundle.getString("redis.port")));
		JedisShardInfo jedisShardInfo2=new JedisShardInfo(bundle.getString("redis.ip1"),Integer.valueOf(bundle.getString("redis.port1")));

		List<JedisShardInfo> list=new LinkedList<JedisShardInfo>();
		list.add(jedisShardInfo1);
		list.add(jedisShardInfo2);

		pool=new ShardedJedisPool(config,list);
	}


	@Autowired
	private UserService userService;

	@Autowired
	private TypeInfoService typesService;
	@Autowired
	private ProductService productService;

	private Usersinfo usersinfo;
	private List<Goodsinfo> collection;
	private Map<String, Object> session;
	private Usersinfo usersinfo1;
	
	//��̨
	private List<Usersinfo> listUsers;
	public List<Usersinfo> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Usersinfo> listUsers) {
		this.listUsers = listUsers;
	}

	public Usersinfo getUsersinfo1() {
		return usersinfo1;
	}

	public void setUsersinfo1(Usersinfo usersinfo1) {
		this.usersinfo1 = usersinfo1;
	}

	public Usersinfo getUsersinfo() {
		return usersinfo;
	}

	public void setUsersinfo(Usersinfo usersinfo) {
		this.usersinfo = usersinfo;
	}

	public String login(){
		usersinfo1 = userService.login(usersinfo);
		if(usersinfo1 == null){
			session.put("loginMsg","��¼ʧ��");
			return "userlogin";
		}else{
			session.put("usersinfo",usersinfo1);
			return "userlogin";
		}
	}

	public void loginout(){
		session.remove("usersinfo");
		//session.remove("cartList");
	}

	public String register(){
		usersinfo1 =  userService.login(usersinfo);
		//�ж��û����Ƿ���ע��
		if(usersinfo1 != null){
			session.put("registerMsg","���û���ע��");
			return "success";
		}else{
			userService.register(usersinfo);
			return "success";
		}
	}

	//�����Ƽ�--ҳ�������ʾ����Ʒ������
	public String list(){
		List<Product> products = productService.getAllProduct();
		List<Typesinfo> types = typesService.getAllTypes();
		List<Product> sport = productService.getSportPro();
		session.put("Product", products);
		session.put("Type", types);
		session.put("Sport", sport);
		return "listAll";
	}

	public String mycart(){
		int u_id;
		String key ="";
		String cartList ="";
		Type listType = new TypeToken<List<Product>>(){}.getType();
		ShardedJedis jedis=pool.getResource();//��ȡһ��jedis����
		Gson gson = new Gson();
		Usersinfo usersinfo = (Usersinfo) ServletActionContext.getRequest().getSession().getAttribute("usersinfo");
		System.out.println(usersinfo);

		if(null == usersinfo){
			key = "xxx";
		}else{
			u_id = usersinfo.getU_id(); //����Ĭ��(��Ҫ��ȡ)
			key = u_id+"_cartList"; //keyΪjedis�ļ�
		}

		//1.�οͷ�ʽ�ӹ�
		if("xxx".equals(key)){
			//��ʱ������
			/*cartList = jedis.get(key);
			List<Product> listsss = gson.fromJson(cartList, listType);
			if(null == cartList || "".equals(cartList)){ //��null��Ϊ�������

			}else{
				session.put("cartList",listsss);
			}*/
		}else{ //2.ʵ����¼
			cartList = jedis.get(key);
			List<Product> listsss = gson.fromJson(cartList, listType);
			if(null == cartList || "".equals(cartList)){ //��null��Ϊ�������

			}else{
				session.put("cartList",listsss);
			}
		}
		return "mycart";
	}
	
	public String listAllUser(){
		listUsers=userService.listAllUser();
		for(int i=0;i<listUsers.size();i++){
			System.out.println(listUsers.get(i));
		}
		return "success";
	}
	
	
	@Override
	public Usersinfo getModel() {
		usersinfo = new Usersinfo();
		return usersinfo;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
