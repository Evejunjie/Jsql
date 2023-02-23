package org.junjie.jsql.annotation.crud;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 快捷数据更新, 更新不会提供全表字段更新,只有有标识的字段才会更新, 主键不允许更新
 * 
 * @author junjie
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Update {

	
	/**
	 * 分组的名称, 这是使用多个更新, Update 的存在,  group 就会作为唯一的区分
	 * @return
	 */
	public Class<? extends Group> group();
	
	/**
	 * 更新条件链接 默认是全 AND  , 使用规则是 (字段.and.字段.or.(字段.or.字段))
	 * 多个组中 只能出现一个 where 推荐在主键上
	 * @return
	 */
	public String where() default "";
	
	
	/**
	 * 我这字段 是关联了 指定表中的字段的,
	 * 通常是其他表的主键 ID 如果有 意味着他是个 select 选项, 必须在这里面选择值,除非 在字段描述中是可空的
	 * @return
	 */
	public Class<?> option() default Object.class;
	
	/**
	 * 映射分组的名称
	 * @return
	 */
	public String optionGroup() default "";
	
	
	/**
	 * 政策
	 * @return
	 */
	public Policy[] value();
	
	/**
	 * 
	 * 
	 * 
	 * 更新依据
	 * @author junjie
	 *
	 */
	public enum Policy {

		/**
		 * 作为更新依据条件,是必填项
		 */
		WHERE,
		
		/**
		 * 使用 IN
		 */
		WHERE_IN,
		
		/**
		 * 需要更新的字段,主键不允许设置 
		 */
		SET,
		
		/**
		 * 累加 set field = field + #{value}; 
		 */
		SET_ACCUMULATE,
		
		/**
		 * 累减 set field = field - #{value}; 
		 */
		SET_CUMULATIVE,

	}

}
