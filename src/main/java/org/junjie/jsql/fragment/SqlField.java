package org.junjie.jsql.fragment;

import org.junjie.jsql.curd.SqlDeposit;

/**
 * SQL 表字段, 是用于 查询列,更新,插入列 字段只有数据库名称, 对于函数来说 优先使用别名 字段名是完整的
 * 
 * @author junjie
 *
 */
public interface SqlField extends SqlFragment {

	/**
	 * 获取字段名称
	 * 
	 * @return
	 */
	public String getDbfield();
	
	
	/**
	 * 返回的数据类型
	 * @return
	 */
	public Class<?> getType();
	

	/**
	 * 写入名称,  别名 或者是 字段名称,  不是其中之一则是 完整片段
	 * @param deposit
	 */
	public  void writeField(SqlDeposit deposit);
	
}
