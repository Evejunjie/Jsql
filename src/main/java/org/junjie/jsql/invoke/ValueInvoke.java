package org.junjie.jsql.invoke;

import org.junjie.jsql.curd.result.abs.AbstractTableSet.AbstractTableReadInfo;
import org.junjie.jsql.curd.result.abs.EntityGenerate.MethodInfo;

public interface ValueInvoke {

	public static final Object null_value = null;

	/**
	 * 将值写入进去
	 * 
	 * @param value
	 * @param method
	 */
	public <E> void invoke(AbstractTableReadInfo read, E entity, MethodInfo method) throws Exception;

	/**
	 * 给定的对象是否可处理
	 * 
	 * @param method
	 * @return
	 * @throws Exception
	 */
	public boolean processable(Class<?> type);

}