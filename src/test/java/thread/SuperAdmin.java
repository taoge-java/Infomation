package com.thread;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.JdbcUtil;

public class SuperAdmin extends Thread{

	private Operation o=new Operation();
	@Override
	public void run() {
		Connection conn=JdbcUtil.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select amount from user_amount where id="+1);
			double money=0;
			while(rs.next()){
				 money=rs.getDouble("amount");
			}
			double after_money=money-100;
			o.operation("update user_amount set amount="+after_money+"", st);
		} catch (SQLException e) {
			System.out.println("SuperAdmin操作失败");
			e.printStackTrace();
		}
		System.out.println("SuperAdmin操作成功");
	}
	
}
