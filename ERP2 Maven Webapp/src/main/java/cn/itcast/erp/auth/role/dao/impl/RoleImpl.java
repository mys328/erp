package cn.itcast.erp.auth.role.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.auth.role.dao.dao.RoleDao;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.auth.role.vo.RoleQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class RoleImpl extends BaseImpl<RoleModel> implements RoleDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		RoleQueryModel rqm = (RoleQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}
