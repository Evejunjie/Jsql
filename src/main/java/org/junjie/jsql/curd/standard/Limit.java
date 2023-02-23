package org.junjie.jsql.curd.standard;

public interface Limit<Return> {

	/**
	 * 分页
	 * 
	 * @param start 开始页
	 * @param row   查询多上条数据
	 * @return
	 */
	public Return limit(long start, long row);
	
	/**
	 * 分页 多上条
	 * @param row 
	 * @return
	 */
	public Return limit(long row);
	
}
