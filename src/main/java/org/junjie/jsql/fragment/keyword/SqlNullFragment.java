package org.junjie.jsql.fragment.keyword;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * 
 * 适用于 is Null 和 is Not Null
 * 
 * @author junjie
 *
 */
public class SqlNullFragment implements SqlFragment {

	/**
	 * 字段信息
	 */
	final SqlFragment field;

	/**
	 * <code>true</code> IS NULL, <code>false</code> IS NOT NULL
	 */
	final boolean isNull;

	public SqlNullFragment(SqlFragment field, boolean isNull) {
		super();
		this.field = field;
		this.isNull = isNull;
	}

	public static SqlNullFragment isNull(SqlFragment field) {
		return new SqlNullFragment(field, true);
	}

	public static SqlNullFragment isNotNull(SqlFragment field) {
		return new SqlNullFragment(field, false);
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.write(field);
		deposit.writeSql(isNull ? " IS NULL " : " IS NOT NULL ");
	}

}
