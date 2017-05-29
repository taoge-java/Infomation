package com.information.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.render.Render;
/**
 * 文件下载辅助类
 * @author Administrator
 *
 */
public class MyFileRender extends Render{
	
	private String file;
	
	private HttpServletResponse response;
	
	private InputStream is;
	

	public MyFileRender(String file, HttpServletResponse response, InputStream is) {
		this.file = file;
		this.response = response;
		this.is = is;
	}


	@SuppressWarnings("null")
	@Override
	public void render() {
		OutputStream out=null;
		try {
			response.addHeader("Content-disposition","attachment;fileName="+ new String(file.getBytes("GBK"), "ISO8859-1") );
			response.setContentType("octets/stream");
			out=response.getOutputStream();
	        byte[] buffer=new byte[1024];
	        int length=1;
	        if((length=is.read(buffer, 0, 1024))!=-1){
	        	out.write(buffer, 0, length);
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(is!=null){
				try {
					is=null;
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					out=null;
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
