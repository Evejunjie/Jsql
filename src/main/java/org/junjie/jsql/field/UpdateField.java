package org.junjie.jsql.field;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.curd.SqlUpdateSet;
import org.junjie.jsql.entity.SqlTableField;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.SqlParam;

public class UpdateField implements SqlUpdateSet {

	/**
	 * 写入的参数
	 */
	SqlFragment param;

	/**
	 * 字段
	 */
	SqlTableField field;

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.writeSqlTableField(getDbfield());
		deposit.writeSql('=');
		deposit.write(param);
	}

	@Override
	public String getDbfield() {
		return field.getDbfield();
	}

	@Override
	public void setParam(SqlFragment fragment) {
		param = fragment;
	}

	@Override
	public void setParam(Object value) {
		param = SqlParam.of(value);
	}

	@Override
	public void writeField(SqlDeposit deposit) {
		deposit.writeSqlTableField(getDbfield());
	}

	@Override
	public Class<?> getType() {
		return field.getType();
	}
}
