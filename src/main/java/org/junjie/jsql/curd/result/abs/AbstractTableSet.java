package org.junjie.jsql.curd.result.abs;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junjie.jsql.curd.result.TableSet;
import org.junjie.jsql.curd.result.abs.EntityGenerate.MapMethodInfo;
import org.junjie.jsql.curd.standard.SqlCrudExecute;
import org.junjie.jsql.exception.JsqlSpliceException;
import org.junjie.jsql.fragment.SqlField;
import org.junjie.jsql.invoke.ValueInvoke;
import org.junjie.jut.Jut;

import lombok.extern.java.Log;

/**
 * 实现都必须是在 内部类, 这样能控制住变量
 * 
 * @author junjie
 *
 */
@Log
public class AbstractTableSet implements TableSet {

	public static EntityGenerate EG = new EntityGenerate();

	static Map<Class<?>, ValueInvoke> TYPE_MAP = new HashMap<>(120);

	private final TableHead         head;
	private final ResultSet         set;
	private final SqlCrudExecute<?> crud;
	private final ResultSetMetaData metaData;
	private final int               rowCount;
	private final int               columnCount;

	AbstractTableReadInfo read = new AbstractTableReadInfo();

	public AbstractTableSet(ResultSet set, SqlCrudExecute<?> crud) throws SQLException {
		super();
		this.set = set;
		this.metaData = set.getMetaData();
		this.crud = crud;
		this.head = new AbstractTableHead();
		set.last();
		// -> 总行数 一行就是 1
		rowCount = set.getRow();
		// -> 1列就是1
		columnCount = metaData.getColumnCount();
	}

	@Override
	public TableHead getHead() {
		return head;
	}

	/**
	 * 数据读取
	 * 
	 * @author junjie
	 *
	 */
	public class AbstractTableReadInfo {

		int col;
		int row;

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}

		public void setRow(int row) throws SQLException {
			if (this.row != row) {
				this.row = row;
				set.absolute(row);
			}
		}

		public void setCol(int col) {
			this.col = col;
		}

		public boolean wasNull() throws SQLException {
			return set.wasNull();
		}

		public byte getByte() throws SQLException {
			return set.getByte(this.col);
		}

		public Reader getNCharacterStream() throws SQLException {
			return set.getNCharacterStream(this.col);
		}

		public Time getTime() throws SQLException {
			return set.getTime(this.col);
		}

		public InputStream getBinaryStream() throws SQLException {
			return set.getBinaryStream(this.col);
		}

		public RowId getRowId() throws SQLException {
			return set.getRowId(this.col);
		}

		public double getDouble() throws SQLException {
			return set.getDouble(this.col);
		}

		public Array getArray() throws SQLException {
			return set.getArray(this.col);
		}

		public BigDecimal getBigDecimal() throws SQLException {
			return set.getBigDecimal(this.col);
		}

		public float getFloat() throws SQLException {
			return set.getFloat(this.col);
		}

		public Clob getClob() throws SQLException {
			return set.getClob(this.col);
		}

		public InputStream getAsciiStream() throws SQLException {
			return set.getAsciiStream(this.col);
		}

		public byte[] getBytes() throws SQLException {
			return set.getBytes(this.col);
		}

		public Ref getRef() throws SQLException {
			return set.getRef(this.col);
		}

		public Timestamp getTimestamp() throws SQLException {
			return set.getTimestamp(this.col);
		}

		public boolean getBoolean() throws SQLException {
			return set.getBoolean(this.col);
		}

		public Date getDate() throws SQLException {
			return set.getDate(this.col);
		}

		public String getNString() throws SQLException {
			return set.getNString(this.col);
		}

		public NClob getNClob() throws SQLException {
			return set.getNClob(this.col);
		}

		public Object getObject() throws SQLException {
			return set.getObject(this.col);
		}

		public short getShort() throws SQLException {
			return set.getShort(this.col);
		}

		public String getString() throws SQLException {
			return set.getString(this.col);
		}

		public Reader getCharacterStream() throws SQLException {
			return set.getCharacterStream(this.col);
		}

		public Blob getBlob() throws SQLException {
			return set.getBlob(this.col);
		}

		public long getLong() throws SQLException {
			return set.getLong(this.col);
		}

		public int getInt() throws SQLException {
			return set.getInt(this.col);
		}

		public URL getURL() throws SQLException {
			return set.getURL(this.col);
		}

		public SQLXML getSQLXML() throws SQLException {
			return set.getSQLXML(this.col);
		}

	}

	public class AbstractTableListValue implements TableListValue {

		protected final int row;

		public AbstractTableListValue(int row) {
			super();
			this.row = row;
		}

		@Override
		public int getRow() {
			return row;
		}

		@Override
		public List<Head> getHead() {
			return head.getValues();
		}

		@Override
		public int getCol() {
			return columnCount;
		}

		@Override
		public AbstractTableReadInfo read() throws SQLException {
			read.setRow(row);
			return read;
		}

		@Override
		public TableSet getTableSet() {
			return AbstractTableSet.this;
		}

		@Override
		public <E> E toEntity(Class<E> clazz) {
			try {
				return EG.generate(clazz, crud.getJsql(), this);
			} catch (Exception e) {
				e.printStackTrace();
				throw new JsqlSpliceException("创建实体类异常", e);
			}
		}

		@Override
		public Map<String, Object> toMap() {
			// -> 表头类型来读取数据
			HashMap<String, Object> hashMap = new HashMap<>(columnCount);
			MapMethodInfo           mmi     = new MapMethodInfo();
			// -> 查询返回的
			List<Head> head = getHead();
			try {
				AbstractTableReadInfo read   = read();
				List<ValueInvoke>     invoke = crud.getJsql().getInvoke();
				for (Head head2 : head) {
					read.setCol(head2.index());
					mmi.setKey(head2.field());
					mmi.setType(head2.type());
					for (ValueInvoke valueInvoke : invoke) {
						if (valueInvoke.processable(mmi.getType())) {
							valueInvoke.invoke(read, hashMap, mmi);
						}
					}
				}
			} catch (Exception e) {
				throw new JsqlSpliceException("创建Map异常", e);
			}
			return hashMap;
		}

	}

	public class AbstractTableHead implements TableHead {

		List<Head> headList;

		public AbstractTableHead() throws SQLException {
			super();
			List<SqlField> fieldList = crud.getFieldList();
			headList = new ArrayList<>(fieldList.size());
			int size = fieldList.size();
			for (int i = 0; i < size; i++) {
				SqlField     sqlField = fieldList.get(i);
				AbstractHead ah       = new AbstractHead(sqlField, i + 1, metaData);
				headList.add(ah);
			}
		}

		@Override
		public List<Head> getValues() {
			return headList;
		}

		@Override
		public int size() {
			try {
				return metaData.getColumnCount();
			} catch (SQLException e) {
				return 0;
			}
		}

		/**
		 * 表头数据信息
		 * 
		 * @author junjie
		 *
		 */
		public class AbstractHead implements Head {

			/**
			 * 
			 */
			final SqlField field;

			/**
			 * 下标位置
			 */
			final int index;

			final String columnName;
			final String columnLabel;

			public AbstractHead(SqlField field, int index, ResultSetMetaData metaData) throws SQLException {
				super();
				this.field = field;
				this.index = index;
				columnName = metaData.getColumnName(index);
				columnLabel = Jut.str.retainLetter(metaData.getColumnLabel(index)).toLowerCase();

			}

			@Override
			public String as() {
				return columnLabel;
			}

			@Override
			public int index() {
				return index;
			}

			@Override
			public String field() {
				return columnLabel;
			}

			@Override
			public Class<?> type() {
				return field.getType();
			}

			@Override
			public SqlField select() {
				return field;
			}

		}

		@Override
		public int getCol(String name) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	@Override
	public TableListValue getRowList(int row) {
		if (row > rowCount || row < 0) {
			throw new IndexOutOfBoundsException(" 行应当在 1~" + rowCount + "之间,但给定的是:" + row);
		}
		return new AbstractTableListValue(row);
	}

}
