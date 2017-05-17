package cn.itcast.erp.invoice.goodstype.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel>{
	
	/**
	 * 废弃：查询需要级联查询，防止查询的数据为空的情况
	 * @param uuid 供应商uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllBySm(Long uuid);

	/**
	 * 查询制定供应商的货物类型
	 * @param supplierUuid 供应商uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllUnionBySm(Long supplierUuid);

	public List<GoodsTypeModel> getAllUnion();

}
