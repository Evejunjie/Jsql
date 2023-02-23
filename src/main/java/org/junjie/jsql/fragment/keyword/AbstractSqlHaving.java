package org.junjie.jsql.fragment.keyword;

import java.util.Collection;
import java.util.function.Consumer;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.entity.SqlTableEntity;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.SqlHaving;
import org.junjie.jsql.fragment.SqlParam;
import org.junjie.jut.LambdaFieldFunction;

public class AbstractSqlHaving implements SqlHaving {

	/**
	 * 实际条件
	 */
	SqlWhere where = new SqlWhere();

	SqlTableEntity table;

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.write(where);
	}

	@Override
	public boolean isEmpty() {
		return where.isEmpty();
	}

	@Override
	public <E, R> SqlHaving eq(LambdaFieldFunction<E, R> filed, R value) {
		where.eq(table.getField(filed), value);
		return this;
	}

	@Override
	public <E, R> SqlHaving eq(SqlFragment filed, SqlFragment compare) {
		where.eq(filed, compare);
		return this;
	}

	@Override
	public <E, R> SqlHaving ne(SqlFragment filed, SqlFragment compare) {
		where.ne(filed, compare);
		return this;
	}

	@Override
	public <E, R> SqlHaving ne(LambdaFieldFunction<E, R> filed, R value) {
		where.ne(table.getField(filed), value);
		return this;
	}

	@Override
	public <E, R> SqlLikeFragment like(LambdaFieldFunction<E, R> filed) {
		return like(table.getField(filed));
	}

	@Override
	public <E, R> SqlLikeFragment like(SqlFragment filed) {
		return where.like(filed);
	}

	@Override
	public <E, R> SqlLikeFragment notLike(LambdaFieldFunction<E, R> filed) {
		return notLike(table.getField(filed));
	}

	@Override
	public <E, R> SqlLikeFragment notLike(SqlFragment filed) {
		return where.notLike(filed);
	}

	@Override
	public SqlHaving in(SqlFragment filed, Collection<Object> value) {
		where.in(filed, value);
		return this;
	}

	@Override
	public <E, R> SqlHaving in(LambdaFieldFunction<E, R> filed, Collection<Object> value) {
		return in(table.getField(filed), value);
	}

	@Override
	public SqlHaving in(SqlFragment filed, SqlFragment compare) {
		where.in(SqlInFragment.in(filed, compare));
		return this;
	}

	@Override
	public <E, R> SqlHaving notIn(LambdaFieldFunction<E, R> filed, Collection<Object> value) {
		where.notIn(where, value);
		return this;
	}

	@Override
	public <R> SqlHaving notIn(SqlFragment filed, Collection<Object> value) {
		where.in(SqlInFragment.notIn(filed, value));
		return this;
	}

	@Override
	public <R> SqlHaving notIn(SqlFragment filed, SqlFragment compare) {
		where.in(SqlInFragment.notIn(filed, compare));
		return this;
	}

	@Override
	public <E, R> SqlHaving isNull(LambdaFieldFunction<E, R> filed) {
		return isNull(table.getField(filed));
	}

	@Override
	public SqlHaving isNull(SqlFragment field) {
		where.isNull(SqlNullFragment.isNull(field));
		return this;
	}

	@Override
	public <E, R> SqlHaving isNotNull(LambdaFieldFunction<E, R> filed) {
		return isNotNull(table.getField(filed));
	}

	@Override
	public SqlHaving isNotNull(SqlFragment field) {
		where.isNotNull(SqlNullFragment.isNotNull(field));
		return this;
	}

	@Override
	public <E, R> SqlHaving ge(LambdaFieldFunction<E, R> filed, R value) {
		return ge(table.getField(filed), SqlParam.of(value));
	}

	@Override
	public SqlHaving ge(SqlFragment field, SqlFragment compare) {
		where.eq(field, compare);
		return this;
	}

	@Override
	public <E, R> SqlHaving gt(LambdaFieldFunction<E, R> filed, R value) {
		return gt(table.getField(filed), SqlParam.of(value));
	}

	@Override
	public SqlHaving gt(SqlFragment field, SqlFragment compare) {
		where.gt(field, compare);
		return this;
	}

	@Override
	public <E, R> SqlHaving le(LambdaFieldFunction<E, R> filed, R value) {
		return le(table.getField(filed), SqlParam.of(value));
	}

	@Override
	public SqlHaving le(SqlFragment field, SqlFragment compare) {
		where.le(field, compare);
		return this;
	}

	@Override
	public <E, R> SqlHaving lt(LambdaFieldFunction<E, R> filed, R value) {
		return lt(table.getField(filed), SqlParam.of(value));
	}

	@Override
	public SqlHaving lt(SqlFragment field, SqlFragment compare) {
		where.le(field, compare);
		return this;
	}

	@Override
	public SqlHaving and() {
		where.and();
		return this;
	}

	@Override
	public SqlHaving and(Consumer<SqlHaving> subcondition) {
		AbstractSqlHaving ash = new AbstractSqlHaving();
		if (subcondition != null) {
			subcondition.accept(ash);
		}
		and();
		where.where(ash);
		return ash;
	}

	@Override
	public SqlHaving or() {
		where.or();
		return this;
	}

	@Override
	public SqlHaving or(Consumer<SqlHaving> subcondition) {
		AbstractSqlHaving ash = new AbstractSqlHaving();
		if (subcondition != null) {
			subcondition.accept(ash);
		}
		or();
		where.where(ash);
		return ash;
	}

	@Override
	public <E, R> SqlHaving between(LambdaFieldFunction<E, R> filed, R start, R end) {
		where.between(table.getField(filed), start, end);
		return this;
	}

	@Override
	public <R, E> SqlBetweenFragment between(LambdaFieldFunction<E, R> filed) {
		SqlBetweenFragment ofBetween = SqlBetweenFragment.ofBetween(table.getField(filed), null, null);
		where.where(ofBetween);
		return ofBetween;
	}

	@Override
	public <R, E> SqlBetweenFragment notBetween(LambdaFieldFunction<E, R> filed) {
		return SqlBetweenFragment.ofNotBetween(table.getField(filed), null, null);
	}

	@Override
	public <R, E> SqlHaving notBetween(LambdaFieldFunction<E, R> filed, R start, R end) {
		where.notBetween(table.getField(filed), start, end);
		return this;
	}

}
