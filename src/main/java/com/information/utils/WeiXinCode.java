package com.information.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成二维码
 * @author Administrator
 *
 */
public class WeiXinCode {
	//二维码格式
	 private static String format=".png";
	//二维码宽度
	 private static final int WIDTH=300;
	//二维码高度
	 private static final int HEIGHT=300;
	
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public static void getCodeImage(String content,Path path){
		 
		 HashMap hint=new HashMap<>();
		 hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
		 hint.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);
		 hint.put(EncodeHintType.MARGIN, 2);
		 try {
			 BitMatrix bitMatrix =new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
		     try {
				MatrixToImageWriter.writeToPath(bitMatrix, format, path);
			} catch (IOException e) {
				e.printStackTrace();
			}//生成二维码
		 } catch (WriterException e) {
			e.printStackTrace();
		}
	 }
}
