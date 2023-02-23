package org.junjie.jsql.fragment.symbol;

import org.junjie.jsql.curd.SqlDeposit;
import org.junjie.jsql.fragment.SqlFragment;

/**
 * SQL 符号, 符号有
 * <ul>
 * <li>=</li>
 * <li>!= 或者 &lt;&gt;</li>
 * <li>&gt;</li>
 * <li>&lt;</li>
 * <li>&lt;=</li>
 * <li>&gt;=</li>
 * </ul>
 * 
 * @author junjie
 *
 */
public class SqlMatchSymbol implements SqlFragment {

	public static final SqlMatchSymbol EQ = new SqlMatchSymbol(" = ");
	public static final SqlMatchSymbol NE = new SqlMatchSymbol(" <> ");
	public static final SqlMatchSymbol GT = new SqlMatchSymbol(" > ");
	public static final SqlMatchSymbol GE = new SqlMatchSymbol(" >= ");
	public static final SqlMatchSymbol LT = new SqlMatchSymbol(" < ");
	public static final SqlMatchSymbol LE = new SqlMatchSymbol(" <= ");

	public static final SqlMatchSymbol L_B = new SqlMatchSymbol("(");

	public static final SqlMatchSymbol R_B = new SqlMatchSymbol(")");

	/**
	 * 比对符号
	 */
	private final String symbol;
	
	public SqlMatchSymbol(String symbol) {
		super();
		this.symbol = symbol;
	}

	@Override
	public void writeFragment(SqlDeposit deposit) {
		deposit.writeSql(symbol);
	}
}
