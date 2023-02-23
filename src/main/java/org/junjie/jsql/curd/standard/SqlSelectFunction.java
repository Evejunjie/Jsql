package org.junjie.jsql.curd.standard;

import org.junjie.jsql.field.SelectFieldFunction;
import org.junjie.jut.LambdaFieldFunction;

/**
 * 聚合函数, 查询专用
 * 
 * @author junjie
 *
 * @param <E>
 */
public interface SqlSelectFunction<E> {

	// =============聚合函数========
	public <R> SelectFieldFunction countBig(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction groupingId(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction grouping(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction count(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction first(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction last(LambdaFieldFunction<E, R> field);
	
	public <R> SelectFieldFunction avg(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction max(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction min(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction sum(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction ucase(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction lcase(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction mid(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction stdev(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction stdevp(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction var(LambdaFieldFunction<E, R> field);

	public <R> SelectFieldFunction varp(LambdaFieldFunction<E, R> field);

	// =========== 数学函数 =============

	public <R> SelectFieldFunction abs(LambdaFieldFunction<E, R> field);

	/**
	 * 它将返回给定值的三角弧余弦值。
	 */
	public <R> SelectFieldFunction acos(LambdaFieldFunction<E, R> field);

	/**
	 * 它返回给定值的三角弧正弦值。
	 */
	public <R> SelectFieldFunction asin(LambdaFieldFunction<E, R> field);

	/**
	 * 它将返回给定值的三角弧切值。
	 */
	public <R> SelectFieldFunction atan(LambdaFieldFunction<E, R> field);

	/**
	 * N2 它用于返回从 X 轴到指定点 （y， x） 的角度（以半径为单位）
	 */
	public <R> SelectFieldFunction atn2();

	/**
	 * 此 SQL 数学函数返回大于或等于指定表达式的最小数字。
	 */
	public <R> SelectFieldFunction ceiling(LambdaFieldFunction<E, R> field);

	/**
	 * 它用于返回给定数字的三角余弦。
	 */
	public <R> SelectFieldFunction cos(LambdaFieldFunction<E, R> field);

	/**
	 * 床 它用于返回给定数字的三角余切值。
	 */
	public <R> SelectFieldFunction cot(LambdaFieldFunction<E, R> field);

	/**
	 * 它用于将指定的辐射角转换为以度为单位测量的等效角度。
	 */
	public <R> SelectFieldFunction degrees(LambdaFieldFunction<E, R> field);

	/**
	 * 它返回 E 的给定值的幂，其中 E 是欧拉数，大约等于 2.71828。
	 */
	public <R> SelectFieldFunction exp(LambdaFieldFunction<E, R> field);

	/**
	 * 这将返回小于或等于指定表达式或给定数字的最大整数值。
	 */

	public <R> SelectFieldFunction floor(LambdaFieldFunction<E, R> field);

	/**
	 * LOG 它将计算以 E 为底的给定数字的自然对数值，其中 E 是欧拉数，等于 2.71828。
	 */
	public <R> SelectFieldFunction log(LambdaFieldFunction<E, R> field);

	/**
	 * 它用于计算给定数字的底数 10 对数值。
	 */
	public <R> SelectFieldFunction log10(LambdaFieldFunction<E, R> field);

	/**
	 * 此 SQL 数学函数返回饼图值，大约等于 3.14 只会返回 PI()
	 */
	public <R> SelectFieldFunction pi();

	/**
	 * 它用于显示用户指定表达式的功效。
	 */
	public <R> SelectFieldFunction power(LambdaFieldFunction<E, R> field);

	/**
	 * 它用于将指定的度角转换为以弧度为单位测量的等效角度。
	 */
	public <R> SelectFieldFunction radians(LambdaFieldFunction<E, R> field);

	/**
	 * 这将返回正数，并且该值将大于或等于 0.0 且小于 1.0
	 */
	public <R> SelectFieldFunction rand(LambdaFieldFunction<E, R> field);

	/**
	 * 它用于将指定的表达式或单个数字舍入到最接近的数字。
	 */
	public <R> SelectFieldFunction round(LambdaFieldFunction<E, R> field, int length);

	/**
	 * 返回给定参数的符号，结果可能是正 （+）、负 （-） 或零 （0）。
	 */
	public <R> SelectFieldFunction sign(LambdaFieldFunction<E, R> field);

	/**
	 * 它用于返回给定数字的三角正弦
	 */
	public <R> SelectFieldFunction sin(LambdaFieldFunction<E, R> field);

	/**
	 * 它查找指定表达式或单个数字的平方根
	 */
	public <R> SelectFieldFunction sqrt(LambdaFieldFunction<E, R> field);

	/**
	 * 它用于返回指定表达式的平方或单个数字
	 */
	public <R> SelectFieldFunction square(LambdaFieldFunction<E, R> field);

	/**
	 * 它返回给定数字的三角正切
	 */
	public <R> SelectFieldFunction tan(LambdaFieldFunction<E, R> field);

	// =================== 字符串函数 ===========
	/**
	 * 它返回字符表达式最左侧字符的 ASCII 代码。
	 */
	public <R> SelectFieldFunction ascii(LambdaFieldFunction<E, R> field);

	/**
	 * 将整数值（ASCII 代码）转换为字符 char 与java 关键字冲突 故改为全大写
	 */
	public <R> SelectFieldFunction CHAR(LambdaFieldFunction<E, R> field);

	/**
	 * 它返回指定表达式的索引位置
	 */
	public <R> SelectFieldFunction charindex(LambdaFieldFunction<E, R> field);

	/**
	 * 组合或连接两个或多个并返回字符串。
	 */
	public <R> SelectFieldFunction concat(LambdaFieldFunction<E, R> field);

	/**
	 * 此函数使用分隔符连接两个或多个 SQL Server 单词。
	 */
	public <R> SelectFieldFunction concatWs(LambdaFieldFunction<E, R> field);

	/**
	 * 返回指定字符表达式的 SOUNDEX 值之间的差异
	 */
	public <R> SelectFieldFunction difference(LambdaFieldFunction<E, R> field);

	/**
	 * 使用指定的格式设置表达式的格式。
	 */

	public <R> SelectFieldFunction format(LambdaFieldFunction<E, R> field);

	/**
	 * 查找指定表达式的长度
	 */
	public <R> SelectFieldFunction length(LambdaFieldFunction<E, R> field);

	/**
	 * 返回指定表达式中最左侧的字符
	 */
	public <R> SelectFieldFunction left(LambdaFieldFunction<E, R> field);

	/**
	 * 它删除字符表达式左侧的空格
	 */
	public <R> SelectFieldFunction ltrim(LambdaFieldFunction<E, R> field);

	/**
	 * 它将给定的文本或表达式转换为小写。
	 */
	public <R> SelectFieldFunction lower(LambdaFieldFunction<E, R> field);

	/**
	 * 它返回指定整数值处的 Unicode 字符
	 */
	public <R> SelectFieldFunction nchar(LambdaFieldFunction<E, R> field);

	/**
	 * 它返回带有分隔符的 Unicode
	 */
	public <R> SelectFieldFunction quotename(LambdaFieldFunction<E, R> field);

	/**
	 * 我们可以使用此 SQL Server 函数将现有字符串替换为新值。
	 */
	public <R> SelectFieldFunction replace(LambdaFieldFunction<E, R> field);

	/**
	 * 将现有句子重复给定次数。
	 */
	public <R> SelectFieldFunction replicate(LambdaFieldFunction<E, R> field);

	/**
	 * 它有助于反转指定的表达式
	 */
	public <R> SelectFieldFunction reverse(LambdaFieldFunction<E, R> field);

	/**
	 * 返回给定表达式中最右边的字符
	 */
	public <R> SelectFieldFunction right(LambdaFieldFunction<E, R> field);

	/**
	 * 它删除给定表达式右侧的空格。
	 */
	public <R> SelectFieldFunction rtrim(LambdaFieldFunction<E, R> field);

	/**
	 * 它用于返回表达式中模式首次出现的起始索引位置。
	 */
	public <R> SelectFieldFunction patindex(LambdaFieldFunction<E, R> field);

	/**
	 * 此 SQL 字符串函数返回 SOUNDEX 代码。
	 */
	public <R> SelectFieldFunction soundex(LambdaFieldFunction<E, R> field);

	/**
	 * 返回重复空格的字符串。
	 */
	public <R> SelectFieldFunction space(LambdaFieldFunction<E, R> field);

	/**
	 * 它返回数值表达式的字符串表示形式。
	 */
	public <R> SelectFieldFunction str(LambdaFieldFunction<E, R> field);

	/**
	 * 它从文本中转义特殊字符。
	 */
	public <R> SelectFieldFunction stringEscape(LambdaFieldFunction<E, R> field);

	/**
	 * 使用分隔符拆分字符表达式。
	 */
	public <R> SelectFieldFunction stringSplit(LambdaFieldFunction<E, R> field);


	/**
	 * 将一个字符串插入另一个字符串
	 */
	public <R> SelectFieldFunction stuff(LambdaFieldFunction<E, R> field);

	/**
	 * 它从给定表达式返回指定数量的字符。
	 */
	public <R> SelectFieldFunction substring(LambdaFieldFunction<E, R> field);

	/**
	 * 它通过将指定的字符替换为一组新字符来转换字符串表达式。
	 */
	public <R> SelectFieldFunction translate(LambdaFieldFunction<E, R> field);

	/**
	 * 删除右侧和左侧的空白区域。
	 */
	public <R> SelectFieldFunction trim(LambdaFieldFunction<E, R> field);

	/**
	 * 返回 Unicode（整数）值，如 Unicode 标准中所定义。
	 */
	public <R> SelectFieldFunction unicode(LambdaFieldFunction<E, R> field);

	/**
	 * 此函数将给定的文本或表达式转换为大写。
	 */
	public <R> SelectFieldFunction upper(LambdaFieldFunction<E, R> field);

	// ========== 时间函数 ===========
	/**
	 * 当前时间
	 */
	public <R> SelectFieldFunction now();

	/**
	 * 它返回运行当前服务器实例的系统的日期和时间。
	 */
	public <R> SelectFieldFunction sysdatetime(LambdaFieldFunction<E, R> field);

	/**
	 * 运行当前 SQL Server 实例的系统的日期时间以及时区偏移量。
	 */
	public <R> SelectFieldFunction sysdatetimeoffset(LambdaFieldFunction<E, R> field);

	/**
	 * 这将返回运行服务器实例的计算机的日期和时间。此处，格式返回为协调世界时或 UTC。
	 */
	public <R> SelectFieldFunction sysutcdatetime(LambdaFieldFunction<E, R> field);

	/**
	 * 返回运行服务器实例的计算机的日期时间。
	 */
	public <R> SelectFieldFunction getdate(LambdaFieldFunction<E, R> field);

	/**
	 * 返回运行服务器实例的系统的日期时间。在这里，它将格式返回为协调世界时或 UTC。
	 */
	public <R> SelectFieldFunction getutcdate(LambdaFieldFunction<E, R> field);

	/**
	 * 从指定的用户返回指定的日期名称。
	 */
	public <R> SelectFieldFunction datename(LambdaFieldFunction<E, R> field);

	/**
	 * 从给定的用户返回指定的日期部分。
	 */
	public <R> SelectFieldFunction datepart(LambdaFieldFunction<E, R> field);

	/**
	 * 返回“天”部分。
	 */
	public <R> SelectFieldFunction day(LambdaFieldFunction<E, R> field);

	/**
	 * 用户指定的月份部分。
	 */
	public <R> SelectFieldFunction month(LambdaFieldFunction<E, R> field);

	/**
	 * 它返回用户指定的年份部分。
	 */
	public <R> SelectFieldFunction year(LambdaFieldFunction<E, R> field);

	/**
	 * 它从给定部分返回日期值。
	 */
	public <R> SelectFieldFunction dateFromparts(LambdaFieldFunction<E, R> field);

	/**
	 * 它将计算开始日期和结束日期之间的差异。
	 */
	public <R> SelectFieldFunction datediff(LambdaFieldFunction<E, R> field);

	/**
	 * 与 DATEDIFF 相同，但它返回 bigint 数据类型。
	 */
	public <R> SelectFieldFunction datediffBig(LambdaFieldFunction<E, R> field);

	/**
	 * 此日期函数通过添加用户指定的数字来返回新的日期时间值。
	 */
	public <R> SelectFieldFunction dateadd(LambdaFieldFunction<E, R> field);

	/**
	 * 返回带有可选偏移量的月份的最后一天
	 */
	public <R> SelectFieldFunction eomonth(LambdaFieldFunction<E, R> field);

}
