package com.basic.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.CostumeSampleDao;
import com.basic.po.CostumeSample;
import com.basic.vo.CostumeSampleVo;
import com.basic.vo.Sample2Vo;
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
				list.get(i).setImg(img.split(",")[0]);
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
	
	public BootTablePageDto<CostumeSampleVo> findMySample(Long num,String name,long entNum, String beginDate,String endDate, int offset, int limit, Long total){
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length()>0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		return dao.findMySample(num,name,entNum,beginTime,endTime,offset,limit,total);
	}
	
	/**获取工厂样品展示列表*/
	public BootTablePageDto<Sample2Vo> getEntSample(long enterpriseNum,Long costumeCode,int offset,int limit,Long total){
		BootTablePageDto<Sample2Vo> result = dao.getEntSample(enterpriseNum,costumeCode,offset,limit,total);
		//获取小图的第一张图
		if(result.getTotal() != 0){
			List<Sample2Vo> list = result.getRows();
			for(int i=0; i<list.size(); i++){
				String img = list.get(i).getImg();
				list.get(i).setImg(img.split(",")[0]);
			}
		}
		return result;
	}
	
	/**获取工厂已添加的样品的产品类别*/
	@SuppressWarnings("unchecked")
	public List<Integer> getCostumeCode(long enterpriseNum){
		String hql = "select distinct costumeCode from CostumeSample where enterpriseNum =:enterpriseNum";
		List<Integer> list = (List<Integer>)dao.find(hql, new String[]{"enterpriseNum"}, new Long[]{enterpriseNum});
		return list;
	}
	
	/**根据样品编号获取对象*/
	public CostumeSample getByNum(long num){
		CostumeSample sample = new CostumeSample();
		sample.setNum(num);
		List<CostumeSample> list = dao.findByExample(sample);
		if(list.size()==1)
			return list.get(0);
		return null;
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
	public long generateNumber(String enterpriseNum){
		String time = DateTransform.Date2String(new Date(), "YYMMddHHmmss");
		int random = new java.util.Random().nextInt(900)+100;
		String numStr = time + random + enterpriseNum.toString().substring(15);
		return Long.valueOf(numStr);
	}
}
