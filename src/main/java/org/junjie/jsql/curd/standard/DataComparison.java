package org.junjie.jsql.curd.standard;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.keyword.Subquery;
import org.junjie.jut.LambdaFieldFunction;

/**
 * 条件比对
 * 
 * @author junjie
 *
 * @param <E>      实体类泛型
 * @param <Retrun> 返回值, 通常是自身
 */
public interface DataComparison<E, Retrun> {

	/**
	 * 指定实体类条件
	 * 
	 * @param entity
	 * @return
	 */
	public Retrun entity(E entity);

	public E entity();

	public <R> Retrun eq(LambdaFieldFunction<E, R> field, R value);

	default <R> Retrun eq(LambdaFieldFunction<E, R> field) {
		return eq(field, field.apply(entity()));
	};

	public <R> Retrun ne(LambdaFieldFunction<E, R> field, R value);

	public <R> Retrun ge(LambdaFieldFunction<E, R> field, R value);

	public <R> Retrun le(LambdaFieldFunction<E, R> field, R value);

	public <R> Retrun gt(LambdaFieldFunction<E, R> field, R value);

	public <R> Retrun lt(LambdaFieldFunction<E, R> field, R value);

	public <R> Retrun likeAll(LambdaFieldFunction<E, R> field, CharSequence value);

	public <R> Retrun like(LambdaFieldFunction<E, R> field, CharSequence value);

	public <R> Retrun likeLift(LambdaFieldFunction<E, R> field, CharSequence value);

	public <R> Retrun likeRight(LambdaFieldFunction<E, R> field, CharSequence value);

	public <R> Retrun in(LambdaFieldFunction<E, R> field, Collection<Object> value);

	public <R> Retrun notIn(LambdaFieldFunction<E, R> field, Collection<Object> value);

	public <R> Retrun between(LambdaFieldFunction<E, R> field, R state, R end);

	public <R> Retrun notBetween(LambdaFieldFunction<E, R> field, R state, R end);

	public <R> Retrun and();

	public <R> Retrun or();

	public Subquery<E> and(Consumer<Subquery<E>> filter);

	public Subquery<E> or(Consumer<Subquery<E>> filter);

	default SqlCase CASE() {
		return CASE((SqlFragment) null);
	};

	public <R> SqlCase CASE(LambdaFieldFunction<E, R> field);

	default SqlCase CASE(Object value) {
		return CASE(SQLCode.ofValueCode(Objects.requireNonNull(value)));
	};

	public SqlCase CASE(SqlFragment fragment);

}
