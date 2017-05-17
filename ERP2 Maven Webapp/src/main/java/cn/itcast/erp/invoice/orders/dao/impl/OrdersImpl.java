package cn.itcast.erp.invoice.orders.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.invoice.orders.dao.dao.OrdersDao;
import cn.itcast.erp.invoice.orders.vo.OrdersModel;
import cn.itcast.erp.invoice.orders.vo.OrdersQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class OrdersImpl extends BaseImpl<OrdersModel> implements OrdersDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		OrdersQueryModel oqm = (OrdersQueryModel)qm;
		if(oqm.getOrderType()!=null && oqm.getOrderType()!= -1){
			dc.add(Restrictions.eq("orderType", oqm.getOrderType()));
		}
		if(oqm.getCreater()!=null && 
			oqm.getCreater().getName()!=null && 
			oqm.getCreater().getName().trim().length()>0){
			dc.createAlias("creater", "c1");
			dc.add(Restrictions.like("c1.name", "%"+oqm.getCreater().getName().trim()+"%"));
		}
		if(oqm.getType()!=null && oqm.getType()!=-1){
			dc.add(Restrictions.eq("type", oqm.getType()));
		}
		//供应商，发货方式，下单人，审核人，跟单人
		//两个查询条件有相同的别名的时候需要将其成为公共的变量
		dc.createAlias("sm", "s");
		if(oqm.getSm() != null &&
				oqm.getSm().getUuid() != null &&
				oqm.getSm().getUuid() != -1){
			dc.add(Restrictions.eq("s.uuid", oqm.getSm().getUuid()));
		}
		if(oqm.getSm()!=null && 
				oqm.getSm().getNeeds()!=null && 
				oqm.getSm().getNeeds() != -1){
			dc.add(Restrictions.eq("s.needs", oqm.getSm().getNeeds()));
		}
		if(oqm.getChecker()!=null && 
				oqm.getChecker().getName()!=null &&
				oqm.getChecker().getName().trim().length() > 0){
			dc.createAlias("creater", "c2");
			dc.add(Restrictions.like("c2.name", "%"+oqm.getChecker().getName().trim()+"%"));
		}
		if(oqm.getCompleter()!=null && 
				oqm.getCompleter().getName()!=null && 
				oqm.getCompleter().getName().trim().length() > 0){
			dc.createAlias("completer", "c3");
			dc.add(Restrictions.like("c3.name", "%"+oqm.getCompleter().getName().trim()+"%"));
		}
	}

	//废弃
	public List<OrdersModel> getAllBuy(Integer orderOrdertypeOfBuy) {
		String hql = "from OrdersModel where orderType = ?";
		return this.getHibernateTemplate().find(hql,orderOrdertypeOfBuy);
	}
	
	//废弃
	public OrdersModel getByUuid(Long uuid) {
		String hql = "from OrdersModel where uuid = ?";
		OrdersModel temp = (OrdersModel) this.getHibernateTemplate().find(hql,uuid).get(0);
		return temp != null ? temp : null;
	}

	public void doQbc2(DetachedCriteria dc,BaseQueryModel qm,Integer[] types){
		dc.add(Restrictions.in("type", types));
		doQbc(dc,qm);
	}
	
	public void doQbc3(DetachedCriteria dc,BaseQueryModel qm,Integer[] taskTypes,Integer[] orderTypes){
		dc.add(Restrictions.in("orderType", taskTypes));
		dc.add(Restrictions.in("type", orderTypes));
		doQbc(dc,qm);
	}
	
	public List<OrdersModel> getAllByOrderType(OrdersQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] orderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrdersModel.class);

		doQbc2(dc, oqm, orderTypes);
		
		return this.getHibernateTemplate().findByCriteria(dc, (pageNum - 1)*pageCount, pageCount);
	}

	public Integer getCountByOrderType(OrdersQueryModel oqm, Integer[] orderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrdersModel.class);
		dc.setProjection(Projections.rowCount());
		
		doQbc2(dc, oqm, orderTypes);
		
		List<Long> temp = this.getHibernateTemplate().findByCriteria(dc);
		return temp.get(0).intValue();
	}
	
	//废弃
	public List<OrdersModel> getAllByTypes(OrdersQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[]types) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrdersModel.class);
		
//		doQbc3(dc, oqm, types);
		
		return this.getHibernateTemplate().findByCriteria(dc, (pageNum - 1)*pageCount, pageCount);
	}

	//废弃
	public Integer getCountByTypes(OrdersQueryModel oqm, Integer[] types) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrdersModel.class);
		dc.setProjection(Projections.rowCount());
		
//		doQbc3(dc, oqm, types);
		
		List<Long> temp = this.getHibernateTemplate().findByCriteria(dc);
		return temp.get(0).intValue();
	}

	public List<OrdersModel> getAllByTypes(OrdersQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] taskTypes,
			Integer[] orderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrdersModel.class);
		
		doQbc3(dc, oqm, taskTypes,orderTypes);
		
		return this.getHibernateTemplate().findByCriteria(dc, (pageNum - 1)*pageCount, pageCount);
	}

	public Integer getCountByTypes(OrdersQueryModel oqm, Integer[] taskTypes,
			Integer[] orderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrdersModel.class);
		dc.setProjection(Projections.rowCount());
		
		doQbc3(dc, oqm, taskTypes,orderTypes);
		
		List<Long> temp = this.getHibernateTemplate().findByCriteria(dc);
		return temp.get(0).intValue();
	}

	public List<OrdersModel> getAllByEmp(Long uuid) {
		String hql = "from OrdersModel where completer.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

	public List<OrdersModel> getAllByInStore() {
		String hql = "from OrdersModel where type = ?";
		return this.getHibernateTemplate().find(hql,OrdersModel.ORDER_TYPE_OF_BUY_IN_STORE);
	}

}
