package com.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

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
	
	/**获取直接子节点
	 * @param id 若为null，表示获取一级节点
	 * */
	@SuppressWarnings("unchecked")
	public List<PO> getChild(ID id){
		if(id == null){	//获取一级组织
			Criterion criterion = Restrictions.eq("lft", 1);
			PO po = dao.findByCriteria(criterion).get(0);
			id = (ID)po.getId();
		}
		return dao.getChild(id);
	}
	
	/**获取节点信息，包括后代节点，以及每个节点的层次信息，结果按左值排序
	 * */
	public List<PO> getNodeWithDescendant(ID id){
		return dao.getNodeWithDescendant(id);
	}
	
	/**获取所有后代节点的ID
	 * @param id 父节点Id
	 * @return List<ID>
	 * */
	public List<ID> getDescendantId(ID id){
		return dao.getDescendantId(id);
	}
	
	/**获取整张表的邻接列表模型*/
	public List<PO> getAdjTree(){
		return dao.getAdjTree();
	}
	
	/**根据ID数组将数据转换为邻接列表模型*/
	public List<PO> transformAdjTree(ID[] ids){
		if(ids.length == 0)
			return null;
		return dao.transformAdjTree(ids);
	}
	
}