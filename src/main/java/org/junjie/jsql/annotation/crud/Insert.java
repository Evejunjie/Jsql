package org.junjie.jsql.annotation.crud;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * 使用框架提供的添加,通常用于后端
 * 只有标识了这字段 , 新增时才会插入
 * @author junjie
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Insert {

	/**
	 * 分组的名称, 这是使用多个查询组, Insert 的存在,  group 就会作为唯一的区分
	 * @return
	 */
	public Class<? extends Group> group();
	
	
	/**
	 * 政策
	 * @return
	 */
	public Policy[] value();
	
	
	/**
	 * 政策
	 * @author junjie
	 *
	 */
	public enum Policy{
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
