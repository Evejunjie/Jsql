package org.junjie.jsql.fragment.keyword;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * SQL 分页
 * 
 * @author junjie
 *
 */
public class SqlLimit implements SqlFragment {

	/**
	 * 跳过多少条
	 */
	protected long offset = -1;

	/**
	 * 开始位置
	 */
	protected long startIndex = -1;

	/**
	 * 读取多少条
	 */
	protected long readRow = 0;

	public static SqlLimit of(long start, long row, long offset) {
		SqlLimit sqlLimit = new SqlLimit();
		sqlLimit.startIndex = start;
		sqlLimit.readRow = row;
		sqlLimit.offset = offset;
		return sqlLimit;
	}

	public static SqlLimit of(long start, long row) {
		return of(start, row, -1l);
	}

	public static SqlLimit of(long row) {
		return of(-1l, row, -1l);
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.writeSql(SQLCode.LIMIT);
		if (startIndex > 0) {
			deposit.writeSqlNumber(startIndex);
			deposit.writeSql(',');
		}
		deposit.writeSqlNumber(readRow);
	}

}
