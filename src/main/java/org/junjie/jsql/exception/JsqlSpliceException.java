package org.junjie.jsql.exception;

import org.junjie.jsql.fragment.SqlFragment;

public class JsqlSpliceException extends RuntimeException {

	SqlFragment               fragment;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JsqlSpliceException() {
		super();
	}

	public JsqlSpliceException(String message) {
		super(message);
	}

	public JsqlSpliceException(String string, SqlFragment fragment) {
		this(string);
		this.fragment = fragment;
	}

	public JsqlSpliceException(SqlFragment fragment,Throwable cause) {
		super(cause);
		this.fragment = fragment;
	}

	public JsqlSpliceException(String string, Exception e) {
		super(string, e);
	}
	
	

}
