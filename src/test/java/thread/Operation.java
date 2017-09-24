package thread;

import java.sql.SQLException;
import java.sql.Statement;

public class Operation {
	public synchronized void operation(String sql,Statement st){
		try {
			st.execute(sql);
			System.out.println("操作成功");
		} catch (SQLException e) {
			System.out.println("操作失败");
			e.printStackTrace();
		}
	}
}
