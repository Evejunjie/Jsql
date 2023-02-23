package org.junjie.jsql.annotation.crud;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 提供快捷查询, 可选分页,默认查询的是有标示字段的数据,
 * 快捷查询不提供连表查询,查询都是本表的数据, 会受到 Deleted.java,Queryable.java 接口的影响,意味着使用 select 查询不会返回被删除的数据
 * 提供条件查询, 
 * @author junjie
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {

	/**
	 * 分组的名称, 这是使用多个查询组, Selects 的存在,  group 就会作为唯一的区分
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
	 * 默认是提供分页的 , 只要一个是 false 则不会体用分页,
	 * 分页分为两种分页, 一种是滚动分页 一种是下标分页, 下标分页 是需要统计查询数量的,
	 * 而滚动则不需要以, 默认是 下标分页
	 * @return
	 */
	public boolean paging() default true;
	
	
	/**
	 * 我这字段 是关联了 指定表中的字段的,
	 * 通常是其他表的主键 ID 如果有 意味着查询的时候会连表查询, 并且返回 对应表中的  option 信息
	 * 更新时 也会查询该值是否存在可用
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
	public Policy[] value()  default {};
	
	
	public enum Policy{
		
		
		/**
		 * 作用域查询条件
		 */
		WHERE,
		
		/**
		 * 滚动分页
		 * {@link Select#paging()} false 则忽略 和警告
		 */
		PAGING_SCROLL,
		
		
		/**
		 * 下标分页
		 * {@link Select#paging()} false 则忽略 和警告
		 */
		PAGING_INDEX,
		
		
		/**
		 * 如果有则意味着 只有GET 标识的字段才会返回数据 
		 * 
		 */
		GET,
		
		
		
		
		
	}
	
	
	
	
	
	

}
