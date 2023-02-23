package org.junjie.jsql.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 表字段
 * 
 * @author junjie
 *
 */
@Retention( RUNTIME)
@Target( FIELD)
public @interface TableField {

	/**
	 * 字段名称, 默认使用驼峰
	 * 
	 * @return
	 */
	public String name();

	/**
	 * 政策
	 * 
	 * @return
	 */
	public Policy[] value();

	/**
	 * 默认是存在与数据库中的,如果 为false 则意味着 不会出现在框架生成的任何SQL中
	 * 
	 * @return
	 */
	public boolean exist() default true;

	/**
	 * 唯一约束 在查询是 会根据唯一约束组先去查是否存在
	 * 
	 * @return
	 */
	public String only();

	/**
	 * 政策
	 * 
	 * @author junjie
	 *
	 */
	public enum Policy {

		/**
		 * 主键, 唯一的哦
		 */
		ID ,

		/**
		 * 默认是不允许存放 null 值的,如果有描述 则可以存放 null 值
		 */
		NULL,
		
		
		/**
		 * 字段只读, 不允许更新这个字段数据
		 */
		READ,
		
		
		
		
		
		

	}

}
