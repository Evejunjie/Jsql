package org.junjie.jsql.curd;

import org.junjie.jsql.fragment.SqlField;

/**
 * Select 查询字段, 只适用于 SELECT 查询结构
 * 
 * @author junjie
 *
 */
public interface SqlSelectField extends SqlField ,SqlAlias<SqlSelectField> {

	/**
	 * 将字段设置在 分组中, 如果字段是聚合函数 那么会自动设置一个别名, 如果有则使用已有的
	 */
	default SqlSelectField order() {
		return orderAsc();
	};

	/**
	 * 将字段作为降序排序, 从大到小,如果是聚合函数 没有别名会自动生成别名名称
	 */
	public SqlSelectField orderDesc();

	/**
	 * 将字段作为升序排序, 从小到大,如果是聚合函数 没有别名会自动生成别名名称
	 */
	public SqlSelectField orderAsc();

	/**
	 * 分组
	 * 
	 * @return
	 */
	public SqlSelectField group();

	/**
	 * 设置别名名称, 通常是自动生成,很少自主调用
	 * 
	 * @param as
	 * @return
	 */
	@Override
	public SqlSelectField as(String as);

	/**
	 * 获取别名名称
	 * 
	 * @return 返回别名名称
	 */
	@Override
	public String as();

	/**
	 * 设置去重
	 * @return 返回自己
	 */
	public SqlSelectField distinct();

}
