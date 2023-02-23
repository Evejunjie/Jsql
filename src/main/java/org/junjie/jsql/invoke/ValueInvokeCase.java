package org.junjie.jsql.invoke;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.junjie.jsql.curd.result.abs.AbstractTableSet.AbstractTableReadInfo;
import org.junjie.jsql.curd.result.abs.EntityGenerate.MethodInfo;

/**
 * 自定义 转换
 * 
 * @author junjie
 *
 */
public class ValueInvokeCase implements ValueInvoke {

	static Map<Class<?>, ValueTypeCast> CAST_MAP = new HashMap<>();

	@Override
	public <E> void invoke(AbstractTableReadInfo read, E entity, MethodInfo method) throws Exception {
		ValueTypeCast cast = CAST_MAP.get(method.getType());
		method.invoke(entity, cast.cast(read));
	}

	@Override
	public boolean processable(Class<?> type) {
		return CAST_MAP.containsKey(type);
	}

	public <E,R> void register(Class<E> clazz, Function<AbstractTableReadInfo, R> func) {
		ValueTypeCastLambda<R> ttcl = new ValueTypeCastLambda<>(clazz);
		ttcl.cast = func;
		register(ttcl);
	}

	public <E> void register(ValueTypeCast cast) {
		CAST_MAP.put(cast.clazz, cast);
	}

	/**
	 * 类型转换
	 * 
	 * @author junjie
	 *
	 */
	public static abstract class ValueTypeCast {

		final Class<?> clazz;

		public ValueTypeCast(Class<?> clazz) {
			super();
			this.clazz = clazz;
		}

		/**
		 * 
		 * 流转换
		 * 
		 * @param input
		 * @return
		 */
		public abstract Object cast(AbstractTableReadInfo input);
	}

	static class ValueTypeCastLambda<R> extends ValueTypeCast {

		public ValueTypeCastLambda(Class<?> target) {
			super(target);
		}

		Function<AbstractTableReadInfo, R> cast;

		@Override
		public Object cast(AbstractTableReadInfo input) {
			return cast.apply(input);
		}
	}

}
