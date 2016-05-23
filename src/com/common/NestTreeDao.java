package com.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.transaction.annotation.Transactional;

import com.common.po.NestTreePO;

/**操作嵌套集合（层次）树
 * */
public class NestTreeDao<ID extends Serializable,PO extends NestTreePO> extends BaseDao<ID,PO> {

	/**添加根节点
	 * @deprecated 根节点直接人工操作数据库,不建议通过页面添加，这有可能导致添加根节点的用户越级添加的风险
	 * */
	@Transactional
	public PO addRootNode(PO po){
		po.setLft(1);
		String tableName = super.getTableName();
		//更新表中所有节点的左右值
		super.executeUpdateByNativeSql("update "+tableName+" set lft = lft + 1 order by lft desc",new String[]{},new Object[]{});
		super.executeUpdateByNativeSql("update "+tableName+" set rgt = rgt + 1 order by rgt desc",new String[]{},new Object[]{});
		int count = ((Long)super.getCount()).intValue();
		po.setRgt( (count+1)*2 );
		super.save(po);
		return po;
	}
	
	/**添加节点
	 * @param pId 父节点
	 * @param po 将要添加的节点
	 * */
	@Transactional
	@SuppressWarnings("unchecked")
	public PO addNode(PO po){
		PO pNode = super.findById((ID)po.getpId());
		String tableName = super.getTableName();
		int lft = pNode.getLft();
		int rgt = pNode.getRgt();
		int refValue = 0;
		if((rgt-lft) == 1){
			//pNode为叶子节点
			refValue = lft;
		}else{
			//pNode有子节点,将要添加的节点放在最后
			refValue = rgt - 1;	//rgt-1为父节点当前最右子节点的右值
		}
		String updateLft = "update "+tableName+" set lft = lft + 2 where lft > :refValue order by lft desc";
		String updateRgt = "update "+tableName+" set rgt = rgt + 2 where rgt > :refValue order by rgt desc";
		super.executeUpdateByNativeSql(updateLft, new String[]{"refValue"}, new Integer[]{refValue});
		super.executeUpdateByNativeSql(updateRgt, new String[]{"refValue"}, new Integer[]{refValue});
		
		po.setLft(refValue+1);
		po.setRgt(refValue+2);
		super.save(po);
		return po;
	}
	
	/**更新节点信息（不更改节点位置）
	 * */
	@SuppressWarnings("unchecked")
	public void updateNode(PO po){
		PO node = super.findById((ID)po.getId());
		po.setLft(node.getLft());
		po.setRgt(node.getRgt());
		super.clear();
		super.update(po);
	}
	
	/**递归删除节点
	 * @param id 被删除节点的id
	 * */
	public void delNodeRecursion(ID id){
		PO po = super.findById(id);
		String tableName = super.getTableName();
		int lft = po.getLft();
		int rgt = po.getRgt();
		int width = rgt - lft + 1;
		super.executeUpdate("delete from "+super.persistentName+" where lft between :lft and :rgt",
				new String[]{"lft","rgt"}, new Integer[]{lft,rgt});
		super.executeUpdateByNativeSql("update "+tableName+" set rgt = rgt-:width where rgt > :rgt order by rgt",
				new String[]{"width","rgt"}, new Integer[]{width,rgt});
		super.executeUpdateByNativeSql("update "+tableName+" set lft = lft-:width where lft > :rgt order by lft",
				new String[]{"width","rgt"}, new Integer[]{width,rgt});
	}
	
	/**非递归删除节点，其子节点连接到被删节点的父节点
	 * @param id 被删除节点的id
	 * */
	public void delNodeNonrecursion(ID id){
		PO po = super.findById(id);
		String tableName = super.getTableName();
		int lft = po.getLft();
		int rgt = po.getRgt();
		super.delete(po);
		super.flush();//此处需要flush，否则接下来连续两句的update有可能会导致键重复错误
		//更新所有后代节点的左右值
		super.executeUpdateByNativeSql(
			"update "+tableName+" set lft = lft-1, rgt = rgt-1 where lft between :lft and :rgt order by lft",
			new String[]{"lft","rgt"}, new Integer[]{lft,rgt});
		//更新被删节点所有右边节点的左右值
		super.executeUpdateByNativeSql("update "+tableName+" set rgt = rgt-2 where rgt > :rgt order by rgt",
			new String[]{"rgt"}, new Integer[]{rgt});
		super.executeUpdateByNativeSql("update "+tableName+" set lft = lft-2 where lft > :rgt order by lft",
			new String[]{"rgt"}, new Integer[]{rgt});
	}
	
	/**获取节点信息，包括后代节点，以及每个节点的层次信息，结果按左值排序
	 * */
	public List<PO> getNodeWithDescendant(ID id){
		PO node = super.findById(id);
		String tableName = super.getTableName();
		//获取对应数据表列名
		List<Object[]> propertyColumnNames = super.getPropertyAndColumnNames();
		propertyColumnNames = propertyColumnNames.subList(2, propertyColumnNames.size());
		
		StringBuffer sqlBuffer = new StringBuffer("select t1.id");
		for(Object[] propertyColumnName : propertyColumnNames){
			sqlBuffer.append(",t1."+propertyColumnName[1]+" as "+propertyColumnName[0]);
		}
		sqlBuffer.append(", count(t2.lft) as hierarchy from (select * from ")
			.append(tableName).append(" where lft between :lft and :rgt) as t1, (select lft,rgt from ")
			.append(tableName).append(" where lft between :lft and :rgt) as t2 where t1.lft between t2.lft and t2.rgt group by t1.lft order by t1.lft");

		List<Object[]> scalar = new ArrayList<Object[]>();
		scalar.add(new Object[]{"id",StandardBasicTypes.INTEGER});
        for (Object[] propertyColumnName : propertyColumnNames) {
        	scalar.add(new Object[]{propertyColumnName[0],propertyColumnName[2]});
        }
        scalar.add(new Object[]{"hierarchy",StandardBasicTypes.LONG});
        List<Integer> values = new ArrayList<Integer>();
        values.add(node.getLft());
        values.add(node.getRgt());
		List<PO> list = (List<PO>)super.findByNativeSql(sqlBuffer.toString(), 
				new String[]{"lft","rgt"}, values, scalar);
		return list;
	}

	/**查询某节点的所有后代节点的ID，返回的集合里不包含父节点ID本身。
	 * @param id 父节点Id
	 * @return List<ID>
	 * */
	@SuppressWarnings("unchecked")
	public List<ID> getDescendantId(ID id){
		PO po = super.findById(id);
		List<Integer> list = new ArrayList<Integer>();
		list.add(po.getLft()+1);	//避免查询的数据包含父节点本身
		list.add(po.getRgt());
		return (List<ID>)find("select id from "+super.persistentName+" where lft between ? and ?",list);
	}
	
	/**获取整张表的邻接列表模型
	 * */
	public List<PO> getAdjTree(){
		String tableName = super.getTableName();
		List<Object[]> propertyColumnNames = super.getPropertyAndColumnNames();
		propertyColumnNames = propertyColumnNames.subList(2, propertyColumnNames.size());
		StringBuffer sqlBuffer = new StringBuffer("select e.id, b.id as pId");
		for(Object[] propertyColumn : propertyColumnNames){
			sqlBuffer.append(", e."+propertyColumn[1]+" as "+propertyColumn[0]);
		}
		sqlBuffer.append(" from ").append(tableName)
			.append(" as e left outer join ").append(tableName)
			.append(" as b on b.lft = (select max(lft) from ").append(tableName)
			.append(" as s where e.lft > s.lft and e.lft < s.rgt) ORDER BY e.lft");
		List<Object[]> scalar = new ArrayList<Object[]>();
		scalar.add(new Object[]{"id",StandardBasicTypes.INTEGER});
		scalar.add(new Object[]{"pId",StandardBasicTypes.INTEGER});
        for (Object[] propertyColumnName : propertyColumnNames) {
        	scalar.add(new Object[]{propertyColumnName[0],propertyColumnName[2]});
        }
		List<PO> list = super.findByNativeSql(sqlBuffer.toString(), new String[]{},
				new ArrayList<ID[]>(), scalar);
		return list;
	}
	
	/**将匹配的记录转换为邻接列表模型
	 * @param ids 要转换的所有记录的ID
	 * */
	public List<PO> transformAdjTree(ID[] ids){
		String tableName = super.getTableName();
		List<Object[]> propertyColumnNames = super.getPropertyAndColumnNames();
		propertyColumnNames = propertyColumnNames.subList(2, propertyColumnNames.size());
		StringBuffer sqlBuffer = new StringBuffer("select e.id, b.id as pId");
		for(Object[] propertyColumn : propertyColumnNames){
			sqlBuffer.append(", e."+propertyColumn[1]+" as "+propertyColumn[0]);
		}
		sqlBuffer.append(" from (select * from ").append(tableName)
			.append(" where id in (:ids)) as e left outer join ").append(tableName)
			.append(" as b on b.lft = (select max(lft) from ").append(tableName)
			.append(" as s where e.lft > s.lft and e.lft < s.rgt) ORDER BY e.lft");
		List<Object[]> scalar = new ArrayList<Object[]>();
		scalar.add(new Object[]{"id",StandardBasicTypes.INTEGER});
		scalar.add(new Object[]{"pId",StandardBasicTypes.INTEGER});
        for (Object[] propertyColumnName : propertyColumnNames) {
        	scalar.add(new Object[]{propertyColumnName[0],propertyColumnName[2]});
        }
		List<ID[]> values = new ArrayList<ID[]>();
		values.add(ids);
		List<PO> list = super.findByNativeSql(sqlBuffer.toString(), new String[]{"ids"},
				values, scalar);
		return list;
	}
}
