package org.junjie.jsql.entity;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.extern.java.Log;

/**
 * 类扫描
 * 
 * @author junjie
 *
 */
@Log
public class SqlEntityScanning {

	/**
	 * 需要扫描的 类
	 */
	Set<Package> scannPackage = new HashSet<>();

	/**
	 * 执行扫描
	 * 
	 * @return 返回扫描的结果
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public List<Class<?>> scann() {
		List<Map.Entry<Package, File>> classFile = new ArrayList<>(scannPackage.size() * 3 / 2);
		for (Package pack : scannPackage) {
			Enumeration<URL> e = null;
			try {
				e = ClassLoader.getSystemResources(pack.getName().replaceAll("\\.", "/"));
			} catch (IOException e1) {
				e1.printStackTrace();
				log.warning("扫包失败:" + e1.getMessage());
				continue;
			}

			// -> 循环读取第一个文件
			while (e.hasMoreElements()) {
				URL url = e.nextElement();

				File file = new File(url.getPath());
				classFile.add(Map.entry(pack, file));
			}
		}
		LinkedList<Class<?>> linkedList = new LinkedList<>();

		// -> 过滤
		Stream<Map.Entry<Package, File>> stream = classFile.stream();
		stream.forEach(entity -> {
			Package key    = entity.getKey();
			File    value  = entity.getValue();
			int     length = value.getPath().length();
			length = length - key.getName().length()-1;
			searchClass(value, length, (f) -> {
				// -> 必须 是 Entity 实例
				return Entity.class.isAssignableFrom(f);
			}, linkedList);
		});
		return linkedList;
	}

	public void searchClass(File file, int length, Predicate<Class<?>> isClass, List<Class<?>> list) {
		File[]   listFiles = file.listFiles();
		Class<?> forName;
		for (File classFile : listFiles) {
			if (classFile.isFile() && classFile.getName().endsWith(".class")) {
				String classPath = classFile.getPath();
				String className = classPath.substring(length + 1, classPath.length() - 6).replace('/', '.')
						.replace('\\', '.');
				try {
					forName = Class.forName(className);
					if (isClass.test(forName)) {
						list.add(forName);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				searchClass(classFile, length, isClass, list);
			}
		}
	}

	public File filter(File catalogue, Predicate<File> isFile) {
		File[] listFiles = catalogue.listFiles();
		for (File file : listFiles) {
			if (file.isFile()) {
				if (isFile.test(catalogue)) {
					return file;
				}
			} else {
				File filter = filter(file, isFile);
				if (filter != null) {
					return filter;
				}
			}
		}
		return null;
	}

	public void addPackage(Package pack) {
		scannPackage.add(pack);
	}

}
