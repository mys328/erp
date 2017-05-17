package cn.itcast.erp.auth.emp.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.format.FormatUtil;


public class EmpModel {
	public static final String EMP_LOGIN_USER_OBJECT_NAME = "loginEm";
	
	//数据结构思想应用
	public static final Integer EMP_GENDER_OF_MAN = 1;
	public static final Integer EMP_GENDER_OF_WOMAN = 0;
	
	public static final String EMP_GENDER_OF_MAN_VIEW = "男";
	public static final String EMP_GENDER_OF_WOMAN_VIEW = "女";
	
	public static final Map<Integer, String> genderMap = new HashMap<Integer, String>();
	static{
		genderMap.put(EMP_GENDER_OF_MAN, EMP_GENDER_OF_MAN_VIEW);
		genderMap.put(EMP_GENDER_OF_WOMAN, EMP_GENDER_OF_WOMAN_VIEW);
	}
	
	private Long uuid;
	private String userName;
	private String name;
	private String pwd;
	private String email;
	private String tele;
	private String address;
	private String lastLoginIp;
	private Integer loginTimes;
	
	private Long lastLoginTime;
	
	private Integer gender;
	private String resAll;
	
	
	public String getResAll() {
		return resAll;
	}
	public void setResAll(String resAll) {
		this.resAll = resAll;
	}

	/*
	//Long:记录的是毫秒值	
	//Date:对long的包装	优点：格式好，缺点：计算时间略有复杂性
	现在的时间是2020年4月31日
	180天前是几号？
	现在的long System.currentTimeMillis()-180*24*60*60*1000
	long-long >0
	Date  2014年1月4日  14：21
	Date  2014年1月4日  14：22	
	*/
	private Long birthday;
	private Set<RoleModel> roles;//多对多
	
	
	public Set<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	//视图值：视图值是一种用于界面显示的变量值，该值不具体对应某个数据库字段，它服务于某个数据库字段
	//当数据库中的某个字段值不便于直接显示时，为该字段添加视图值，用于显示对应的信息
	//1.定义一个String类型的变量，变量名是无法合理显示的字段的字段名+View
	//2.提供其get方法
	//3.在其对应的变量的set方法中对这个View值进行初始化
	private String birthdayView;
	private String genderView;
	private String lastLoginTimeView;
	
	public String getLastLoginTimeView() {
		return lastLoginTimeView;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Integer getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	public Long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		this.lastLoginTimeView = FormatUtil.formatDate(lastLoginTime);
	}
	public String getGenderView() {
		return genderView;
	}
	public String getBirthdayView() {
		return birthdayView;
	}

	//多对一
	private DepModel dm;

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
		this.genderView = genderMap.get(gender);
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
		this.birthdayView = FormatUtil.formatDate(birthday);
	}

	public DepModel getDm() {
		return dm;
	}

	public void setDm(DepModel dm) {
		this.dm = dm;
	}
	
}
