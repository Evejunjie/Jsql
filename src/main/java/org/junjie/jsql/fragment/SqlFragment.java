package org.junjie.jsql.fragment;

import org.junjie.jsql.curd.SqlDeposit;

/**
 *  SQL 片段 ,实现此接口 意味着 该类是用于SQL 片段拼接的,<br>
 * @see 参数注入
 * @author junjie
 *
 */
public interface SqlFragment {
	
	
	/**
	 * 将SQL 片段进行写入
	 * @param deposit
	 */
	public void writeFragment(SqlDeposit deposit);
	
}
