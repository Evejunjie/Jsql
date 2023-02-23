package org.junjie.jsql.curd.standard;

import org.junjie.jsql.fragment.SqlField;
import org.junjie.jut.LambdaFieldFunction;

public interface GroupBy<E,Return> {

	public <R> Return group(LambdaFieldFunction<E, R> field);
	
	public <R> Return group(SqlField field);

	
}
