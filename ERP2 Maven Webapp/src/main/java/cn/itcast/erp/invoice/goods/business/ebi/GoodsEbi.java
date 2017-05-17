package cn.itcast.erp.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel>{

	public List<GoodsModel> getAllByGtm(Long goodTypeUuid);

	public GoodsModel getGmByUuid(Long goodsUuid);

	/**
	 * 查询制定商品类型的商品
	 * @param uuid 商品类型uuid
	 * @return
	 */
	public List<GoodsModel> getAllUnionByGtm(Long uuid);

}
