package org.junjie.jsql.function;

import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * 聚合函数, 聚合函数需要指定数据列,如果没有数据列 他不属于聚合函数
 * 
 * @author junjie
 *
 */
public class AggregateFunction extends AbstractSqlFunction {

	/**
	 * 函数名称
	 */
	protected final Aggregate name;

	@Override
	public CharSequence getName() {
		return name.name();
	}

	@Override
	public Class<?> getType() {
		Class<?> type = name.getType();
		if (type == Object.class) {
			// -> 参数
			for (SqlFragment sf : params) {
				if (sf instanceof SqlField) {
					return ((SqlField) sf).getType();
				}
			}
		}
		return type;
	}

	public AggregateFunction(Aggregate name) {
		super();
		this.name = name;
	}

	public static AggregateFunction of(Aggregate aggregate) {
		return new AggregateFunction(aggregate);
	}

	public enum Aggregate {
		/**
		 * AVG 平均 返回 一个数字
		 */
		AVG(Number.class) ,

		/**
		 * 函数用于计算 SELECT 语句选择的项目/行数
		 */
		COUNT_BIG(Number.class) ,

		/**
		 * 用于计算分组级别。此GROUPING_ID函数将返回整数值
		 */
		GROUPING_ID(Number.class) ,

		/**
		 * 分组函数
		 */
		GROUPING(Number.class) ,

		/**
		 * 计数
		 */
		COUNT(Number.class) ,

		/**
		 * FIRST 第一个 第一个值 不确定类型, 那么取第一个参数值类型
		 */
		FIRST(Object.class) ,

		/**
		 * 函数返回指定的字段中最后一个记录的值。
		 */
		LAST(Object.class) ,

		/**
		 * MAX 函数返回一列中的最大值。NULL 值不包括在计算中。
		 */
		MAX(Number.class) ,

		/**
		 * MIN 函数返回一列中的最小值。NULL 值不包括在计算中。
		 */
		MIN(Number.class) ,

		/**
		 * SUM 函数返回数值列的总数（总额）。
		 */
		SUM(Number.class) ,

		/**
		 * UCASE 函数把字段的值转换为大写
		 */
		UCASE(String.class) ,

		/**
		 * LCASE 函数把字段的值转换为小写。
		 */
		LCASE(String.class) ,

		/**
		 * MID 函数用于从文本字段中提取字符。 SELECT MID(column_name,start[,length]) FROM table_name
		 */
		MID(String.class) ,

		/**
		 * STDEV : 返回给定表达式中所有值的填充统计标准偏差
		 */
		STDEV(Number.class) ,

		/**
		 * STDEVP : 返回给定表达式中所有值的填充统计标准偏差
		 */
		STDEVP(Number.class) ,

		/**
		 * 返回给定表达式中所有值的统计方差。
		 */
		VAR(Number.class) ,

		/**
		 * 返回给定表达式中所有值的统计方差。
		 */
		VARP(Number.class),

		;

		/**
		 * 返回的 数据类型
		 */
		Class<?> type;

		public Class<?> getType() {
			return type;
		}

		private Aggregate(Class<?> type) {
			this.type = type;
		}
	}

}
