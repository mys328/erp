package cn.itcast.erp.invoice.goods.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.erp.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class GoodsEbo implements GoodsEbi{
	private GoodsDao goodsDao;
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void save(GoodsModel gm) {
		//修改使用次数
		gm.setUseNum(0);
		goodsDao.save(gm);
	}

	public void update(GoodsModel gm) {
//		goodsDao.update(gm);
	}

	public void delete(GoodsModel gm) {
		goodsDao.delete(gm);
	}

	public GoodsModel get(Serializable uuid) {
		return goodsDao.get(uuid);
	}

	public List<GoodsModel> getAll() {
		return goodsDao.getAll();
	}

	public List<GoodsModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return goodsDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return goodsDao.getCount(qm);
	}

	public List<GoodsModel> getAllByGtm(Long goodTypeUuid) {
		return goodsDao.getAllByGtm(goodTypeUuid);
	}

	public GoodsModel getGmByUuid(Long goodsUuid) {
		return goodsDao.getGmByUuid(goodsUuid);
	}

	public List<GoodsModel> getAllUnionByGtm(Long uuid) {
		return goodsDao.getAllUnionByGtm(uuid);
	}

}
