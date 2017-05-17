package cn.itcast.erp.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.menu.business.ebi.MenuEbi;
import cn.itcast.erp.auth.menu.dao.dao.MenuDao;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class MenuEbo implements MenuEbi{
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void save(MenuModel mm) {
		menuDao.save(mm);
	}

	public void update(MenuModel mm) {
		//快照修改，只修改需要修改的
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setName(mm.getName());
		temp.setUrl(mm.getUrl());
		menuDao.update(temp);
	}

	public void delete(MenuModel mm) {
		//级联删除时，需要注意先加载然后再删除，这时的对象才具有延时加载的效果
		MenuModel temp = menuDao.get(mm.getUuid());
		menuDao.delete(temp);
	}

	public MenuModel get(Serializable uuid) {
		return menuDao.get(uuid);
	}

	public List<MenuModel> getAll() {
		return menuDao.getAll();
	}

	public List<MenuModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return menuDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return menuDao.getCount(qm);
	}

	public List<MenuModel> getAllOneLevel() {
		return menuDao.getAllByPuuidIsOneOrZero();
	}

	public void update(MenuModel mm, Long[] roleUuids) {
		//快照修改，只修改需要修改的
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setName(mm.getName());
		temp.setUrl(mm.getUrl());
		//array->list
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for (Long uuid : roleUuids) {
			RoleModel rm = new RoleModel();
			rm.setUuid(uuid);
			roles.add(rm);
		}
		temp.setRoles(roles);
		menuDao.update(temp);
	}

	public void save(MenuModel mm, Long[] roleUuids) {
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for (Long uuid : roleUuids) {
			RoleModel rm = new RoleModel();
			rm.setUuid(uuid);
			roles.add(rm);
		}
		mm.setRoles(roles);
		menuDao.save(mm);
	}

	public List<MenuModel> getAllOneLevelByEmp(Long uuid) {
		return menuDao.getAllOneLevelByUuid(uuid);
	}

	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid) {
		return menuDao.getAllByUuidAndPuuid(uuid,puuid);
	}

}
