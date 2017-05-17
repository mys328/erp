package cn.itcast.erp.util.interceptor;


import java.util.List;




import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.util.exception.AppException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor{
	
	private ResEbi resEbi;//struts会自动的装配
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}


	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName + "." + methodName;
		System.out.println(allName);
		
		//由直接在拦截器里面进行资源查询的时候，每次都需要查询，这样会使得程序的效率很低，
		//因此在这里使用监听器，在程序加载的时候就加载好，这样后面就不会再进行查询
		String allRes = ServletActionContext.getServletContext().getAttribute("allRes").toString();
		if(!allRes.contains(allName)){
			return invocation.invoke();
		}
		
		EmpModel em = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);

//		System.out.println("-----==-----");
//		System.out.println(em.getResAll());
//		System.out.println("-----==-----");
		
		//在登录的时候将用户的权限查询出来提高程序的效率
		if(em.getResAll().contains(allName)){
			return invocation.invoke();
		}
		
		throw new AppException("对不起你没有访问权限！");
	}

}
