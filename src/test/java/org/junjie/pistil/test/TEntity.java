package org.junjie.pistil.test;

import org.junjie.jsql.annotation.crud.Group;
import org.junjie.jsql.annotation.crud.Select;
import org.junjie.jsql.entity.Entity;

import lombok.Data;

@Data
public class TEntity implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Select( group = B.class)
	private String value;
	
	@Select( group = B.class)
	private int age;
	
	private String name;
	

	public class A extends Group {
	}

	public class B extends Group {
	}

}
