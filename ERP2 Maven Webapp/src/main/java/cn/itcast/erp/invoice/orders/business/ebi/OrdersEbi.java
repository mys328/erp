package cn.itcast.erp.invoice.orders.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.orders.vo.OrdersModel;
import cn.itcast.erp.invoice.orders.vo.OrdersQueryModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface OrdersEbi extends BaseEbi<OrdersModel>{

	/**
	 * 保存订单
	 * @param om 订单对象信息
	 * @param goodsUuid 商品uuid 
	 * @param nums 商品数量
	 * @param prices 商品价格
	 * @param login 登录人
	 */
	public void saveBuy(OrdersModel om, Long[] goodsUuid, Integer[] nums,
			Double[] prices, EmpModel login);

	public List<OrdersModel> getAllBuy(Integer orderOrdertypeOfBuy);

	public OrdersModel getByOrder(Long uuid);

	public List<OrdersModel> getAllBuy(OrdersQueryModel oqm, Integer pageNum,
			Integer pageCount);
	
	public List<OrdersModel> getAllBuyCheck(OrdersQueryModel oqm,
			Integer pageNum, Integer pageCount);

	public Integer getCountByBuyCheck(OrdersQueryModel oqm);

	public void buyCheckNoPass(Long uuid, EmpModel login);

	public void buyCheckPass(Long uuid, EmpModel login);
	
	/**
	 * 根据状态查询订单
	 * @param oqm 查询对象
	 * @param pageNum 页码
	 * @param pageCount 每页显示数量
	 * @return
	 */
	public List<OrdersModel> getAllTask(OrdersQueryModel oqm, Integer pageNum,
			Integer pageCount);

	/**
	 * 根据状态查询订单的数量
	 * @param oqm 查询对象
	 * @return 
	 */
	public Integer getCountTask(OrdersQueryModel oqm);
	
	/**
	 * 登录人给指定订单分配指定的运输部门的员工完成运输任务
	 * @param login 操作人(这个参数可以废弃)
	 * @param orderUuid 订单uuid
	 * @param empModel 完成人
	 */
	public void assignTask(EmpModel login, Long orderUuid, EmpModel empModel);

	/**
	 * 查询登录人的订单
	 * @param uuid 登录人uuid
	 * @return
	 */
	public List<OrdersModel> getAllTask(Long uuid);

	public void endOrder(Long uuid);

	/**
	 * 查询订单状态为入库中的订单
	 * @return
	 */
	public List<OrdersModel> getAllByInStore();

	/**
	 * 订单入库
	 * @param num 数量
	 * @param storeUuid 仓库uuid
	 * @param odmUuid 订单明细uuid
	 * @param login 操作人
	 * @return
	 */
	public OrderDetailModel goodsIn(Integer num, Long storeUuid,
			Long odmUuid, EmpModel login);

}
