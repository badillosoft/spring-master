/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import superjuices.repository.DBManager;

/**
 *
 * @author alejandro
 */
public class EstatusRepository {
    
    //La funcion que nos permitira insertar un nuevo estatus
    public static void insertar(EstatusData estatus) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" INSERT INTO estatus(estado) VALUES(?); ");
        
        st.setString(1, estatus.getEstado());
        
        st.executeUpdate();
        
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            estatus.setId(rs.getInt(1));
        }
        
        System.out.println("Se ha insertado un nuevo estatus con ID:"+estatus.getId());
        
    }
    
    public static void actualizar(EstatusData estatus) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" UPDATE estatus SET estado=? WHERE id=?; ");
        
        st.setString(1, estatus.getEstado());
        
        st.setInt(2, estatus.getId());
        
        st.executeUpdate();
        
        System.out.println("Se actualizó estatus con ID:"+estatus.getId());
    }
    
    public static void eliminar(EstatusData estatus) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" DELETE FROM estatus WHERE id=?; ");
        
        st.setInt(1, estatus.getId());
        
        st.executeUpdate();
        
        System.out.println("Se eliminó estatus con ID:"+estatus.getId());
    }
    
    public static EstatusData buscarById(int id) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" SELECT * FROM estatus WHERE id=?; ");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        EstatusData estatus = new EstatusData();
        estatus.setId(id);
        
        if(rs.next()) {
            estatus.setEstado(rs.getString("estado"));
        }
        
        return estatus;
        
    }
    
}
