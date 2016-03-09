package com.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**数据库操作基类
 * @param T 实体类的类型
 * @param ID 实体类的ID属性的类型，实现了Serializable接口
 * @author zhiyu
 * @version 2016-03-04
 * */
@SuppressWarnings("unchecked")
public class BaseDao<T, ID extends Serializable> {

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> persistentClass;	//实体类类型
	
	public BaseDao(){
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		persistentClass = (Class<T>)params[0];
	}
	
	/**获取上下文关联的Session*/
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void flush(){
		
	}
	
	public void clear(){
		
	}
	
	/**根据ID加载实体，实体类型为Dao的泛型参数类型
	 * @param id 主键
	 * @return 若未查找到返回null
	 * */
	public T findById(ID id){
		return (T)getCurrentSession().get(persistentClass, id);
	}
	
	/**根据实体类型，ID加载实体。此重载方法是为了便于查找非Dao泛型参数的实体。
	 * @param entityClass 要加载的实体类型
	 * @param id 主键
	 * @return 若未查找到返回null
	 */
	public T findById(Class<T> entityClass, Serializable id){
		return (T)getCurrentSession().get(entityClass, id);
	}
	
//	public List<T> findByExample(T exampleInstance,String... excludeProperty){
//		
//	}
	
	/**保存实体
	 * */
	public Serializable save(T entity){
		return sessionFactory.getCurrentSession().save(entity);
	}
	
	/**更新实体
	 * */
	public void update(T entity){
		sessionFactory.getCurrentSession().update(entity);
	}
	
	/**删除实体
	 * */
	public void delete(T entity){
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	/**根据 ID 删除实体
	 * */
	public void delete(Class<T> entityClazz , Serializable id){
		sessionFactory.getCurrentSession()
		.createQuery("delete " + entityClazz.getSimpleName()
		+ " en where en.id = ?0")
		.setParameter("0" , id)
		.executeUpdate();
	}
	
	/**获取所有实体
	 * */
	public List<T> findAll(Class<T> entityClazz){
		return getCurrentSession()
				.createQuery("from "+entityClazz.getSimpleName()).list();
	}
	
	/**获取实体总数
	 * */
	public long findCount(Class<T> entityClazz){
		List<T> l = sessionFactory.getCurrentSession().createQuery("select count(*) from "
		+ entityClazz.getSimpleName()).list();
		// 返回查询得到的实体总数
		if (l != null && l.size() == 1 )
		{
		return (Long)l.get(0);
		}
		return 0;
	}
	
	/**根据 HQL语句查询实体*/
	protected List<T> find(String hql){
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	/**根据带占位符参数的 HQL语句查询实体*/
	protected List<T> find(String hql , Object... params){
		// 创建查询
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		// 为包含占位符的 HQL 语句设置参数
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		return (List<T>)query.list();
	}
	
	/**
	* 使用 HQL 语句进行分页查询操作
	* @param hql 需要查询的 HQL 语句
	* @param pageNo 查询第 pageNo 页的记录
	* @param pageSize 每页需要显示的记录数
	* @return 当前页的所有记录
	*/
	protected List<T> findByPage(String hql, int pageNo, int pageSize){
		return sessionFactory.getCurrentSession().createQuery(hql)
			.setFirstResult((pageNo - 1) * pageSize)
			.setMaxResults(pageSize)
			.list();
	}
	
	/**
	* 使用 HQL 语句进行分页查询操作
	* @param hql 需要查询的 HQL 语句
	* @param params 如果 hql 带占位符参数， params 用于传入占位符参数
	* @param pageNo 查询第 pageNo 页的记录
	* @param pageSize 每页需要显示的记录数
	* @return 当前页的所有记录
	*/
	protected List<T> findByPage(String hql , int pageNo, int pageSize, Object... params)
	{
		// 创建查询
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		// 为包含占位符的 HQL 语句设置参数
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		// 执行分页，并返回查询结果
		return query.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
	}
}
