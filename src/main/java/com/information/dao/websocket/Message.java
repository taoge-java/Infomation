package com.information.dao.websocket;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;


public class Message {

private String welcome;
	
	private List<String> name;

	private Gson gson;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@SuppressWarnings("deprecation")
	public void setContent(String content,String name) {
		this.content =new Date().toLocaleString()+" "+name+":"+content+"</br>";
	}
	private String content;
	
	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public String tojson(){
		return gson.toJson(this);
	}
}
