/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alejandro
 */
public class DBManager {
    
    //Creamos la variable global para la conexion
    private static Connection conn = null;
    
    //Creamos el metodo para conectanor a nuestra bae de datos
    public static void connect(String url, String user, String pass) {
        try {
            DBManager.conn = DriverManager.getConnection(url, user, pass);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Una funcion para realizar los query necesarios;
    public static PreparedStatement myQuery(String query) {
        try {
            PreparedStatement st = DBManager.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            return st;
        } catch(SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
    
}
