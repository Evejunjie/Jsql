package org.junjie.jsql.fragment;

import org.junjie.jsql.curd.SqlDeposit;

/**
 * SQL 参数, 参数类继承 SqlFragment.java , SQL 片段如果 需要进行参数注入 那么请实现此类
 * 
 * @author junjie
 *
 */
public interface SqlParam extends SqlFragment {

	/**
	 * 将参数写入进去
	 * 
	 * @param deposit
	 */
	public void writeParam(SqlDeposit deposit);

	/**
	 * 写入单个参数
	 * @param value
	 * @return
	 */
	public static SqlParam of(Object value) {
		return new SqlParamPrepared(value);
	}
	
	public static class SqlParamPrepared implements SqlParam {

		/**
		 * 参数值
		 */
		final Object value;
		
		public SqlParamPrepared(Object value) {
			super();
			this.value = value;
		}

		@Override
		public void writeFragment(SqlDeposit deposit) {
			deposit.writeSql('?');
		}

		@Override
		public void writeParam(SqlDeposit deposit) {
			deposit.writeParam(value);
		}

	}

}
