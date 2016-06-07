package com.yc.lining.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.lining.entity.Product;
import com.yc.lining.entity.ProductBean;
import com.yc.lining.entity.Usersinfo;
import com.yc.lining.service.ProductService;
import com.yc.lining.util.PageUtil;

@Controller("proAction")
public class ProductAction implements ModelDriven<ProductBean>,SessionAware{
	
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
	private ProductService productService;
	private Map<String, Object> session;
	private ProductBean product;
	private List<Product> list = new ArrayList<Product>();
	private PageUtil pageUtil;
	private List<Object> pageList;
	private int Buyamount;
	private int num = 0;
	private String flag ="";
	private List<ProductBean> list3;
	private String searchName;//搜素名
	
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<ProductBean> getList3() {
		return list3;
	}

	public void setList3(List<ProductBean> list3) {
		this.list3 = list3;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

	public List<Object> getPageList() {
		return pageList;
	}

	public void setPageList(List<Object> pageList) {
		this.pageList = pageList;
	}

	public int getBuyamount() {
		return Buyamount;
	}

	public void setBuyamount(int buyamount) {
		Buyamount = buyamount;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	//用于处理购买数量
	public void buyAmount(){
		System.out.println("购买数量==>"+Buyamount);
		//session.put("Buyamount", Buyamount);
	}
	
	//展示详情
	public String details(){
		Product product_1 = productService.ProductDetailsById(product.getPro_number());
		String [] color =product_1.getColor().split(",");
		String [] img =product_1.getPro_img().split(",");
		session.put("goodsinfo", product_1);
		session.put("goodsinfocolor", color);
		session.put("goodsinfoimg", img);
		return "Finddetails";
	}

	//加入购物车(做为测试用)
	@SuppressWarnings("unchecked")
	public String cart(){
		Usersinfo usersinfo = (Usersinfo) ServletActionContext.getRequest().getSession().getAttribute("usersinfo");
		System.out.println("!"+usersinfo);
		int index=0; //用于找同一件商品的索引
		int u_id = usersinfo.getU_id(); //首先默认(需要获取)
		ShardedJedis jedis=pool.getResource();//获取一个jedis对象
		Gson gson = new Gson();
		String key = u_id+"_cartList"; //key为jedis的键
		boolean flag = false; //判断是否为同一件商品
		System.out.println("当前用户id为==>"+u_id);
		//对redis处理
		String cartList = jedis.get(key);
		if(null == cartList || "".equals(cartList)){
			if(session.get("cartList") == null ){
				Product product1 = productService.ProductDetailsById(product.getPro_number());
				product1.setBuyamount(Buyamount);
				list.add(product1);
				//还未处理完
			}else{
				List<Product>  list1 = new ArrayList<Product>();
				list1 = (List<Product>) session.get("cartList");
				for(int i=0;i<list1.size();i++){
					if(product.getPro_number() == list1.get(i).getPro_number()){
						flag =true;
						index = i;
					}else{
						flag =false;
					}
				}
				if(false ==flag){
					Product product3 = productService.ProductDetailsById(product.getPro_number());
					product3.setBuyamount(Buyamount);
					list.add(product3);
				}else{
					Product product3 = new Product();
					Buyamount= Buyamount+list1.get(index).getBuyamount();
					product3.setBuyamount(Buyamount);
					//暂时不处理
					System.out.println("相同商品的购买数量为++>"+Buyamount);
				}
			}
			
		}else{
			List<Product> listsss = (List<Product>) gson.fromJson(cartList,Object.class);
			System.out.println("由gson转过来的数据==>"+listsss);
			for (int i = 0; i < listsss.size(); i++) {
				if(product.getPro_number() == listsss.get(i).getPro_number()){
					flag =true;
					index = i;
				}else{
					flag =false;
				}
			}
			if(false ==flag){
				Product product3 = productService.ProductDetailsById(product.getPro_number());
				product3.setBuyamount(Buyamount);
				list.add(product3);
			}else{
				Product product3 = new Product();
				Buyamount= Buyamount+listsss.get(index).getBuyamount();
				product3.setBuyamount(Buyamount);
				//暂时不处理
				System.out.println("相同商品的购买数量为++>"+Buyamount);
			}
		}
		session.put("cartList",list);
		
		String jsonResult = gson.toJson(list);//把对象转换成json字符串
		jedis.del(key);
		jedis.set(key, jsonResult);
		
		pool.getResource();//释放
		pool.returnResource(jedis);
		return "Addcart";
	}
	
	//////////////多重查询///////////////(PageUtil)session.getAttribute("pageutil");
	
	/*//模糊查询
	public String searchInfo(){
		return "searchInfo";
	}*/
	
	/*public String findAll(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil00");
		
		if(null == pageUtil){
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		
		list3=productService.findPageUtil(pageUtil);
		session.put("pageUtil00", pageUtil);
		session.put("products", list3);
		return "success";
	}*/
	
	public String getCount(){
		int num=productService.getCount();
		session.put("num", num);
		return "num";
	}

	//根据新品分页查询
	public void findByDate(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil01");
		System.out.println("ssion====================="+pageUtil+"num"+num);
		if(null == pageUtil){
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		pageUtil.setTotalPages(pageUtil.getTotalPages());
		System.out.println("ssion2====================="+pageUtil);
		session.put("pageUtil01", pageUtil);
		List<ProductBean> products=productService.findByDate(pageUtil);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(products);
		System.out.println("日期排序==》"+products);
		Gson gson = new Gson();//把对象与json字符串转换对象
		String jsonResult = gson.toJson(pageList);//把对象转换成json字符串
		//取到响应对象
		HttpServletResponse response = ServletActionContext.getResponse();//取到响应对象
		response.setCharacterEncoding("UTF-8");
		response.setContentType("charset=utf-8");
		
		try {
			PrintWriter out = response.getWriter();
			out.println(jsonResult);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//按价格降序分页查询
	public void findByPriceDesc(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil02");
		System.out.println("ssion====================="+pageUtil+"num===>"+num);
		if(null == pageUtil){
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		pageUtil.setTotalPages(pageUtil.getTotalPages());
		System.out.println("ssion2====================="+pageUtil);
		session.put("pageUtil02", pageUtil);
		List<ProductBean> products=productService.findByPriceDesc(pageUtil);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(products);
		System.out.println("价格降序==》"+products);
		Gson gson = new Gson();//把对象与json字符串转换对象
		String jsonResult = gson.toJson(pageList);//把对象转换成json字符串
		//取到响应对象
		HttpServletResponse response = ServletActionContext.getResponse();//取到响应对象
		response.setCharacterEncoding("UTF-8");
		response.setContentType("charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.println(jsonResult);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//根据商品名分页查询
	public void findByType(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil05");
		//System.out.println("ssion==第一次的总页数==================="+pageUtil.getTotalPages()+"num----"+num);
		
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount3(product));
		
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		pageUtil.setTotalPages(pageUtil.getTotalPages());
		session.put("pageUtil05", pageUtil);
		pageUtil.setSearchName(product.getSearchName());
		System.out.println("所搜的名字==>"+searchName);
		List<ProductBean> products=productService.findByType(pageUtil);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(products);
		
		System.out.println("模糊查询==》"+products);
		Gson gson = new Gson();//把对象与json字符串转换对象
		String jsonResult = gson.toJson(pageList);//把对象转换成json字符串
		//取到响应对象
		HttpServletResponse response = ServletActionContext.getResponse();//取到响应对象
		response.setCharacterEncoding("UTF-8");
		response.setContentType("charset=utf-8");
		
		try {
			PrintWriter out = response.getWriter();
			out.println(jsonResult);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findByPrice(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil03");
		
		//不管为不为空，每次都要查出总条数
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount2(product));
			
		System.out.println("ssion====================="+pageUtil+"分页num===>"+num);
		if(1==num || 0==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		pageUtil.setTotalPages(pageUtil.getTotalPages());
		System.out.println("ssion2====================="+pageUtil);
		session.put("pageUtil03", pageUtil);
		
		pageUtil.setLowPrice(product.getLowPrice());
		pageUtil.setHeighPrice(product.getHighPrice());
		System.out.println("ssion2输入价格之后====================="+pageUtil);
		List<ProductBean> products = productService.findByPrice(pageUtil);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(products);
		System.out.println("价格区间查询==》"+products);
		Gson gson = new Gson();//把对象与json字符串转换对象
		String jsonResult = gson.toJson(pageList);//把对象转换成json字符串
		//取到响应对象
		HttpServletResponse response = ServletActionContext.getResponse();//取到响应对象
		response.setCharacterEncoding("UTF-8");
		response.setContentType("charset=utf-8");
		
		try {
			PrintWriter out = response.getWriter();
			out.println(jsonResult);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String findByPage(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil");
		if(null == pageUtil){
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount());
		}
		if(1==num){
			pageUtil.setPageNo(1);
		}else if(2==num){
			pageUtil.setPageNo(pageUtil.getProPageNo());
		}else if(3==num){
			pageUtil.setPageNo(pageUtil.getNextPageNo());
		}else if(4==num){
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		list3=productService.findPageUtil(pageUtil);
		session.put("pageUtil", pageUtil);
		session.put("list", list3);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(list3);
		System.out.println("productsList==>"+list3);
		System.out.println("当前的pageUtil内容为==>"+pageUtil);
		return "success";
	}

	@Override
	public ProductBean getModel() {
		product=new ProductBean();
		return product;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session  = session;
	}
	
}
