package org.kzcw.core;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
public class Query {

	// 单例模式实例
	private List<QueryTrem> list = new ArrayList<QueryTrem>();;

 
	public List<QueryTrem> addLikeEnd(String propertyName, Object value) {
		QueryTrem queryTrem = new QueryTrem();
		queryTrem.setPropertyName(propertyName);
		queryTrem.setValue(value);
		queryTrem.setType(QueryTrem.LIKEEND);
		this.list.add(queryTrem);
		return this.list;
	}

	public List<QueryTrem> addGeTerm(String propertyName, Object value) {
		//大于等于
		QueryTrem queryTrem = new QueryTrem();
		queryTrem.setPropertyName(propertyName);
		queryTrem.setValue(value);
		queryTrem.setType(QueryTrem.GE);
		this.list.add(queryTrem);
		return this.list;
	}
	
	public List<QueryTrem> addLeTerm(String propertyName, Object value) {
		//小于等于
		QueryTrem queryTrem = new QueryTrem();
		queryTrem.setPropertyName(propertyName);
		queryTrem.setValue(value);
		queryTrem.setType(QueryTrem.LE);
		this.list.add(queryTrem);
		return this.list;
	}

	public List<QueryTrem> addEqTerm(String propertyName, Object value) {
		//等于
		QueryTrem queryTrem = new QueryTrem();
		queryTrem.setPropertyName(propertyName);
		queryTrem.setValue(value);
		queryTrem.setType(QueryTrem.EQ);
		this.list.add(queryTrem);
		return this.list;
	}

	/**
	 * 不等于
	 * @param propertyName
	 * @param value
	 * @return List<QueryTrem>
	 */
	public List<QueryTrem> addNotTerm(String propertyName, Object value) {
		QueryTrem queryTrem = new QueryTrem();
		queryTrem.setPropertyName(propertyName);
		queryTrem.setValue(value);
		queryTrem.setType(QueryTrem.NOT);
		this.list.add(queryTrem);
		return this.list;
	}

	public List<QueryTrem> addSort(String propertyName, Object value) {
		//大于等于
		QueryTrem queryTrem = new QueryTrem();
		queryTrem.setPropertyName(propertyName);
		queryTrem.setValue(value);
		queryTrem.setType(QueryTrem.SORT);
		this.list.add(queryTrem);
		return this.list;
	}

	public List<QueryTrem> addInTerm(String propertyName, Object[] values) {
		//包括
		QueryTrem queryTrem = new QueryTrem();
		queryTrem.setPropertyName(propertyName);
		queryTrem.setValues(values);
		queryTrem.setType(QueryTrem.IN);
		this.list.add(queryTrem);
		return this.list;
	}

	/**
	 * in使用
	 * @param propertyName
	 * @param value
	 * @return List<QueryTrem>
	 */
	public List<QueryTrem> addInTerm(String propertyName, String value) {
		QueryTrem queryTrem = new QueryTrem();
		queryTrem.setPropertyName(propertyName);
		queryTrem.setValues(value.split(","));
		queryTrem.setType(QueryTrem.IN);
		this.list.add(queryTrem);
		return this.list;
	}

	public static Criteria returnCriteriaSql(List<QueryTrem> queryTrem, Criteria criteria) {
		return criteria;
	}

	public List<QueryTrem> getList() {
		return list;
	}
}
