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
import supertourist.data.AsesorData;

/**
 *
 * @author diego
 */
public class AsesorRepository {
    
    public void insertar(AsesorData asesor){
        String query = QueryUtils.Asesor.INSERT;        
        PreparedStatement st = DbManager.generateQuery(query);
        try{
            st.setString(1, asesor.getName());
            st.setString(2, asesor.getEmail());
        }catch(SQLException err){
            System.out.println(err.getMessage());
        }
       
        int id = DbManager.executeInsert(st);
        asesor.setId(id);
    }
    
    public void update(AsesorData asesor) throws SQLException{
        String query = QueryUtils.Asesor.UPDATE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setString(1, asesor.getName());
        st.setString(2, asesor.getEmail());
        st.setInt(3, asesor.getId());
        st.executeUpdate();
    }
    
     public void delete(AsesorData asesor) throws SQLException{
        String query = QueryUtils.Asesor.DELETE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setInt(1, asesor.getId());
        st.executeUpdate();
    }
     
     public static AsesorData searchById(int id) throws SQLException{
         String query = QueryUtils.Asesor.SEARCH_ID;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setInt(1,id);
         
        ResultSet rs = st.executeQuery();
        
        AsesorData asesor = new AsesorData();
        asesor.setId(id);
        
        if(rs.next()){
            String name = rs.getString(QueryUtils.Asesor.FIRST_FIELD);
            String email = rs.getString(QueryUtils.Asesor.SECOND_FIELD);
            
            asesor.setName(name);
            asesor.setEmail(email);
        } 
            
         return asesor;
     }
     
     public static List<AsesorData> searchByName(String nameSearch) throws SQLException{
         String query = QueryUtils.Asesor.SEARCH_INTERESTING_FIELD;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setString(1,nameSearch);
         
        ResultSet rs = st.executeQuery();
        
        List<AsesorData> asesores = new ArrayList();
        
        while(rs.next()){
            String name = rs.getString(QueryUtils.Asesor.FIRST_FIELD);
            String email = rs.getString(QueryUtils.Asesor.SECOND_FIELD);
            int id = rs.getInt("id");
            
            AsesorData asesor = new AsesorData();
            asesor.setId(id);
            asesor.setName(name);
            asesor.setEmail(email);
            asesores.add(asesor);
        } 
            
         return asesores;
     }
}
