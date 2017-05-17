package cn.itcast.erp.auth.res.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface ResEbi extends BaseEbi<ResModel>{

	List<ResModel> getResByEm(Long uuid);

}
