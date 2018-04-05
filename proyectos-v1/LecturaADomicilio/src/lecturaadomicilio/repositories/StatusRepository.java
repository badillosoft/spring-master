/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lecturaadomicilio.data.StatusData;

/**
 *
 * @author Pablo
 */
public class StatusRepository {
    public static void insertar(StatusData estatus) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO estatus (descripcion) VALUES (?);");
        
        st.setString(1, estatus.getDescripcion());
        
        int id = DBManager.executeInsert(st);
        
        estatus.setId(id);
    }
    
    public static void actualizar(StatusData estatus) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE estatus SET descripcion=? WHERE id=?;");
        
        st.setString(1, estatus.getDescripcion());
        st.setInt(2, estatus.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(StatusData estatus) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM estatus WHERE id=?;");
        
        st.setInt(1, estatus.getId());
        
        st.executeUpdate();
    }
    
    public static StatusData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM estatus WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        StatusData estatus = new StatusData();
        estatus.setId(id);
        
        if (rs.next()) {
            
            String descripcion = rs.getString("descripcion");
                    
            estatus.setDescripcion(descripcion);
        }
        
        return estatus;
    }
    
}
