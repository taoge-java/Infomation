package com.information.utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import com.information.constant.WeiXinConstant;


/**
 * 摘要算法工具类
 * @author zengjintao
 * 2017年3月17号上午11:40
 */
public class EncryptUtil {

	/**
	 * DES加密算法
	 */
	public static void encodeDES(String des){
		//生成key
		try {
			KeyGenerator keygenerator=KeyGenerator.getInstance("DES");
			keygenerator.init(56);
			SecretKey secretkey = keygenerator.generateKey();
			byte[] bytes=secretkey.getEncoded();

			//key转换
		    DESKeySpec desedekeyspec=new DESKeySpec(bytes);
			SecretKeyFactory factory=SecretKeyFactory.getInstance("DES");
		    Key key=factory.generateSecret(desedekeyspec);
				   
		    //加密
		    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5padding");
		    cipher.init(Cipher.ENCRYPT_MODE,key);
		    byte[] result= cipher.doFinal(des.getBytes());
		    System.out.println(Hex.encodeHexString(result));
		    
		    //解密
		    cipher.init(Cipher.DECRYPT_MODE, key);
		    result=cipher.doFinal(result);
		    System.out.println("解密"+new String(result));
		   // return  Hex.encodeHexString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * des算法解密
	 * @param key
	 */
	public static void decodeDES(String key){
		
	}
	
	/**
	 * 3重des加密算法
	 */
	
	public static void getJDK3des(String des){
		//生成key
		try {
			KeyGenerator keygenerator=KeyGenerator.getInstance("DESede");
			keygenerator.init(168);
			SecretKey secretkey = keygenerator.generateKey();
			byte[] bytes=secretkey.getEncoded();

			//key转换
		    DESedeKeySpec desedekeyspec=new DESedeKeySpec(bytes);
			SecretKeyFactory factory=SecretKeyFactory.getInstance("DESede");
		    Key key=factory.generateSecret(desedekeyspec);
				   
		    //加密
		    Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5padding");
		    cipher.init(Cipher.ENCRYPT_MODE,key);
		    byte[] result= cipher.doFinal(des.getBytes());
		    System.out.println(Hex.encodeHexString(result));
		    
		    //解密
		    cipher.init(Cipher.DECRYPT_MODE, key);
		    result=cipher.doFinal(result);
		    System.out.println("解密"+new String(result));
		   // return  Hex.encodeHexString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/***
	 * AES加密算法
	 */
	public static void getAES(String aes){
		//生成key
		try {
			KeyGenerator keygenerator=KeyGenerator.getInstance("AES");
			keygenerator.init(128);
			SecretKey secretkey=keygenerator.generateKey();
			byte[] bytes=secretkey.getEncoded();
		
		//key转换
			Key key=new SecretKeySpec(bytes, "AES");
		//加密
			Cipher cipher=	Cipher.getInstance("AES/ECB/PKCS5padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result=cipher.doFinal(aes.getBytes());
			 System.out.println(Hex.encodeHexString(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * PBE加密算法
	 */
	
	public static void getPBE(String pbe){
		SecureRandom securerandom =new SecureRandom();
	    byte[] bytes=securerandom.generateSeed(8);
	    
	    //口令密钥
	    String password="hello";
	    PBEKeySpec pbekeyspec =new PBEKeySpec(password.toCharArray());
	    try {
			SecretKeyFactory factory=SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			Key key=factory.generateSecret(pbekeyspec);
			
			//加密
			PBEParameterSpec  pbeparameterspec=new PBEParameterSpec(bytes,100);
			Cipher cipher=	Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key,pbeparameterspec);
			byte[] result=cipher.doFinal(pbe.getBytes());
			System.out.println(Hex.encodeHexString(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * SHA摘要算法
	 * @throws NoSuchAlgorithmException 
	 */
	public static String getSHA(String key) throws NoSuchAlgorithmException{
		MessageDigest sha=MessageDigest.getInstance("SHA");
		byte[] by=sha.digest(key.getBytes());
	    return Hex.encodeHexString(by);
	}
	
	/**
	 * 
	 * @param salt 可以是时间戳
	 * @return
	 */
	public static String encodeSalt(String salt){
		return DigestUtils.sha1Hex(salt);
	}
	
	/**
	 * mac消息摘要算法
	 */
	public static String getMac(String macStr){
		try {
			KeyGenerator keygenerator=KeyGenerator.getInstance("HmacMD5");//初始化密钥
			SecretKey secretkey=keygenerator.generateKey();
			byte[] key= secretkey.getEncoded();//获得密钥
			
			SecretKey secretkeySpec=new SecretKeySpec(key, "HmacMD5");
			Mac mac=Mac.getInstance(secretkeySpec.getAlgorithm());
			mac.init(secretkeySpec);
			byte[] hmac=mac.doFinal(macStr.getBytes());
			return Hex.encodeHexString(hmac);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 参数校验
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public  static String checkSignature(String timestamp,String nonce){
		String str[]={WeiXinConstant.TOKEN, timestamp, nonce};
		Arrays.sort(str);
		StringBuilder sb=new StringBuilder();
		for(String s:str){
			sb.append(s);
		}
		return getSHA1(sb.toString());
	}
	/**
	 * SHA加密算法
	 * @param key
	 * @return
	 */
	public static String getSHA1(String key){
		try {
	      MessageDigest digest=MessageDigest.getInstance("SHA");
	      byte[] bytes=digest.digest(key.getBytes());
	      //将字节数组转换成16进制字符串
	      return org.apache.commons.codec.binary.Hex.encodeHexString(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * md5加密算法
	 * @param key
	 * @return
	 */
	public static String getMD5(String key){
		try {
		    MessageDigest digest=MessageDigest.getInstance("MD5");
		    byte[] bytes=digest.digest(key.getBytes());
		      //将字节数组转换成16进制字符串
	        return org.apache.commons.codec.binary.Hex.encodeHexString(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
