package com.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.transaction.annotation.Transactional;

import com.common.po.NestTreePO;
import com.util.JacksonJson;

/**操作嵌套集合（层次）树
 * */
public class NestTreeDao<ID extends Serializable,PO extends NestTreePO> extends BaseDao<ID,PO> {

	/**添加根节点
	 * @deprecated 根节点直接人工操作数据库,不建议通过页面添加，这有可能导致添加根节点的用户越级添加的风险
	 * */
	@Transactional
	public PO addRootNode(PO po){
		po.setLft(1);
		//更新表中所有节点的左右值
		super.executeUpdate("update "+super.persistentName+" set lft = lft + 1 order by lft desc");
		super.executeUpdate("update "+super.persistentName+" set rgt = rgt + 1 order by rgt desc");
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
	public PO addNode(ID pId, PO po){
		PO pNode = super.findById(pId);
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
		String updateLft = "update "+super.persistentName+" set lft = lft + 2 where lft > :refValue order by lft desc";
		String updateRgt = "update "+super.persistentName+" set rgt = rgt + 2 where rgt > :refValue order by rgt desc";
		super.executeUpdate(updateLft, new String[]{"refValue"}, new Integer[]{refValue});
		super.executeUpdate(updateRgt, new String[]{"refValue"}, new Integer[]{refValue});
		
		po.setLft(refValue+1);
		po.setRgt(refValue+2);
		super.save(po);
		return po;
	}
	
	/**非递归删除节点，其子节点连接到被删节点的父节点
	 * @param id 被删除节点的id
	 * */
	public void delNodeNonrecursion(ID id){
		PO po = super.findById(id);
		int lft = po.getLft();
		int rgt = po.getRgt();
		super.delete(po);
		super.flush();//此处需要flush，否则接下来连续两句的update有可能会导致键重复错误
		//更新所有后代节点的左右值
		super.executeUpdate(
			"update "+super.persistentName+" set lft = lft-1, rgt = rgt-1 where lft between :lft and :rgt order by lft",
			new String[]{"lft","rgt"}, new Integer[]{lft,rgt});
		//更新被删节点所有右边节点的左右值
		super.executeUpdate("update "+super.persistentName+" set rgt = rgt-2 where rgt > :rgt order by rgt",
			new String[]{"rgt"}, new Integer[]{rgt});
		super.executeUpdate("update "+super.persistentName+" set lft = lft-2 where lft > :rgt order by lft",
			new String[]{"rgt"}, new Integer[]{rgt});
	}
	
	/**递归删除节点
	 * @param id 被删除节点的id
	 * */
	public void delNodeRecursion(ID id){
		PO po = super.findById(id);
		int lft = po.getLft();
		int rgt = po.getRgt();
		int width = rgt - lft + 1;
		super.executeUpdate("delete from "+super.persistentName+" where lft between :lft and :rgt",
				new String[]{"lft","rgt"}, new Integer[]{lft,rgt});
		super.executeUpdate("update "+super.persistentName+" set rgt = rgt-:width where rgt > :rgt order by rgt",
				new String[]{"width","rgt"}, new Integer[]{width,rgt});
		super.executeUpdate("update "+super.persistentName+" set lft = lft-:width where lft > :rgt order by lft",
				new String[]{"width","rgt"}, new Integer[]{width,rgt});
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
			.append(tableName).append(" where lft between :lft1 and :rgt1) as t1, (select lft,rgt from ")
			.append(tableName).append(" where lft between :lft2 and :rgt2) as t2 where t1.lft between t2.lft and t2.rgt group by t1.lft order by t1.lft");

		List<Object[]> scalar = new ArrayList<Object[]>();
		scalar.add(new Object[]{"id",StandardBasicTypes.INTEGER});
        for (Object[] propertyColumnName : propertyColumnNames) {
        	scalar.add(new Object[]{propertyColumnName[0],propertyColumnName[2]});
        }
        scalar.add(new Object[]{"hierarchy",StandardBasicTypes.LONG});
        List<Integer> values = new ArrayList<Integer>();
        values.add(node.getLft());
        values.add(node.getRgt());
        values.add(node.getLft());
        values.add(node.getRgt());
		List<PO> list = (List<PO>)super.findByNativeSql(sqlBuffer.toString(), 
				new String[]{"lft1","rgt1","lft2","rgt2"}, values, scalar);
		return list;
	}

	/**查询某节点的所有后代节点的ID
	 * @param id 父节点Id
	 * @return List<ID>
	 * */
	@SuppressWarnings("unchecked")
	public List<ID> getDescendantId(ID id){
		PO po = super.findById(id);
		List<Integer> list = new ArrayList<Integer>();
		list.add(po.getLft()+1);
		list.add(po.getRgt());
		return (List<ID>)find("select id from "+super.persistentName+" where lft between ? and ?",list);
	}
	
	/**将匹配的记录转换为邻接列表模型
	 * @param ids 要转换的所有记录的ID
	 * */
	public List<PO> transformAdjTree(ID[] ids){
		String tableName = super.getTableName();
		List<Object[]> propertyColumnNames = super.getPropertyAndColumnNames();
		propertyColumnNames = propertyColumnNames.subList(2, propertyColumnNames.size());
//		for(Object[] propertyColumn : propertyColumnNames){
//			System.out.println(propertyColumn[0]+" "+propertyColumn[1]);
//		}
		String sql = "select e.id, b.id as pId, e.menu_name as menuName, e.path from "
				+ "(select * from "+tableName+" where id in (:ids)) as e "
				+" left outer join "+tableName+" as b on b.lft = (select max(lft) from "+tableName
				+" as s where e.lft > s.lft and e.lft < s.rgt) ORDER BY e.lft";
		List<Object[]> scalar = new ArrayList<Object[]>();
		scalar.add(new Object[]{"id",StandardBasicTypes.INTEGER});
		scalar.add(new Object[]{"pId",StandardBasicTypes.INTEGER});
		scalar.add(new Object[]{"menuName",StandardBasicTypes.STRING});
		scalar.add(new Object[]{"path",StandardBasicTypes.STRING});
		List<ID[]> values = new ArrayList<ID[]>();
		values.add(ids);
		List<PO> list = super.findByNativeSql(sql.toString(), new String[]{"ids"},
				values, scalar);
		return list;
	}
}
