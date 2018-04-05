/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supertourist.repositories;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class DbManager {
    private static Connection conn = null;
    
    public static void connect(String url, String user,String password){
        try{
            DbManager.conn = DriverManager.getConnection(url,user,password);
        } catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    public static PreparedStatement generateQuery(String query){
        try{
        PreparedStatement st = DbManager.conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        return st;
        }catch(SQLException err){
            System.out.println(err.getMessage());
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
        }
        return -1;
    }
}
