package org.junjie.jsql.invoke;

import org.junjie.jsql.curd.result.abs.AbstractTableSet.AbstractTableReadInfo;
import org.junjie.jsql.curd.result.abs.EntityGenerate.MethodInfo;
import org.junjie.jsql.exception.JsqlSpliceException;

/**
 * 
 * 基本类型 需要继承, 是为了兼容 null
 * 
 * @author junjie
 *
 */
public class ValueInvokePrimitive implements ValueInvoke {

	@Override
	public <E> void invoke(AbstractTableReadInfo read, E entity, MethodInfo method) throws Exception {
		Class<?> type = method.getType();
		switch (type.getName()) {
		case "int": {
			int int1 = read.getInt();
			if (!read.wasNull()) {
				method.invoke(entity, int1);
			}
		}
			break;
		case "long": {
			long val = read.getLong();
			if (!read.wasNull()) {
				method.invoke(entity, val);
			}
		}
			break;
		case "double": {
			double val = read.getDouble();
			if (!read.wasNull()) {
				method.invoke(entity, val);
			}
		}
			break;
		case "boolean": {
			boolean val = read.getBoolean();
			if (!read.wasNull()) {
				method.invoke(entity, val);
			}
		}
			break;
		case "byte": {
			byte val = read.getByte();
			if (!read.wasNull()) {
				method.invoke(entity, val);
			}
		}
			break;
		case "short": {
			short val = read.getShort();
			if (!read.wasNull()) {
				method.invoke(entity, val);
			}
		}
			break;
		case "float": {
			float val = read.getFloat();
			if (!read.wasNull()) {
				method.invoke(entity, val);
			}
		}
			break;
		case "char": {
			char val = (char) read.getInt();
			if (!read.wasNull()) {
				method.invoke(entity, val);
			}
		}
			break;

		default:
			throw new JsqlSpliceException("函数 参数不是基本类型");
		}
	}

	@Override
	public boolean processable(Class<?> type) {
		return type.isPrimitive();
	}

}
