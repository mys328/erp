package cn.itcast.erp.invoice.goodstype.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class GoodsTypeImpl extends BaseImpl<GoodsTypeModel> implements GoodsTypeDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		GoodsTypeQueryModel gqm = (GoodsTypeQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	public List<GoodsTypeModel> getAllByUuid(Long uuid) {
		String hql = "from GoodsTypeModel gtm where gtm.sm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

	public List<GoodsTypeModel> getAllUnionBySm(Long supplierUuid) {
		String hql = "select distinct gtm from GoodsTypeModel gtm join gtm.sm s where s.uuid = ?";
		return this.getHibernateTemplate().find(hql,supplierUuid);
	}

	public List<GoodsTypeModel> getAllUnion() {
		String hql = "select distinct g from GoodsModel gm join gm.gtm g";
		return this.getHibernateTemplate().find(hql);
	}

}
