package org.junjie.jsql.invoke;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

import org.junjie.jsql.curd.result.abs.AbstractTableSet.AbstractTableReadInfo;
import org.junjie.jsql.curd.result.abs.EntityGenerate.MethodInfo;

/**
 * 默认注册的对象转换
 * 
 * @author junjie
 *
 */
public class ValueInvokeObject implements ValueInvoke {

	@FunctionalInterface
	public interface GetValueFunction<T, R> {
		R apply(T t) throws SQLException;
	}

	public ValueInvokeObject() {
		super();
		readMap.put(Byte.class, AbstractTableReadInfo::getByte);
		readMap.put(Time.class, AbstractTableReadInfo::getTime);
		readMap.put(InputStream.class, AbstractTableReadInfo::getBinaryStream);
		readMap.put(RowId.class, AbstractTableReadInfo::getRowId);
		readMap.put(Double.class, AbstractTableReadInfo::getDouble);
		readMap.put(Array.class, AbstractTableReadInfo::getArray);
		readMap.put(BigDecimal.class, AbstractTableReadInfo::getBigDecimal);
		readMap.put(Float.class, AbstractTableReadInfo::getFloat);
		readMap.put(Clob.class, AbstractTableReadInfo::getClob);
		readMap.put(byte[].class, AbstractTableReadInfo::getBytes);
		readMap.put(Ref.class, AbstractTableReadInfo::getRef);
		readMap.put(Timestamp.class, AbstractTableReadInfo::getTimestamp);
		readMap.put(Boolean.class, AbstractTableReadInfo::getBoolean);
		readMap.put(Date.class, AbstractTableReadInfo::getDate);

		readMap.put(NClob.class, AbstractTableReadInfo::getNClob);
		readMap.put(Object.class, AbstractTableReadInfo::getObject);
		readMap.put(Short.class, AbstractTableReadInfo::getShort);
		readMap.put(String.class, AbstractTableReadInfo::getString);
		readMap.put(Reader.class, AbstractTableReadInfo::getCharacterStream);
		readMap.put(Blob.class, AbstractTableReadInfo::getBlob);
		readMap.put(Long.class, AbstractTableReadInfo::getLong);
		readMap.put(Integer.class, AbstractTableReadInfo::getInt);
		readMap.put(URL.class, AbstractTableReadInfo::getURL);
		readMap.put(SQLXML.class, AbstractTableReadInfo::getSQLXML);

		readMap.put(java.util.Date.class, (read) -> {
			Timestamp timestamp = read.getTimestamp();
			if (timestamp == null) {
				return null;
			} else {
				return new Date(timestamp.getTime());
			}
		});

		readMap.put(Number.class, (read) -> {
			return read.getBigDecimal();
		});

		readMap.put(CharSequence.class, (read) -> {
			return read.getString();
		});

		readMap.put(LocalDate.class, (read) -> {
			Timestamp timestamp = read.getTimestamp();
			if (timestamp == null) {
				return null;
			}
			return timestamp.toLocalDateTime().toLocalDate();
		});

		readMap.put(LocalDateTime.class, (read) -> {
			Timestamp timestamp = read.getTimestamp();
			if (timestamp == null) {
				return null;
			}
			return timestamp.toLocalDateTime();
		});

		readMap.put(LocalTime.class, (read) -> {
			Timestamp timestamp = read.getTimestamp();
			if (timestamp == null) {
				return null;
			}
			return timestamp.toLocalDateTime().toLocalTime();
		});

		readMap.put(Month.class, (read) -> {
			Timestamp timestamp = read.getTimestamp();
			if (timestamp == null) {
				return null;
			}
			return Month.from(timestamp.toLocalDateTime());
		});
		readMap.put(MonthDay.class, (read) -> {
			Timestamp timestamp = read.getTimestamp();
			if (timestamp == null) {
				return null;
			}
			return MonthDay.from(timestamp.toLocalDateTime());
		});
		readMap.put(Year.class, (read) -> {
			Timestamp timestamp = read.getTimestamp();
			if (timestamp == null) {
				return null;
			}
			return Year.from(timestamp.toLocalDateTime());
		});
		readMap.put(YearMonth.class, (read) -> {
			Timestamp timestamp = read.getTimestamp();
			if (timestamp == null) {
				return null;
			}
			return YearMonth.from(timestamp.toLocalDateTime());
		});
		readMap.put(BigInteger.class, (read) -> {
			BigDecimal number = read.getBigDecimal();
			if (number == null) {
				return null;
			}
			return number.toBigInteger();
		});
		readMap.put(StringBuilder.class, (read) -> {
			String str = read.getString();
			if (str == null) {
				return null;
			}
			return new StringBuilder(str);
		});
		readMap.put(StringBuffer.class, (read) -> {
			String str = read.getString();
			if (str == null) {
				return null;
			}
			return new StringBuffer(str);
		});
		readMap.put(Character.class, (read) -> {
			int c = read.getInt();
			if (read.wasNull()) {
				return null;
			}
			return Character.valueOf((char) c);
		});

	}

	Map<Class<?>, GetValueFunction<AbstractTableReadInfo, Object>> readMap = new HashMap<>();

	@Override
	public <E> void invoke(AbstractTableReadInfo read, E entity, MethodInfo method) throws Exception {
		Class<?>                                        type     = method.getType();
		GetValueFunction<AbstractTableReadInfo, Object> function = readMap.get(type);
		method.invoke(entity, function.apply(read));
	}

	@Override
	public boolean processable(Class<?> type) {
		return readMap.containsKey(type);
	}

}
