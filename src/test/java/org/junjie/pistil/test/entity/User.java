package org.junjie.pistil.test.entity;

import org.junjie.jsql.entity.Entity;

import lombok.Data;

@Data
public class User implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long   id;
	private int    age;
	private String name;
	private String sex;

}
