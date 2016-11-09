package inserttext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String dbid = "hr";
	private static final String dbpw = "hr";
	

	//jdbc ����̹� �ε�
	static {
		try{
			Class.forName(driver); 
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private ConnectionManager() {}
		
	//Connection ��ü �����Ͽ� ����
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, dbid, dbpw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//Connection �ݱ�
	public static void close(Connection con) {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
