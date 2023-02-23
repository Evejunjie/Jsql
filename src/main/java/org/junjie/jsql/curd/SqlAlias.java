package org.junjie.jsql.curd;

public interface SqlAlias<R> {

	/**
	 * 别名
	 * @return
	 */
	public String as();
	
	/**
	 * 设置别名
	 * @param value
	 */
	public R as(String value);
	
}
