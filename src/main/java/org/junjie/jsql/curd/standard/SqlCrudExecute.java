package org.junjie.jsql.curd.standard;

import java.util.List;

import org.junjie.jsql.JSql;
import org.junjie.jsql.fragment.SqlField;

/**
 * 可执行 SQL, 适用于 CRUD, 对返回结果做帮助处理
 * 查询用的比较多, 更新,删除,新增 通常返回的是受影响行数
 * 
 * JOIN 泛型是 主表
 * 
 * @author junjie
 *
 * @param <E>
 */
public interface SqlCrudExecute<E> {
	
	
	/**
	 * @return
	 */
	JSql getJsql();
	

	/**
	 * 获取当前执行 主表类型
	 * @return
	 */
	Class<E> getEclass();
	
	/**
	 * 获取当前 操作的主列, 删除操作请返回 null
	 * @return
	 */
	List<SqlField> getFieldList(); 

}
