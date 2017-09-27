/**
 * 
 */
package com.information.dao;

import org.springframework.stereotype.Component;

import com.information.annotation.ConfigurationProperties;

/**
 * @author taoge
 * @version 1.0
 * @create_at 2017年9月14日上午9:48:27
 */
@ConfigurationProperties(prefix="girl.properties")
@Component
public class Girl {

	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
