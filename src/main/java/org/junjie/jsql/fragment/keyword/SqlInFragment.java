package org.junjie.jsql.fragment.keyword;

import java.util.Collection;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.exception.JsqlSpliceException;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.SqlParam;
import org.junjie.jut.Jut;

/**
 * @author junjie
 *
 */
public class SqlInFragment implements SqlFragment {

	/**
	 * 参数
	 */
	SqlFragment param;

	/**
	 * 是否是 not 不包含
	 */
	boolean not = false;

	/**
	 * 字段信息
	 */
	SqlFragment field;

	public SqlInFragment(SqlFragment field, Collection<Object> list, boolean not) {
		this.param = new SqlParamSet(list);
		this.not = not;
		this.field = field;
	}

	public SqlInFragment(SqlFragment field, SqlFragment fragment, boolean not) {
		super();
		this.param = fragment;
		this.not = not;
		this.field = field;
	}

	public static SqlInFragment of(SqlFragment field, Collection<Object> list, boolean not) {
		if (Jut.coll.isEmpty(list)) {
			// -> 错误
			throw new JsqlSpliceException(" 使用 IN 查询 集合值不允许是一个不可用的集合 ");
		}
		return new SqlInFragment(field, list, not);
	}

	public static SqlInFragment of(SqlFragment field, SqlFragment fragment, boolean not) {
		return new SqlInFragment(field, fragment, not);
	}

	public static SqlInFragment in(SqlFragment field, Collection<Object> list) {
		return of(field, list, false);
	}

	public static SqlInFragment in(SqlFragment field, SqlFragment fragment) {
		return of(field, fragment, false);
	}
	public static SqlInFragment notIn(SqlFragment field,SqlFragment fragment) {
		return of(field, fragment, true);
	}
	public static SqlInFragment notIn(SqlFragment field, Collection<Object> list) {
		return of(field, list, true);
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		// -> 字段
		deposit.write(field);
		if (not) {
			deposit.writeSql(SQLCode.NOT);
		}
		deposit.writeSql(SQLCode.IN);
		deposit.writeSql('(');
		// -> 写入参数
		deposit.write(param);
		deposit.writeSql(')');
	}

	/**
	 * IN 参数, 默认是一个集合
	 * 
	 * @author junjie
	 *
	 */
	public class SqlParamSet implements SqlParam {

		final Collection<Object> list;

		public SqlParamSet(Collection<Object> list) {
			super();
			this.list = list;
		}

		@Override
		public void writeFragment(SqlDeposit deposit) {
			// -> 写参数
			int size = list.size() - 1;
			for (int i = 0; i < size; i++) {
				deposit.writeSql("?,");
			}
			deposit.writeSql("?");
		}

		@Override
		public void writeParam(SqlDeposit deposit) {
			deposit.writeParam(list);
		}

	}

}
