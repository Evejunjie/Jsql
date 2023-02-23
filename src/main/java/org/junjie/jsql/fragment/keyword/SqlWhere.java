package org.junjie.jsql.fragment.keyword;

import static org.junjie.jsql.fragment.symbol.SqlMatchSymbol.EQ;
import static org.junjie.jsql.fragment.symbol.SqlMatchSymbol.GE;
import static org.junjie.jsql.fragment.symbol.SqlMatchSymbol.GT;
import static org.junjie.jsql.fragment.symbol.SqlMatchSymbol.LE;
import static org.junjie.jsql.fragment.symbol.SqlMatchSymbol.LT;
import static org.junjie.jsql.fragment.symbol.SqlMatchSymbol.NE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.curd.SqlFilter;
import org.junjie.jsql.curd.standard.SqlCase;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.symbol.SqlMatchSymbol;

public class SqlWhere implements SqlFilter, SqlFragment {

	/**
	 * 条件合集
	 */
	List<SqlFragment> whereFragment = new ArrayList<>(50);

	/**
	 * 下一个链接条件
	 */
	SqlAndOr andOr;

	public void where(SqlFragment field) {
		if (andOr != null) {
			whereFragment.add(andOr);
		}
		whereFragment.add(field);
		andOr = SqlAndOr.AND;
	}

	private void where(SqlFragment field, SqlMatchSymbol symbol, SqlFragment value) {
		if (andOr != null) {
			whereFragment.add(andOr);
		}
		whereFragment.add(field);
		whereFragment.add(symbol);
		whereFragment.add(value);
		andOr = SqlAndOr.AND;
	}

	@Override
	public void eq(SqlFragment field, SqlFragment value) {
		where(field, EQ, value);
	}

	@Override
	public void ne(SqlFragment field, SqlFragment value) {
		where(field, NE, value);
	}

	@Override
	public void gt(SqlFragment field, SqlFragment value) {
		where(field, GT, value);
	}

	@Override
	public void ge(SqlFragment field, SqlFragment value) {
		where(field, GE, value);
	}

	@Override
	public void lt(SqlFragment field, SqlFragment value) {
		where(field, LT, value);
	}

	@Override
	public void le(SqlFragment field, SqlFragment value) {
		where(field, LE, value);
	}

	@Override
	public void isNull(SqlNullFragment field) {
		where(field);
	}

	@Override
	public void isNotNull(SqlNullFragment field) {
		where(field);
	}

	@Override
	public void in(SqlFragment field, Collection<Object> value) {
		where(SqlInFragment.in(field, value));
	}
	
	@Override
	public void in(SqlInFragment sqlin) {
		where(sqlin);
	}

	@Override
	public void in(SqlFragment field, SqlFragment value) {
		where(SqlInFragment.in(field, value));
	}

	@Override
	public void notIn(SqlFragment field, Collection<Object> value) {
		where(SqlInFragment.notIn(field, value));
	}

	@Override
	public void notIn(SqlFragment field, SqlFragment value) {
		where(SqlInFragment.notIn(field, value));
	}

	@Override
	public SqlLikeFragment notLike(SqlFragment field) {
		SqlLikeFragment slf = SqlLikeFragment.ofNotLike(field);
		where(slf);
		return slf;
	}
	
	@Override
	public void notLike(SqlFragment field, SqlFragment value) {
		SqlLikeFragment slf = notLike(field);
		slf.setParam(value);
	}

	@Override
	public void like(SqlFragment field, SqlFragment value) {
		SqlLikeFragment slf = SqlLikeFragment.ofLike(field);
		slf.setParam(value);
		where(slf);
	}

	/**
	 * 返回一个 like查询
	 * 
	 * @param field
	 * @return
	 */
	public SqlLikeFragment like(SqlFragment field) {
		SqlLikeFragment slf = SqlLikeFragment.ofLike(field);
		where(slf);
		return slf;
	}

	@Override
	public void likeAll(SqlFragment field, CharSequence value) {
		Objects.requireNonNull(value);
		SqlLikeFragment slf = SqlLikeFragment.ofLike(field);
		slf.all(value.toString());
		where(slf);
	}

	@Override
	public void and() {
		andOr = SqlAndOr.AND;
	}

	@Override
	public void and(SqlFragment fragment) {
		and();
		where(fragment);
	}

	@Override
	public void or() {
		andOr = SqlAndOr.OR;
	}

	@Override
	public void or(SqlFragment fragment) {
		or();
		where(fragment);
	}

	@Override
	public void between(SqlFragment field, SqlFragment start, SqlFragment end) {
		where(SqlBetweenFragment.ofBetween(field, start, end));
	}

	@Override
	public void notBetween(SqlFragment field, SqlFragment start, SqlFragment end) {
		where(SqlBetweenFragment.ofNotBetween(field, start, end));
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		for (SqlFragment sqlFragment : whereFragment) {
			deposit.write(sqlFragment);
		}
	}

	public boolean isEmpty() {
		return whereFragment.isEmpty();
	}

	@Override
	public SqlCase CASE(SqlFragment fragment) {
		AbstractSqlCase asc = new AbstractSqlCase();
		asc.CASE(fragment);
		where(asc);
		return asc;
	}
}
