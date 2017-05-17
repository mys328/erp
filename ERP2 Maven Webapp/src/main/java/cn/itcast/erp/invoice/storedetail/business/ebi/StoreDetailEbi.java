package cn.itcast.erp.invoice.storedetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.storedetail.vo.StoreDetailModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface StoreDetailEbi extends BaseEbi<StoreDetailModel>{

}
