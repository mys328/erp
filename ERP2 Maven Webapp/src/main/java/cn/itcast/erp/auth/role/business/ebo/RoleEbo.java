package cn.itcast.erp.auth.role.business.ebo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.dao.dao.RoleDao;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class RoleEbo implements RoleEbi{
	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void save(RoleModel rm) {
		roleDao.save(rm);
	}

	public void update(RoleModel rm) {
		roleDao.update(rm);
	}

	public void delete(RoleModel rm) {
		roleDao.delete(rm);
	}

	public RoleModel get(Serializable uuid) {
		return roleDao.get(uuid);
	}

	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}

	public List<RoleModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return roleDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return roleDao.getCount(qm);
	}

	public void save(RoleModel rm, Long[] resesUuid) {
		Set<ResModel> res = new HashSet<ResModel>();
		for (Long uuid : resesUuid) {
			System.out.println("uuid=="+uuid);
			ResModel resm = new ResModel();
			resm.setUuid(uuid);
			res.add(resm);
		}
		rm.setReses(res);
		roleDao.save(rm);
	}

	public void update(RoleModel rm, Long[] resesUuid) {
		Set<ResModel> res = new HashSet<ResModel>();
		//array >> set
		//给rolemodel设置外键
		for (Long uuid : resesUuid) {
			System.out.println("uuid=="+uuid);
			ResModel resm = new ResModel();
			resm.setUuid(uuid);
			res.add(resm);
		}
		rm.setReses(res);
		roleDao.update(rm);
	}

	public void update(RoleModel rm, Long[] resesUuid, Long[] menuUuids) {
		Set<ResModel> res = new HashSet<ResModel>();
		//array >> set
		//给rolemodel设置外键
		for (Long uuid : resesUuid) {
			System.out.println("uuid=="+uuid);
			ResModel resm = new ResModel();
			resm.setUuid(uuid);
			res.add(resm);
		}
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		//array >> set
		//给menumodel设置外键
		for (Long uuid : menuUuids) {
			MenuModel mm = new MenuModel();
			mm.setUuid(uuid);
			menus.add(mm);
		}
		rm.setReses(res);
		rm.setMenus(menus);
		roleDao.update(rm);
	}

	public void save(RoleModel rm, Long[] resesUuid, Long[] menuUuids) {
		Set<ResModel> res = new HashSet<ResModel>();
		for (Long uuid : resesUuid) {
			ResModel resm = new ResModel();
			resm.setUuid(uuid);
			res.add(resm);
		}
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for (Long uuid : menuUuids) {
			MenuModel mm = new MenuModel();
			mm.setUuid(uuid);
			menus.add(mm);
		}
		rm.setMenus(menus);
		rm.setReses(res);
		roleDao.save(rm);
	}

}
