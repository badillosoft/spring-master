/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supertourist.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import supertourist.QueryUtils;
import supertourist.data.SuscripcionData;

/**
 *
 * @author diego
 */
public class SuscripcionRepository {
     
    public void insertar(SuscripcionData suscripcion){
        String query = QueryUtils.Suscripcion.INSERT;        
        PreparedStatement st = DbManager.generateQuery(query);
        try{
            st.setInt(1, suscripcion.getUsuario());
        }catch(SQLException err){
            System.out.println(err.getMessage());
        } 
        int id = DbManager.executeInsert(st);
        suscripcion.setId(id);
    }
    
    public void update(SuscripcionData suscripcion) throws SQLException{
        String query = QueryUtils.Suscripcion.UPDATE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setInt(1, suscripcion.getUsuario());
        st.setInt(2, suscripcion.getId());
        st.executeUpdate();
    }
    
     public void delete(SuscripcionData suscripcion) throws SQLException{
        String query = QueryUtils.Suscripcion.DELETE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setInt(1, suscripcion.getId());
        st.executeUpdate();
    }
     
     public static SuscripcionData searchById(int id) throws SQLException{
         String query = QueryUtils.Suscripcion.SEARCH_ID;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setInt(1,id);
         
        ResultSet rs = st.executeQuery();
        
        SuscripcionData suscripcion = new SuscripcionData();
        suscripcion.setId(id);
        
        if(rs.next()){
            int usuario = rs.getInt(QueryUtils.Suscripcion.FIRST_FIELD);
            
            suscripcion.setUsuario(usuario);
        } 
            
         return suscripcion;
     }
}
