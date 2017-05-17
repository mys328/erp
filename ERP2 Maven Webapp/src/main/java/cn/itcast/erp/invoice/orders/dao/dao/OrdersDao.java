package cn.itcast.erp.invoice.orders.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.orders.vo.OrdersModel;
import cn.itcast.erp.invoice.orders.vo.OrdersQueryModel;
import cn.itcast.erp.util.base.BaseDao;

public interface OrdersDao extends BaseDao<OrdersModel> {

	public List<OrdersModel> getAllBuy(Integer orderOrdertypeOfBuy);

	public OrdersModel getByUuid(Long uuid);

	public List<OrdersModel> getAllByOrderType(OrdersQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] orderTypes);

	public Integer getCountByOrderType(OrdersQueryModel oqm, Integer[] orderTypes);
	
	//废弃
	public List<OrdersModel> getAllByTypes(OrdersQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] taskTypes);
	//废弃
	public Integer getCountByTypes(OrdersQueryModel oqm, Integer[] taskTypes);

	public List<OrdersModel> getAllByTypes(OrdersQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] taskTypes,
			Integer[] orderTypes);

	public Integer getCountByTypes(OrdersQueryModel oqm, Integer[] taskTypes,
			Integer[] orderTypes);

	public List<OrdersModel> getAllByEmp(Long uuid);

	public List<OrdersModel> getAllByInStore();

}
