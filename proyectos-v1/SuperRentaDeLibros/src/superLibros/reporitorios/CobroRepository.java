/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superLibros.reporitorios;

import RentaLibros.data.CobroData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zaira
 */
public class CobroRepository {
    public static void insertar(CobroData cobro) throws SQLException{
        PreparedStatement st= DBManager.generateQuery("INSERT INTO cobro (total, token, completado) VALUES(?,?,?);");
        
        st.setDouble(1, cobro.getTotal());
        st.setString(2, cobro.getToken());
        st.setInt(3, cobro.getCompletado());
       
        int id= DBManager.executeInsert(st);
        cobro.setId(id);   
    }
    public static void actualizar(CobroData cobro)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("UPDATE cobro SET total = ?, token =?, completado =? WHERE id=?");
        st.setDouble(1, cobro.getTotal());
        st.setString(2, cobro.getToken());
        st.setInt(3, cobro.getCompletado());
        st.setInt(4, cobro.getId());
        st.executeUpdate();
    }
    
    public static void eliminar(CobroData cobro)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("DELETE FROM cobro  WHERE id=?");
        st.setInt(1, cobro.getId());
        st.executeUpdate();
    }
     
    public static CobroData buscarPorId(int id) throws SQLException{
            PreparedStatement st= DBManager.generateQuery("SELECT * FROM cobro  WHERE id=?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            CobroData cobro = new CobroData();
            cobro.setId(id);
            if(rs.next()){
                double total = rs.getDouble("total");
                String token = rs.getString("token");
                int completado = rs.getInt("completado");
                cobro.setId(id);
                cobro.setTotal(total);
                cobro.setToken(token);
                cobro.setCompletado(completado);
            }
            return cobro;
    }
    
    
}
