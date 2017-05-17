package cn.itcast.erp.auth.dep.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
//切面:spring内置
//切入点:定义了该注解所在的类或接口中的所有方法
//execution(cn.itcast.erp.auth.dep.business.ebi.DepEbi.*(..))
public interface DepEbi extends BaseEbi<DepModel>{
	
}
