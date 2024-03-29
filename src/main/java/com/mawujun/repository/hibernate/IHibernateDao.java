package com.mawujun.repository.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.mawujun.repository.cnd.Cnd;

/**
 * 限制 方法名称相同的时候，参数个数必须不同，否则MyMapperProxy在调用的时候不能识别，原因是这里不能使用具体的泛型
 * @author mawujun email:16064988@163.com qq:16064988
 *
 */
public interface IHibernateDao <T, ID extends Serializable>{

	public ID create(T entity);
	public void create(final Cnd cnd);
	public void createOrUpdate(final T entity);
	
	public void update(T entity);
	/**
	 * 通过Cnd。update().set(...)。andEquals();来指定更新的字段和条件
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param cnd
	 */
	public void update(Cnd cnd);
	/**
	 * 动态更新，对有值的字段进行更新，即如果字段=null，那就不进行更新
	 * @param entity
	 */
	public void updateIgnoreNull(final T entity);
	
	
	public void delete(T entity);
	public void deleteById(ID id);
	public T get(ID id);
	
	
	
	
	
	public Serializable[] createBatch(final T... entities);
	public Serializable[] createBatch(final Collection<T> entities);
	
	/**
	 * 注意，如果某个属性没有设置值，将会更新为null
	 * @author mawujun 16064988@qq.com 
	 * @param entities
	 * @return
	 */
	public int updateBatch(final T... entities);
	public int updateBatch(final Collection<T> entities);
	
	public int deleteAll();
	/**
	 * 注意，使用Cnd的地方表示删除的是泛型指定的类。
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param cnd
	 * @return
	 */
	public int deleteBatch(Cnd cnd);
	public int deleteBatch(final Collection<T> entities);
	public int deleteBatch(final T... entities);
	public int deleteBatch(final ID... IDS);
	
	
	public int queryCount(Cnd cnd);
	public Object queryMax(String property,Cnd cnd);
	public Object queryMax(Cnd cnd);
	public Object queryMin(String property,Cnd cnd);
	public Object queryMin(Cnd cnd);
	public Object querySum(Cnd cnd);
	public Object queryAvg(Cnd cnd);
	/**
	 * 返回第一个对象
	 * @author mawujun email:mawujun1234@163.com qq:16064988
	 * @param whereInfos
	 * @return
	 */
	public T queryUnique(Cnd cnd);
	public <M> M queryUnique(Cnd cnd,Class<M> classM);
	
	public List<T> queryAll();
	public List<T> query(Cnd cnd);
	
	//public <M> List<M> query(Cnd cnd,Class<M> classM);
	//public PageResult<T> queryPage(final PageParam pageRequest);
	
}
