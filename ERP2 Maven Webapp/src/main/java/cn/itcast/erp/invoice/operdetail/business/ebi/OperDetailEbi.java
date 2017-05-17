package cn.itcast.erp.invoice.operdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.operdetail.vo.OperDetailModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface OperDetailEbi extends BaseEbi<OperDetailModel>{

}
