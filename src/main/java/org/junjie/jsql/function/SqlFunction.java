package org.junjie.jsql.function;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.function.AggregateFunction.Aggregate;
import org.junjie.jsql.function.DateFunction.SQLDate;
import org.junjie.jsql.function.MathematicalFunction.Mathematical;
import org.junjie.jsql.function.StringFunction.SQLString;

/**
 * SQL 函数, 允许自定义, 需要子类实现函数名称, <br>
 * 函数 允许有别名,<br>
 * 函数 允许有参数, 参数 可以是片段代码,允许 是非片段代码,但必须实现 To String方法或者是基本的数据类型
 * 
 * 函数内容 用法请参考 <a href="https://dofactory.com/sql/"> https://dofactory.com/sql/</a>
 * 
 * @author junjie
 *
 */
public interface SqlFunction extends SqlField {


	
	/**
	 * 获取函数名称
	 * 
	 * @return
	 */
	public CharSequence getName();
	
	/**
	 * 添加参数
	 * 
	 * @param param
	 * @return
	 */
	public SqlFunction param(Object param);

	public SqlFunction param(SqlFragment fragment);

	default SqlFunction param(Object param1, Object param2) {
		param(param1);
		param(param2);
		return this;
	}

	default SqlFunction param(Object param1, Object param2, Object param3) {
		param(param1);
		param(param2);
		param(param3);
		return this;
	}

	/**
	 * 函数参数
	 */
	public List<SqlFragment> getParams();

	@Override
	default void writeFragment(SqlDeposit deposit) {
		deposit.writeSql(getName());
		deposit.writeSql('(');
		Collection<SqlFragment> params = getParams();
		if (params == null || params.isEmpty()) {
			deposit.writeSql(')');
		} else {
			Iterator<SqlFragment> it = params.iterator();
			for (;;) {
				SqlFragment next = it.next();
				deposit.write(next);
				if (!it.hasNext())
					break;
				deposit.writeSql(',');
			}
			deposit.writeSql(')');
		}

	}

	/**
	 * SQL Server ABS 函数是一个数学函数，它将返回指定表达式的绝对正值。用于查找绝对值的 ABS 函数的语法为：
	 * 
	 * @param param
	 * @return
	 */
	public static SqlFunction abs() {
		return MathematicalFunction.of(Mathematical.ABS);
	}

	/**
	 * SQL Server ACOS 函数计算指定表达式的三角弧余弦。弧余弦也称为余弦函数的逆函数，ACOS 的语法为
	 * 
	 * @param param
	 * @return
	 */
	public static SqlFunction acos() {
		return MathematicalFunction.of(Mathematical.ACOS);
	}

	/**
	 * SQL 服务器 ASIN 是用于计算指定表达式的三角学正弦的数学函数之一。反正弦也称为正弦函数的逆函数。ASIN 函数的语法为：
	 * 
	 * @param param
	 * @return
	 */
	public static SqlFunction asin() {
		return MathematicalFunction.of(Mathematical.ASIN);
	}

	/**
	 * SQL Server ATAN 函数是一个数学函数，用于计算指定表达式的三角反正切。SQL Arc 切线也称为 TANGENT 函数的逆函数。ATAN
	 * 函数的语法为
	 * 
	 * @param param
	 * @return
	 */
	public static SqlFunction atan() {
		return MathematicalFunction.of(Mathematical.ATAN);
	}

	/**
	 * SQL Server ATN2 函数是一个数学函数，它返回正 x 轴与从原点到点 （y， x） 的直线之间的角度。ATN2 函数的语法是。
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static SqlFunction atn2() {
		return MathematicalFunction.of(Mathematical.ATN2);
	}

	/**
	 * SQL Server CEILING 函数是一个数学函数。它用于返回最接近的整数值，该整数值大于或等于指定的表达式。天花板函数的语法是
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction ceiling() {
		return MathematicalFunction.of(Mathematical.CEILING);
	}

	/**
	 * SQL Server COS 函数是一个数学函数，用于计算指定表达式的三角余弦。COS 函数的语法为
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction cos() {
		return MathematicalFunction.of(Mathematical.COS);
	}

	/**
	 * SQL Server COT 函数是一个数学函数，用于计算指定表达式的三角余切。COT 函数的语法是
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction cot() {
		return MathematicalFunction.of(Mathematical.COT);
	}

	/**
	 * SQL Server DEGREES 函数是一个数学函数，用于将以弧度为单位测量的角度转换为以度为单位测量的近似等效角度。度函数的语法为
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction degrees() {
		return MathematicalFunction.of(Mathematical.DEGREES);
	}

	/**
	 * SQL EXP 函数是一个数学函数，用于返回 E 的给定浮点值的幂，其中 E 是欧拉数，大约等于 2.71828。例如，如果我们将表达式指定为
	 * EXP（2）。这意味着 e² ==> 2.718² ==> 7.38。
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction exp() {
		return MathematicalFunction.of(Mathematical.EXP);
	}

	/**
	 * SQL Server FLOOR 函数用于返回最接近的整数值，该值小于或等于指定的表达式或值。楼层函数的基本语法是：
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction floor() {
		return MathematicalFunction.of(Mathematical.FLOOR);
	}

	/**
	 * SQL Server LOG 函数计算给定浮点值的自然对数值，其语法为
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction log() {
		return MathematicalFunction.of(Mathematical.LOG);
	}

	/**
	 * SQL Server LOG10 函数是一个数学函数，用于计算给定浮点值的 10 个基数对数值，其语法为
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction log10() {
		return MathematicalFunction.of(Mathematical.LOG10);
	}

	/**
	 * 此SQL数学函数用于计算指定表达式或数值的幂，其语法为
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction power() {
		return MathematicalFunction.of(Mathematical.POWER);
	}

	/**
	 * SQL 弧度函数是一个数学函数，用于将以度为单位测量的角度转换为以弧度为单位的近似等效角度。SQL Server RADIIANS 函数的语法是
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction radians() {
		return MathematicalFunction.of(Mathematical.RADIANS);
	}

	/**
	 * SQL Server RAND 函数是一个数学函数，用于返回介于 0 和 1 之间的伪随机数。RAND 函数生成的值从 <>（包含）开始且小于 <>。
	 * 
	 * @return
	 */
	public static SqlFunction rand() {
		return MathematicalFunction.of(Mathematical.RAND);
	}

	/**
	 * 此 SQL Server 函数将指定的数值表达式或单个数字舍入为用户指定的长度或精度，语法为
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction round(Object param1, int length) {
		return MathematicalFunction.of(Mathematical.ROUND).param(param1, length);
	}

	public static SqlFunction sign() {
		return MathematicalFunction.of(Mathematical.SIGN);
	}

	/**
	 * SQL Server SIN 函数计算指定表达式的正弦三角函数，其语法为
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction sin() {
		return MathematicalFunction.of(Mathematical.SIN);
	}

	/**
	 * SQL SQRT 函数查找指定表达式或数字的平方根。用于查找平方根的 SQRT 函数的语法为
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction sqrt() {
		return MathematicalFunction.of(Mathematical.SQRT);
	}

	public static SqlFunction pi() {
		return MathematicalFunction.of(Mathematical.PI);
	}

	/**
	 * SQL 平方函数是一种数学函数，用于计算指定表达式或数字的平方。SQUARE 函数的语法是
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction square() {
		return MathematicalFunction.of(Mathematical.SQUARE);
	}

	/**
	 * SQL TAN 函数是一个数学函数，用于计算指定表达式的三角切线。SQL Server TAN 函数的语法为：
	 * 
	 * @param param1
	 * @return
	 */
	public static SqlFunction tan() {
		return MathematicalFunction.of(Mathematical.TAN);
	}

	public static SqlFunction now() {
		return DateFunction.of(SQLDate.NOW);
	}

	public static SqlFunction sysdatetime() {
		return DateFunction.of(SQLDate.SYSDATETIME);
	}

	public static SqlFunction sysdatetimeoffset() {
		return DateFunction.of(SQLDate.SYSDATETIMEOFFSET);
	}

	public static SqlFunction sysutcdatetime() {
		return DateFunction.of(SQLDate.SYSUTCDATETIME);
	}

	public static SqlFunction getdate() {
		return DateFunction.of(SQLDate.GETDATE);
	}

	public static SqlFunction getutcdate() {
		return DateFunction.of(SQLDate.GETUTCDATE);
	}

	public static SqlFunction datename() {
		return DateFunction.of(SQLDate.DATENAME);
	}

	public static SqlFunction datepart() {
		return DateFunction.of(SQLDate.DATEPART);
	}

	public static SqlFunction day() {
		return DateFunction.of(SQLDate.DAY);
	}

	public static SqlFunction month() {
		return DateFunction.of(SQLDate.MONTH);
	}

	public static SqlFunction year() {
		return DateFunction.of(SQLDate.YEAR);
	}

	public static SqlFunction date_fromparts() {
		return DateFunction.of(SQLDate.DATE_FROMPARTS);
	}

	public static SqlFunction datediff() {
		return DateFunction.of(SQLDate.DATEDIFF);
	}

	public static SqlFunction datediff_big() {
		return DateFunction.of(SQLDate.DATEDIFF_BIG);
	}

	public static SqlFunction dateadd() {
		return DateFunction.of(SQLDate.DATEADD);
	}

	public static SqlFunction eomonth() {
		return DateFunction.of(SQLDate.EOMONTH);
	}

	public static SqlFunction avg() {
		return AggregateFunction.of(Aggregate.AVG);
	}

	public static SqlFunction count_big() {
		return AggregateFunction.of(Aggregate.COUNT_BIG);
	}

	public static SqlFunction grouping_id() {
		return AggregateFunction.of(Aggregate.GROUPING_ID);
	}

	public static SqlFunction grouping() {
		return AggregateFunction.of(Aggregate.GROUPING);
	}

	public static SqlFunction count() {
		return AggregateFunction.of(Aggregate.COUNT);
	}

	public static SqlFunction first() {
		return AggregateFunction.of(Aggregate.FIRST);
	}

	public static SqlFunction last() {
		return AggregateFunction.of(Aggregate.LAST);
	}

	public static SqlFunction max() {
		return AggregateFunction.of(Aggregate.MAX);
	}

	public static SqlFunction min() {
		return AggregateFunction.of(Aggregate.MIN);
	}

	public static SqlFunction sum() {
		return AggregateFunction.of(Aggregate.SUM);
	}

	public static SqlFunction ucase() {
		return AggregateFunction.of(Aggregate.UCASE);
	}

	public static SqlFunction lcase() {
		return AggregateFunction.of(Aggregate.LCASE);
	}

	public static SqlFunction mid() {
		return AggregateFunction.of(Aggregate.MID);
	}

	public static SqlFunction stdev() {
		return AggregateFunction.of(Aggregate.STDEV);
	}

	public static SqlFunction stdevp() {
		return AggregateFunction.of(Aggregate.STDEVP);
	}

	public static SqlFunction var() {
		return AggregateFunction.of(Aggregate.VAR);
	}

	public static SqlFunction varp() {
		return AggregateFunction.of(Aggregate.VARP);
	}

	public static SqlFunction ascii() {
		return StringFunction.of(SQLString.ASCII);
	}

	public static SqlFunction CHAR() {
		return StringFunction.of(SQLString.CHAR);
	}

	public static SqlFunction charindex() {
		return StringFunction.of(SQLString.CHARINDEX);
	}

	public static SqlFunction concat() {
		return StringFunction.of(SQLString.CONCAT);
	}

	public static SqlFunction concat_ws() {
		return StringFunction.of(SQLString.CONCAT_WS);
	}

	public static SqlFunction difference() {
		return StringFunction.of(SQLString.DIFFERENCE);
	}

	public static SqlFunction format() {
		return StringFunction.of(SQLString.FORMAT);
	}

	public static SqlFunction length() {
		return StringFunction.of(SQLString.LENGTH);
	}

	public static SqlFunction left() {
		return StringFunction.of(SQLString.LEFT);
	}

	public static SqlFunction ltrim() {
		return StringFunction.of(SQLString.LTRIM);
	}

	public static SqlFunction lower() {
		return StringFunction.of(SQLString.LOWER);
	}

	public static SqlFunction nchar() {
		return StringFunction.of(SQLString.NCHAR);
	}

	public static SqlFunction quotename() {
		return StringFunction.of(SQLString.QUOTENAME);
	}

	public static SqlFunction replace() {
		return StringFunction.of(SQLString.REPLACE);
	}

	public static SqlFunction replicate() {
		return StringFunction.of(SQLString.REPLICATE);
	}

	public static SqlFunction reverse() {
		return StringFunction.of(SQLString.REVERSE);
	}

	public static SqlFunction right() {
		return StringFunction.of(SQLString.RIGHT);
	}

	public static SqlFunction rtrim() {
		return StringFunction.of(SQLString.RTRIM);
	}

	public static SqlFunction patindex() {
		return StringFunction.of(SQLString.PATINDEX);
	}

	public static SqlFunction soundex() {
		return StringFunction.of(SQLString.SOUNDEX);
	}

	public static SqlFunction space() {
		return StringFunction.of(SQLString.SPACE);
	}

	public static SqlFunction str() {
		return StringFunction.of(SQLString.STR);
	}

	public static SqlFunction string_escape() {
		return StringFunction.of(SQLString.STRING_ESCAPE);
	}

	public static SqlFunction string_split() {
		return StringFunction.of(SQLString.STRING_SPLIT);
	}

	public static SqlFunction string_agg() {
		return StringFunction.of(SQLString.STRING_AGG);
	}

	public static SqlFunction stuff() {
		return StringFunction.of(SQLString.STUFF);
	}

	public static SqlFunction substring() {
		return StringFunction.of(SQLString.SUBSTRING);
	}

	public static SqlFunction translate() {
		return StringFunction.of(SQLString.TRANSLATE);
	}

	public static SqlFunction trim() {
		return StringFunction.of(SQLString.TRIM);
	}

	public static SqlFunction unicode() {
		return StringFunction.of(SQLString.UNICODE);
	}

	public static SqlFunction upper() {
		return StringFunction.of(SQLString.UPPER);
	}

}
