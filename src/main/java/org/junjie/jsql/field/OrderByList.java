package org.junjie.jsql.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * 排序,排序是最后才会执行的
 * 
 * @author junjie
 *
 */
public class OrderByList implements SqlFragment {

	/**
	 * 需要排序的字段
	 */
	List<Map.Entry<String, SqlField>> by = new ArrayList<>();

	public void asc(SqlField field) {
		by.add(Map.entry(SQLCode.ASC, field));
	}

	public void desc(SqlField field) {
		by.add(Map.entry(SQLCode.DESC, field));
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		if (by.isEmpty()) {
			return;
		}
		deposit.writeSql(SQLCode.ORDER_BY);
		int size = by.size();
		for (int i = 0;;) {
			Entry<String, SqlField> entry = by.get(i);
			// -> 排序 排序只允许 SqlField 字段,
			// -> 别名优先 ,如果没有别名 以及字段名称
			SqlField value = entry.getValue();
			value.writeField(deposit);
			deposit.writeSql(entry.getKey());
			if (++i >= size) {
				break;
			}
			deposit.writeSql(',');
		}
	}

}
