package org.kzcw.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl<T extends Serializable> implements BaseService<T> {


	@Autowired
	protected BaseDao<T> dao;
	
	public void setBaseDao(BaseDao<T> dao) {
		this.dao = dao;
	}
	
	public void save(T t){
		General am = (General)t;
		am.setADDTIME(new Date()); 
		dao.save(t);
	}
	
	public void update(T t){
		dao.update(t);
	}
	
	public void delete(T t) {
		dao.delete(t);
	}

	public long findCount() {
		return 1;
		//return dao.findCount();
	}
	
	public T findUniqueBy(String propertyName, Object value) {
		return null;
		//return dao.findUniqueBy(query);
	}
    //按照ID查找
	public T findById(long id) {
		return this.findUniqueBy("id", id);
	}

    //判断是否存在
	public boolean isExist(String propertyName, String value, long id) {
		General m = null;
		if("number".equals(propertyName)){
			m = (General) this.findUniqueBy(propertyName, Integer.valueOf(value));
		}else{
			m = (General) this.findUniqueBy(propertyName, value);
		}
		if (m == null) {
			return true;
		} else if (m.getID() == id) {
			return true;
		} else {
			return false;
		}
	}

	public boolean ExecSQL(String sql) {
		return true;
	}
	
	public List<T> findByExecSQL(StringBuffer querySql){
	     return dao.findEntryByExecSQL(querySql);
	}
}
