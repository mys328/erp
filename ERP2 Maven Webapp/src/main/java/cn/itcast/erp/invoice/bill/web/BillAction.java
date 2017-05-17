package cn.itcast.erp.invoice.bill.web;

import java.util.List;

import cn.itcast.erp.invoice.bill.business.ebi.BillEbi;
import cn.itcast.erp.invoice.bill.vo.BillQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.orders.vo.OrdersModel;
import cn.itcast.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseAction;

public class BillAction extends BaseAction{
	public BillQueryModel bqm = new BillQueryModel();

	private BillEbi billEbi;
	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	} 

	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}

	public String buyBillList(){
		//查询所有的供应商
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList", supplierList);
		
		//加载符合条件的报表信息
		List<Object[]> billList = billEbi.getAllByBill(bqm);
		put("billList", billList);
		return "buyBillList";
	}
	
	
	//--------ajax--------------
	//--------ajax--------------
	//--------ajax--------------
	//--------ajax--------------
	private List<OrderDetailModel> billGoodsList;
	public List<OrderDetailModel> getBillGoodsList() {
		return billGoodsList;
	}

	//根据商品获取报表明细
	public String ajaxGetBillByGoods(){
		billGoodsList = billEbi.getBillByGoods(bqm);
		return "ajaxGetBillByGoods";
	}
	
	
	
	
	
	
	
	
	
	
}
