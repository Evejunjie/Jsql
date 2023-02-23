package org.junjie.jsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junjie.jsql.curd.AbstractSqlDeposit;
import org.junjie.jsql.curd.db.SqlDatabaseType;
import org.junjie.jsql.curd.fulfil.AbstractQuery;
import org.junjie.jsql.curd.fulfil.SqlQuery;
import org.junjie.jsql.curd.result.JsqlQueryResultSet;
import org.junjie.jsql.curd.standard.Update;
import org.junjie.jsql.entity.Entity;
import org.junjie.jsql.entity.SqlEntityFactory;
import org.junjie.jsql.entity.SqlEntityScanning;
import org.junjie.jsql.entity.SqlTableEntity;
import org.junjie.jsql.invoke.ValueInvoke;
import org.junjie.jsql.invoke.ValueInvokeCase;
import org.junjie.jsql.invoke.ValueInvokeEnum;
import org.junjie.jsql.invoke.ValueInvokeObject;
import org.junjie.jsql.invoke.ValueInvokePrimitive;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * 类查询, 提供的所有查询 都需要使用类
 * 
 * 
 * @author junjie
 *
 */
@Log
public class JSql {

	public JSql() {
		super();
		setSqlDatabaseType(SqlDatabaseType.MYSQL);
		factory.addFieldFilter(configure::fieldName);
		factory.addNameFilter(configure::tableName);
		invoke.add(viPrimitive);
		invoke.add(viObject);
		invoke.add(viCase);
		invoke.add(viEnum);
	}

	@Getter
	List<ValueInvoke> invoke = new ArrayList<>();

	ValueInvokeCase      viCase      = new ValueInvokeCase();
	ValueInvokeEnum      viEnum      = new ValueInvokeEnum();
	ValueInvokeObject    viObject    = new ValueInvokeObject();
	ValueInvokePrimitive viPrimitive = new ValueInvokePrimitive();

	JsqlConfigure configure = new JsqlConfigure();

	/**
	 * 实体类数据工厂
	 */
	SqlEntityFactory factory = new SqlEntityFactory();

	public void setSqlDatabaseType(SqlDatabaseType dbType) {
		SqlDatabaseType.setDbType(dbType);
	}

	/**
	 * 扫包
	 */
	SqlEntityScanning scanning = new SqlEntityScanning();

	/**
	 * 数据库 链接, 每执行一次SQL 都会从这里获取 流
	 */
	@Setter
	DataSource source;

	public Connection getConnection() {
		try {
			return source.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public SqlEntityFactory getFactory() {
		return factory;
	}

	public void scanPackets() {
		List<Class<?>> scann = scanning.scann();
		for (Class<?> eclass : scann) {
			factory.register(eclass);
		}
	}

	public void addPackage(Package pack) {
		scanning.addPackage(pack);
	}

	public void addPackageClass(Class<?> classPack) {
		scanning.addPackage(classPack.getPackage());
	}

	public <E extends Entity> SqlQuery<E> query(Class<E> eclass) {
		SqlTableEntity entity = factory.getEntity(eclass);
		return new SqlQuery<E>(entity, this);
	}

	public <E extends Entity> Update update(Class<E> eclass) {
		SqlTableEntity entity = factory.getEntity(eclass);
		return null;
	}

	/**
	 * 执行查询
	 * 
	 * @param <E>
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public <E> JsqlQueryResultSet<E> execute(AbstractQuery<E> query) throws SQLException {
		AbstractSqlDeposit asd = new AbstractSqlDeposit();
		asd.table(query);
		asd.write(query);
		String outputSql = asd.outputSql();
		log.info(" RUN SQL: ".concat(outputSql));
		Connection        connection       = getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(outputSql);

		List<Object> params = asd.getParams();
		int          size   = params.size();
		for (int i = 0; i < size; i++) {
			prepareStatement.setObject(i + 1, params.get(i));
		}
		log.info(" RUN Param: ".concat(params.toString()));
		ResultSet executeQuery = prepareStatement.executeQuery();

		return JsqlQueryResultSet.of(query, executeQuery);
	};

	// public <E extends Entity> Insert<E> insert(Class<E> eclass) {
	// SqlTableEntity<E> entity = factory.getEntity(eclass);
	// return new Insert<>(entity);
	// }

}
