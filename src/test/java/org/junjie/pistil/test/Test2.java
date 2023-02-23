package org.junjie.pistil.test;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class Test2 {
	
	public static void main(String[] args) {
		Method[] declaredFields = ResultSet.class.getDeclaredMethods();
		Set<String> arrayList = new HashSet<>(100);
		for (Method method : declaredFields) {
			if(method.getName().startsWith("get")) {
				if(method.getParameterCount()==1) {
					String name = method.getName();
					arrayList.add(name);
				}
				
			}
			
		}
		System.err.println(arrayList);
		
		
	}

}
