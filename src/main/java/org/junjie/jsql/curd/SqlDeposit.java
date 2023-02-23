package org.junjie.jsql.curd;

import java.time.chrono.ChronoLocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junjie.jsql.fragment.SqlFragment;
import org.junjie.jsql.fragment.SqlParam;

/**
 * SQL 寄存, 用于存储写入的sql 片段,参数等数据信息
 * 
 * @author junjie
 *
 */
public interface SqlDeposit {

	/**
	 * 写入参数
	 * 
	 * @param value
	 */
	public void writeParam(int value);

	public void writeParam(Number value);

	public void writeParam(String value);

	public void writeParam(Date value);

	public void writeParam(Object value);

	public void writeParam(ChronoLocalDate value);

	public void writeParam(Collection<Object> value);

	/**
	 * 写sql 片段
	 * 
	 * @param sql
	 */
	public void writeSql(CharSequence sql);

	/**
	 * 写入单个字符, 对 符号很有帮助
	 * 
	 * @param sql
	 */
	public void writeSql(char sql);

	/**
	 * 写入SQL 片段,
	 * 
	 * @param fragment 写入的片段代码 如果是 {@link SqlParam} 会进行参数写入
	 */
	public void write(SqlFragment fragment);

	/**
	 * 写入一个数字
	 * 
	 * @param value
	 */
	public void writeSqlNumber(Number value);

	/**
	 * 这个与 写入参数不一样, 如果是字符串 会进行转义 和 加上引号,
	 * 
	 * @param value
	 */
	public void writeSqlValue(Object value);

	/**
	 * 写入一个 AS , 如果数据库支持
	 */
	public void writeAS();

	/**
	 * 使用[`] 反引号进行 字符串包裹
	 * 
	 * @param sql
	 */
	public void writeSqlBackquote(CharSequence sql);

	/**
	 * 写入一个别名, 会写入 AS(数据库支持) , 会进行反引号包裹
	 * 
	 * @param sql
	 */
	default void writeAS(CharSequence sql) {
		writeAS();
		writeSqlBackquote(sql);
	}

	/**
	 * 表字段, 如果标有别名 会是 别名.`字段名`
	 * 
	 * @param dbfield
	 */
	public void writeSqlTableField(String dbfield);

	/**
	 * 输出当前的sql
	 * 
	 * @return
	 */
	public String outputSql();
	
	/**
	 * 返回所有写入的参数
	 * @return
	 */
	public List<Object> getParams(); 
	
	
	/**
	 * 获取一个片段写入 , 只用于 sql 写入 不会包含参数数据
	 * @return
	 */
	public SqlDeposit getSqlFragment();

}
