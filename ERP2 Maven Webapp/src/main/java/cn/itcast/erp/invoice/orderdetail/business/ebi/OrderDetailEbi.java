package cn.itcast.erp.invoice.orderdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface OrderDetailEbi extends BaseEbi<OrderDetailModel>{

}
