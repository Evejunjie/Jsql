package org.junjie.jsql.database.cast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Date;

/**
 * 数据字段值转换为 java 类型
 * 
 * @author junjie
 *
 */
public interface ToJava {

	public Number numberValue();

	public BigInteger bigIntegerValue();

	public BigDecimal bigDecimalValue();

	public Integer intValue();

	public Long longValue();

	public Float floatValue();

	public Double doubleValue();

	public Byte byteValue();

	public Short shortValue();

	public Character charValue();

	public String stringValue();

	public Date dateValue();

	public LocalDate localDateValue();

	public LocalDateTime localDateTimeValue();

	public LocalTime localTimeValue();

	public YearMonth yearMonthValue();

	public Year yearValue();

	public Month monthValue();

}
