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

public abstract class CastJava implements ToJava {

	@Override
	public Number numberValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public BigInteger bigIntegerValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public BigDecimal bigDecimalValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public Integer intValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public Long longValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public Float floatValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public Double doubleValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public Byte byteValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public Short shortValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public Character charValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public String stringValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public Date dateValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public LocalDate localDateValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public LocalDateTime localDateTimeValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public LocalTime localTimeValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public YearMonth yearMonthValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public Year yearValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

	@Override
	public Month monthValue() {
		throw new UnsupportedOperationException("未实现该接口");
	}

}
