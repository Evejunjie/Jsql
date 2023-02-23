package org.junjie.jsql.function;

import java.util.ArrayList;
import java.util.List;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlFragment;

public abstract class AbstractSqlFunction implements SqlFunction {

	List<SqlFragment> params = new ArrayList<>(6);

	String fieldCode;

	/**
	 * 如果没有 那么返回的则是 null
	 */
	@Override
	public String getDbfield() {
		return fieldCode;
	}

	@Override
	public void writeField(SqlDeposit deposit) {
		// -> 完整的名称 不包含
		SqlDeposit sf = deposit.getSqlFragment();
		sf.write(this);
		String outputSql = sf.outputSql();
		deposit.writeSql(outputSql);
		fieldCode = outputSql;
	}

	@Override
	public SqlFunction param(Object value) {
		if (params instanceof SqlFragment) {
			params.add((SqlFragment) value);
		} else {
			params.add(SQLCode.ofValueCode(value));
		}
		return this;
	}

	@Override
	public SqlFunction param(SqlFragment fragment) {
		params.add(fragment);
		return this;
	}

	@Override
	public List<SqlFragment> getParams() {
		return params;
	}

}
