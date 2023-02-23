package org.junjie.jsql.curd;

import org.junjie.jsql.fragment.SqlField;

/**
 * 插入时 给定值 , 值必须是 给定, 而不是数据库读取的 <br>
 * 插入值不允许字段表达式
 * @author junjie
 *
 */
public interface SqlInsertValue extends  SqlField {
	
	
	/**
	 * 将参数写入进去, 与SQL 不同, 参数是要等待 写完SQL 后才独立处理
	 * 
	 * @param deposit
	 */
	public void writeParam(SqlDeposit deposit);
	
	
}
