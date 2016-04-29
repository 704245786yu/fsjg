package com.common;

import java.io.Serializable;
import java.util.List;

import com.common.po.NestTreePO;

public abstract class NestTreeBiz < DAO extends NestTreeDao<ID, PO>, ID extends Serializable, PO extends NestTreePO >
		extends BaseBiz<DAO, ID, PO> {

	@Override
	public void save(PO po){
		dao.addNode(po);
	}
	
	@Override
	public void update(PO po){
		dao.updateNode(po);
	}
	
	/**根据ID递归删除节点
	 * @param id 被删除节点的id
	 * */
	@Override
	public void deleteById(ID id){
		dao.delNodeRecursion(id);
	}
	
	/**获取整张表的邻接列表模型
	 * */
	public List<PO> getAdjTree(){
		return dao.getAdjTree();
	}
	
	public List<PO> transformAdjTree(ID[] ids){
		return dao.transformAdjTree(ids);
	}
}