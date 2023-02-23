package org.junjie.jsql.curd.fulfil;

import org.junjie.jsql.JSql;
import org.junjie.jsql.entity.SqlTableEntity;

public class SqlQuery<E> extends AbstractQuery<E> {

	public SqlQuery(SqlTableEntity table,JSql jsql) {
		super(table,jsql);
	}
	
}
