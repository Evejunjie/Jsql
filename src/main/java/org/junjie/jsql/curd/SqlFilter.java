package org.junjie.jsql.curd;

import java.util.Collection;
import java.util.Objects;

import org.junjie.jsql.curd.standard.SqlCase;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.SqlParam;
import org.junjie.jsql.fragment.keyword.SqlInFragment;
import org.junjie.jsql.fragment.keyword.SqlLikeFragment;
import org.junjie.jsql.fragment.keyword.SqlNullFragment;

/**
 * SQL 条件过滤, 用于生成 WHERE 后面的语句
 * 
 * @author junjie
 *
 */
public interface SqlFilter {

	/**
	 * 符号 [=] 数据值一直
	 * 
	 * @param field 比较字段, 也有可能是代理的 sql 片段
	 * @param value 比对的值 sql 片段
	 */
	public void eq(SqlFragment field, SqlFragment value);

	default void eq(SqlFragment field, Object value) {
		eq(field, SqlParam.of(value));
	};

	/**
	 * 数据值不一致 符号[ != 或者 <> ]
	 * 
	 * @param field
	 * @param value
	 */
	public void ne(SqlFragment field, SqlFragment value);

	default void ne(SqlFragment field, Object value) {
		ne(field, SqlParam.of(value));
	};

	/**
	 * 值是否大于 符号[ 参数1 > 参数2 ]
	 * 
	 * @param field 参数
	 * @param value 比较值
	 */
	public void gt(SqlFragment field, SqlFragment value);

	default void gt(SqlFragment field, Object value) {
		gt(field, SqlParam.of(value));
	};

	/**
	 * 值是否大于等于 符号[ 参数1 >= 参数2 ]
	 * 
	 * @param field 参数
	 * @param value 比较值
	 */
	public void ge(SqlFragment field, SqlFragment value);

	default void ge(SqlFragment field, Object value) {
		ge(field, SqlParam.of(value));
	};

	/**
	 * 值是否小于 符号[ 参数1 < 参数2 ]
	 * 
	 * @param field 参数
	 * @param value 比较值
	 */
	public void lt(SqlFragment field, SqlFragment value);

	default void lt(SqlFragment field, Object value) {
		lt(field, SqlParam.of(value));
	};

	/**
	 * 值是否小于等于 符号[ 参数1 <= 参数2 ]
	 * 
	 * @param field 参数
	 * @param value 比较值
	 */
	public void le(SqlFragment field, SqlFragment value);

	default void le(SqlFragment field, Object value) {
		le(field, SqlParam.of(value));
	};

	/**
	 * 字段值结果是否 NULL
	 * 
	 * @param field 字段或者表达式
	 */
	public void isNull(SqlNullFragment field);

	default void isNull(SqlField field) {
		isNull(SqlNullFragment.isNull(field));
	};

	/**
	 * 字段或者表达式 值不是 NULL
	 * 
	 * @param field
	 */
	public void isNotNull(SqlNullFragment field);

	default void isNotNull(SqlField field) {
		isNotNull(SqlNullFragment.isNotNull(field));
	};

	/**
	 * @param field 字段
	 * @param value 包含的值
	 */
	public void in(SqlFragment field, Collection<Object> value);

	public void in(SqlInFragment sqlin);

	/**
	 * @param field 比较的字段
	 * @param value 允许是个子查询
	 */
	public void in(SqlFragment field, SqlFragment value);

	/**
	 * 不包含 在指定集合中
	 * 
	 * @param field 字段
	 * @param value 指定集合
	 */
	public void notIn(SqlFragment field, Collection<Object> value);

	/**
	 * 不包含 在指定集合中
	 * 
	 * @param field 字段
	 * @param value 指定集合
	 */
	public void notIn(SqlFragment field, SqlFragment value);

	/**
	 * 模糊匹配不包含
	 * 
	 * @param field 字段
	 * @param value 给定的值 不会添加 百分号
	 */
	public void notLike(SqlFragment field, SqlFragment value);

	public SqlLikeFragment notLike(SqlFragment field);

	/**
	 * 模糊匹配包含
	 * 
	 * @param field 字段
	 * @param value 给定的值 不会添加 百分号
	 */
	public void like(SqlFragment field, SqlFragment value);

	default void like(SqlFragment field, CharSequence value) {
		like(field, SqlParam.of(value));
	};

	/**
	 * 模糊匹配 默认会检测第一个或者最后一个是否是通配符 不是则会进行 % 添加
	 * 
	 * @param field 字段
	 * @param value 值 必须是 参数
	 */
	public void likeAll(SqlFragment field, CharSequence value);

	/**
	 * 使用 AND 符号拼接下一个条件
	 * 
	 * @return 返回的是自身
	 */
	public void and();

	/**
	 * 使用 AND 拼接当前给定的 条件 若条件为 null 则是一个普通的 AND
	 * 
	 * @param filter
	 * @return
	 */
	public void and(SqlFragment fragment);

	/**
	 * 使用 OR 符号拼接下一个条件
	 * 
	 * @return 返回的是自身
	 */
	public void or();

	/**
	 * 使用 OR 拼接当前给定的 条件 若条件为 null 则是一个普通的 OR
	 * 
	 * @param filter
	 * @return
	 */
	public void or(SqlFragment fragment);

	/**
	 * 指定结果在给定
	 * 
	 * @param field
	 * @param start
	 * @param end
	 */
	public void between(SqlFragment field, SqlFragment start, SqlFragment end);

	default void between(SqlFragment field, Object start, Object end) {
		between(field, SqlParam.of(start), SqlParam.of(end));
	};

	/**
	 * 值不在指定的区间范围内
	 * 
	 * @param field
	 * @param start
	 * @param end
	 */
	public void notBetween(SqlFragment field, SqlFragment start, SqlFragment end);

	default void notBetween(SqlFragment field, Object start, Object end) {
		notBetween(field, SqlParam.of(start), SqlParam.of(end));
	};

	default SqlCase CASE() {
		return CASE((SqlFragment) null);
	};

	default SqlCase CASE(Object value) {
		return CASE(SQLCode.ofValueCode(Objects.requireNonNull(value)));
	};

	public SqlCase CASE(SqlFragment fragment);

}
