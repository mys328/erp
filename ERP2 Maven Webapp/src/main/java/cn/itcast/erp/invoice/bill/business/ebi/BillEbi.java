package cn.itcast.erp.invoice.bill.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.bill.vo.BillQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;

@Transactional
public interface BillEbi{

	public List<Object[]> getAllByBill(BillQueryModel bqm);

	public List<OrderDetailModel> getBillByGoods(BillQueryModel bqm);

}
