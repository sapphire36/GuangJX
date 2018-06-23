package org.kzcw.core;
import java.util.List;
 
public interface BaseService<T> {
 
	public void save(T t);
	public void update(T t);//更新
	public void delete(T t);//删除
	public long findCount();//统计数量
	//查询
	public T findUniqueByProperty(String propertyName, Object value);
	public T findById(long id);
	public List<T> list();//查询所有
	public boolean ExecSQL(StringBuffer sql);
	public List<T> findByExecSQL(StringBuffer querySql); 
}
