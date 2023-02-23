package org.junjie.jsql.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.junjie.jsql.exception.JsqlSpliceException;

import lombok.extern.java.Log;

/**
 * 存放所有标数据 , 和维护实体类 表数据 对表名,字段名做初始化过滤选择
 * 
 * @author junjie
 *
 */
@Log
public class SqlEntityFactory {

	/**
	 *  
	 */
	private Map<Class<?>, SqlTableEntity> entityMap = new HashMap<>(1024);

	/**
	 * 表面过滤器
	 */
	private List<Function<SqlTableEntity, String>> tableNameFilter = new ArrayList<>();

	private List<Function<SqlTableField, String>> tableFieldFilter = new ArrayList<>();

	public void addNameFilter(Function<SqlTableEntity, String> filer)  {
		tableNameFilter.add(filer);
	} 
	public void addFieldFilter(Function<SqlTableField, String> filer)  {
		tableFieldFilter.add(filer);
	} 
	
	/**
	 * 注册 一个类, 对该类的字段数据进行扫描处理
	 * 
	 * @param eclass
	 */
	public  void register(Class<?> eclass) {
		SqlTableEntity generate = SqlTableEntity.generate(this, eclass);
		entityMap.put(eclass, generate);
	}

	public SqlTableEntity getEntity(Class<?> eclass) {
		SqlTableEntity sqlTableEntity = entityMap.get(eclass);
		if (sqlTableEntity == null) {
			throw new JsqlSpliceException("没有注册: " + eclass + "; 请先注册");
		}
		return sqlTableEntity;
	}

	/**
	 * 通过实体类数据 获取数据库表名称
	 * 
	 * @param <E>        泛型
	 * @param entityInfo 实体类, 主要是做过滤
	 * @return
	 */
	public String getTable(SqlTableEntity table) {
		for (Function<SqlTableEntity , String> function : tableNameFilter) {
			String apply = function.apply(table);
			table.tableName = apply;
		}
		return table.getTableName();
	}

	public String getTableField(SqlTableField field) {
		for (Function<SqlTableField, String> function : tableFieldFilter) {
			field.setDbfield(function.apply(field));
		}
		return field.getDbfield();
	}

}
