package file;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class FileUtils {
	
	@Test
	public void testInputStream() throws IOException {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			 fileInputStream = new FileInputStream("d:\\Tenant_admin.java");
			 fileOutputStream = new FileOutputStream("e:\\text.txt");
			 int flag=0;
			 byte[] bytes = new byte[1024];
			 while((flag=fileInputStream.read(bytes, 0, 1024)) != -1){
				fileOutputStream.write(bytes, 0, flag);
			 }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			fileOutputStream.close();
			fileInputStream.close();
		}
	}
	
	@Test
	public void testBuffer() throws IOException{
		BufferedReader reader= new BufferedReader(new FileReader("d:\\Tenant_admin.java"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("d:\\text.txt"));
		String content = null;
		while ((content =reader.readLine()) !=null) {
			writer.write(content+"\n");
		}
		writer.close();
		reader.close();
	}
}
