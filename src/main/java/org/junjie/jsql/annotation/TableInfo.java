package org.junjie.jsql.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表信息
 * 
 * @author junjie
 *
 */
@Documented
@Retention( RetentionPolicy.RUNTIME)
@Target( value = { TYPE })
public @interface TableInfo {

	/**
	 * 表名称
	 * 
	 * @return
	 */
	public String name();
	

	/**
	 * 政策数据
	 * @return
	 */
	public Policy[] value() default { Policy.INSER_AUTO };

	/**
	 * 插入时 如何指定ID
	 * 
	 * @author junjie
	 *
	 */
	public enum Policy {

		/**
		 * 全局唯一, 适用于 字符串
		 * 
		 */
		INSER_UUID ,

		/**
		 * 全局唯一ID 53位(53位是由于网页端 只支持53位)
		 */
		INSER_G53 ,

		/**
		 * 全局唯一ID 64位
		 */
		INSER_G64 ,

		/**
		 * 跟随数据库 (插入数据时不会指定), 如果ID有值则使用ID,没有则是数据库自行处理
		 */
		INSER_AUTO ,

		/**
		 * 在配置中自定义数据新增规则
		 */
		INSER_CUSTOM,

		/**
		 * 允许删除,
		 * 
		 */
		CRUD_DELETED,
		
		/**
		 * 允许修改
		 */
		CRUD_MODIFIED,
		
		/**
		 * 允许查询,如果
		 */
		CRUD_QUERYABLE,
		
		
	}

}
