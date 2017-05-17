package cn.itcast.erp.invoice.bill.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.bill.vo.BillQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;


public interface BillDao {

	public List<Object[]> getAllByBill(BillQueryModel bqm);

	public List<OrderDetailModel> getBillByGoods(BillQueryModel bqm);

}
