package org.junjie.jsql.curd.standard;

import org.junjie.jsql.fragment.SqlFragment;

/**
 * <pre>
 * CASE ${case}
    WHEN  ${when} THEN ${then}
    ELSE ${else} END;
 * </pre>
 * 
 * @author junjie
 *
 */
public interface SqlCase extends SqlFragment {

	/**
	 * 设置 CASE 后的比对数据
	 * 
	 * @param fragment
	 * @return
	 */
	public SqlCase CASE(SqlFragment fragment);

	/**
	 * @param value 匹配一个 java 值
	 * @return
	 */
	public SqlCase CASE(Object value);

	/**
	 * @param wherer 条件数据
	 * @param then   匹配结果
	 * @return
	 */
	public SqlCase WHEN(SqlFragment when, SqlFragment then);

	/**
	 * @param whenValue
	 * @param thenValue
	 * @return
	 */
	public SqlCase WHEN(Object whenValue, Object thenValue);

	/**
	 * 最后默认值
	 * 
	 * @param then
	 * @return
	 */
	public SqlCase ELSE(SqlFragment then);

	public SqlCase ELSE(Object thenValue);

}
