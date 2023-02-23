package org.junjie.jsql.curd.standard;

import org.junjie.jut.LambdaFieldFunction;

public interface Insert<E, Retrun> {

	public <R> Retrun entity(E entity);

	/**
	 * 如果 entity 没有指定 , 那么更新的 则是 into 的
	 * @param <R>
	 * @param field
	 * @return
	 */
	public <R> Retrun set(LambdaFieldFunction<E, R> field);

	public <R> Retrun set(LambdaFieldFunction<E, R> field, R value);

	/**
	 * 从指定查询中读取
	 * @param <R>
	 * @param query
	 * @return
	 */
	public <R> Retrun into(Query<E, ?> query);


}
