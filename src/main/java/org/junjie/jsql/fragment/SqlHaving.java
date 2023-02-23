package org.junjie.jsql.fragment;

import java.util.Collection;
import java.util.function.Consumer;

import org.junjie.jsql.fragment.keyword.SqlBetweenFragment;
import org.junjie.jsql.fragment.keyword.SqlLikeFragment;
import org.junjie.jut.LambdaFieldFunction;

/**
 * Having 条件过滤 SQL Server 具有子句限制分组依据返回的行数（或记录数）。<br>
 * Have 子句以在组应用聚合函数后筛选数据。
 * 
 * Where 不允许我们根据汇总数据检查任何条件。<br>
 * 因此，要根据汇总数据检查任何条件，我们必须使用 Have 子句。<br>
 * 要使用 SQL Server 具有子句，我们必须使用 Group By，因为 With 会过滤我们从该集合中获取的数据。<br>
 * 如果我们没有使用 GROUP BY，那么这个子句的行为就像一个 WHERE <br>
 * 
 * @param <E> 实体类
 * @author junjie
 *
 */
public interface SqlHaving extends SqlFragment {

	/**
	 * 是否 有条件, 有时候是调用了 但没有使用
	 * 
	 * @return
	 */
	public boolean isEmpty();

	public <E, R> SqlHaving eq(LambdaFieldFunction<E, R> filed, R value);

	public <E, R> SqlHaving eq(SqlFragment filed, SqlFragment compare);

	public <E, R> SqlHaving ne(SqlFragment filed, SqlFragment compare);

	public <E, R> SqlHaving ne(LambdaFieldFunction<E, R> filed, R value);

	public <E, R> SqlLikeFragment like(LambdaFieldFunction<E, R> filed);

	public <E, R> SqlLikeFragment like(SqlFragment filed);

	public <E, R> SqlLikeFragment notLike(LambdaFieldFunction<E, R> filed);

	public <E, R> SqlLikeFragment notLike(SqlFragment filed);

	public  SqlHaving in(SqlFragment filed, Collection<Object> value);

	public <E,R> SqlHaving in(LambdaFieldFunction<E, R> filed, Collection<Object> value);

	public SqlHaving in(SqlFragment filed, SqlFragment compare);

	public <E, R> SqlHaving notIn(LambdaFieldFunction<E, R> filed, Collection<Object> value);

	public <R> SqlHaving notIn(SqlFragment filed, Collection<Object> value);

	public <R> SqlHaving notIn(SqlFragment filed, SqlFragment compare);

	public <E, R> SqlHaving isNull(LambdaFieldFunction<E, R> filed);

	public SqlHaving isNull(SqlFragment field);

	public <E, R> SqlHaving isNotNull(LambdaFieldFunction<E, R> filed);

	public SqlHaving isNotNull(SqlFragment field);

	public <E, R> SqlHaving ge(LambdaFieldFunction<E, R> filed, R value);

	public SqlHaving ge(SqlFragment field, SqlFragment compare);

	public <E, R> SqlHaving gt(LambdaFieldFunction<E, R> filed, R value);

	public SqlHaving gt(SqlFragment filed, SqlFragment compare);

	public <E, R> SqlHaving le(LambdaFieldFunction<E, R> filed, R value);

	public SqlHaving le(SqlFragment filed, SqlFragment compare);

	public <E, R> SqlHaving lt(LambdaFieldFunction<E, R> filed, R value);

	public SqlHaving lt(SqlFragment filed, SqlFragment compare);

	public SqlHaving and();

	public SqlHaving and(Consumer<SqlHaving> subcondition);

	public SqlHaving or();

	public SqlHaving or(Consumer<SqlHaving> subcondition);

	public <E, R> SqlHaving between(LambdaFieldFunction<E, R> filed, R start, R end);

	public <R, E> SqlBetweenFragment between(LambdaFieldFunction<E, R> filed);

	public <R, E> SqlBetweenFragment notBetween(LambdaFieldFunction<E, R> filed);

	public <R, E> SqlHaving notBetween(LambdaFieldFunction<E, R> filed, R start, R end);

}
