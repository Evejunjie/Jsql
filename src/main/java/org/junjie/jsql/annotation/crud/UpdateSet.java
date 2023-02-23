package org.junjie.jsql.annotation.crud;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention( RUNTIME)
@Target( FIELD)
public @interface UpdateSet {
	public Update [] value();
}
