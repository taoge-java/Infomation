package com.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.information.utils.PackageUtil;

/**
 * 单元测试类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月22日上午8:24:13
 */
public class InformationTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testPackage(){
//		List object=PackageUtil.scanPackage("com.information.dao","WEB-INF/classes", false);
//		System.out.println(object);
//		List list=new ArrayList<>();
//		list.add("com.information.dao");
//		list.add("com.information.controller");
//		List object1=PackageUtil.scanPackage(list,"WEB-INF/classes",false);
//		System.out.println(object1);

		String name=this.getClass().getSimpleName();
		String first=name.substring(0,1);
		System.out.println(first);
		System.err.println(this.getClass().getSimpleName().replace(first,first.toLowerCase()));
	}
}
