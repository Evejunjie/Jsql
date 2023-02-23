package org.junjie.jsql.field;

import java.util.List;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.curd.SqlSelectField;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.function.SqlFunction;

/**
 * 在 query 中使用聚合查询 会返回该对象
 * 
 * @author junjie
 *
 */
public class SelectFieldFunction implements SqlFunction, SqlSelectField {

	String as;

	/**
	 * 字段
	 */
	SqlSelectField field;
	

	/**
	 * 函数
	 */
	final SqlFunction function;

	public SelectFieldFunction(SqlFunction function) {
		super();
		this.function = function;
	}


	/**
	 * 使用 函数的
	 */
	@Override
	public String getDbfield() {
		return function.getDbfield();
	}
	

	public SelectFieldFunction field(SqlSelectField field) {
		this.field = field;
		return this;
	}
	
	/**
	 * 
	 */
	@Override
	public void writeField(SqlDeposit deposit) {
		if (as != null) {
			deposit.writeSqlBackquote(as);
		} else {
			// -> 完整的函数
			function.writeField(deposit);
		}
	}

	@Override
	public SqlSelectField orderDesc() {
		field.orderDesc();
		return this;
	}

	@Override
	public SqlSelectField orderAsc() {
		field.orderAsc();
		return this;
	}

	@Override
	public SqlSelectField group() {
		field.group();
		return this;
	}

	@Override
	public SqlSelectField as(String as) {
		this.as = as;
		return this;
	}

	@Override
	public String as() {
		return this.as;
	}

	@Override
	public SqlSelectField distinct() {
		field.distinct();
		return this;
	}

	@Override
	public CharSequence getName() {
		return function.getName();
	}

	@Override
	public SqlFunction param(Object param) {
		function.param(param);
		return this;
	}

	@Override
	public SqlFunction param(SqlFragment fragment) {
		function.param(fragment);
		return this;
	}

	@Override
	public List<SqlFragment> getParams() {
		return function.getParams();
	}

	public static SelectFieldFunction of(SqlSelectField field2, SqlFunction function2) {
		SelectFieldFunction sff = new SelectFieldFunction(function2);
		sff.field = field2;
		return sff;
	}

	@Override
	public Class<?> getType() {
		return function.getType();
	}

	

}
