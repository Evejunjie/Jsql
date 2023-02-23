package org.junjie.jsql.curd.standard;

import org.junjie.jut.LambdaFieldFunction;

public interface OrderBy<E,Return> {

	public <R> Return orderDesc(LambdaFieldFunction<E, R> field);

	public <R> Return orderAsc(LambdaFieldFunction<E, R> field);

	default <R> Return order(LambdaFieldFunction<E, R> field) {
		return orderAsc(field);
	};
	
}
