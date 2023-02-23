package org.junjie.jsql.function;

import java.util.Date;

import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * SQL 日期函数 SQL Server Date 函数通常以 dd-mm-yyyy hh：mm：ss 格式或任何其他特定于系统的格式存储 。<br>
 * 但是，在某些情况下，我们必须提取各个部分，例如月，月名称，年，日等。<br>
 * 有时，我们可能必须格式化日期并从原始日期中添加或减去几天。<br>
 * 我们可以在所有这些场景中使用内置的标准 SQL Server 函数来操作日期。
 * 
 * @author junjie
 *
 */
public class DateFunction extends AbstractSqlFunction {

	final SQLDate date;

	@Override
	public CharSequence getName() {
		return date.name();
	}

	@Override
	public Class<?> getType() {
		Class<?> type = date.getType();
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

	
	public DateFunction(SQLDate date) {
		super();
		this.date = date;
	}

	public static DateFunction of(SQLDate date) {
		return new DateFunction(date);
	}

	public enum SQLDate {

		/**
		 * 当前时间
		 */
		NOW(Date.class) ,

		/**
		 * 它返回运行当前服务器实例的系统的日期和时间。
		 */
		SYSDATETIME(Date.class) ,

		/**
		 * 运行当前 SQL Server 实例的系统的日期时间以及时区偏移量。
		 */
		SYSDATETIMEOFFSET(String.class) ,

		/**
		 * 这将返回运行服务器实例的计算机的日期和时间。此处，格式返回为协调世界时或 UTC。
		 */
		SYSUTCDATETIME(String.class) ,

		/**
		 * 返回运行服务器实例的计算机的日期时间。
		 */
		GETDATE(Date.class) ,

		/**
		 * 返回运行服务器实例的系统的日期时间。在这里，它将格式返回为协调世界时或 UTC。
		 */
		GETUTCDATE(String.class) ,

		/**
		 * 从指定的用户返回指定的日期名称。
		 */
		DATENAME(String.class) ,

		/**
		 * 从给定的用户返回指定的日期部分。
		 */
		DATEPART(String.class) ,

		/**
		 * 返回“天”部分。
		 */
		DAY(Number.class) ,

		/**
		 * 用户指定的月份部分。
		 */
		MONTH(Number.class) ,

		/**
		 * 它返回用户指定的年份部分。
		 */
		YEAR(Number.class) ,

		/**
		 * 它从给定部分返回日期值。
		 */
		DATE_FROMPARTS(Number.class) ,

		/**
		 * 它将计算开始日期和结束日期之间的差异。
		 */
		DATEDIFF(Date.class) ,

		/**
		 * 与 DATEDIFF 相同，但它返回 bigint 数据类型。
		 */
		DATEDIFF_BIG(Number.class) ,

		/**
		 * 此日期函数通过添加用户指定的数字来返回新的日期时间值。
		 */
		DATEADD(Date.class) ,

		/**
		 * 返回带有可选偏移量的月份的最后一天
		 */
		EOMONTH(Number.class),;

		private Class<?> type;

		public Class<?> getType() {
			return type;
		}

		private SQLDate(Class<?> type) {
			this.type = type;
		}

	}



}
