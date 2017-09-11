package com.information.utils;

import java.math.BigDecimal;

/** 
 * @author zengjintao 
 * @version 创建时间：2016年9月9日 下午2:42:19 
 * 金额格式化 
 */
public class NumberTools {

	public String toNumber(BigDecimal num){
		if(num.doubleValue() == 0d){
			return "0";
		}
		return num.stripTrailingZeros().toPlainString();
	}
	
	public String toNumber(Object num){
		if(num==null){
			return "";
		}
		return BigDecimal.valueOf(Double.parseDouble(num.toString()))  
	            .stripTrailingZeros().toPlainString();
	}
	
	public String toNumber(double num){
		if( num == 0d){
			return "0";
		}
		return new BigDecimal(num).stripTrailingZeros().toPlainString();
	}
	
	//0不显示
	public String toNonZeroNumber(Object num){
		if(num==null){
			return "";
		}
		if(Double.parseDouble(num.toString()) == 0d){
			return "";
		}
		return BigDecimal.valueOf(Double.parseDouble(num.toString()))  
	            .stripTrailingZeros().toPlainString();
	}
	
	public String toNumber(Object num, Object zeroShow){
		if(num == null){
			return "";
		}
		if(Double.parseDouble(num.toString()) == 0d){
			if(zeroShow == null){
				return "0";
			}
			if(zeroShow.equals(true)){
				return "";
			}
			return "0";
		}
		return BigDecimal.valueOf(Double.parseDouble(num.toString()))  
	            .stripTrailingZeros().toPlainString();
	}
	
}
