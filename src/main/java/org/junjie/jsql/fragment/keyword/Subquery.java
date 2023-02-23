package org.junjie.jsql.fragment.keyword;

import java.util.Collection;
import java.util.function.Consumer;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.curd.standard.DataComparison;
import org.junjie.jsql.curd.standard.SqlCase;
import org.junjie.jsql.entity.SqlTableEntity;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jut.LambdaFieldFunction;

/**
 * 子查询, 条件包裹 , 不会牵涉到函数
 * 
 * @author junjie
 *
 * @param <E>
 */
public class Subquery<E> implements DataComparison<E, Subquery<E>>,SqlFragment {

	protected E entity;

	protected SqlTableEntity table;

	protected SqlWhere where;

	public Subquery(SqlTableEntity table) {
		super();
		this.table = table;
		this.where = new SqlWhere();

	}

	@Override
	public Subquery<E> entity(E entity) {
		this.entity = entity;
		return this;
	}

	@Override
	public <R> Subquery<E> eq(LambdaFieldFunction<E, R> field, R value) {
		where.eq(table.getField(field), value);
		return this;
	}

	@Override
	public <R> Subquery<E> ne(LambdaFieldFunction<E, R> field, R value) {
		where.ne(table.getField(field), value);
		return this;
	}

	@Override
	public <R> Subquery<E> ge(LambdaFieldFunction<E, R> field, R value) {
		where.ge(table.getField(field), value);
		return this;
	}

	@Override
	public <R> Subquery<E> le(LambdaFieldFunction<E, R> field, R value) {
		where.le(table.getField(field), value);
		return this;
	}

	@Override
	public <R> Subquery<E> gt(LambdaFieldFunction<E, R> field, R value) {
		where.gt(table.getField(field), value);
		return this;
	}

	@Override
	public <R> Subquery<E> lt(LambdaFieldFunction<E, R> field, R value) {
		where.lt(table.getField(field), value);
		return this;
	}

	@Override
	public <R> Subquery<E> likeAll(LambdaFieldFunction<E, R> field, CharSequence value) {
		where.likeAll(table.getField(field), value);
		return this;
	}

	@Override
	public <R> Subquery<E> like(LambdaFieldFunction<E, R> field, CharSequence value) {
		where.like(table.getField(field), value);
		return this;
	}

	@Override
	public <R> Subquery<E> likeLift(LambdaFieldFunction<E, R> field, CharSequence value) {
		SqlLikeFragment like = where.like(table.getField(field));
		like.left(value.toString());
		return this;
	}

	@Override
	public <R> Subquery<E> likeRight(LambdaFieldFunction<E, R> field, CharSequence value) {
		SqlLikeFragment like = where.like(table.getField(field));
		like.right(value.toString());
		return this;
	}

	@Override
	public <R> Subquery<E> in(LambdaFieldFunction<E, R> field, Collection<Object> value) {
		where.in(table.getField(field), value);
		return this;
	}

	@Override
	public <R> Subquery<E> notIn(LambdaFieldFunction<E, R> field, Collection<Object> value) {
		where.notIn(table.getField(field), value);
		return this;
	}

	@Override
	public <R> Subquery<E> between(LambdaFieldFunction<E, R> field, R state, R end) {
		where.between(table.getField(field), state, end);
		return this;
	}

	@Override
	public <R> Subquery<E> notBetween(LambdaFieldFunction<E, R> field, R state, R end) {
		where.notBetween(table.getField(field), state, end);
		return this;
	}

	@Override
	public <R> Subquery<E> and() {
		where.and();
		return this;
	}

	@Override
	public <R> Subquery<E> or() {
		where.or();
		return this;
	}

	@Override
	public Subquery<E> and(Consumer<Subquery<E>> filter) {
		Subquery<E> subquery = new Subquery<>(table);
		subquery.entity(entity);
		filter.accept(subquery);
		and();
		where.where(subquery);
		return subquery;
	}

	@Override
	public Subquery<E> or(Consumer<Subquery<E>> filter) {
		Subquery<E> subquery = new Subquery<>(table);
		subquery.entity(entity);
		filter.accept(subquery);
		or();
		where.where(subquery);
		return subquery;
	}

	@Override
	public <R> SqlCase CASE(LambdaFieldFunction<E, R> field) {
		return CASE(field.apply(entity));
	}

	@Override
	public SqlCase CASE(SqlFragment fragment) {
		return where.CASE(fragment);
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		if(where.isEmpty()) {
			return;
		}
		deposit.writeSql('(');
		deposit.write(where);
		deposit.writeSql(')');
	}

	@Override
	public E entity() {
		return entity;
	}

}
