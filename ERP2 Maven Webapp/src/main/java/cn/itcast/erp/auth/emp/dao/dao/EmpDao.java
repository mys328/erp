package cn.itcast.erp.auth.emp.dao.dao;

import java.util.List;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.util.base.BaseDao;

public interface EmpDao extends BaseDao<EmpModel> {
	public EmpModel getByUserNameAndPwd(String userName, String pwd);

	public boolean updatePwdByUserNameAndPwd(String userName, String pwd,
			String newPwd);

	public List<EmpModel> getAllByUuid(Long uuid);

	public List<EmpModel> getEmByUuid(Long uuid);
}
