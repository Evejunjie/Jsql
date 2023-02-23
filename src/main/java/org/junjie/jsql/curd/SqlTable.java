package org.junjie.jsql.curd;

public interface SqlTable extends SqlAlias<SqlTable> {
	
	/**
	 * 获取表名
	 * @return
	 */
	public String getTableName();
	
}
