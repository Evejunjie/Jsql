package org.junjie.jsql.fragment.keyword;

import java.util.ArrayList;
import java.util.Iterator;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.curd.SqlSelectField;
import org.junjie.jsql.exception.JsqlSpliceException;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.function.SqlFunction;

/**
 * 分组
 * 
 * @author junjie
 *
 */
public class SqlGroupBy implements SqlFragment {

	/**
	 * 需要分组的列
	 */
	protected ArrayList<SqlSelectField> group = new ArrayList<>();

	public SqlGroupBy append(SqlSelectField field) {
		group.add(field);
		return this;
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		Iterator<SqlSelectField> it = group.iterator();
		if (!it.hasNext())
			return;
		deposit.writeSql(SQLCode.GROUP_BY);

		for (;;) {
			SqlSelectField next = it.next();
			// -> 是否需要表前缀
			String as = next.as();
			if (as != null) {
				deposit.writeAS(as);
			} else {
				String dbfield = next.getDbfield();
				if (dbfield == null) {
					deposit.writeSqlTableField(dbfield);
				} else if (next instanceof SqlFunction) {
					deposit.write(next);
				} else {
					throw new JsqlSpliceException("给定的 列字段 SQL 片段不可用", next);
				}
			}
			if (!it.hasNext())
				return;
			deposit.writeSql(',');
		}

	}

}
