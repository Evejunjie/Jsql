package org.junjie.jsql.curd.standard;

import java.util.Collection;
import java.util.function.Consumer;

import org.junjie.jsql.curd.SqlTable;
import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.SqlHaving;
import org.junjie.jut.LambdaFieldFunction;

/**
 * 标准查询, 查询依赖于 Lambda 表达式, 以便在修改字段时 IDEA 会有对应的提示数据
 * 
 * @author junjie
 *
 * @param <E>      实体类泛型
 * @param <Return> 函数执行后的返回结果,通常是自身
 */
public interface Query<E, Return>
		extends DataComparison<E, Return>, SqlSelectFunction<E>, SqlBy<E, Return>, Limit<Return>, SqlTable,SqlFragment,SqlCrudExecute<E>{

	public <R> SqlField select(LambdaFieldFunction<E, R> field);

	public <R> Return select(LambdaFieldFunction<E, R>... field);

	/**
	 * 查询指定字段数据
	 * 
	 * @param <R>
	 * @param field
	 * @return
	 */
	public <R> Return select(Collection<LambdaFieldFunction<E, R>> field);

	/**
	 * SqlHaving
	 * 
	 * @param filter 过滤条件
	 * @return
	 */
	public Return having(Consumer<SqlHaving> filter);

}
