package com.basic.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.CostumeCategoryBiz;
import com.basic.biz.DistrictBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.biz.IndentBiz;
import com.basic.biz.IndentQuoteBiz;
import com.basic.biz.PersonBiz;
import com.basic.dto.IndentDto;
import com.basic.po.BasicUser;
import com.basic.po.Indent;
import com.basic.po.UserAbstract;
import com.basic.vo.ConfirmIndentVo;
import com.basic.vo.IndentDistVo;
import com.basic.vo.IndentVo;
import com.basic.vo.MyQuotedVo;
import com.basic.vo.NewstQuoteIndentVo;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.util.DateTransform;

@Controller
@RequestMapping("indent")
public class IndentCtrl extends BaseCtrl<IndentBiz,Integer,Indent>{

	@Autowired
	private DistrictBiz districtBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private PersonBiz personBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private IndentQuoteBiz indentQuoteBiz;
	
	private static final long imgMaxSize = 200000;//图片最大200kb
	private static final long docMaxSize = 500000;//文档最大500kb
	
	/**返回最新发布的个人订单、工厂订单、最新接到报价的订单
	 * */
	@Override
	public ModelAndView showDefaultPage(HttpSession session){
		ModelAndView mav = new ModelAndView("main/indent");
		List<IndentDistVo> personIndents = biz.getNewstByUserType((byte)1);
		List<IndentDistVo> enterpriseIndents = biz.getNewstByUserType((byte)2);
		List<NewstQuoteIndentVo> newstQuotes = biz.getNewstQuote();
		mav.addObject("personIndents", personIndents);
		mav.addObject("enterpriseIndents", enterpriseIndents);
		mav.addObject("newstQuotes", newstQuotes);
		return mav;
	}
	
	/**显示发布订单页面*/
	@RequestMapping("showRelease")
	public ModelAndView showRelease(){
		ModelAndView mav = new ModelAndView("main/indentRelease");
		return mav;
	}
	
	/**发布订单前，上传图片用*/
	@RequestMapping("uploadImg")
	@ResponseBody
	public ReturnValueVo uploadImg(@RequestParam("file")MultipartFile file, HttpSession session){
		//检查是否登录
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser == null){
			return  new ReturnValueVo(ReturnValueVo.ERROR, "nologin");
		}
		//判断是否为png/jpeg文件，且检测文件大小
//		String fieldName = file.getName();//获取表单中文件组件的名字
		String fileName = file.getOriginalFilename();//获取上传文件的原名
		String ary[] = fileName.split("\\.");
		String suffix = ary[ary.length-1];
		long size = file.getSize();//获取文件的字节大小，单位为byte
		String contentType = file.getContentType();
//		System.out.println("fieldName:"+fieldName+"||fileName:"+fileName+"||size:"+size);
		if( size>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
			return new ReturnValueVo(ReturnValueVo.ERROR, "imageError");
		}
		
		//通过transferTo()将文件存储到硬件中
		try {
			String uploadDir = session.getServletContext().getResource("uploadFile/indent/").getPath();
			Date date = new Date();
			String newFileName = basicUser.getId()+""+date.getTime()+"."+suffix;
			file.transferTo(new File(uploadDir + newFileName));
			return new ReturnValueVo(ReturnValueVo.SUCCESS, newFileName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, null);
		}
	}
	
	/**发布订单前，上传文档用*/
	@RequestMapping("uploadDoc")
	@ResponseBody
	public ReturnValueVo uploadDoc(@RequestParam("file")MultipartFile file, HttpSession session){
		//检查是否登录
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser == null){
			return  new ReturnValueVo(ReturnValueVo.ERROR, "nologin");
		}
		
		//判断是否为txt、word、excel文件，且检测文件大小
		String fileName = file.getOriginalFilename();//获取上传文件的原名
		String ary[] = fileName.split("\\.");
		String suffix = ary[ary.length-1];
		long size = file.getSize();//获取文件的字节大小，单位为byte
		if( size>docMaxSize || (!suffix.equals("txt") && !suffix.equals("doc") && !suffix.equals("docx") && !suffix.equals("xls") && !suffix.equals("xlsx")) ){
			return new ReturnValueVo(ReturnValueVo.ERROR, "docError");
		}
		
		//通过transferTo()将文件存储到硬件中
		try {
			String uploadDir = session.getServletContext().getResource("uploadFile/indent/").getPath();
			Date date = new Date();
			String newFileName = basicUser.getId()+""+date.getTime()+"."+suffix;
			file.transferTo(new File(uploadDir + newFileName));
			return new ReturnValueVo(ReturnValueVo.SUCCESS, newFileName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, null);
		}
	}
	
	/**发布订单*/
	@RequestMapping(value="release",method=RequestMethod.POST)
	public ModelAndView release(Indent indent,HttpSession session){
		//检查是否登录
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser == null){
			return new ModelAndView("redirect:../login.jsp");
		}
		indent.setIndentNum(this.generateNumber(basicUser.getTelephone()));
		indent.setState((byte)0);
		indent.setCreateBy(basicUser.getId());
		indent.setCreateUserType(basicUser.getRoleId().byteValue());
		biz.save(indent);
		ModelAndView mav = new ModelAndView("main/indentReleaseSuccess");
		return mav;
	}
	
	/**生成订单编号格式：YYMMddHHmmss + 手机号后5位
	 * @param telephone 手机号
	 * */
	private Long generateNumber(Long telephone){
		String time = DateTransform.Date2String(new Date(), "YYMMddHHmmss");
		String numStr = time + telephone.toString().substring(7);
		return Long.valueOf(numStr);
	}
	
	/**订单搜索,返回indentList页面,不能包括订单状态为2(已接单)和3(已失效)的订单
	 * @param province..town 接单用户的省市区县乡镇编码
	 * */
	@RequestMapping("search")
	public ModelAndView search(Long province,Long city,Long county,Long town, Integer[] costumeCode,String indentKeyword){
		if(indentKeyword == null)
			indentKeyword = "";
		BootTablePageDto<IndentDto> result = biz.search(province,city,county,town,costumeCode,null,null,indentKeyword,0,20,null);
		ModelAndView mav = new ModelAndView("main/indentList");
		mav.addObject("result", result);
		//保留页面顶部搜索框的状态
		mav.addObject("tabIndex",0);
		mav.addObject("indentKeyword",indentKeyword);
		return mav;
	}
	
	/**订单搜索,异步访问用,不能包括订单状态为2(已接单)和3(已失效)的订单
	 * @param province..town 接单用户的省市区县乡镇编码
	 * @param offset
	 * @param total 可为null
	 * */
	@RequestMapping(value="search2")
	@ResponseBody
	public BootTablePageDto<IndentDto> search2(Long province,Long city,Long county,Long town, Integer[] costumeCode, 
			Integer processType,Byte saleMarket,String indentKeyword,int offset,Long total){
		if(indentKeyword == null)
			indentKeyword = "";
		int limit = 20;
		BootTablePageDto<IndentDto> result = biz.search(province,city,county,town,costumeCode,processType,saleMarket,indentKeyword,offset,limit,total);
		return result;
	}
	
	@RequestMapping("detail/{indentNum}")
	public ModelAndView detail(@PathVariable Long indentNum, HttpSession session){
		Indent indent = biz.getByNum(indentNum);
		//产品类型
		String[] costumeCodeStr = indent.getCostumeCode().split(",");
		List<Integer> costumeCodes = new ArrayList<Integer>();
		for(int i=0; i<costumeCodeStr.length; i++){
			costumeCodes.add(Integer.valueOf(costumeCodeStr[i]));
		}
		List<String> costumes = costumeCategoryBiz.getNameByCode(costumeCodes);
		
		//接单地区要求
		List<Long> districtCode = new ArrayList<Long>();
		districtCode.add(indent.getCondProvince());
		districtCode.add(indent.getCondCity());
		List<String> districts = districtBiz.getNameByCode(districtCode);


		//发单用户所在地
		int userId = indent.getCreateBy();
		byte userType = indent.getCreateUserType();
		UserAbstract userAbstract = null;
		if(userType == 1)
			userAbstract = personBiz.getByBasicUserId(userId);
		else if(userType == 2)
			userAbstract = enterpriseBiz.getByBasicUserId(userId);
		ArrayList<Long> districtCodes = new ArrayList<Long>();
		districtCodes.add(userAbstract.getProvince());
		districtCodes.add(userAbstract.getCity());
		districtCodes.add(userAbstract.getCounty());
		districtCodes.add(userAbstract.getTown());
		List<String> districtNames = districtBiz.getNameByCode(districtCodes);
		
		//订单报价数
		long quoteNum = indentQuoteBiz.getQuoteNum(indent.getIndentNum());
		
		ModelAndView mav = new ModelAndView("main/indentDetail");
		mav.addObject("indent", indent);
		mav.addObject("costumes", costumes);
		mav.addObject("districts", districts);
		mav.addObject("quoteNum", quoteNum);
		mav.addObject("user", userAbstract);
		mav.addObject("districtNames", districtNames);
		return mav;
	}
	
	@RequestMapping("showMyReleased")
	public String showMyReleased(){
		return "main/myCenter/myReleased";
	}
	
	/**个人中心-我发布的订单
	 * @param indentNum 订单编号
	 * @param indentName 订单名称,模糊匹配
	 * @param state 订单状态
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @param total 总记录数
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * */
	@RequestMapping("myReleased")
	@ResponseBody
	public BootTablePageDto<Indent> myReleased(Long indentNum, String indentName, Byte state, String beginDate, String endDate,
			Long total, int offset, int limit, HttpSession session){
		BasicUser user = BasicUserCtrl.getLoginUser(session);
		return biz.getMyReleased(indentNum, indentName, state, beginDate, endDate, user.getId(), total, offset, limit);
	}

	/**个人中心-我的报价
	 * @param indentNum 订单编号
	 * @param indentName 订单名称,模糊匹配
	 * @param state 订单状态
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @param total 总记录数
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * */
	@RequestMapping("myQuoted")
	@ResponseBody
	public BootTablePageDto<MyQuotedVo> myQuoted(Long indentNum, String indentName, Byte state, String beginDate, String endDate,
			Long total, int offset, int limit, HttpSession session){
		BasicUser user = BasicUserCtrl.getLoginUser(session);
		int enterpriseId = enterpriseBiz.getIdByUserId(user.getId());
		return biz.getMyQuoted(indentNum, indentName, state, beginDate, endDate, enterpriseId, total, offset, limit);
	}

	/**显示我的报价
	 * */
	@RequestMapping("showMyQuoted")
	public String showMyQuoted(){
		return "main/myCenter/myQuoted";
	}
	
	/**显示我收到的报价*/
	@RequestMapping("showMyReceivedQuote")
	public String showMyReceivedQuote(){
		return "main/myCenter/myReceivedQuote";
	}
	
	/**显示我收到的订单*/
	@RequestMapping("showMyReceivedIndent")
	public String showMyReceivedIndent(){
		return "main/myCenter/myReceivedIndent";
	}
	
	/**我收到的报价
	 * @param indentNum 订单编号
	 * @param indentName 订单名称,模糊匹配
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @param total
	 * @param offset
	 * @param limit
	 * @return
	 */
	@RequestMapping("myReceivedQuote")
	@ResponseBody
	public BootTablePageDto<IndentVo> myReceivedQuote(Long indentNum, String indentName, String beginDate, String endDate,
			Long total, int offset, int limit, HttpSession session){
		BasicUser user = BasicUserCtrl.getLoginUser(session);
		return biz.myReceivedQuote(indentNum, indentName, beginDate, endDate, user.getId(), total, offset, limit);
	}
	
	/**我收到的订单
	 * @param indentNum 订单编号
	 * @param indentName 订单名称,模糊匹配
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @param total
	 * @param offset
	 * @param limit
	 * @return
	 */
	@RequestMapping("myReceivedIndent")
	@ResponseBody
	public BootTablePageDto<MyQuotedVo> myReceivedIndent(Long indentNum, String indentName, String beginDate, String endDate,
			Long total, int offset, int limit, HttpSession session){
		BasicUser user = BasicUserCtrl.getLoginUser(session);
		int enterpriseId = enterpriseBiz.getIdByUserId(user.getId());
		return biz.myReceivedIndent(indentNum, indentName, beginDate, endDate, enterpriseId, total, offset, limit);
	}
	
	/**确认订单,并发送短信通知接单方
	 * 要根据session获取当前登录用户的ID进行数据的更新，防止非订单创建者非法操作。
	 * @param telephone 报价企业手机号
	 * */
	@RequestMapping("confirm")
	@ResponseBody
	public ReturnValueVo confirm(long indentNum,String indentName,int enterpriseId,long telephone,double price,HttpSession session){
		int userId = BasicUserCtrl.getLoginUser(session).getId();
		biz.confirm(indentNum, indentName, enterpriseId, telephone, price, userId);
		return new ReturnValueVo(ReturnValueVo.SUCCESS, null);
	}
	
	/**显示我确认的订单*/
	@RequestMapping("showMyConfirmed")
	public String showMyConfirmed(){
		return "main/myCenter/myConfirmed";
	}
	
	/**我确认的订单*/
	@RequestMapping("myConfirmed")
	@ResponseBody
	public BootTablePageDto<ConfirmIndentVo> myConfirmed(Long indentNum, String indentName, String beginDate, String endDate,
			Long total, int offset, int limit, HttpSession session){
		BasicUser user = BasicUserCtrl.getLoginUser(session);
		return biz.myConfirmed(indentNum, indentName, beginDate, endDate, user.getId(), total, offset, limit);
	}
	
	/**显示后台管理页面*/
	@RequestMapping("showManage")
	public ModelAndView showManage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/indent");
		return mav;
	}
	
	/**后台分页查询*/
	@RequestMapping("findByPage")
	@ResponseBody
	public BootTablePageDto<Indent> findByPage(Long indentNum,Byte state,String beginDate,String endDate, int offset, int limit, Long total){
		return biz.findByPage(indentNum, state, beginDate, endDate, offset, limit, total);
	}

	@RequestMapping("delByIndentNum")
	@ResponseBody
	public Integer delByIndentNum(long indentNum, HttpSession session) {
		return biz.delByIndentNum(indentNum);
	}
	
}
