package cn.itcast.erp.invoice.operdetail.vo;

import java.util.HashMap;
import java.util.Map;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.store.vo.StoreModel;
import cn.itcast.erp.util.format.FormatUtil;

public class OperDetailModel {
	public static final Integer OPERDETAIL_TYPE_OF_IN_STORE= 1;
	public static final Integer OPERDETAIL_TYPE_OF_OUT_STORE = 2;
	
	public static final String OPERDETAIL_TYPE_OF_IN_STORE_VIEW = "入库";
	public static final String OPERDETAIL_TYPE_OF_OUT_STORE_VIEW = "出库";
	
	public static final Map<Integer,String> typeMap = new HashMap<Integer,String>();
	
	static{
		typeMap.put(OPERDETAIL_TYPE_OF_IN_STORE, OPERDETAIL_TYPE_OF_IN_STORE_VIEW);
		typeMap.put(OPERDETAIL_TYPE_OF_OUT_STORE, OPERDETAIL_TYPE_OF_OUT_STORE_VIEW);
	}
	
	private Long uuid;
	
	private Integer num;
	
	private Integer type;
	private Long operTime;
	
	private String operTimeView;
	private String typeView;
	
	private EmpModel em;
	private GoodsModel gm;
	private StoreModel sm;
	
	public String getOperTimeView() {
		return operTimeView;
	}
	public String getTypeView() {
		return typeView;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		this.typeView = typeMap.get(type);
	}
	public Long getOperTime() {
		return operTime;
	}
	public void setOperTime(Long operTime) {
		this.operTime = operTime;
		this.operTimeView = FormatUtil.formatDate(operTime);
	}
	public EmpModel getEm() {
		return em;
	}
	public void setEm(EmpModel em) {
		this.em = em;
	}
	public GoodsModel getGm() {
		return gm;
	}
	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	public StoreModel getSm() {
		return sm;
	}
	public void setSm(StoreModel sm) {
		this.sm = sm;
	}
	
}
