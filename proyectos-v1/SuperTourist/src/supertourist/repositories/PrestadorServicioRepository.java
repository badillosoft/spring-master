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
import supertourist.data.PrestadorServicioData;

/**
 *
 * @author diego
 */
public class PrestadorServicioRepository {
    
    public void insertar(PrestadorServicioData prestador){
        String query = QueryUtils.PrestadorServicio.INSERT;        
        PreparedStatement st = DbManager.generateQuery(query);
        try{
            st.setString(1, prestador.getName());
            st.setString(2, prestador.getService());
        }catch(SQLException err){
            System.out.println(err.getMessage());
        }
       
        int id = DbManager.executeInsert(st);
        prestador.setId(id);
    }
    
    public void update(PrestadorServicioData prestador) throws SQLException{
        String query = QueryUtils.PrestadorServicio.UPDATE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setString(1, prestador.getName());
        st.setString(2, prestador.getService());
        st.setInt(3, prestador.getId());
        st.executeUpdate();
    }
    
     public void delete(PrestadorServicioData prestador) throws SQLException{
        String query = QueryUtils.PrestadorServicio.DELETE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setInt(1, prestador.getId());
        st.executeUpdate();
    }
     
     public static PrestadorServicioData searchById(int id) throws SQLException{
         String query = QueryUtils.PrestadorServicio.SEARCH_ID;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setInt(1,id);
         
        ResultSet rs = st.executeQuery();
        
        PrestadorServicioData prestador = new PrestadorServicioData();
        prestador.setId(id);
        
        if(rs.next()){
            String name = rs.getString(QueryUtils.PrestadorServicio.FIRST_FIELD);
            String service = rs.getString(QueryUtils.PrestadorServicio.SECOND_FIELD);
            
            prestador.setName(name);
            prestador.setService(service);
        } 
            
         return prestador;
     }
     
     public static List<PrestadorServicioData> searchByService(String serviceSearch) throws SQLException{
         String query = QueryUtils.PrestadorServicio.SEARCH_INTERESTING_FIELD;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setString(1,serviceSearch);
         
        ResultSet rs = st.executeQuery();
        
        List<PrestadorServicioData> prestadores = new ArrayList();
        
        while(rs.next()){
            String name = rs.getString(QueryUtils.PrestadorServicio.FIRST_FIELD);
            String service = rs.getString(QueryUtils.PrestadorServicio.SECOND_FIELD);
            int id = rs.getInt("id");
            
            PrestadorServicioData prestador = new PrestadorServicioData();
            prestador.setId(id);
            prestador.setName(name);
            prestador.setService(service);
            prestadores.add(prestador);
        } 
            
         return prestadores;
     }
}
