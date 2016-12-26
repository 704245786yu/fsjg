package com.basic.biz;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.basic.dao.CostumeSampleDao;
import com.basic.po.CostumeSample;
import com.basic.vo.CostumeSampleVo;
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.util.DateTransform;
import com.util.FileUtil;

@Service
public class CostumeSampleBiz extends BaseBiz<CostumeSampleDao, Integer, CostumeSample> {

	public BootTablePageDto<CostumeSampleVo> findByPage(Long num,String name,String enterpriseName, String beginDate,String endDate, int offset, int limit, Long total){
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length()>0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		return dao.findByPage(num,name,enterpriseName,beginTime,endTime,offset,limit,total);
	}
	
	/**删除样品时同时删除图片信息*/
	public void deleteById(Integer id,String uploadDir) {
		String[] imgs = dao.getImgs(id);
		String smImg = imgs[0];
		if(smImg.length()!=0){
			String[] pics = smImg.split(",");
			FileUtil.delImg(uploadDir, pics);
		}
		String detailImg = imgs[1];
		if(detailImg.length()!=0){
			String[] pics = detailImg.split(",");
			FileUtil.delImg(uploadDir, pics);
		}
		dao.deleteById(id);
	}
	
	/**生成样品编号格式：YYMMddHHmmss+ 3位随机数 + 工厂编号后4位
	 * @param telephone 手机号
	 * */
	public Long generateNumber(Long enterpriseNum){
		String time = DateTransform.Date2String(new Date(), "YYMMddHHmmss");
		int random = new java.util.Random().nextInt(900)+100;
		String numStr = time + random +enterpriseNum.toString().substring(15);
		return Long.valueOf(numStr);
	}
}
