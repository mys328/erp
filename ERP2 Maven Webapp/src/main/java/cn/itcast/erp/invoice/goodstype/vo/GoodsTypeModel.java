package cn.itcast.erp.invoice.goodstype.vo;

import cn.itcast.erp.invoice.supplier.vo.SupplierModel;

public class GoodsTypeModel {
	private Long uuid;
	private String name;
	private SupplierModel sm;
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SupplierModel getSm() {
		return sm;
	}
	public void setSm(SupplierModel sm) {
		this.sm = sm;
	}
	
}
