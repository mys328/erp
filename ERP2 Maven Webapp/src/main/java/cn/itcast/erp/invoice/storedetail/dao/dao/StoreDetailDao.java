package cn.itcast.erp.invoice.storedetail.dao.dao;

import cn.itcast.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.itcast.erp.util.base.BaseDao;

public interface StoreDetailDao extends BaseDao<StoreDetailModel> {

	public StoreDetailModel getAllBySmAndGm(Long storeUuid, Long uuid);

	public java.util.List<Object[]> getGoodsWarnInfo();


}
