package com.test;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;
import org.junit.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.information.kit.ModelKit;
import com.information.model.primary.system.SystemAdmin;
import com.information.utils.ClassScanner;
/**
 * 单元测试类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月22日上午8:24:13
 */
public class InformationTest {

	@Test
	public void testPackage(){
		String name=this.getClass().getSimpleName();
		String first=name.substring(0,1);
		System.out.println(first);
		System.err.println(this.getClass().getSimpleName().replace(first,first.toLowerCase()));
	}
	
	
	@SuppressWarnings("unused")
	@Test
	public void google(){
		long strt = System.currentTimeMillis();
		Injector injector = Guice.createInjector();
		SystemAdmin admin = injector.getInstance(SystemAdmin.class);
//		System.out.println(System.currentTimeMillis()-strt);
//		admin.test();
		
//	    Genie genie = Genie.create();
//	    
//	    SystemAdmin admin = genie.get(SystemAdmin.class);
	    
	    System.out.println(System.currentTimeMillis()-strt);
	}
	private static final String URL = "jdbc:mysql://localhost:3306/alone?serverTimezone=UTC&characterEncoding=utf8";
	private static final String USER = "root";
	private static final String PASS = "123456";
	
	@Test
	public  void  createJava() {
		C3p0Plugin c3p0=new C3p0Plugin(URL, USER, PASS);
		ModelKit modelKit = new ModelKit("cn.model.test",c3p0.getDataSource());
		modelKit.create();
	}
	
	@Test
	public void fileTest() throws IOException{
		Connection connection = JdbcUtil.getConnection();
		try {
			DatabaseMetaData  databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getTables(null, null, null, null);
			while (resultSet.next()) {
				System.err.println(resultSet.getString("TABLE_NAME"));;
			}
		}catch(Exception e){
			e.printStackTrace();
		} 
		File file = new File("D:\\JavaWeb\\Information\\src\\main\\java\\cn\\model\\test\\");
		if (!file.exists()) {
			file.mkdirs();
		}
		File file2 = new File(file, "2.txt");
		if(!file2.exists()){
			file2.createNewFile();
		}
		String string="dsds.";
		System.out.println(string.contains("a"));
	}
	
	
	public String  getPath(){
		try {
			String path = this.getClass().getClassLoader().getResource("").toURI().getPath();
			return new File(path).getAbsolutePath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
	   List<Class> list = ClassScanner.scanClass(false);
	   //List<Class> list=	ClassScanner.scanClassByAnnotation(Autowired.class, false);
	   System.out.println(list.size());
	}
}
