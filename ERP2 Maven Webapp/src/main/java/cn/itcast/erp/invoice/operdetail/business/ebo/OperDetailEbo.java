package cn.itcast.erp.invoice.operdetail.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.erp.invoice.operdetail.business.ebi.OperDetailEbi;
import cn.itcast.erp.invoice.operdetail.dao.dao.OperDetailDao;
import cn.itcast.erp.invoice.operdetail.vo.OperDetailModel;
import cn.itcast.erp.util.base.BaseQueryModel;

public class OperDetailEbo implements OperDetailEbi{
	private OperDetailDao operDetailDao;
	public void setOperDetailDao(OperDetailDao operDetailDao) {
		this.operDetailDao = operDetailDao;
	}

	public void save(OperDetailModel om) {
		operDetailDao.save(om);
	}

	public void update(OperDetailModel om) {
		operDetailDao.update(om);
	}

	public void delete(OperDetailModel om) {
		operDetailDao.delete(om);
	}

	public OperDetailModel get(Serializable uuid) {
		return operDetailDao.get(uuid);
	}

	public List<OperDetailModel> getAll() {
		return operDetailDao.getAll();
	}

	public List<OperDetailModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return operDetailDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return operDetailDao.getCount(qm);
	}

}
