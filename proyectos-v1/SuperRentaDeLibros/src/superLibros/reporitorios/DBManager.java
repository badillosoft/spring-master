
package superLibros.reporitorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author zaira
 */
public class DBManager {
    private static Connection con = null;
    public static void connect(String url, String user, String password){
        try{
            DBManager.con = DriverManager.getConnection(url,user,password);
        }catch(SQLException err){
           System.out.println(err.getMessage());
           err.printStackTrace();
       }
    }
    
    
    public static PreparedStatement generateQuery(String query){
        try{
            PreparedStatement st = DBManager.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            return st;
        }catch(SQLException err){
           System.out.println(err.getMessage());
           err.printStackTrace();
           return null;
       }
    }
    public static int executeInsert(PreparedStatement st){
      try{
          st.executeUpdate();
          ResultSet rs = st.getGeneratedKeys();
          if(rs.next()){
                return rs.getInt(1);
            }         
        }catch(SQLException err){
           System.out.println(err.getMessage());
           err.printStackTrace();
       } 
      return -1;
    }
}
