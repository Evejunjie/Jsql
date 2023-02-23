package org.junjie.jsql.field;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.curd.SqlInsertValue;
import org.junjie.jsql.entity.SqlTableField;
import org.junjie.jsql.fragment.SqlParam;

/**
 * 插入字段
 * 
 * @author junjie
 *
 */
public class InserField  implements SqlInsertValue {

	/**
	 * 写入值, 这是异步的
	 */
	SqlParam param;

	/**
	 * 实际字段
	 */
	SqlTableField field;

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.writeSqlTableField(getDbfield());
	}

	@Override
	public String getDbfield() {
		return field.getDbfield();
	}

	@Override
	public void writeParam(SqlDeposit deposit) {
		deposit.write(param);
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
