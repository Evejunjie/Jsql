package org.junjie.jsql.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junjie.jsql.annotation.TableInfo;
import org.junjie.jsql.curd.SqlSelectField;
import org.junjie.jsql.curd.fulfil.AbstractQuery;
import org.junjie.jsql.field.SelectField;
import org.junjie.jsql.fragment.SqlField;
import org.junjie.jut.LambdaFunction;

import lombok.Getter;

/**
 * 数据库类信息,
 * 
 * @author junjie
 *
 */
public class SqlTableEntity {

	/**
	 * 表名
	 */
	String tableName;

	/**
	 * 表 注解
	 */
	TableInfo table;

	/**
	 * 实体类 类型
	 */
	@Getter
	Class<?> eclass;

	/**
	 * 实体类主键
	 */
	SqlTableField primaryKey;

	/**
	 * 删除标识字段
	 */
	SqlTableField deleted;

	/**
	 * 更新锁 , 一般不会那么频繁, 注意 如果是正负数比较 会判断远离0的
	 */
	SqlTableField lock;

	/**
	 * 字段列表
	 */
	List<SqlField> fieldList;

	public List<SqlField> getFieldList() {
		return fieldList;
	}

	/**
	 * 字段映射
	 */
	Map<String, SqlField> fieldMap;

	/**
	 * 初始化 表字段
	 */
	private void inserField() {
		Set<Field>  set       = new LinkedHashSet<>(100);
		Set<Method> methodSet = new LinkedHashSet<>(300);
		{
			Class e = eclass;
			do {
				Collections.addAll(set, e.getDeclaredFields());
				Collections.addAll(set, e.getFields());
			} while ((e = e.getSuperclass()) != Object.class && e != null);

		}
		{
			Class e = eclass;
			do {
				Collections.addAll(methodSet, e.getDeclaredMethods());
				Collections.addAll(methodSet, e.getMethods());
			} while ((e = e.getSuperclass()) != Object.class && e != null);
		}
		// -> 所有的函数

		List<Method> collect = methodSet.stream().filter(method -> {
			if (Modifier.isStatic(method.getModifiers())) {
				return false;
			}
			String name = method.getName();
			if (name.startsWith("is") || name.startsWith("get")) {
				return method.getParameterCount() == 0;
			}
			if (name.startsWith("set")) {
				// -> 一个参数 并且必须是相同类型
				return method.getParameterCount() == 1;
			}
			return false;
		}).collect(Collectors.toList());

		List<SqlTableField> collect2 = set.stream().filter(field -> {
			// -> 静态
			int modifiers = field.getModifiers();
			if (Modifier.isStatic(modifiers)) {
				return false;
			}
			if (Modifier.isTransient(modifiers)) {
				return false;
			}
			return true;
		}).map(field -> {
			return SqlTableField.of(field, eclass, collect);
		}).filter(SqlTableField::isExist).collect(Collectors.toList());
		fieldList = new ArrayList<>(collect2);
		fieldMap = new HashMap<>(fieldList.size());
		for (SqlTableField gs : collect2) {
			fieldMap.put(gs.getField(), gs);
		}
	}

	public static <E> SqlTableEntity generate(SqlEntityFactory factory, Class<E> eclass) {
		SqlTableEntity entityInfo = new SqlTableEntity();
		entityInfo.eclass = eclass;
		entityInfo.table = eclass.getAnnotation(TableInfo.class);
		// -> 从配置中 获取表名
		entityInfo.tableName = factory.getTable(entityInfo);
		// -> 处理字段
		entityInfo.inserField();
		return entityInfo;
	}

	public SqlField getField(String name) {
		return fieldMap.get(name);
	}

	public SqlField getField(LambdaFunction<?> function) {
		return fieldMap.get(function.getName());
	}

	public <E> SqlSelectField getSelectField(LambdaFunction<?> function, AbstractQuery<E> abstractQuery) {
		return SelectField.of(abstractQuery, fieldMap.get(function.getName()));
	}

	public String getTableName() {
		return tableName;
	}

}
