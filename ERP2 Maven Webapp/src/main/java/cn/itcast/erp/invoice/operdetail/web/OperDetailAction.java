package cn.itcast.erp.invoice.operdetail.web;

import java.util.List;

import cn.itcast.erp.invoice.operdetail.business.ebi.OperDetailEbi;
import cn.itcast.erp.invoice.operdetail.vo.OperDetailModel;
import cn.itcast.erp.invoice.operdetail.vo.OperDetailQueryModel;
import cn.itcast.erp.util.base.BaseAction;

public class OperDetailAction extends BaseAction{
	public OperDetailModel om = new OperDetailModel();
	public OperDetailQueryModel oqm = new OperDetailQueryModel();

	private OperDetailEbi operDetailEbi;
	public void setOperDetailEbi(OperDetailEbi operDetailEbi) {
		this.operDetailEbi = operDetailEbi;
	}

	//列表
	public String list(){
		setDataTotal(operDetailEbi.getCount(oqm));
		List<OperDetailModel> operDetailList = operDetailEbi.getAll(oqm,pageNum,pageCount);
		put("operDetailList", operDetailList);
		return LIST;
	}

	//到添加
	public String input(){
		if(om.getUuid()!=null){
			om = operDetailEbi.get(om.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(om.getUuid() == null){
			operDetailEbi.save(om);
		}else{
			operDetailEbi.update(om);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		operDetailEbi.delete(om);
		return TO_LIST;
	}

}
