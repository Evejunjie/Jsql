package org.junjie.jsql.curd.result;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junjie.jsql.curd.result.abs.AbstractTableSet.AbstractTableReadInfo;
import org.junjie.jsql.fragment.SqlField;

/**
 * 查询结果集
 * 
 * @author junjie
 *
 */
public interface TableSet {

	/**
	 * 获取表头
	 * 
	 * @return
	 */
	public TableHead getHead();

	/**
	 * 返回当前行数据
	 * 
	 * @param row 当前
	 * @return
	 */
	public TableListValue getRowList(int row);

	/**
	 * 表头数据 通常是第一行
	 * 
	 * @author junjie
	 *
	 */
	public interface TableHead {

		public int size();

		/**
		 * 当前列数据 , 返回的是
		 * 
		 * @return
		 */
		public List<Head> getValues();

		/**
		 * 通过名称返回 所在 列, 不存在返回 0
		 * 
		 * @param name
		 * @return
		 */
		public int getCol(String name);

	}

	public interface Head {

		/**
		 * 别名名称
		 * 
		 * @return
		 */
		public String as();

		/**
		 * 下标位置
		 * 
		 * @return
		 */
		public int index();

		/**
		 * 有别名则是 别名
		 * 
		 * @return
		 */
		public String field();

		/**
		 * 实体类数据类型
		 * 
		 * @return
		 */
		public Class<?> type();

		/**
		 * @return
		 */
		public SqlField select();

	}

	public interface TableListValue {

		public TableSet getTableSet();

		/**
		 * 获取 当前行号
		 * 
		 * @return
		 */
		public int getRow();

		/**
		 * 获取总行号
		 * 
		 * @return
		 */
		public int getCol();

		/**
		 * 将当前的 数据转换为该实体类
		 * @param <E>
		 * @param clazz
		 * @return
		 */
		public <E> E toEntity(Class<E> clazz);
		
		/**
		 * 将数据转换为 Map 对象数据, 会通过查询类的返回值进行返回
		 * @param <E>
		 * @param clazz
		 * @return
		 */
		public Map<String,Object> toMap();
		
		/**
		 * 获取表头数据
		 * 
		 * @return
		 */
		public List<Head> getHead();

		/**
		 * 数据读取
		 * @return
		 * @throws SQLException
		 */
		public AbstractTableReadInfo read() throws SQLException;

	}


}
