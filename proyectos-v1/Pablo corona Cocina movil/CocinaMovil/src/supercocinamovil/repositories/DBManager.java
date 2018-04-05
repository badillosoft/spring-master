package supercocinamovil.repositories;

import java.sql.*;

/**
 *
 * @author Pablo
 */
public class DBManager {
    
   private static Connection conn = null;
    
    public static void connect(String url, String user, String password){
        try {
            DBManager.conn = DriverManager.getConnection(url, user , password);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
    
    public static PreparedStatement generateQuery(String query){
        try {
            PreparedStatement st = DBManager.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            return st;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }     
    }
    
    public static int executeInsert(PreparedStatement st){
        try {
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            
        }
        return -1;
    }
}
