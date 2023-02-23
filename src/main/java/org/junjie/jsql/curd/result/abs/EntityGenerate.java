package org.junjie.jsql.curd.result.abs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junjie.jsql.JSql;
import org.junjie.jsql.curd.result.TableSet.Head;
import org.junjie.jsql.curd.result.TableSet.TableListValue;
import org.junjie.jsql.curd.result.abs.AbstractTableSet.AbstractTableReadInfo;
import org.junjie.jsql.invoke.ValueInvoke;
import org.junjie.jut.Jut;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * 实体类数据, 只会调用set 函数,
 * 
 * 获取
 * 
 * @author junjie
 *
 */
@Log
public class EntityGenerate {

	/**
	 * 第一个泛型是 实体类,第二个是 字段名称加上 类型名称,第三个则是函数
	 */
	Map<Class<?>, EntityMethod> entityMethod = new HashMap<>();

	public EntityMethod register(Class<?> clazz) {
		EntityMethod em = new EntityMethod(clazz);
		entityMethod.put(clazz, em);
		return em;
	}

	public EntityMethod get(Class<?> type) {
		EntityMethod em = entityMethod.get(type);
		if (em == null) {
			return register(type);
		}
		return em;
	}

	public <T> T generate(Class<T> clazz, JSql jsql, TableListValue value) throws Exception {
		EntityMethod   em          = get(clazz);
		Constructor<T> constructor = clazz.getConstructor();
		// -> 无参构造
		T newInstance = constructor.newInstance();
		// -> 查询返回的
		List<Head>            head   = value.getHead();
		AbstractTableReadInfo read   = value.read();
		List<ValueInvoke>     invoke = jsql.getInvoke();
		for (Head head2 : head) {
			read.setCol(head2.index());
			String     field      = head2.field();
			MethodName methodName = em.getMethodName(field);
			if (methodName == null) {
				continue;
			}
			for (MethodInfo<?> mi : methodName.list) {
				for (ValueInvoke valueInvoke : invoke) {
					if (valueInvoke.processable(mi.getType())) {
						valueInvoke.invoke(read, newInstance, mi);
					}
				}
			}
		}
		return newInstance;
	};

	public static class EntityMethod {

		Map<String, MethodName> map = new HashMap<>(50);

		final Class<?> clazz;

		public EntityMethod(Class<?> clazz) {
			super();
			this.clazz = clazz;

			// -> 所有字段
			LinkedList<Method> lk = new LinkedList<>();
			Class<?>           e  = clazz;
			do {
				Collections.addAll(lk, e.getDeclaredMethods());
				Collections.addAll(lk, e.getMethods());
			} while ((e = e.getSuperclass()) != Object.class && e != null);
			List<Method> collect = lk.stream().filter(method -> {
				return !Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers());
			}).filter(method -> {
				return method.getName().startsWith("set");
			}).filter(method -> {
				return method.getParameterCount() == 1;
			}).collect(Collectors.toList());

			for (Method m : collect) {
				// -> 转小写 并且去除 set 并且移除 符号
				String name = Jut.str.retainNumberLetter(m.getName().substring(3)).toLowerCase();
				// -> 返回类型
				MethodName methodName = map.get(name);
				if (methodName == null) {
					methodName = new MethodName(name);
					map.put(name, methodName);
				}
				methodName.add(m);
			}
		}

		public MethodName getMethodName(String name) {
			return map.get(name);
		}

	}

	public static class MethodName {
		/**
		 * 保留数字字母,并且转为为小写
		 */
		final String name;

		List<MethodInfo> list = new ArrayList<>();

		public MethodName(String methodName) {
			super();
			this.name = methodName;
		}

		public void add(Method m) {
			list.add(new EntityMethodInfo(m));
		}

	}

	public static interface MethodInfo<E> {
		
		/**
		 * 获取需要转换的类型
		 * @return
		 */
		Class<?> getType();
		
		public void invoke(E entity, Object value) throws Exception;
	}
	
	public static class MapMethodInfo implements MethodInfo<Map<String,Object>>{

		/**
		 * 当前键
		 */
		@Setter
		String key;
		
		@Setter
		@Getter
		Class<?> type;
		
		@Override
		public void invoke(Map<String,Object> entity, Object value) throws Exception {
			entity.put(key, value);
		}
		
	}
	

	@Getter
	public static class EntityMethodInfo implements MethodInfo<Object> {

		/**
		 * 第一个参数类型
		 */
		final Class<?> type;
		/**
		 * 方法
		 */
		final Method   method;

		public EntityMethodInfo(Method method) {
			this.method = method;
			this.type = method.getParameterTypes()[0];
		}

		@Override
		public void invoke(Object entity, Object value) throws Exception {
			method.invoke(entity, value);
		}

	}

}
