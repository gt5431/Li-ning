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
	private String searchName;//������
	
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

	//���ڴ���������
	public void buyAmount(){
		System.out.println("��������==>"+Buyamount);
		//session.put("Buyamount", Buyamount);
	}
	
	//չʾ����
	public String details(){
		Product product_1 = productService.ProductDetailsById(product.getPro_number());
		String [] color =product_1.getColor().split(",");
		String [] img =product_1.getPro_img().split(",");
		session.put("goodsinfo", product_1);
		session.put("goodsinfocolor", color);
		session.put("goodsinfoimg", img);
		return "Finddetails";
	}

	//���빺�ﳵ(��Ϊ������)
	@SuppressWarnings("unchecked")
	public String cart(){
		Usersinfo usersinfo = (Usersinfo) ServletActionContext.getRequest().getSession().getAttribute("usersinfo");
		System.out.println("!"+usersinfo);
		int index=0; //������ͬһ����Ʒ������
		int u_id = usersinfo.getU_id(); //����Ĭ��(��Ҫ��ȡ)
		ShardedJedis jedis=pool.getResource();//��ȡһ��jedis����
		Gson gson = new Gson();
		String key = u_id+"_cartList"; //keyΪjedis�ļ�
		boolean flag = false; //�ж��Ƿ�Ϊͬһ����Ʒ
		System.out.println("��ǰ�û�idΪ==>"+u_id);
		//��redis����
		String cartList = jedis.get(key);
		if(null == cartList || "".equals(cartList)){
			if(session.get("cartList") == null ){
				Product product1 = productService.ProductDetailsById(product.getPro_number());
				product1.setBuyamount(Buyamount);
				list.add(product1);
				//��δ������
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
					//��ʱ������
					System.out.println("��ͬ��Ʒ�Ĺ�������Ϊ++>"+Buyamount);
				}
			}
			
		}else{
			List<Product> listsss = (List<Product>) gson.fromJson(cartList,Object.class);
			System.out.println("��gsonת����������==>"+listsss);
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
				//��ʱ������
				System.out.println("��ͬ��Ʒ�Ĺ�������Ϊ++>"+Buyamount);
			}
		}
		session.put("cartList",list);
		
		String jsonResult = gson.toJson(list);//�Ѷ���ת����json�ַ���
		jedis.del(key);
		jedis.set(key, jsonResult);
		
		pool.getResource();//�ͷ�
		pool.returnResource(jedis);
		return "Addcart";
	}
	
	//////////////���ز�ѯ///////////////(PageUtil)session.getAttribute("pageutil");
	
	/*//ģ����ѯ
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

	//������Ʒ��ҳ��ѯ
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
		System.out.println("��������==��"+products);
		Gson gson = new Gson();//�Ѷ�����json�ַ���ת������
		String jsonResult = gson.toJson(pageList);//�Ѷ���ת����json�ַ���
		//ȡ����Ӧ����
		HttpServletResponse response = ServletActionContext.getResponse();//ȡ����Ӧ����
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
	
	
	//���۸����ҳ��ѯ
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
		System.out.println("�۸���==��"+products);
		Gson gson = new Gson();//�Ѷ�����json�ַ���ת������
		String jsonResult = gson.toJson(pageList);//�Ѷ���ת����json�ַ���
		//ȡ����Ӧ����
		HttpServletResponse response = ServletActionContext.getResponse();//ȡ����Ӧ����
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
	
	//������Ʒ����ҳ��ѯ
	public void findByType(){
		PageUtil pageUtil = (PageUtil) session.get("pageUtil05");
		//System.out.println("ssion==��һ�ε���ҳ��==================="+pageUtil.getTotalPages()+"num----"+num);
		
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
		System.out.println("���ѵ�����==>"+searchName);
		List<ProductBean> products=productService.findByType(pageUtil);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(products);
		
		System.out.println("ģ����ѯ==��"+products);
		Gson gson = new Gson();//�Ѷ�����json�ַ���ת������
		String jsonResult = gson.toJson(pageList);//�Ѷ���ת����json�ַ���
		//ȡ����Ӧ����
		HttpServletResponse response = ServletActionContext.getResponse();//ȡ����Ӧ����
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
		
		//����Ϊ��Ϊ�գ�ÿ�ζ�Ҫ���������
			pageUtil = new PageUtil();
			pageUtil.setPageNo(1);
			pageUtil.setPageSize(8);
			pageUtil.setTotalSize(productService.getCount2(product));
			
		System.out.println("ssion====================="+pageUtil+"��ҳnum===>"+num);
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
		System.out.println("ssion2����۸�֮��====================="+pageUtil);
		List<ProductBean> products = productService.findByPrice(pageUtil);
		pageList = new ArrayList<Object>();
		pageList.add(pageUtil);
		pageList.add(products);
		System.out.println("�۸������ѯ==��"+products);
		Gson gson = new Gson();//�Ѷ�����json�ַ���ת������
		String jsonResult = gson.toJson(pageList);//�Ѷ���ת����json�ַ���
		//ȡ����Ӧ����
		HttpServletResponse response = ServletActionContext.getResponse();//ȡ����Ӧ����
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
		System.out.println("��ǰ��pageUtil����Ϊ==>"+pageUtil);
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
