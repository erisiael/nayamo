package inserttext;

import java.io.BufferedReader; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class TakeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("src/inserttext/qqq.txt"));
		StringBuilder contentReceiver = new StringBuilder();
		char[] buf = new char[1024];
		
		while(bufferedReader.read(buf)>0){
			contentReceiver.append(buf);
			
		}
			System.out.println(contentReceiver.toString());
			
			InsertDAO dao = new InsertDAO();
			dao.insertText(contentReceiver.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
			
	}

}
