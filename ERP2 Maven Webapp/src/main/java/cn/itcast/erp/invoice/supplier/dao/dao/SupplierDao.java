package cn.itcast.erp.invoice.supplier.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseDao;

public interface SupplierDao extends BaseDao<SupplierModel> {

	public List<SupplierModel> getAllUnion();

}
