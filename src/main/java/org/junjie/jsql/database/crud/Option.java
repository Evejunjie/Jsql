package org.junjie.jsql.database.crud;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 只允许在字段中使用
 * 
 * @author junjie
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Option {

	/**
	 * 政策
	 * 
	 * @return
	 */
	public Policy[] value();

	/**
	 * 顺序, 分组顺序, 展示排序, 数据位置排序
	 * 
	 * @return
	 */
	public int order();

	/**
	 * 规则制定
	 * 
	 * @author junjie
	 *
	 */
	public enum Policy {

		/**
		 * 用于查询过滤, 可以通过提交 的字段名来做匹配, 允许 like(字段名.like=值) 以及多条件 where=((字段名.or.字段名).and.字段名)
		 */
		QUERY,

		/**
		 * 用于展示 ,多个则是拼接 [,]
		 */
		LABEL,

		/**
		 * 提交的值, 这是唯一的 不允许出现多次
		 */
		VALUE,

		/**
		 * 分组, 归类分组
		 */
		GROUP,

		/**
		 * 升序
		 */
		SORT_ASC,
		/**
		 * 降序
		 */
		SORT_DESC

	}

}
