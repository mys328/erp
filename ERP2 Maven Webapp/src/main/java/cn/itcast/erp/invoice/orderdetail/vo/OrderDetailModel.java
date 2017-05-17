package cn.itcast.erp.invoice.orderdetail.vo;

import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.orders.vo.OrdersModel;
import cn.itcast.erp.util.format.FormatUtil;

public class OrderDetailModel {
	private Long uuid;
	
	private Integer num;
	private Integer surplus;
	
	private Double price;
	
	private String priceView;
	private String totalPriceView;
	
	private GoodsModel gm;
	private OrdersModel om;
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Integer getSurplus() {
		return surplus;
	}
	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}
	//获取总价格
	public Double getTotalPrice(){
		return getPrice() * getNum();
	}
	public String getTotalPriceView() {
		return totalPriceView;
	}
	public String getPriceView() {
		return priceView;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
		this.priceView = FormatUtil.formatMoney(price);
		this.totalPriceView = FormatUtil.formatMoney(getTotalPrice());
	}
	public GoodsModel getGm() {
		return gm;
	}
	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	public OrdersModel getOm() {
		return om;
	}
	public void setOm(OrdersModel om) {
		this.om = om;
	}
	
}
