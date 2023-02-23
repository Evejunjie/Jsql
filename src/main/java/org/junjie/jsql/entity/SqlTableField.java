package org.junjie.jsql.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junjie.jsql.annotation.TableField;
import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SqlField;
import org.junjie.jut.Jut;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * 实体类 get set, 数据库对应的字段信息, 以及自己对应的表信息
 * 
 * @author junjie
 * @param <E> 类
 * @param <V> 数据类型
 */
@Log
@Getter
public class SqlTableField implements SqlField {

	/**
	 * 数据类型
	 */
	Class<?> vclass;
	/**
	 * 实体类 类型
	 */
	Class<?> eclass;

	Method get = null , set = null;

	/**
	 * 实体类字段名称
	 */
	private String field;

	/**
	 * 表字段
	 */
	@Getter
	@Setter
	private String dbfield;

	/**
	 * 字段是持久化存储的
	 */
	private boolean exist = true;

	/**
	 * 获取实体类字段值
	 * 
	 * @param value
	 * @return
	 */
	public Object get(Object entity) {
		try {
			return get.invoke(entity);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 设置字段值
	 * 
	 * @param entity 实体
	 * @param value  值
	 */
	public void set(Object entity, Object value) {
		try {
			set.invoke(entity, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public Class<?> getVclass() {
		return vclass;
	}

	public Class<?> getEclass() {
		return eclass;
	}

	public String getField() {
		return field;
	}

	/**
	 * 给定字段值 匹配 字段值相关的 get|is,set 函数
	 * 
	 * @param <E>
	 * @param <V>
	 * @param field      字段
	 * @param eclass
	 * @param methodList
	 * @return
	 */
	public static <E, V extends Serializable> SqlTableField of(Field field, Class<E> eclass,
			List<Method> methodList) {

		String name = Jut.str.firstUpper(field.getName());
		// -> 查找对应的 get set
		String getf = (field.getType() == boolean.class ? "is" : "get") + name;
		String setf = "set" + name;
		Method get  = null , set = null;

		for (Method method : methodList) {
			if (set == null && method.getName().equalsIgnoreCase(setf)) {
				// -> 参数
				if (method.getParameterTypes()[0] == field.getType()) {
					set = method;
					if (get != null) {
						break;
					}
				}
			} else if (get == null && method.getName().equalsIgnoreCase(getf)) {

				if (method.getReturnType() == field.getType()) {
					get = method;
					if (set != null) {
						break;
					}
				}
			}
		}
		if (get == null || set == null) {
			// -> 警告
			throw new RuntimeException("实体字段找不完完成的 get,set field: [" + field.getName() + "] entity:[" + eclass + "] ");
		} else {
			SqlTableField gs = new SqlTableField();
			gs.eclass = eclass;
			gs.field = field.getName();
			gs.dbfield = gs.field;
			TableField tableField = field.getAnnotation(TableField.class);
			if (tableField != null) {
				if (Jut.str.isPresent(tableField.name())) {
					gs.dbfield = tableField.name();
				}
				gs.exist = tableField.exist();
			}
			gs.vclass = field.getType();
			gs.set = set;
			gs.get = get;
			return gs;
		}
	}

	

	@Override
	public void writeFragment(SqlDeposit deposit) {
		// -> 将名称写入 不会牵涉的 别名
		deposit.writeSqlTableField(dbfield);
	}


	@Override
	public void writeField(SqlDeposit deposit) {
		deposit.writeSqlTableField(getDbfield());
	}

	@Override
	public Class<?> getType() {
		return vclass;
	}

	@Override
	public String toString() {
		return "SqlTableField [vclass=" + vclass +  ", field="
				+ field + ", dbfield=" + dbfield + ", exist=" + exist + "]";
	}

	
	
}
