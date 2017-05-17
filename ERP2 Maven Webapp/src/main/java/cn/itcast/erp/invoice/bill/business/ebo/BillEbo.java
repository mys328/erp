package cn.itcast.erp.invoice.bill.business.ebo;

import java.util.List;

import cn.itcast.erp.invoice.bill.business.ebi.BillEbi;
import cn.itcast.erp.invoice.bill.dao.dao.BillDao;
import cn.itcast.erp.invoice.bill.vo.BillQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.orders.vo.OrdersModel;

public class BillEbo implements BillEbi{
	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}
	
	public List<Object[]> getAllByBill(BillQueryModel bqm) {
		return billDao.getAllByBill(bqm);
	}

	public List<OrderDetailModel> getBillByGoods(BillQueryModel bqm) {
		bqm.setOrderType(OrdersModel.ORDER_ORDERTYPE_OF_BUY);
		return billDao.getBillByGoods(bqm);
	}

}
