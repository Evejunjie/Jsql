package org.junjie.jsql.function;

import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * 数学函数
 * 
 * @author junjie
 *
 */
public class MathematicalFunction extends AbstractSqlFunction {

	final Mathematical mathematical;

	public MathematicalFunction(Mathematical mathematical) {
		super();
		this.mathematical = mathematical;
	}

	@Override
	public CharSequence getName() {
		return mathematical.name();
	}

	@Override
	public Class<?> getType() {
		Class<?> type = mathematical.getType();
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

	
	public static MathematicalFunction of(Mathematical math) {
		return new MathematicalFunction(math);
	}

	public enum Mathematical {
		/**
		 * 它返回绝对值或绝对正值。
		 */
		ABS(Number.class) ,
		/**
		 * 它将返回给定值的三角弧余弦值。
		 */
		ACOS(Number.class),
		/**
		 * 它返回给定值的三角弧正弦值。
		 */
		ASIN(Number.class),
		/**
		 * 它将返回给定值的三角弧切值。
		 */
		ATAN(Number.class),
		/**
		 * N2 它用于返回从 X 轴到指定点 （y， x） 的角度（以半径为单位）
		 */
		ATN2(Number.class),
		/**
		 * 此 SQL 数学函数返回大于或等于指定表达式的最小数字。
		 */
		CEILING(Number.class),
		/**
		 * 它用于返回给定数字的三角余弦。
		 */
		COS(Number.class),
		/**
		 * 床 它用于返回给定数字的三角余切值。
		 */
		COT(Number.class) ,
		/**
		 * 它用于将指定的辐射角转换为以度为单位测量的等效角度。
		 */
		DEGREES(Number.class) ,
		/**
		 * 它返回 E 的给定值的幂，其中 E 是欧拉数，大约等于 2.71828。
		 */
		EXP(Number.class) ,
		/**
		 * 这将返回小于或等于指定表达式或给定数字的最大整数值。
		 */
		FLOOR(Number.class) ,
		/**
		 * LOG 它将计算以 E 为底的给定数字的自然对数值，其中 E 是欧拉数，等于 2.71828。
		 */
		LOG(Number.class) ,
		/**
		 * 它用于计算给定数字的底数 10 对数值。
		 */
		LOG10(Number.class) ,
		/**
		 * 此 SQL 数学函数返回饼图值，大约等于 3.14
		 */
		PI(Double.class) ,
		/**
		 * 它用于显示用户指定表达式的功效。
		 */
		POWER(Number.class) ,
		/**
		 * 它用于将指定的度角转换为以弧度为单位测量的等效角度。
		 */
		RADIANS(Number.class) ,
		/**
		 * 这将返回正数，并且该值将大于或等于 0.0 且小于 1.0
		 */
		RAND(Number.class) ,
		/**
		 * 它用于将指定的表达式或单个数字舍入到最接近的数字。
		 */
		ROUND(Number.class) ,
		/**
		 * 返回给定参数的符号，结果可能是正 （+）、负 （-） 或零 （0）。
		 */
		SIGN(Number.class) ,
		/**
		 * 它用于返回给定数字的三角正弦
		 */
		SIN(Number.class) ,
		/**
		 * 它查找指定表达式或单个数字的平方根
		 */
		SQRT(Number.class) ,
		/**
		 * 它用于返回指定表达式的平方或单个数字
		 */
		SQUARE(Number.class) ,
		/**
		 * 它返回给定数字的三角正切
		 */
		TAN(Number.class),

		;

		Class<?> type;

		public Class<?> getType() {
			return type;
		}

		private Mathematical(Class<?> type) {
			this.type = type;
		}
		
		
		
	}

}
