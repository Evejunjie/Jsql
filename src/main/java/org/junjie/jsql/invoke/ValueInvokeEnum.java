package org.junjie.jsql.invoke;

import java.lang.reflect.Method;

import org.junjie.jsql.curd.result.abs.AbstractTableSet.AbstractTableReadInfo;
import org.junjie.jsql.curd.result.abs.EntityGenerate.MethodInfo;

public class ValueInvokeEnum implements ValueInvoke {

	@Override
	public <E> void invoke(AbstractTableReadInfo read, E entity, MethodInfo method) throws Exception {
		String string = read.getString();
		if (string == null || string.isEmpty()) {
			method.invoke(entity, null);
		} else {
			Class<?> type           = method.getType();
			Method   declaredMethod = type.getDeclaredMethod("valueOf", String.class);
			method.invoke(entity, declaredMethod.invoke(null, string));
		}
	}

	@Override
	public boolean processable(Class<?> type) {
		return type.isEnum();
	}

}
