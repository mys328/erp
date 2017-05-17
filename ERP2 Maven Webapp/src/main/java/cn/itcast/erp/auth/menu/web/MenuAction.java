package cn.itcast.erp.auth.menu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.itcast.erp.auth.menu.business.ebi.MenuEbi;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.menu.vo.MenuQueryModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseAction;

public class MenuAction extends BaseAction {
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	private MenuEbi menuEbi;
	private RoleEbi roleEbi;

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	// 列表
	public String list() {
		setDataTotal(menuEbi.getCount(mqm));
		List<MenuModel> menuList = menuEbi.getAll(mqm, pageNum, pageCount);
		put("menuList", menuList);
		return LIST;
	}

	// 到添加
	public String input() {
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList", roleList);
		// 加载的时候只需要加载系统菜单和一级菜单
		List<MenuModel> menuList = menuEbi.getAllOneLevel();
		put("menuList", menuList);
		//回显到页面
		if (mm.getUuid() != null) {
			mm = menuEbi.get(mm.getUuid());
			//set->array
			roleUuids = new Long[mm.getRoles().size()];
			int i = 0;
			for(RoleModel rm : mm.getRoles()){
				roleUuids[i++] = rm.getUuid();
			}
		}
		return INPUT;
	}

	public Long[] roleUuids;
	
	// 添加
	public String save() {
		if (mm.getUuid() == null) {
			menuEbi.save(mm, roleUuids);
		} else {
			menuEbi.update(mm, roleUuids);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		menuEbi.delete(mm);
		return TO_LIST;
	}

	//显示菜单
	public void showMenu() throws IOException{
		String root = getRequest().getParameter("root");
		
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		StringBuilder json = new StringBuilder();
		json.append("[");
		
		if("source".equals(root)){
			//生成一级菜单
			List<MenuModel> menuList = menuEbi.getAllOneLevelByEmp(getLogin().getUuid());
			for(MenuModel temp : menuList){
				json.append("{\"text\":\"");
				json.append(temp.getName());
				json.append("\",\"hasChildren\":\"true\",\"classes\":\"folder\",\"id\":\"");
				json.append(temp.getUuid());
				json.append("\"},");
			}
		} else {
			//生成二级菜单项
			//获取指定一级菜单的二级菜单项
			Long puuid = new Long(root);
			List<MenuModel> menuList = menuEbi.getByEmpAndPuuid(getLogin().getUuid(),puuid);
			for(MenuModel temp :menuList){
				json.append("{\"text\":\"<a class='hei' target='main' href='");
				json.append(temp.getUrl());
				json.append("'>");
				json.append(temp.getName());
				json.append("</a>\",\"hasChildren\":false,\"classes\":\"file\"},");
			}
		}
		//最后多了一个逗号
		json.deleteCharAt(json.length()-1);
		json.append("]");
		
		pw.write(json.toString());
		pw.flush();
	}
}
