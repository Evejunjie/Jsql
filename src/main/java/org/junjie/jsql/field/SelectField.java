package org.junjie.jsql.field;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.curd.SqlSelectField;
import org.junjie.jsql.curd.fulfil.AbstractQuery;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlField;

/**
 * 
 * 查询字段
 * 
 * @author junjie
 *
 */
public class SelectField implements SqlSelectField {

	/**
	 * 是否去重
	 */
	protected boolean distinct = false;

	/**
	 * 别名 如果是用了 聚合函数 会生成一个独立的别名,如果没有 依旧使用字段名称, 有则优先使用该别名,别名不能重复,java 不会做验证 SQL 会,
	 * 聚合函数会优先使用此别名, 不会使用 field 里面的名称
	 */
	protected String as;

	OrderByList order;

	GroupByList group;

	/**
	 * 字段
	 */
	final SqlField field;

	public SelectField(SqlField field) {
		super();
		this.field = field;
	}

	@Override
	public String getDbfield() {
		return field.getDbfield();
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		if (distinct) {
			deposit.writeSql(SQLCode.DISTINCT);
		}
		deposit.writeSqlTableField(getDbfield());
		if (as != null) {
			deposit.writeAS(as);
		}
	}

	@Override
	public String as() {
		return as;
	}

	@Override
	public SqlSelectField as(String as) {
		this.as = as;
		return this;
	}

	@Override
	public SqlSelectField distinct() {
		distinct = true;
		return this;
	}

	/**
	 * 返回 * 符号
	 * 
	 * @return
	 */
	public static SelectField all() {
		return SelectFieldAll.all;
	}

	/**
	 * 当使用 * 号时
	 * 
	 * @author junjie
	 *
	 */
	protected static class SelectFieldAll extends SelectField {
		public SelectFieldAll() {
			super(null);
		}

		protected static final SelectFieldAll all = new SelectFieldAll();

		@Override
		public void writeFragment(SqlDeposit deposit) {
			deposit.writeSql(" * ");
		}
	}

	@Override
	public SqlSelectField group() {
		group.add(this);
		return this;
	}

	@Override
	public SqlSelectField orderDesc() {
		order.desc(this);
		return this;
	}

	@Override
	public SqlSelectField orderAsc() {
		order.asc(this);
		return this;
	}

	@Override
	public void writeField(SqlDeposit deposit) {
		if (as != null) {
			deposit.writeAS(as);
		} else {
			deposit.writeSqlTableField(getDbfield());
		}
	}

	public static <E> SelectField of(AbstractQuery<E> abstractQuery, SqlField field) {
		SelectField sf = new SelectField(field);
		sf.order = abstractQuery.getOrder();
		sf.group = abstractQuery.getGroup();
		return sf;
	}

	@Override
	public Class<?> getType() {
		return field.getType();
	}

}
