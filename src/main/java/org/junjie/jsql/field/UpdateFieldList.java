package org.junjie.jsql.field;

import java.util.ArrayList;
import java.util.List;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.curd.SqlUpdateSet;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * 更新字段
 * @author junjie
 *
 */
public class UpdateFieldList implements SqlFragment {

	List<SqlUpdateSet> setList = new ArrayList<>();

	public void set(SqlUpdateSet set) {
		setList.add(set);
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		int size = setList.size();
		for (int i = 0;;) {
			deposit.write(setList.get(i));
			if (++i >= size) {
				break;
			}
			deposit.writeSql(',');
		}
	}

}
