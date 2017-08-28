package com.yc.lining.inteceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{
	
	private static final long serialVersionUID = -7236236055969111808L;


	/*@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> session = ActionContext.getContext().getSession(); 
		Object obj = session.get("userinfo");
		if(obj ==null){
			session.put("errorMsg","登录失败!");
			return "login";
		}
		return invocation.invoke();
	}*/

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> session = ActionContext.getContext().getSession(); 
		Object obj = session.get("usersinfo");
		if(null == obj || "".equals(obj.toString()) ){
			session.put("errorMsg","亲,您要登录才能购买哦!");
			return "loginInt";
		}
		return invocation.invoke();
	}

}
