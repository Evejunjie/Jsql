package org.junjie.jsql.curd;

import java.time.chrono.ChronoLocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.SqlParam;
import org.junjie.jut.Jut;
import org.junjie.jut.time.DateFormat;

public class AbstractSqlDeposit implements SqlDeposit {

	/**
	 * 表数据信息
	 */
	SqlTable table;
	
	public void table(SqlTable table) {
		this.table = table;
	}

	/**
	 * 实际存储的SQL 值
	 */
	StringBuilder sqlCode = new StringBuilder(1024);

	/**
	 * 参数
	 */
	List<Object> params = new LinkedList<>();

	@Override
	public void writeParam(int value) {
		params.add(value);
	}

	@Override
	public void writeParam(Object value) {
		params.add(value);
	}

	@Override
	public void writeParam(Number value) {
		params.add(value);
	}

	@Override
	public void writeParam(String value) {
		params.add(value);
	}

	@Override
	public void writeParam(Date value) {
		params.add(value);
	}

	@Override
	public void writeParam(ChronoLocalDate value) {
		params.add(value);
	}

	@Override
	public void writeParam(Collection<Object> value) {
		params.addAll(value);
	}

	@Override
	public void writeSql(CharSequence sql) {
		sqlCode.append(sql);
	}

	@Override
	public void writeSql(char sql) {
		sqlCode.append(sql);
	}

	/**
	 * 核心写入功能
	 */
	@Override
	public void write(SqlFragment fragment) {
		// -> 将参数写入
		fragment.writeFragment(this);
		// -> 判断是否支持 参数写入
		if (fragment instanceof SqlParam) {
			((SqlParam) fragment).writeParam(this);
		}
	}

	@Override
	public void writeSqlNumber(Number value) {
		sqlCode.append(value);
	}

	@Override
	public void writeSqlValue(Object value) {
		if (value instanceof CharSequence) {
			sqlCode.append('\'');
			sqlCode.append(SQLCode.escape(sqlCode));
			sqlCode.append('\'');
		} else {
			if (value instanceof Number) {
				sqlCode.append(value.toString());
			} else if (value instanceof Date) {
				// -> 格式化为 时分秒
				String format = Jut.date.format((Date) value, DateFormat.DEFAUT);
				sqlCode.append('\'');
				sqlCode.append(format);
				sqlCode.append('\'');
			} else {
				sqlCode.append('\'');
				sqlCode.append(value.toString());
				sqlCode.append('\'');
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public void writeAS() {
		sqlCode.append(SQLCode.AS);
	}

	@Override
	public void writeSqlBackquote(CharSequence sql) {
		sqlCode.append('`');
		sqlCode.append(sql);
		sqlCode.append('`');
	}

	@Override
	public void writeSqlTableField(String dbfield) {
		// -> 首先 是否有别名
		String as = table.as();
		if (as != null) {
			writeSqlBackquote(as);
			sqlCode.append('.');
		}
		writeSqlBackquote(dbfield);
	}

	@Override
	public String outputSql() {
		return sqlCode.toString();
	}

	@Override
	public List<Object> getParams() {
		return params;
	}

	@Override
	public SqlDeposit getSqlFragment() {
		SqlDepositFragment sdf = new SqlDepositFragment();
		sdf.table(table);
		return sdf;
	}
	
	class SqlDepositFragment extends AbstractSqlDeposit{
		@Override
		public void write(SqlFragment fragment) {
			fragment.writeFragment(this);
		}
	}

}
