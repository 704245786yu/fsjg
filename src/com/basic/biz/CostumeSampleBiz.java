package com.basic.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.CostumeSampleDao;
import com.basic.po.CostumeSample;
import com.basic.vo.CostumeSampleVo;
import com.basic.vo.SampleVo;
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.util.DateTransform;
import com.util.FileUtil;

@Service
public class CostumeSampleBiz extends BaseBiz<CostumeSampleDao, Integer, CostumeSample> {

	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	
	public BootTablePageDto<SampleVo> search(Long province,Long city,Long county,Long town,Integer costumeCode, 
			String keyword,int offset,int limit,Long total){
		//判断是否需要根据关键字匹配主营产品
		Integer[] costumeCodes = null;
		if(costumeCode == null){
			List<Integer> costumeCategoryCodes = new ArrayList<Integer>();
			int endIndex = 0;
			if(keyword.length() > 0){
				costumeCategoryCodes = costumeCategoryBiz.getCodeByCategoryName(keyword);
				//为保证性能，取前3条服饰类型记录
				endIndex = costumeCategoryCodes.size()>3 ? 3 : costumeCategoryCodes.size();
			}
			costumeCodes = costumeCategoryCodes.subList(0, endIndex).toArray(new Integer[]{});
		}
		
		BootTablePageDto<SampleVo> result = dao.search(province,city,county,town,costumeCodes,keyword,offset,limit,total);
		//获取小图的第一张图
		if(result.getTotal() != 0){
			List<SampleVo> list = result.getRows();
			for(int i=0; i<list.size(); i++){
				String img = list.get(i).getImg();
				list.get(i).setImg(img.substring(0, 19));
			}
		}
		return result;
	}
	
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
