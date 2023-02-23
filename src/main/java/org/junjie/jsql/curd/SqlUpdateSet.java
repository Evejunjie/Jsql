package org.junjie.jsql.curd;

import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * 更新字段 字段需要包含给定的值, 和字段列表, <br>
 * 列表必须是普通的 字段 不允许是表达式<br>
 * 给定的值 允许是表达式 或者是一个值
 * 
 * @author junjie
 *
 */
public interface SqlUpdateSet extends  SqlField {
	
	/**
	 * 设置 一个表达式为,设置后  另一个则会失效
	 * @param fragment
	 */
	public void setParam(SqlFragment fragment);
	
	/**
	 * 设置
	 * @param value
	 */
	public void setParam(Object value);
	
}
