package cn.itcast.erp.auth.menu.dao.dao;

import java.util.List;

import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.util.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel> {

	public List<MenuModel> getAllByPuuidIsOneOrZero();

	public List<MenuModel> getAllOneLevelByUuid(Long uuid);

	public List<MenuModel> getAllByUuidAndPuuid(Long uuid, Long puuid);

}
