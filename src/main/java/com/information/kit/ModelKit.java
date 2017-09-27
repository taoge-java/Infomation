package com.information.kit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.google.common.collect.Lists;
import com.information.utils.StrKit;
import com.jfinal.kit.PathKit;

/**
 * model类生成工具类
 * @author zengjintao
 *  @version 1.0
 * @create_at 2017年8月22日
 */
public class ModelKit {

	private String packeName;
	
	private DataSource dataSource;
	
	public static final String DEFAULT="/src/main/java/";
	
	private static final List<String> tables = Lists.newArrayList();
	
	public ModelKit(String packeName){
		this.packeName = DEFAULT + packeName;
	}
	
	public ModelKit(String packeName,DataSource dataSource){
		this.packeName = DEFAULT + packeName;
		this.dataSource = dataSource;
	}
	
	public ModelKit(String src,String packeName){
		this.packeName = src + packeName;
	}
	
	public void create(){
		long start = System.currentTimeMillis();
		try {
			Connection connection = dataSource.getConnection();
			DatabaseMetaData  databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getTables(null, null, null, null);
			while (resultSet.next()) {
				tables.add(resultSet.getString("TABLE_NAME"));
			}
			for (Iterator<String> iterator = tables.iterator(); iterator.hasNext();) {
				String tableName = iterator.next();
				PreparedStatement pt= connection.prepareStatement("select * from "+tableName);
			    ResultSetMetaData metadate=  pt.getMetaData();
			    int count = metadate.getColumnCount();
			    String[] columnNames = new String[count];
			    String[] typeName = new String[count];
			    int[] columnDisplaySize = new int[count];
		        for (int i = 0; i < count; i++) {
		        	String columnName = metadate.getColumnName(i+1);
		        	if(columnName.contains("_")){
		        		String[] columnNameArrays = columnName.split("_");
		        		columnName="";
		        		for (int j = 0; j < columnNameArrays.length; j++) {
		        			columnName += j == 0 ? columnNameArrays[j] : StrKit.totoUpperCaseFirst(columnNameArrays[j]);
						}
			      	    columnNames[i] = columnName;//列名
		        	}else{
		        		columnNames[i] = metadate.getColumnName(i+1);//列名
		        	}
			      	typeName[i] = metadate.getColumnTypeName(i+1);//列类型
			      	columnDisplaySize[i] = metadate.getColumnDisplaySize(i+1);
			    }
		        String newTableName = getNewTabelName(tableName);
				String context = createModel(columnNames,typeName,columnDisplaySize,newTableName);
				generModel(context,newTableName);
		    }
			long end = System.currentTimeMillis();
			System.out.println("生成model成功,耗时"+(end-start)+"ms");
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("生成model异常");
		}
	}

	/**
	 * 将类写入文件
	 * @param context
	 */
	private  void generModel(String context,String tableName) {
		FileWriter filterWriter = null;
		try {
			String filePath = PathKit.getWebRootPath()+ packeName.replace(".", "\\");
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdirs();
			}
			File source = new File(file,StrKit.totoUpperCaseFirst(tableName)+ ".java");
			if(!source.exists()){
				source.createNewFile();
			}
			filterWriter = new FileWriter(source);
			filterWriter.write(context);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				filterWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private  String createModel(String[] columnName, String[] typeName,int[] columnDisplaySize,String tableName) {
		StringBuffer stringBuffer = new StringBuffer();
		StringBuffer packBuffer = new StringBuffer();
		generPackage(packBuffer);
		stringBuffer.append("public class  "+StrKit.totoUpperCaseFirst(tableName)+"{\n\n");
		generFiled(stringBuffer,columnName,columnDisplaySize,typeName);
		for (int i = 0; i < columnName.length; i++) {
			stringBuffer.append("\tpublic void set"+StrKit.totoUpperCaseFirst(columnName[i])+"("+getType(typeName[i],columnDisplaySize[i])+" "+columnName[i]+"){");
			stringBuffer.append("\n\t\t"+"this."+columnName[i]+" = "+columnName[i]+";\n\t}\n\n");
			stringBuffer.append("\tpublic  "+getType(typeName[i],columnDisplaySize[i])+"  get"+StrKit.totoUpperCaseFirst(columnName[i])+"(){");
			stringBuffer.append("\n\t\t"+"return\t"+columnName[i]+";\n\t}\n");
		}
		List<String> backTypeName = Arrays.asList(typeName);
		if(backTypeName.contains("DATETIME")){
			packBuffer.append("import java.util.Date;\n\n");
		}
		if(backTypeName.contains("DECIMAL")){
			packBuffer.append("import java.math.BigDecimal;\n");
		}
		stringBuffer.append("}");
		return  packBuffer.toString() + stringBuffer.toString();
	}

	/**
	 * 生成属性
	 * @param stringBuffer
	 * @param columnName
	 * @param typeName
	 */
	private void generFiled(StringBuffer stringBuffer, String[] columnName,int[] columnDisplaySize, String[] typeName) {
		for (int i = 0; i < columnName.length; i++) {
			stringBuffer.append("\tprivate  "+getType(typeName[i],columnDisplaySize[i])+"  "+columnName[i]+";\n\n");
		}
	}

	private void generPackage(StringBuffer packBuffer) {
		packBuffer.append("package "+packeName.replace("/src/main/java/","")+";\n\n");
	}

	/**
	 * 获取数据库表字段类型
	 * @param typeName
	 * @return
	 */
	private  String  getType(String typeName,int size) {
		if(typeName.equals("VARCHAR")||typeName.equals("NVARCHAR")){
			return "String";
		}else if(typeName.equals("DATETIME")){
			return "Date";  
		}else if(typeName.equals("MONEY")){
			return "double";  
		}else if(typeName.equals("INT")){
			return "int";
		}else if(typeName.equals("TINYINT")||typeName.equals("SMALLINT")){
			if(size == 1){
				return "boolean";
			}
			return "int";
		}else if(typeName.equals("DECIMAL")){
			return "BigDecimal";
		}else  if(typeName.equals("FLOAT")){
			return "float";
		}else  if(typeName.equals("DOUBLE")){
			return "double";
		}else if(typeName.equals("BIGINT")){
			return "long";
		}
		return null;
	}
	
	private String getNewTabelName(String tableName){
		if(tableName.contains("_")){
			String[] tableNameArry = tableName.split("_");
			String newTableName="";
			for (int i = 0; i < tableNameArry.length; i++) {
				newTableName += StrKit.totoUpperCaseFirst(tableNameArry[i]);
			}
			return newTableName;
		}
		return tableName;
	}
}
