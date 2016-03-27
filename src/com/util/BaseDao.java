package com.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**数据库操作基类
 * @param ID 实体类的ID属性的类型，实现了Serializable接口
 * @param T 实体类的类型
 * @author zhiyu
 * @version 2016-03-04
 * */
@SuppressWarnings("unchecked")
@Transactional
public class BaseDao<ID extends Serializable, T> {

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> persistentClass;	//实体类类型
	
	public BaseDao(){
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		persistentClass = (Class<T>)params[1];
	}
	
//	session.flush();//保持与数据库数据的同步
//	session.clear();//清楚内部缓存的全部数据，及时释放出占用的内存
	
	/**获取上下文关联的Session*/
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	/*========================================
	 * 增、删、改
	 * =======================================*/
	
	/**保存实体*/
	public Serializable save(T entity){
		return getCurrentSession().save(entity);
	}
	
	/**更新实体*/
	public void update(T entity){
		getCurrentSession().update(entity);
	}
	
	/**保存或更新实体*/
	public void saveOrUpdate(T entity){
		getCurrentSession().saveOrUpdate(entity);
	}
	
	/**删除实体*/
	public void delete(T entity){
		getCurrentSession().delete(entity);
	}
	
	/**根据ID删除实体*/
	public void deleteById(ID id){
		Query query = getCurrentSession()
				.createQuery("delete "+persistentClass.getSimpleName()+"where id = :id");
		query.setParameter("id" , id).executeUpdate();
	}
	
	/**批量保存*/
	public void saveBatch(List<T> list){
		Session session = getCurrentSession();
		for(int i=0; i<list.size(); i++){
			session.save(list.get(i));
			if(i%30 == 0){//单次批量操作的数目
				session.flush();
				session.clear();
			}
		}
	}
	
	/**批量保存或更新*/
	public void saveOrUpdateBatch(List<T> list){
		Session session = getCurrentSession();
		for(int i=0; i<list.size(); i++){
			session.saveOrUpdate(list.get(i));
			if(i%30 == 0){//单次批量操作的数目
				session.flush();
				session.clear();
			}
		}
	}
	
	/**根据Id数组批量删除
	 * @param ids id数组
	 * */
	public void deleteByIds(ID[] ids){
		Query query = getCurrentSession()
				.createQuery("delete "+persistentClass.getSimpleName()+" where id in (:ids)");
		query.setParameterList("ids", ids).executeUpdate();
	}
	
	
	/*==============================================
	 * 查询
	 * =============================================*/
	
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
	
	/**根据Ids集合查询*/
	public List<T> findByIds(Collection<Serializable> ids){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		Criterion criterion = Restrictions.in("id", ids);
		criteria.add(criterion);
		return criteria.list();
	}
	
	/**获取实体总数*/
	public long findCount(){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	/**根据HQL查询记录总数
	 * @param hql hql语句
	 * */
	public long findCount(String hql, Object... params){
		Query query = getCurrentSession().createQuery(hql);
		for(int i=0; i<params.length; i++)
		{
			query.setParameter(i, params[i]);
		}
		return (Long)query.uniqueResult();
	}
	
	/**根据传入的属性执行查询
	 * @param propertyNames 精确查询属性名
	 * @param values1 精确查询属性值
	 * @param propertyNames 模糊匹配查询属性名
	 * @param values1 模糊匹配查询属性值，参数值中需加入模糊匹配符
	 * */
	public List<T> findByProperty(String[] propertyNames,Object[] values1, 
			String[] propertyNamesByLike,Object[] values2){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);

		for(int i=0; i<propertyNames.length; i++){
			criteria.add(Restrictions.eq(propertyNames[i], values1[i]));
		}
		for(int i=0; i<propertyNamesByLike.length; i++){
			criteria.add(Restrictions.like(propertyNamesByLike[i], values2[i]));
		}
		return criteria.list();
	}
	
	/**QBE查询方式
	 * @param exampleEntity 包含查询条件的实例
	 * @param excludeProperty 不需要查询的属性名
	 * */
	public List<T> findByExample(T exampleEntity,String... excludeProperty){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		Example example = Example.create(exampleEntity);
		for(String exclude : excludeProperty){
			example.excludeProperty(exclude);
		}
		criteria.add(example);
		return criteria.list();
	}
	
	/**获取所有实体*/
	public List<T> getAll(){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		return criteria.list();
	}
	
	
	protected List<T> findByCriteria(Criterion... criterion){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		for(Criterion c : criterion){
			criteria.add(c);
		}
		return criteria.list();
	}
	
	/**根据 HQL语句查询实体*/
	protected List<T> find(String hql){
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	/**根据带占位符参数的 HQL语句查询实体*/
	protected List<T> find(String hql, Object... params){
		Query query = getCurrentSession().createQuery(hql);
		// 为包含占位符的 HQL 语句设置参数
		for(int i=0; i<params.length; i++)
		{
			query.setParameter(i, params[i]);
		}
		return query.list();
	}
	
	
	public List<T> findByPage(int pageNo, int pageSize){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		return criteria.list();
	}
	
	/**
	* 使用 HQL 语句进行分页查询操作
	* @param hql 需要查询的 HQL语句
	* @param params 如果hql带占位符参数， params用于传入占位符参数
	* @param pageNo 查询第 pageNo页的记录
	* @param pageSize 每页需要显示的记录数
	* @return 当前页的所有记录
	*/
	protected List<T> findByPage(String hql , int pageNo, int pageSize, Object... params)
	{
		Query query = getCurrentSession().createQuery(hql);
		//为包含占位符的 HQL语句设置参数
		for(int i=0; i<params.length; i++)
		{
			query.setParameter(i, params[i]);
		}
		// 执行分页，并返回查询结果
		return query.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
	}
}
