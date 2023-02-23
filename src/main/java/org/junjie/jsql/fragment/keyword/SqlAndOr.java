package org.junjie.jsql.fragment.keyword;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SQLCode;
import org.junjie.jsql.fragment.SqlFragment;

public class SqlAndOr implements SqlFragment {

	public static final SqlAndOr AND = new SqlAndOr(true);

	public static final SqlAndOr OR = new SqlAndOr(false);

	private final boolean andOr;

	public SqlAndOr(boolean andOr) {
		super();
		this.andOr = andOr;
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.writeSql(andOr ? SQLCode.AND : SQLCode.OR);
	}
	

}
