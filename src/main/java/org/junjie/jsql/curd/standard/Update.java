package org.junjie.jsql.curd.standard;

import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jut.LambdaFieldFunction;

public interface Update<E, Retrun> extends DataComparison<E, Retrun> {

	/**
	 * 设置实体类数据, 用于更新, 如果 没有指定列 那么则是全更新
	 * 
	 * @param entity
	 * @return
	 */
	Retrun setEntity(E entity);

	/**
	 * 更新字段指定值
	 * 
	 * @param <R>
	 * @param field
	 * @param apply
	 * @return
	 */
	public <R> Retrun set(LambdaFieldFunction<E, R> field, R apply);

	/**
	 * 更新字段, 从 setEntity 设置值
	 * 
	 * @param <R>
	 * @param field
	 * @return
	 */
	public <R> Retrun set(LambdaFieldFunction<E, R> field);

	/**
	 * 更新字段
	 * 
	 * @param field 指定字段值
	 * @param value 值
	 * @return
	 */
	public Retrun set(SqlField field, SqlFragment value);

}
