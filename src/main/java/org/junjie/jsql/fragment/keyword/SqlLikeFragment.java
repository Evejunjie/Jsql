package org.junjie.jsql.fragment.keyword;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.SqlParam;

/**
 * 模糊匹配
 * 
 * @author junjie
 *
 */
public class SqlLikeFragment implements SqlFragment {

	/**
	 * 模糊匹配的值
	 */
	SqlFragment param;

	public void setParam(SqlFragment value) {
		param = value;
	}

	/**
	 * 是否是不匹配, 默认是匹配的
	 */
	final boolean not;

	/**
	 * 匹配字段
	 */
	final SqlFragment field;

	public SqlLikeFragment all(String value) {
		param = SqlParam.of("%".concat(value));
		return this;
	}

	public SqlLikeFragment left(String value) {
		param = SqlParam.of("%".concat(value));
		return this;
	}

	public SqlLikeFragment right(String value) {
		param = SqlParam.of("%".concat(value));
		return this;
	}

	public SqlLikeFragment(SqlFragment field, boolean not) {
		super();
		this.field = field;
		this.not = not;
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.write(field);
		if (not) {
			deposit.writeSql(SQLCode.NOT);
		}
		deposit.writeSql(SQLCode.LIKE);
		deposit.write(param);
	}

	public static SqlLikeFragment ofLike(SqlFragment field) {
		return new SqlLikeFragment(field, false);
	}

	public static SqlLikeFragment ofNotLike(SqlFragment field) {
		return new SqlLikeFragment(field, true);
	}

}
