package cn.itcast.erp.invoice.store.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.store.vo.StoreModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface StoreEbi extends BaseEbi<StoreModel>{

}
