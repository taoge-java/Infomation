package com.information.kit;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.information.utils.StrKit;


public class TableBeanBuilder {
	
	public static List<String> getTableColumnNames(DatabaseMetaData databaseMetaData,ResultSet resultSet){
		return null;
	}
	
	public static Map<String, Object[]> getTableColumnNames(ResultSetMetaData metadate){
		Map<String,Object[]> map = new HashMap<>();
		try {
			int count = metadate.getColumnCount();
		    String[] columnNames = getColumnNames(metadate,count);
		    String[] typeNames = getTypeName(metadate,count);
		    Integer[] columnDisplaySize = getColumnDisplaySize(metadate,count);
		    map.put("columnNames", columnNames);
		    map.put("typeNames", typeNames);
		    map.put("columnDisplaySize", columnDisplaySize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	private static Integer[] getColumnDisplaySize(ResultSetMetaData metadate, int count) throws SQLException {
		Integer[] columnDisplaySize = new Integer[count];
		for (int i = 0; i < count; i++) {
			columnDisplaySize[i] = metadate.getColumnDisplaySize(i+1);
	    }
		return columnDisplaySize;
	}

	private static String[] getTypeName(ResultSetMetaData metadate, int count) throws SQLException {
		String[] typeName = new String[count];
		for (int i = 0; i < count; i++) {
			typeName[i] = metadate.getColumnTypeName(i+1);//列类型
	    }
		return typeName;
	}

	private static String[] getColumnNames(ResultSetMetaData metadate, int count) throws SQLException {
		String[] columnNames = new String[count];
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
	    }
		return columnNames;
	}
	
	public static String changeTbalesNameToClass(String tableName){
        String newTableName="";
		if(tableName.contains("_")){
			String tableNameArry[] = tableName.split("_");
			for (int i = 0; i < tableNameArry.length; i++) {
				newTableName += StrKit.totoUpperCaseFirst(tableNameArry[i]);
			}
			return newTableName;
		}
		return tableName;
	}
}
