package org.junjie.jsql.annotation.crud;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * having , 当有的字段不存在表中时
 * 
 * @author junjie
 *
 */
@Target( ElementType.FIELD)
@Retention( RetentionPolicy.RUNTIME)
public @interface Having {
}