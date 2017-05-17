package cn.itcast.erp.invoice.goods.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.erp.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class GoodsImpl extends BaseImpl<GoodsModel> implements GoodsDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		GoodsQueryModel gqm = (GoodsQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	public List<GoodsModel> getAllByGtm(Long goodTypeUuid) {
		String hql = "from GoodsModel where gtm.uuid = ?";
		return this.getHibernateTemplate().find(hql,goodTypeUuid);
	}

	public GoodsModel getGmByUuid(Long goodsUuid) {
		String hql = "from GoodsModel where uuid = ?";
		List<GoodsModel> goodsList = this.getHibernateTemplate().find(hql,goodsUuid);
		return goodsList.get(0);
	}

	public List<GoodsModel> getAllUnionByGtm(Long uuid) {
		String hql = "select distinct gm from GoodsModel gm join gm.gtm g where g.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

	public void updateGoodsUseNum() {
		/*
		 * update tbl_goods g set g.useNum = (select count(odm.uuid) from tbl_detail_order odm where odm.goodsUuid = g.uuid)
		 */
		String hql = "update GoodsModel g set g.useNum = (select count(odm.uuid) from OrderDetailModel odm where odm.gm.uuid = g.uuid)";
		this.getHibernateTemplate().bulkUpdate(hql);
	}

	public List<Object[]> getGoodsWarnInfo() {
		String sql = "select gm.name,sum(sdm.num)>=gm.maxNum ,sum(sdm.num)<=gm.minNum from tbl_storeDetail sdm,tbl_goods gm where  sdm.goodsUuid = gm.uuid group by sdm.goodsUuid";
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();
		SQLQuery sq = session.createSQLQuery(sql);
		return sq.list();
	}

	public static void main(String[] args) {
		ApplicationContext atc = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-goods.xml");
		GoodsDao goodsDao = (GoodsDao) atc.getBean("goodsDao");
		System.out.println(goodsDao.getGoodsWarnInfo());
	}
}
