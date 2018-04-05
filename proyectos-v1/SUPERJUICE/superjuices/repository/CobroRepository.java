/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import superjuices.data.CobroData;

/**
 *
 * @author alejandro
 */
public class CobroRepository {
    
    //La funcion que nos permitira insertar un nuevo cliente
    public static void insertar(CobroData cobro) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" INSERT INTO cobros(total, token_paypal, completado) VALUES( ?, ?, ?); ");
        
        st.setInt(1, cobro.getTotal());
        st.setString(2, cobro.getToken_paypal());
        st.setShort(3, (short)cobro.getCompletado());
        
        st.executeUpdate();
        
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            cobro.setId(rs.getInt(1));
        }
        
        System.out.println("Se ha insertado un nuevo cobro con ID:"+cobro.getId());
        
    }
    
    public static void actualizar(CobroData cobro) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" UPDATE cobros SET total=?, token_paypal=?, completado=? WHERE id=?; ");
        
        st.setInt(1, cobro.getTotal());
        st.setString(2, cobro.getToken_paypal());
        st.setShort(3, cobro.getCompletado());
        
        st.setInt(4, cobro.getId());
        
        st.executeUpdate();
        
        System.out.println("Se actualizó la informacion de un cobro con ID:"+cobro.getId());
    }
    
    public static void eliminar(CobroData cobro) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" DELETE FROM cobros WHERE id=?; ");
        
        st.setInt(1, cobro.getId());
        
        st.executeUpdate();
        
        System.out.println("Se eliminó cobro con ID:"+cobro.getId());
    }
    
    public static CobroData buscarById(int id) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" SELECT * FROM cobros WHERE id=?; ");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        CobroData cobro = new CobroData();
        cobro.setId(id);
        
        if(rs.next()) {
            cobro.setTotal(rs.getInt("total"));
            cobro.setToken_paypal(rs.getString("token_paypal"));
            cobro.setCompletado(rs.getShort("completado"));
        }
        
        return cobro;
        
    }
    
}
