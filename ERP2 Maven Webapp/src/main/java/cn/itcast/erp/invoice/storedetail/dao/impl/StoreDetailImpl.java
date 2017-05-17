package cn.itcast.erp.invoice.storedetail.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.erp.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import cn.itcast.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.itcast.erp.invoice.storedetail.vo.StoreDetailQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class StoreDetailImpl extends BaseImpl<StoreDetailModel> implements StoreDetailDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		StoreDetailQueryModel sqm = (StoreDetailQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	public StoreDetailModel getAllBySmAndGm(Long storeUuid, Long uuid) {
		String hql = "from StoreDetailModel where storeUuid = ? and goodsUuid = ?";
		List<StoreDetailModel> temp = this.getHibernateTemplate().find(hql,storeUuid,uuid);
		return temp.size()>0 ? temp.get(0) : null;
	}
	
	//废弃
	public List<Object[]> getGoodsWarnInfo() {
		String sql = "select gm.uuid,gm.name,sum(sdm.num)>=gm.maxNum ,sum(sdm.num)<=gm.minNum from tbl_storeDetail sdm,tbl_goods gm where  sdm.goodsUuid = gm.uuid group by sdm.goodsUuid";
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();
		SQLQuery sq = session.createSQLQuery(sql);
		return sq.list();
	}
	
	public static void main(String[] args) {
		ApplicationContext atc = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-storeDetail.xml");
		StoreDetailDao goodsDao = (StoreDetailDao) atc.getBean("storeDetailDao");
		System.out.println(goodsDao.getGoodsWarnInfo());
	}

}
