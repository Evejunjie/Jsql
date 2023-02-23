package org.junjie.jsql.curd.result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.junjie.jsql.curd.result.TableSet.TableListValue;
import org.junjie.jsql.curd.result.abs.AbstractTableSet;
import org.junjie.jsql.curd.standard.SqlCrudExecute;

public class JsqlQueryResultSet<E> implements JsqlResultSet {

	/**
	 * 执行
	 */
	SqlCrudExecute<E> query;

	/**
	 * 表数据
	 */
	TableSet table;

	/**
	 * 获取查询的实体类数据
	 * 
	 * @param <E>
	 * @return
	 */
	public E getEntity() {
		TableListValue rowList = table.getRowList(0);
		return rowList.toEntity(query.getEclass());
	}

	public Map<String, Object> getMap() {
		TableListValue rowList = table.getRowList(0);
		return rowList.toMap();
	}

	public TableSet getTable() {
		return table;
	}

	public static <E> JsqlQueryResultSet<E> of(SqlCrudExecute<E> execute, ResultSet executeQuery) throws SQLException {
		JsqlQueryResultSet<E> jqrs = new JsqlQueryResultSet<>();
		jqrs.query = execute;
		jqrs.table = new AbstractTableSet(executeQuery, execute);
		return jqrs;
	};

}
