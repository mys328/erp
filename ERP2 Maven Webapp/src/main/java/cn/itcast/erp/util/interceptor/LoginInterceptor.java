package cn.itcast.erp.util.interceptor;

import cn.itcast.erp.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation invocation) throws Exception {
		//执行除了登录操作之前的所有操作做登录校验
		//获取本次操作的信息
		/*
		System.out.println(invocation.getProxy().getAction());
		System.out.println(invocation.getProxy().getActionName());		emp_login
		System.out.println(invocation.getProxy().getMethod());
		*/
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName+"."+methodName;
		
		if("cn.itcast.erp.auth.emp.web.EmpAction.login".equals(allName)){
			return invocation.invoke();
		}
		
		//解决登录嵌套的问题
//		System.out.println(invocation.getProxy().getActionName()+"---"+invocation.getProxy().getAction().getClass().getName());
		if(invocation.getProxy().getActionName().equals("page_login")){
			return invocation.invoke();
		}
		
		//获取当前登录人信息
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		//如果当前没有登录，跳转到登录页面
		if(loginEm == null){
			//跳转到登录
			return "noLogin";
		}
		
		//执行原始操作
		return invocation.invoke();
	}

	
}
