package org.kzcw.core;
public class QueryTrem {
	
	public final static String LIKEEND = "LIKEEND";//like语句结束位置，相当于"like '%value'
	public final static String NOT = "NOT";//不等于
	public final static String EQ = "EQ";//等于
	public final static String GE = "GE";//大于等于
	public final static String LE = "LE";//小于等于
	public final static String SORT = "SORT";//排序
	public final static String ASC = "ASC";
	public final static String DESC = "DESC"; 
	public final static String IN = "IN";
	
	private String propertyName;
	private Object value;
	private String type;
	private Boolean or;
	private Object[] values;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	/**
	 * 获得属性 or 的值
	 * @return Boolean 
	 */
	public Boolean getOr() {
		return or;
	}

	/**
	 * 为属性 or 赋值
	 * @param or Boolean
	 */
	public void setOr(Boolean or) {
		this.or = or;
	}
}
