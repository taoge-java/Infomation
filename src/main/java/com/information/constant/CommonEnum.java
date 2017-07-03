package com.information.constant;

public class CommonEnum {

	/**
	 * 性别
	 * @author Administrator
	 *
	 */
	public enum Gender {
		MAN(1, "男"), 
		WOMAN(0,"女");
		private String name;
		private int value;
		private Gender(int value,String name){
			this.name=name;
			this.value=value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
	}

	
	public enum LogType {
		
		LOGIN(1,"登录/登出"),
		DELETE(2,"删除日志"),
		MODIFY(3,"修改/创建");
		
		private LogType(int value,String name){
			this.value=value;
			this.name=name;
		}
		int value;
		
		String name;
		
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

	public enum Super {
		
		YSE(1,"是"),
		NO(0,"否");
		
		int value;
		
		String name;
		
		private  Super(int value,String name){
			this.value=value;
			this.name=name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public enum PathType{
		TARGET(2,"target/classes"),WEB_INF(1,"WEB-INF/classes"),;
	    
		int value;
		
		String name;
		
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		private PathType(int value,String name){
			this.value=value;
			this.name=name;
		}
	}
}
