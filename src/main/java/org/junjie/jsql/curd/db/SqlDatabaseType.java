package org.junjie.jsql.curd.db;

import org.junjie.jsql.fragment.SQLCode;

import lombok.Getter;

/**
 * 存储各个数据库的标识信息
 * 
 * @author junjie
 *
 */
public class SqlDatabaseType {

	/**
	 * 是否支持 Sql 加上 AS
	 */
	boolean as;

	public boolean as() {
		return as;
	}

	/**
	 * 数据库名称
	 */
	String name;

	public String name() {
		return name;
	}

	@Getter
	private static SqlDatabaseType dbType;

	public SqlDatabaseType(boolean as, String name) {
		super();
		this.as = as;
		this.name = name;
	}

	public static void setDbType(SqlDatabaseType dbType) {
		SqlDatabaseType.dbType = dbType;
		SQLCode.AS = dbType.as ? " AS " : " ";
	}

	/**
	 * MY Sql 数据库
	 */
	public static SqlDatabaseType MYSQL = new SqlDatabaseType(true, "MYSQL");

	public static SqlDatabaseType MARIADB = new SqlDatabaseType(true, "MARIADB");

	public static SqlDatabaseType ORACLE = new SqlDatabaseType(false, "ORACLE");

	public static SqlDatabaseType SQLSERVER = new SqlDatabaseType(false, "SQLSERVER");

	public static SqlDatabaseType DB2 = new SqlDatabaseType(true, "DB2");

	public static SqlDatabaseType POSTGRESQL = new SqlDatabaseType(true, "POSTGRESQL");

}
