package com.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.po.NestTreePO;

public abstract class NestTreeCtrl <BIZ extends NestTreeBiz<?, ID, PO>, ID extends Serializable, PO extends NestTreePO>
		extends BaseCtrl<BIZ, ID, PO> {
	
	/**获取直接子节点
	 * @param id 若为null，表示获取一级节点
	 * */
	@RequestMapping("getChild")
	@ResponseBody
	public List<PO> getChild(ID id){
		return biz.getChild(id);
	}
	
	/**获取整张表的邻接列表模型
	 * */
	@RequestMapping("getAdjTree")
	@ResponseBody
	public List<?> getAdjTree(){
		return biz.getAdjTree();
	}
	
}