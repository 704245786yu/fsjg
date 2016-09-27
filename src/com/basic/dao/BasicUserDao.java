package com.basic.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.basic.po.BasicUser;
import com.common.BaseDao;

@Repository
public class BasicUserDao extends BaseDao<Integer, BasicUser>{

	/**检查用户名、手机号是否已经存在*/
	public boolean isExist(String userName,Long telephone){
		String hql = "select count(1) from BasicUser where userName =:userName or telephone =:telephone";
		long amount = super.getCount(hql, new String[]{"userName","telephone"}, new Object[]{userName,telephone});
		if(amount > 0)
			return true;
		else
			return false;
	}
	
	public boolean nameIsExist(String userName, Integer id){
		long amount = 0;
		if(id == null){
			String hql = "select count(1) from BasicUser where userName =:userName";
			amount = super.getCount(hql, new String[]{"userName"}, new String[]{userName});
		}else{
			String hql = "select count(1) from BasicUser where userName =:userName and id !=:id";
			amount = super.getCount(hql, new String[]{"userName","id"}, new Object[]{userName,id});
		}
		return amount>0 ? true : false;
	}
	
	/**手机号码是否已存在，若id为null判断全表，若不为null，则排除该id值的记录
	 * @param id 要排除在检测范围内的id记录
	 * */
	public boolean teleIsExist(Long telephone, Integer id){
		long amount = 0;
		if(id == null){
			String hql = "select count(1) from BasicUser where telephone =:telephone";
			amount = super.getCount(hql, new String[]{"telephone"}, new Long[]{telephone});
		}else{
			String hql = "select count(1) from BasicUser where telephone =:telephone and id !=:id";
			amount = super.getCount(hql, new String[]{"telephone","id"}, new Object[]{telephone,id});
		}
		return amount>0 ? true : false;
	}
	
	/**手机号是否存在
	 * 返回存在手机号
	 * */
	@SuppressWarnings("unchecked")
	public List<Long> teleIsExist(Collection<Long> telephones){
		String hql = "select telephone from BasicUser where telephone in (:telephones)";
		return (List<Long>)super.find(hql, new String[]{"telephones"}, new Object[]{telephones});
	}
	
	/**用户登录
	 * 检查用户名或者手机号码
	 * */
	public BasicUser login(String nameOrTele, String password){
		Criterion c1 = Restrictions.eq("userName", nameOrTele);
		Criterion c2 = Restrictions.eq("password", password);
		List<BasicUser> list = super.findByCriteria(c1,c2);
		if(list.size() == 1)
			return list.get(0);
		try {
			long telephone = Long.parseLong(nameOrTele);
			c1 = Restrictions.eq("telephone", telephone);
			list = super.findByCriteria(c1,c2);
			if(list.size() == 1)
				return list.get(0);
		} catch (NumberFormatException e) {
			return null;
		}
		return null;
	}
}
