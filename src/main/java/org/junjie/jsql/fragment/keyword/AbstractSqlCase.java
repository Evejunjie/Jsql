package org.junjie.jsql.fragment.keyword;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.curd.standard.SqlCase;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlFragment;

public class AbstractSqlCase implements SqlCase {

	/**
	 * WHEN 匹配值 CASE ${value}
	 */
	SqlFragment switchValue;

	/**
	 * 匹配条件 WHEN ${key} then ${value}
	 */
	List<Map.Entry<SqlFragment, SqlFragment>> caseList = new LinkedList<>();

	/**
	 * 默认值 else ${value} END
	 */
	SqlFragment elseEnd;

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.writeSql('(');
		deposit.writeSql(SQLCode.CASE);
		if(switchValue!=null) {
			deposit.write(switchValue);
		}
		for (Entry<SqlFragment, SqlFragment> entry : caseList) {
			deposit.writeSql(SQLCode.WHEN);
			deposit.write(entry.getKey());
			deposit.writeSql(SQLCode.THEN);
			deposit.write(entry.getValue());
		}
		if(elseEnd!=null) {
			deposit.writeSql(SQLCode.ELSE);	
			deposit.write(elseEnd);
		}
		deposit.writeSql(SQLCode.END);
		deposit.writeSql(')');
	}

	@Override
	public SqlCase CASE(SqlFragment fragment) {
		this.switchValue = fragment;
		return this;
	}

	@Override
	public SqlCase CASE(Object value) {
		return CASE(SQLCode.ofValueCode(value));
	}

	@Override
	public SqlCase WHEN(SqlFragment when, SqlFragment then) {
		caseList.add(Map.entry(when, then)  );
		return this;
	}

	@Override
	public SqlCase WHEN(Object whenValue, Object thenValue) {
		return WHEN(SQLCode.ofValueCode(whenValue), SQLCode.ofValueCode(thenValue));
	}

	@Override
	public SqlCase ELSE(SqlFragment then) {
		elseEnd = then;
		return this;
	}

	@Override
	public SqlCase ELSE(Object then) {
		return ELSE(SQLCode.ofValueCode(then));
	}

}
