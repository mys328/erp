package cn.itcast.erp.util.timer;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import cn.itcast.erp.util.format.FormatUtil;

@Transactional
public class TimerTask {
	private GoodsDao goodsDao;
	private JavaMailSender mailSender;
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void updateGoodsUseNum(){
//		update tbl_goods g set g.useNum = (select count(odm.uuid) from tbl_detail_order odm where odm.goodsUuid = g.uuid)
		goodsDao.updateGoodsUseNum();
	}
	
	public void storeWarn(){
		List<Object[]> infoList = goodsDao.getGoodsWarnInfo();
		
		SimpleMailMessage smm = new SimpleMailMessage();
		
		smm.setFrom("itcast0228@126.com");
		smm.setTo("5@qq.com");
		smm.setSubject("qq被抽中为年度会员通知【"+FormatUtil.formatDate(System.currentTimeMillis())+"】");
		smm.setSentDate(new Date());
		StringBuilder sd = new StringBuilder();
		sd.append("恭喜你，qq为1906986750的用户被抽中为2016年度qq年度会员！请及时查看qq！");
		/*
		for(Object[] objs :infoList){
			
			String name = objs[0].toString();
			BigInteger maxFlag = (BigInteger) objs[1];
			BigInteger minFlag = (BigInteger) objs[2];
			System.out.println(name);
			System.out.println(maxFlag);
			System.out.println(minFlag);
			if(maxFlag.intValue() == 1){
				sd.append("商品【");
				sd.append(name);
				sd.append("】超过上限，请及时补货！");
				continue;
			}
			
			if(minFlag.intValue() == 1){
				sd.append("商品【");
				sd.append(name);
				sd.append("】超过上限，请及时补货！");
			}
		}*/
		System.out.println("发送了");
		smm.setText(sd.toString());
		mailSender.send(smm);
	}
}
