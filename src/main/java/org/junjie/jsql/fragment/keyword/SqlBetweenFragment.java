package org.junjie.jsql.fragment.keyword;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * SQL Between
 * 
 * @author junjie
 *
 */
public class SqlBetweenFragment implements SqlFragment {

	/**
	 * <code>true</code> IS NULL, <code>false</code> IS NOT NULL
	 */
	final boolean isNot;

	/**
	 * 字段信息
	 */
	SqlFragment field;

	/**
	 * 开始
	 */
	SqlFragment start;
	public void start(SqlFragment start) {
		this.start = start;
	}
	/**
	 * 结束
	 */
	SqlFragment end;
	
	public void end(SqlFragment end) {
		this.end = end;
	}
	
	

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.writeSql('(');
		deposit.write(field);
		if (isNot) {
			deposit.writeSql(SQLCode.NOT);
		}
		deposit.writeSql(SQLCode.BETWEEN);
		deposit.write(start);
		deposit.writeSql(SQLCode.AND);
		deposit.write(end);
		deposit.writeSql(')');
	}

	public SqlBetweenFragment(boolean isNot, SqlFragment field, SqlFragment start, SqlFragment end) {
		super();
		this.isNot = isNot;
		this.field = field;
		this.start = start;
		this.end = end;
	}

	public static SqlBetweenFragment ofBetween(SqlFragment field, SqlFragment start, SqlFragment end) {
		return new SqlBetweenFragment(false, field, start, end);
	}

	public static SqlBetweenFragment ofNotBetween(SqlFragment field, SqlFragment start, SqlFragment end) {
		return new SqlBetweenFragment(true, field, start, end);
	}

}
