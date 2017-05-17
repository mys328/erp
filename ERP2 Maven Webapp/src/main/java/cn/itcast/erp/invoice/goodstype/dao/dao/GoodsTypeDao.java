package cn.itcast.erp.invoice.goodstype.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.util.base.BaseDao;

public interface GoodsTypeDao extends BaseDao<GoodsTypeModel> {

	public List<GoodsTypeModel> getAllByUuid(Long uuid);

	public List<GoodsTypeModel> getAllUnionBySm(Long supplierUuid);

	public List<GoodsTypeModel> getAllUnion();

}
