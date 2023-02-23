package org.junjie.jsql.fragment;

import org.junjie.jsql.curd.SqlDeposit;

public final class SQLCode {

	private SQLCode() {
		throw new AssertionError("No org.junjie.pistil.sql.fragment.SQLCode instances for you!");
	}

	/**
	 * 查询
	 */
	public static final String SELECT = "SELECT ";

	/**
	 * 哪一张表
	 */
	public static final String FROM = " FROM ";

	/**
	 * 
	 */
	public static final String WHERE = " WHERE ";

	/**
	 * NOT
	 */
	public static final String NOT = " NOT ";

	public static final String BETWEEN = " BETWEEN ";

	public static final String DISTINCT = " DISTINCT ";

	public static final String LIMIT = " LIMIT ";

	public static final String GROUP_BY = " GROUP BY ";

	public static final String ORDER_BY = " ORDER BY ";

	public static final String ASC  = " ASC ";
	public static final String DESC = " DESC ";

	public static final String PI = " PI ";

	public static final String HAVING = " HAVING ";

	/**
	 * 更新
	 */
	public static final String UPDATE = "UPDATE ";

	/**
	 * 
	 */
	public static final String SET = " SET ";

	public static final String DELETE = "DELETE ";

	public static final String VALUES = " VALUES ";

	public static final String INSERT_INTO = " INSERT INTO ";

	public static final String IN = " IN";

	public static final String AND = " AND ";

	public static final String OR = " OR ";

	public static final String LIKE = " LIKE ";
	
	public static final String NULL = " NULL ";

	public static final String CASE = " CASE ";

	public static final String WHEN = " WHEN ";

	public static final String THEN = " THEN ";

	public static final String END = " END ";

	public static final String ELSE = " ELSE ";

	public static  String AS = " ";

	public static CharSequence escape(CharSequence string) {
		// -> 将字符串转义
		int           length = string.length();
		StringBuilder sb     = new StringBuilder(length + 6);
		for (int i = 0; i < length; i++) {
			char charAt = string.charAt(i);
			if (charAt == '\'') {
				sb.append('\\');
			}
			sb.append(charAt);
		}
		return sb;
	}

	/**
	 * 将给定的 值 直接写入为SQL, 字符串会加上 单引号 并且转义字符
	 * @param value
	 * @return
	 */
	public static SqlFragment ofValueCode(Object value) {
		return new SqlFragmentCode(value);
	};

	public static class SqlFragmentCode implements SqlFragment {

		final Object value;

		public SqlFragmentCode(Object value) {
			super();
			this.value = value;
		}

		@Override
		public void writeFragment(SqlDeposit deposit) {
			deposit.writeSqlValue(value);
		}

	}

}
