package cn.itcast.erp.invoice.operdetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.invoice.operdetail.dao.dao.OperDetailDao;
import cn.itcast.erp.invoice.operdetail.vo.OperDetailModel;
import cn.itcast.erp.invoice.operdetail.vo.OperDetailQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class OperDetailImpl extends BaseImpl<OperDetailModel> implements OperDetailDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		OperDetailQueryModel oqm = (OperDetailQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}
