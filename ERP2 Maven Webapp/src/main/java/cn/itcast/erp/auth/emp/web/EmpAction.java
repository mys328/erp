package cn.itcast.erp.auth.emp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.erp.auth.dep.business.ebi.DepEbi;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.emp.vo.EmpQueryModel;
import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseAction;

public class EmpAction extends BaseAction{
	public EmpModel em = new EmpModel();
	public EmpQueryModel eqm = new EmpQueryModel();

	private EmpEbi empEbi;
	private DepEbi depEbi;
	private RoleEbi roleEbi;
	private ResEbi resEbi;
	
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	//列表
	public String list(){
		//加载所有部门的信息数据
		List<DepModel> depList = depEbi.getAll();
		put("depList",depList);
		setDataTotal(empEbi.getCount(eqm));
		List<EmpModel> empList = empEbi.getAll(eqm,pageNum,pageCount);
		put("empList", empList);
		return LIST;
	}

	//到添加
	public String input(){
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList", roleList);
		//加载所有部门信息数据
		List<DepModel> depList = depEbi.getAll();
		put("depList",depList);
		if(em.getUuid()!=null){
			//数据回显，set>>>array
			em = empEbi.get(em.getUuid());
			int i = 0;
			rolesUuid = new Long[em.getRoles().size()];
			for(RoleModel temp:em.getRoles()){
				System.out.println(temp.getUuid());
				rolesUuid[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}

	public Long[] rolesUuid;
	
	//添加
	public String save(){
		if(em.getUuid() == null){
			empEbi.save(em,rolesUuid);
		}else{
			empEbi.update(em,rolesUuid);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		empEbi.delete(em);
		return TO_LIST;
	}

	//登录
	public String login(){
		HttpServletRequest request = getRequest();
		String loginIp = request.getHeader("x-forwarded-for"); 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getRemoteAddr(); 
		}
		EmpModel loginEm = empEbi.login(em.getUserName(),em.getPwd(),loginIp);
		if(loginEm == null){
			this.addActionError("对不起，用户名密码错误！");
			return "loginFail";
		}else{
			//在登录成功的时候，查询该用户的所有权限
			List<ResModel> resList = resEbi.getResByEm(loginEm.getUuid());
			StringBuilder sbf = new StringBuilder();
			for (ResModel rm : resList) {
				sbf.append(rm.getText());
				sbf.append(",");
			}
//			System.out.println("11111111111111");
//			System.out.println(sbf.toString()+"=="+loginEm.getUuid());
//			System.out.println("11111111111111");
			loginEm.setResAll(sbf.toString());
			
			putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, loginEm);
			return "loginSuccess";
		}
	}
	
	//登出/注销
	public String logout(){
		//1.获得session.removeAtrribute("name");
		//2.所谓登录失败指loginEm == null,setAttribute("name",null)
		putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, null);
		return "noLogin";
	}
	
	//跳转到修改密码
	public String toChangePwd(){
		return "toChangePwd";
	}
	
	public String newPwd;
	//修改密码
	public String changePwd(){
		//原始密码:em.pwd
		//新密码:newPwd
		//修改密码功能如何实现？
		//1.从session中获取登录人信息，比对原始密码是否相同，如果相同，使用新密码更新原始密码(数据不同步)
		//2.使用原始密码与用户名查找数据，得到数据后，使用快照更新新密码(数据并发不同步)
		//A,13:01时间修改密码功能
		//B,13:01时间修改密码功能
		//3.执行update ...  set pwd = newPwd where userName = session[userName] and pwd = em.pwd
		//调用业务层完成修改密码
		boolean flag = empEbi.changePwd(getLogin().getUserName(),em.getPwd(),newPwd);
		if(flag){
			//修改成功
			//重新登录
			putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, null);
			return "noLogin";
		}else{
			//提示用户当前信息输入有误
			//信息：自己处理
			return "toChangePwd";
		}
	}
	
	
	
	
}
