package org.junjie.jsql.field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.entity.SqlTableEntity;
import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;

import lombok.Getter;

public class SelectFieldList implements SqlFragment {

	/**
	 * 需要查询的列数据
	 */
	@Getter
	protected List<SqlField> select = new ArrayList<>(24);
	
	/**
	 * 实体表数据
	 */
	SqlTableEntity from;

	
	public SelectFieldList(SqlTableEntity from) {
		super();
		this.from = from;
	}


	@Override
	public void writeFragment(SqlDeposit deposit) {
		 List<SqlField> fieldList = select;
		if (select.isEmpty()) {
			fieldList = select = from.getFieldList();
		}
		Iterator<SqlField> it        = fieldList.iterator();
		for (;;) {
			SqlField next = it.next();
			deposit.write(next);
			if (!it.hasNext()) {
				break;
			}
			deposit.writeSql(',');
		}
		deposit.writeSql(' ');
	}

	
	public void select(SqlField field) {
		select.add(field);
	}

}
