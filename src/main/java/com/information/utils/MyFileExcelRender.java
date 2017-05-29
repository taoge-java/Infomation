package com.information.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jfinal.render.Render;

@SuppressWarnings("unused")
public class MyFileExcelRender extends Render{
	/**
	 * excel文件导出工具类
	 */
	private String file;
	private HttpServletResponse response;
	private  HSSFWorkbook  work;
	public MyFileExcelRender(String file, HttpServletResponse response, HSSFWorkbook work) {
	
		this.file = file;
		this.response = response;
		this.work = work;
	}
	@Override
	public void render() {
		try {
			response.addHeader("Content-disposition", "attachment; filename="+ new String(file.getBytes("GBK"), "ISO8859-1"));
			response.setContentType("octets/stream");
			OutputStream out = response.getOutputStream();
			work.write(out);  
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
