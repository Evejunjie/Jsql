package org.junjie.jsql.curd.standard;

/**
 * 
 * @author junjie
 *
 * @param <E>
 * @param <Return>
 */
public interface Deleted<E, Return> extends DataComparison<E, Return>, OrderBy<E, Return>
,Limit<Return> 
{



}
