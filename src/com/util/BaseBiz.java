package com.util;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**基础业务类
 * */
@SuppressWarnings({"rawtypes","unchecked"})
public class BaseBiz<DAO extends BaseDao, PO> {

	@Autowired
	private DAO dao;
	
	public void save(PO entity){
		dao.save(entity);
	}
	
	public void update(PO entity){
		dao.update(entity);
	}
	
	public void delete(PO entity){
		dao.delete(entity);
	}
	
	public void deleteById(Serializable id){
		dao.deleteById(id);
	}
	
	public void deleteByIds(Serializable[] ids){
		dao.deleteByIds(ids);
	}
	
	public List<PO> getAll(){
		return dao.getAll();
	}
}
