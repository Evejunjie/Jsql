package org.junjie.jsql.field;

import java.util.ArrayList;
import java.util.List;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * 需要分组的数据
 * 
 * @author junjie
 *
 */
public class GroupByList implements SqlFragment {

	/**
	 * 需要分组
	 */
	List<SqlField> by = new ArrayList<>();

	@Override
	public void writeFragment(SqlDeposit deposit) {
		int size = by.size();
		if (size == 0) {
			return;
		}
		deposit.writeSql(SQLCode.GROUP_BY);
		for (int i = 0;;) {
			SqlField sqlField = by.get(i);
			sqlField.writeField(deposit);
			if (++i >= size) {
				break;
			}
			deposit.writeSql(',');
		}
	}

	public void add(SqlField field) {
		by.add(field);
	}
}
