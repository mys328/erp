package cn.itcast.erp.invoice.orders.web;

import java.util.List;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.orders.business.ebi.OrdersEbi;
import cn.itcast.erp.invoice.orders.vo.OrdersModel;
import cn.itcast.erp.invoice.orders.vo.OrdersQueryModel;
import cn.itcast.erp.invoice.store.business.ebi.StoreEbi;
import cn.itcast.erp.invoice.store.vo.StoreModel;
import cn.itcast.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.util.base.BaseAction;

public class OrdersAction extends BaseAction{
	public OrdersModel om = new OrdersModel();
	public OrdersQueryModel oqm = new OrdersQueryModel();

	private OrdersEbi ordersEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	private GoodsEbi goodsEbi;
	private EmpEbi empEbi;
	
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public void setOrdersEbi(OrdersEbi ordersEbi) {
		this.ordersEbi = ordersEbi;
	}

	//列表
	public String list(){
		setDataTotal(ordersEbi.getCount(oqm));
		List<OrdersModel> ordersList = ordersEbi.getAll(oqm,pageNum,pageCount);
		put("ordersList", ordersList);
		return LIST;
	}

	//到添加
	public String input(){
		if(om.getUuid()!=null){
			om = ordersEbi.get(om.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(om.getUuid() == null){
			ordersEbi.save(om);
		}else{
			ordersEbi.update(om);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		ordersEbi.delete(om);
		return TO_LIST;
	}
	
	//-------------采购订单-----------------
	//-------------采购订单-----------------
	//-------------采购订单-----------------
	//-------------采购订单-----------------
	
	//添加页面
	public String buyInput(){
		//供应商
		List<SupplierModel> supplierList = supplierEbi.getAllUnion();
		//商品类别
		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllUnion();
		//商品
		List<GoodsModel> gmList = goodsEbi.getAll();
		
		put("supplierList",supplierList);
		put("gtmList",gtmList);
		put("gmList",gmList);
		return "buyInput";
	}
	
	//订单页面
	public String buyList(){
//		List<OrdersModel> ordersList = ordersEbi.getAllBuy(OrdersModel.ORDER_ORDERTYPE_OF_BUY);
		setDataTotal(ordersEbi.getCount(oqm));
		List<OrdersModel> ordersList = ordersEbi.getAllBuy(oqm,pageNum,pageCount);
		put("ordersList", ordersList);
		return "buyList";
	}
	
	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices; 
	
	//订单保存
	public String saveBuy(){
//		for(Long temp : goodsUuids){
//			System.out.println(temp);
//		}
//		System.out.println("----------");
//		for(Integer temp : nums){
//			System.out.println(temp);
//		}
//		System.out.println("----------");
//		for(Double temp : prices){
//			System.out.println(temp);
//		}
		ordersEbi.saveBuy(om,goodsUuids,nums,prices,getLogin());
		return "saveBuy";
	}
	
	//订单详情
	public String orderDetail(){
		om = ordersEbi.get(om.getUuid());
		put("om", om);
		return "orderDetail";
	}
	
	//-------------采购审核-----------------
	//-------------采购审核-----------------
	//-------------采购审核-----------------
	//-------------采购审核-----------------
	//审核列表
	public String buyCheckList(){
		setDataTotal(ordersEbi.getCountByBuyCheck(oqm));
		List<OrdersModel> ordersList = ordersEbi.getAllBuyCheck(oqm,pageNum,pageCount);
		put("ordersList", ordersList);
		return "buyCheckList";
	}
	
	//审核详情
	public String buyCheck(){
		//获取一个订单
		om = ordersEbi.get(om.getUuid());
		put("om", om);
		return "buyCheck";
	}
	
	//审核驳回
	public String buyCheckNoPass(){
		ordersEbi.buyCheckNoPass(om.getUuid(),getLogin());
		return "buyCheckNoPass";
	}
	
	//审核驳回
	public String buyCheckPass(){
		ordersEbi.buyCheckPass(om.getUuid(),getLogin());
		return "buyCheckPass";
	}
	
	//------商品运输------------
	//------商品运输------------
	//------商品运输------------
	
	//运输任务列表
	public String taskList(){
		setDataTotal(ordersEbi.getCountTask(oqm));
		List<OrdersModel> ordersList = ordersEbi.getAllTask(oqm,pageNum,pageCount);
		put("ordersList", ordersList);
		return "taskList";
	}
	
	//运输任务详情
	public String taskDetail(){
		//获取运输部门的人员
		List<EmpModel> empList = empEbi.getAllByDep(getLogin().getDm().getUuid());
		put("empList", empList);
		//获取一个订单
		om = ordersEbi.get(om.getUuid());
		put("om", om);
		return "taskDetail";
	}
	
	//任务分配
	public String assignTask(){
		//当前登录人分配运输任务给指定人
		//订单，登录人
		ordersEbi.assignTask(getLogin(),om.getUuid(),om.getCompleter());
		return "toTaskList";
	}
	
	//登录人任务查询
	public String taskFindList(){
		//根据登录人查询对应的订单
		List<OrdersModel> ordersList = ordersEbi.getAllTask(getLogin().getUuid());
		put("ordersList", ordersList);
		return "taskFindList";
	}
	
	//任务查询详情
	public String taskFindDetail(){
		om = ordersEbi.get(om.getUuid());
		return "taskFindDetail";
	}
	
	//订单完结
	public String endOrder(){
		ordersEbi.endOrder(om.getUuid());
		return "toTaskFindList";
	}
	
	
	//----------入库--------------
	//----------入库--------------
	//----------入库--------------
	//----------入库--------------
	private StoreEbi storeEbi;
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}

	//入库
	public String inStoreList(){
		//查询订单状态为入库中的订单
		//--------------这里应该分页做进货和销售退货的离线查询----------
		List<OrdersModel> ordersList = ordersEbi.getAllByInStore();
		put("ordersList", ordersList);
		return "inStoreList";
	}
	
	//入库详情
	public String inStoreDetail(){
		//根据订单uuid查询制定订单明细
		om = ordersEbi.get(om.getUuid());
		//查询所有的仓库信息
		List<StoreModel> storeList = storeEbi.getAll();
		System.out.println(storeList);
		put("storeList", storeList);
		return "inStoreDetail";
	}
	
	
	//-------AJAX-------------
	//-------AJAX-------------
	//-------AJAX-------------
	
	private List<GoodsTypeModel> gtmList;
	private List<GoodsModel> gmList;
	private GoodsModel gm;
	public GoodsModel getGm() {
		return gm;
	}
	public List<GoodsModel> getGmList() {
		return gmList;
	}
	public List<GoodsTypeModel> getGtmList() {
		return gtmList;
	}
	
	public Long supplierUuid;
	public Long gtmUuid;
	public Long gmUuid;
	public String used;
	
	//供应商
	public String ajaxGetGtm(){
		gtmList = goodsTypeEbi.getAllUnionBySm(supplierUuid);
		gmList = goodsEbi.getAllUnionByGtm(gtmList.get(0).getUuid());
		gm = gmList.get(0);
		return "ajaxGetGtm";
	} 
	
	//供应商(过滤重复)
	public String ajaxGetGtm2(){
		gtmList = goodsTypeEbi.getAllUnionBySm(supplierUuid);
		
		//判断商品类型里面还是否有商品，如果没有商品，则过滤掉这个商品类型
		Goods:
		for(int i = gtmList.size() - 1; i >= 0; i--){
			List<GoodsModel> gmList = goodsEbi.getAllByGtm(gtmList.get(i).getUuid());
			for(GoodsModel gm : gmList){
				String temp = "'"+gm.getUuid()+",";
				if(!used.contains(temp)){
					//保留该类型，直接判断下一个类型
					continue Goods;
				}
			}
			//该类型里面的商品全部都使用过，删除
			gtmList.remove(i);
		}
		
		gmList = goodsEbi.getAllUnionByGtm(gtmList.get(0).getUuid());
		//删除使用过的商品：遍历gmList，看是否有已经使用过的商品，如果有，则删除
		//删除时采用倒序删除
		for(int i = gmList.size() - 1; i >= 0; i--){
			String temp = "'"+gmList.get(i).getUuid()+"'";
			if(used.contains(temp)){
				gmList.remove(i);
			}
		}
		gm = gmList.get(0);
		return "ajaxGetGtm";
	} 
	
	//商品类型
	public String ajaxGetByGtm(){
		gmList = goodsEbi.getAllUnionByGtm(gtmUuid);
		//删除使用过的商品：遍历gmList，看是否有已经使用过的商品，如果有，则删除
		//删除时采用倒序删除
		for(int i = gmList.size() - 1; i >= 0; i--){
			String temp = "'"+gmList.get(i).getUuid()+"'";
			if(used.contains(temp)){
				gmList.remove(i);
			}
		}
		gm = gmList.get(0);
		return "ajaxGetByGtm";
	}
	
	//商品
	public String ajaxGetGm(){
		gm = goodsEbi.get(gmUuid);
		return "ajaxGetGm";
	}
	
	public Integer num;
	public Long storeUuid;
	public Long odmUuid;
	private OrderDetailModel odm;
	public OrderDetailModel getOdm() {
		return odm;
	}

	//入库
	public String ajaxInGoods(){
		odm = ordersEbi.goodsIn(num,storeUuid,odmUuid,getLogin());
		return "ajaxInGoods";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
