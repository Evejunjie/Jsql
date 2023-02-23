package org.junjie.jsql;

import org.junjie.jsql.entity.SqlTableEntity;
import org.junjie.jsql.entity.SqlTableField;
import org.junjie.jut.Jut;

/**
 * 配置
 * 
 * @author junjie
 *
 */
public class JsqlConfigure {

	/**
	 * 表前缀
	 */
	private String tablePrefix;

	/**
	 * 字段前缀
	 */
	private String fieldPrefix;

	/**
	 * 表驼峰 , 用于没有指定表名, 使用java 类名 然后 驼峰名加上下划线
	 */
	private boolean tableHump=true;

	/**
	 * 字段驼峰, 用于没有指定字段名, 使用java 字段名 然后 驼峰名加上下划线
	 */
	private boolean fieldHump=true;

	public  String tableName(SqlTableEntity entity) {
		String tableName = entity.getTableName();
		if (Jut.str.isEmpty(tableName)) {
			// -> 类名
			Class<?> eclass = entity.getEclass();
			tableName = eclass.getSimpleName();
		}
		if (tableHump) {
			tableName = Jut.str.hump(tableName).toLowerCase();
		}
		if(tablePrefix!=null) {
			return tablePrefix.concat(tableName);
		}
		return tableName;
	};
	
	public  String fieldName(SqlTableField field) {
		String dbfield = field.getDbfield();
		if(Jut.str.isEmpty(dbfield)) {
			dbfield = field.getField();
		}
		if (fieldHump) {
			dbfield = Jut.str.hump(dbfield).toLowerCase();
		}
		if(fieldPrefix!=null) {
			return fieldPrefix.concat(dbfield);
		}
		return dbfield;
	};

}
