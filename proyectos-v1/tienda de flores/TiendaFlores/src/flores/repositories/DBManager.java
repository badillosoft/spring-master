package flores.repositories;

import java.sql.*;


public class DBManager {
	
private static Connection  conn = null;
	
	public static void connect(String url,String user,String password) {
		try {
			DBManager.conn = DriverManager.getConnection(url, user, password);
		} catch (Exception error) {
			// TODO: handle exception
			System.out.println(error.getMessage());
			
		}
	}
	public static PreparedStatement generateQuery (String query) {
		try {
			PreparedStatement st = DBManager.conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			return st;
		} catch (Exception error) {
			// TODO: handle exception
			System.out.println(error.getMessage());
			return null;
		}
	}
	
	public static int excuteInsert(PreparedStatement st) {
		try {
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception error) {
			// TODO: handle exception
			System.out.println(error.getMessage());
		}
		return -1;
	}

}
