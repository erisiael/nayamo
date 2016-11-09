package inserttext;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertDAO {
	Connection con = ConnectionManager.getConnection();

	public void insertText(String text) throws SQLException{
		//String t = text.trim();
		//System.out.println(t);
		String[] in = text.split(",");
		System.out.println(in[0]);
		PreparedStatement ps = null;
		try{
			for(int i = 0 ; i<in.length;i++){
				String sql = "insert into IT (name)values (?)";
				ps = con.prepareStatement(sql);
				ps.setString(1,in[i]);
				ps.executeUpdate();
			}
		}catch(java.sql.SQLException s){
			System.out.println("");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	}


}
