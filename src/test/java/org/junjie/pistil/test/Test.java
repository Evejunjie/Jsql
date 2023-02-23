package org.junjie.pistil.test;

import java.sql.SQLException;
import java.util.Map;

import org.junjie.jsql.JSql;
import org.junjie.jsql.curd.fulfil.SqlQuery;
import org.junjie.jsql.curd.result.JsqlQueryResultSet;
import org.junjie.pistil.test.TEntity.A;
import org.junjie.pistil.test.entity.User;
import org.mariadb.jdbc.Driver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Test {

	/**
	 * 查询终于完成了
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void mayin(String[] args) throws Exception {
		JSql           jSql  = getPistil();
		SqlQuery<User> query = jSql.query(User.class);
		query.as("tab");
		query.eq(User::getId, 1);
		JsqlQueryResultSet<User> execute = jSql.execute(query);

		User entity = execute.getEntity();

		Map<String, Object> map = execute.getMap();

		System.err.println(entity);
		System.err.println(map);

	}

	public void a() throws SQLException {
		// JSql jSql = getPistil();
		// jSql.initialization(TEntity.class);
		//
		// Connection conn = null;
		// Statement statement = null;
		// ResultSet rs = null;
		// try {
		// conn = jSql.getConnection();
		// statement = conn.createStatement();
		// rs = statement.executeQuery("select * from user");
		// while (rs.next()) {
		// System.out.println(rs.getString("name"));
		// System.out.println(rs.getString("age"));
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } finally {
		// if (conn != null) {
		// conn.close();
		// }
		// }
	}

	public static JSql getPistil() {
		JSql jSql = new JSql();

		jSql.addPackageClass(A.class);

		// 实例化类
		HikariConfig hikariConfig = new HikariConfig();
		// 设置url
		hikariConfig.setJdbcUrl(
				"jdbc:mariadb://localhost:3306/junjie?useUnicode=true&useJDBCCompliantTimezoneShift=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
		// 数据库帐号
		hikariConfig.setUsername("root");
		// 数据库密码
		hikariConfig.setPassword("at4001");
		hikariConfig.setDriverClassName(Driver.class.getName());
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		HikariDataSource ds = new HikariDataSource(hikariConfig);

		jSql.setSource(ds);

		jSql.scanPackets();

		return jSql;
	}

}
