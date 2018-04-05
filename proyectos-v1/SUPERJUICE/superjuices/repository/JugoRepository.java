/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import superjuices.data.JugoData;

/**
 *
 * @author alejandro
 */
public class JugoRepository {
    
    //La funcion que nos permitira insertar un nuevo cliente
    public static void insertar(JugoData jugo) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" INSERT INTO jugos(sabor, tam, precio) VALUES( ?, ?, ?); ");
        
        st.setString(1, jugo.getSabor());
        st.setString(2, jugo.getTam());
        st.setInt(3, jugo.getPrecio());
        
        st.executeUpdate();
        
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            jugo.setId(rs.getInt(1));
        }
        
        System.out.println("Se ha insertado un nuevo sabor de jugo:"+jugo.getSabor());
        
    }
    
    public static void actualizar(JugoData jugo) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" UPDATE jugos SET sabor=?, tam=?, precio=? WHERE id=?; ");
        
        st.setString(1, jugo.getSabor());
        st.setString(2, jugo.getTam());
        st.setInt(3, jugo.getPrecio());
        
        st.setInt(4, jugo.getId());
        
        st.executeUpdate();
        
        System.out.println("Se actualizó la informacion de un jugo con ID:"+jugo.getId());
    }
    
    public static void eliminar(JugoData jugo) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" DELETE FROM jugos WHERE id=?; ");
        
        st.setInt(1, jugo.getId());
        
        st.executeUpdate();
        
        System.out.println("Se eliminó jugo con ID:"+jugo.getId());
    }
    
    public static JugoData buscarById(int id) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" SELECT * FROM jugos WHERE id=?; ");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        JugoData jugo = new JugoData();
        jugo.setId(id);
        
        if(rs.next()) {
            jugo.setSabor(rs.getString("sabor"));
            jugo.setTam(rs.getString("tam"));
            jugo.setPrecio(rs.getInt("precio"));
        }
        
        return jugo;
        
    }
    
}
