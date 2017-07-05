package com.information.dao.websocket;

import java.util.List;

import com.google.gson.Gson;
import com.information.utils.DateUtil;

public class Message {

    private String message;
	
	private List<String> name;

	private Gson gson;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setContent(String content,String name) {
		this.content =DateUtil.toLocaleString()+" "+name+":"+content+"</br>";
	}
	
	private String content;
	
	public String getMessage() {
		return message;
	}

	public void setWelcome(String message) {
		this.message = message;
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
