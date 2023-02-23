package org.junjie.jsql.function;

import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;

public class StringFunction extends AbstractSqlFunction {

	/**
	 * 函数名称
	 */
	final SQLString string;

	public StringFunction(SQLString string) {
		super();
		this.string = string;
	}

	@Override
	public CharSequence getName() {
		return string.name();
	}

	@Override
	public Class<?> getType() {
		Class<?> type = string.getType();
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

	public enum SQLString {

		/**
		 * 它返回字符表达式最左侧字符的 ASCII 代码。
		 */
		ASCII(Number.class) ,
		/**
		 * 将整数值（ASCII 代码）转换为字符
		 */
		CHAR(String.class) ,
		/**
		 * 它返回指定表达式的索引位置
		 */
		CHARINDEX(Number.class) ,
		/**
		 * 组合或连接两个或多个并返回字符串。
		 */
		CONCAT(String.class) ,
		/**
		 * 此函数使用分隔符连接两个或多个 SQL Server 单词。
		 */
		CONCAT_WS(String.class) ,
		/**
		 * 返回指定字符表达式的 SOUNDEX 值之间的差异
		 */
		DIFFERENCE(Number.class) ,
		/**
		 * 使用指定的格式设置表达式的格式。
		 */
		FORMAT(String.class) ,
		/**
		 * 查找指定表达式的长度
		 */
		LENGTH(Number.class) ,
		/**
		 * 返回指定表达式中最左侧的字符
		 */
		LEFT(String.class) ,
		/**
		 * 它删除字符表达式左侧的空格
		 */
		LTRIM(String.class) ,
		/**
		 * 它将给定的文本或表达式转换为小写。
		 */
		LOWER(String.class) ,
		/**
		 * 它返回指定整数值处的 Unicode 字符
		 */
		NCHAR(String.class) ,
		/**
		 * 它返回带有分隔符的 Unicode
		 */
		QUOTENAME(String.class) ,
		/**
		 * 我们可以使用此 SQL Server 函数将现有字符串替换为新值。
		 */
		REPLACE(String.class) ,
		/**
		 * 将现有句子重复给定次数。
		 */
		REPLICATE(String.class) ,
		/**
		 * 它有助于反转指定的表达式
		 */
		REVERSE(String.class) ,
		/**
		 * 返回给定表达式中最右边的字符
		 */
		RIGHT(String.class) ,
		/**
		 * 它删除给定表达式右侧的空格。
		 */
		RTRIM(String.class) ,
		/**
		 * 它用于返回表达式中模式首次出现的起始索引位置。
		 */
		PATINDEX(Number.class) ,
		/**
		 * 此 SQL 字符串函数返回 SOUNDEX 代码。
		 */
		SOUNDEX(Number.class) ,
		/**
		 * 返回重复空格的字符串。
		 */
		SPACE(String.class) ,
		/**
		 * 它返回数值表达式的字符串表示形式。
		 */
		STR(Number.class) ,
		/**
		 * 它从文本中转义特殊字符。
		 */
		STRING_ESCAPE(String.class) ,
		/**
		 * 使用分隔符拆分字符表达式。
		 */
		STRING_SPLIT(String.class) ,
		/**
		 * 它连接字符串表达式，并在它们之间放置指定的分隔符。
		 */
		STRING_AGG(String.class) ,
		/**
		 * 将一个字符串插入另一个字符串
		 */
		STUFF(String.class) ,
		/**
		 * 它从给定表达式返回指定数量的字符。
		 */
		SUBSTRING(String.class) ,
		/**
		 * 它通过将指定的字符替换为一组新字符来转换字符串表达式。
		 */
		TRANSLATE(String.class) ,
		/**
		 * 删除右侧和左侧的空白区域。
		 */
		TRIM(String.class) ,
		/**
		 * 返回 Unicode（整数）值，如 Unicode 标准中所定义。
		 */
		UNICODE(Number.class) ,
		/**
		 * 此函数将给定的文本或表达式转换为大写。
		 */
		UPPER(String.class),;

		Class<?> type;

		public Class<?> getType() {
			return type;
		}

		private SQLString(Class<?> type) {
			this.type = type;
		}

	}

	public static SqlFunction of(SQLString ascii) {
		return new StringFunction(ascii);
	}

}
