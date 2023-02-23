package org.junjie.jsql.annotation.crud;

import lombok.AllArgsConstructor;

/**
 * 
 * 使用实例
 * <pre>
 * public class T extends Group{
	
	// 使用 Between 需要指定值的大小
	Between date = new Between("1","2");
}
 * </pre>
 * 
 * @author junjie
 *
 */
@AllArgsConstructor
public class BetweenValue{
	Object max; 
	Object min;
}	
