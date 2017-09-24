package thread;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.test.JdbcUtil;


public class Admin extends Thread{
	private Operation o=new Operation();
	@Override
	public void run() {
		Connection conn=JdbcUtil.getConnection();
		try {
			Statement st=conn.createStatement();
			double money=0;
			ResultSet rs=st.executeQuery("select amount from user_amount where id="+1);
			while(rs.next()){
				 money=rs.getDouble("amount");
			}
			double after_money=money+50;
			o.operation("update user_amount set amount="+after_money+"", st);
			System.out.println("Admin操作成功");
		} catch (SQLException e) {
			System.out.println("Admin操作失败");
			e.printStackTrace();
		}
	}
}
