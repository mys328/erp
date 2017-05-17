package cn.itcast.erp.invoice.orders.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.operdetail.dao.dao.OperDetailDao;
import cn.itcast.erp.invoice.operdetail.vo.OperDetailModel;
import cn.itcast.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.orders.business.ebi.OrdersEbi;
import cn.itcast.erp.invoice.orders.dao.dao.OrdersDao;
import cn.itcast.erp.invoice.orders.vo.OrdersModel;
import cn.itcast.erp.invoice.orders.vo.OrdersQueryModel;
import cn.itcast.erp.invoice.store.vo.StoreModel;
import cn.itcast.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import cn.itcast.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.itcast.erp.util.base.BaseQueryModel;
import cn.itcast.erp.util.exception.AppException;
import cn.itcast.erp.util.number.NumberGenerator;

public class OrdersEbo implements OrdersEbi {
	private OrdersDao ordersDao;
	private OrderDetailDao orderDetailDao;
	private StoreDetailDao storeDetailDao;
	private OperDetailDao operDetailDao;
	public void setOperDetailDao(OperDetailDao operDetailDao) {
		this.operDetailDao = operDetailDao;
	}
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	public void save(OrdersModel om) {
		ordersDao.save(om);
	}

	public void update(OrdersModel om) {
		ordersDao.update(om);
	}

	public void delete(OrdersModel om) {
		ordersDao.delete(om);
	}

	public OrdersModel get(Serializable uuid) {
		return ordersDao.get(uuid);
	}

	public List<OrdersModel> getAll() {
		return ordersDao.getAll();
	}

	public List<OrdersModel> getAll(BaseQueryModel qm, Integer pageNum,
			Integer pageCount) {
		return ordersDao.getAll(qm, pageNum, pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return ordersDao.getCount(qm);
	}

	public void saveBuy(OrdersModel om, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel login) {
		// 保存订单
		// 设置订单号:订单号唯一
		String orderNum = NumberGenerator.generatorOrderNum();
		om.setOrderNum(orderNum);
		// 订单创建时间是当前系统时间
		om.setCreateTime(System.currentTimeMillis());
		// 当前保存的是采购订单
		om.setOrderType(OrdersModel.ORDER_ORDERTYPE_OF_BUY);
		// 新保存的订单的状态是未审核
		om.setType(OrdersModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		// 制单人
		om.setCreater(login);
		// 对应的供应商（已经封装在了om）

		Integer totalNum = 0;
		Double totalPrice = 0.0d;
		Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
		for (int i = 0; i < goodsUuids.length; i++) {
			// 创建订单明细的对象并添加到集合中
			OrderDetailModel odm = new OrderDetailModel();
			// 设置订单明细数量
			odm.setNum(nums[i]);
			// 设置订单明细单价
			odm.setPrice(prices[i]);
			//设置剩余数量
			odm.setSurplus(nums[i]);
			// 设置订单明细的商品
			GoodsModel gm = new GoodsModel();
			gm.setUuid(goodsUuids[i]);
			//商品中的字段改变
			odm.setGm(gm);
			// 设置所属的订单
			odm.setOm(om);
			// 将明细对象加入集合
			odms.add(odm);

			totalNum += nums[i];
			totalPrice += nums[i] * prices[i];
		}
		// 设置订单中对应的所有明细数据
		om.setOdms(odms);
		// 设置订单总数量
		om.setTotalNum(totalNum);
		// 设置订单总价值
		om.setTotalPrice(totalPrice);

		ordersDao.save(om);
	}

	public List<OrdersModel> getAllBuy(Integer orderOrdertypeOfBuy) {
		return ordersDao.getAllBuy(orderOrdertypeOfBuy);
	}

	public OrdersModel getByOrder(Long uuid) {
		return ordersDao.getByUuid(uuid);
	}

	public List<OrdersModel> getAllBuy(OrdersQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		oqm.setOrderType(OrdersModel.ORDER_ORDERTYPE_OF_BUY);
		return ordersDao.getAll(oqm, pageNum, pageCount);
	}
	
	//审核时：未审核和审核通过
	private Integer[] orderTypes = new Integer[]{
			OrdersModel.ORDER_TYPE_OF_BUY_NO_CHECK,
			OrdersModel.ORDER_TYPE_OF_BUY_CHECK_PASS
			};
	public List<OrdersModel> getAllBuyCheck(OrdersQueryModel oqm,
			Integer pageNum, Integer pageCount) {
		return ordersDao.getAllByOrderType(oqm, pageNum, pageCount,orderTypes);
	}

	public Integer getCountByBuyCheck(OrdersQueryModel oqm) {
		return ordersDao.getCountByOrderType(oqm,orderTypes);
	}

	public void buyCheckNoPass(Long uuid, EmpModel checker) {
		OrdersModel temp = ordersDao.get(uuid);
		//快照修改
		//逻辑校验
		if(!temp.getType().equals(OrdersModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		//审核时间
		temp.setCheckTime(System.currentTimeMillis());
		//审核人
		temp.setChecker(checker);
		//审核驳回
		temp.setType(OrdersModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
	}

	public void buyCheckPass(Long uuid, EmpModel checker) {
		OrdersModel temp = ordersDao.get(uuid);
		//快照修改
		//逻辑校验
		if(!temp.getType().equals(OrdersModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		//审核时间
		temp.setCheckTime(System.currentTimeMillis());
		//审核人
		temp.setChecker(checker);
		//审核通过
		temp.setType(OrdersModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
	}
	
	private Integer[] taskTypes = new Integer[]{
			OrdersModel.ORDER_ORDERTYPE_OF_BUY,
			OrdersModel.ORDER_ORDERTYPE_OF_SALE,
			OrdersModel.ORDER_ORDERTYPE_OF_RETURN_BUY,
			OrdersModel.ORDER_ORDERTYPE_OF_RETURN_SALE
		};
	//分配任务时:审核通过和已分配
	private Integer[] types = new Integer[]{
			OrdersModel.ORDER_TYPE_OF_BUY_CHECK_PASS,
			OrdersModel.ORDER_TYPE_OF_BUY_BUYING
			};
	public List<OrdersModel> getAllTask(OrdersQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		return ordersDao.getAllByTypes(oqm, pageNum, pageCount,taskTypes,types);
	}

	public Integer getCountTask(OrdersQueryModel oqm) {
		return ordersDao.getCountByTypes(oqm,taskTypes,types);
	}

	public void assignTask(EmpModel login, Long orderUuid, EmpModel completer) {
		//根据uuid查询订单
		OrdersModel temp = ordersDao.get(orderUuid);
		//逻辑校验
		if(!temp.getType().equals(OrdersModel.ORDER_TYPE_OF_BUY_CHECK_PASS)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		//指定完成人
		temp.setCompleter(completer);
		//修改订单状态
		temp.setType(OrdersModel.ORDER_TYPE_OF_BUY_BUYING);
	}

	public List<OrdersModel> getAllTask(Long uuid) {
		return ordersDao.getAllByEmp(uuid);
	}

	public void endOrder(Long uuid) {
		//快照更新
		OrdersModel temp = ordersDao.get(uuid);
		//逻辑校验
		if(!temp.getType().equals(OrdersModel.ORDER_TYPE_OF_BUY_BUYING)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		//将其状态设置为入库
		temp.setType(OrdersModel.ORDER_TYPE_OF_BUY_IN_STORE);
	}

	public List<OrdersModel> getAllByInStore() {
		return ordersDao.getAllByInStore();
	}
	
	public OrderDetailModel goodsIn(Integer num, Long storeUuid,
			Long odmUuid, EmpModel login) {
		//修改订单明细的剩余入库数量：快照更新
		OrderDetailModel odm = orderDetailDao.get(odmUuid);
		OrdersModel om = odm.getOm();
		
		if(!om.getType().equals(OrdersModel.ORDER_TYPE_OF_BUY_IN_STORE)){
			throw new AppException("悟空，不要调皮！");
		}
		if(odm.getSurplus()<num){
			throw new AppException("悟空，不要调皮！");
		}
		
		odm.setSurplus(odm.getNum()-num);
		
		GoodsModel gm = odm.getGm();
		StoreModel sm = new StoreModel();
		sm.setUuid(storeUuid);
		
		//哪个仓库的哪个货物已入库数量
		//分为没有入库和入过库
		StoreDetailModel sdm = storeDetailDao.getAllBySmAndGm(storeUuid,gm.getUuid());
		if(sdm!=null){
			//入过库：快照修改
			sdm.setNum(sdm.getNum()+num);
		} else {
			sdm = new StoreDetailModel();
			sdm.setGm(gm);
			sdm.setNum(num);
			sdm.setSm(sm);
			storeDetailDao.save(sdm);
		}
		
		//入库日志：谁什么时候在哪个仓库哪个货物入库多少？
		OperDetailModel opdm = new OperDetailModel();
		opdm.setEm(login);
		opdm.setGm(gm);
		opdm.setNum(num);
		opdm.setOperTime(System.currentTimeMillis());
		opdm.setSm(sm);
		opdm.setType(OperDetailModel.OPERDETAIL_TYPE_OF_IN_STORE);
		operDetailDao.save(opdm);
		
		//设置订单入库状态为完毕
		int sum = 0;
		for (OrderDetailModel temp : om.getOdms()) {
			sum += temp.getSurplus();
		}
		//如果所有的剩余入库量为0，则入库完毕
		if(sum == 0){
			om.setType(OrdersModel.ORDER_TYPE_OF_BUY_COMPLETE);
			om.setEndTime(System.currentTimeMillis());
		}
		return odm;
	}

}
