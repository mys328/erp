package cn.itcast.erp.auth.dep.web;

import java.util.List;

import cn.itcast.erp.auth.dep.business.ebi.DepEbi;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.dep.vo.DepQueryModel;
import cn.itcast.erp.util.base.BaseAction;

public class DepAction extends BaseAction{
	public DepModel dm = new DepModel();
	public DepQueryModel dqm = new DepQueryModel();
	
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	//跳转到列表页面
	public String list(){
		setDataTotal(depEbi.getCount(dqm));
		List<DepModel> depList = depEbi.getAll(dqm,pageNum,pageCount);
		put("depList", depList);
		return LIST;
	}
	
	//跳转到添加页面
	public String input(){
		if(dm.getUuid()!=null){
			dm = depEbi.get(dm.getUuid());
		}
		return INPUT;
	}
	
	//添加功能
	public String save(){
		if(dm.getUuid() == null){
			depEbi.save(dm);
		}else{
			depEbi.update(dm);
		}
		return TO_LIST;
	}
	
	//删除部门
	public String delete(){
		depEbi.delete(dm);
		return TO_LIST;
	}
	
}
