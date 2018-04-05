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
import supertourist.data.EncargadoData;

/**
 *
 * @author diego
 */
public class EncargadoRepository {
    
    public void insertar(EncargadoData encargado){
        String query = QueryUtils.Encargado.INSERT;        
        PreparedStatement st = DbManager.generateQuery(query);
        try{
            st.setString(1, encargado.getName());
            st.setString(2, encargado.getEmail());
        }catch(SQLException err){
            System.out.println(err.getMessage());
        }
       
        int id = DbManager.executeInsert(st);
        encargado.setId(id);
    }
    
    public void update(EncargadoData encargado) throws SQLException{
        String query = QueryUtils.Encargado.UPDATE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setString(1, encargado.getName());
        st.setString(2, encargado.getEmail());
        st.setInt(3, encargado.getId());
        st.executeUpdate();
    }
    
     public void delete(EncargadoData encargado) throws SQLException{
        String query = QueryUtils.Encargado.DELETE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setInt(1, encargado.getId());
        st.executeUpdate();
    }
     
     public static EncargadoData searchById(int id) throws SQLException{
         String query = QueryUtils.Encargado.SEARCH_ID;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setInt(1,id);
         
        ResultSet rs = st.executeQuery();
        
        EncargadoData encargado = new EncargadoData();
        encargado.setId(id);
        
        if(rs.next()){
            String name = rs.getString(QueryUtils.Encargado.FIRST_FIELD);
            String email = rs.getString(QueryUtils.Encargado.SECOND_FIELD);
            
            encargado.setName(name);
            encargado.setEmail(email);
        } 
            
         return encargado;
     }
     
     public static List<EncargadoData> searchByName(String nameSearch) throws SQLException{
         String query = QueryUtils.Encargado.SEARCH_INTERESTING_FIELD;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setString(1,nameSearch);
         
        ResultSet rs = st.executeQuery();
        
        List<EncargadoData> encargados = new ArrayList();
        
        while(rs.next()){
            String name = rs.getString(QueryUtils.Encargado.FIRST_FIELD);
            String email = rs.getString(QueryUtils.Encargado.SECOND_FIELD);
            int id = rs.getInt("id");
            
            EncargadoData encargado = new EncargadoData();
            encargado.setId(id);
            encargado.setName(name);
            encargado.setEmail(email);
            encargados.add(encargado);
        } 
            
         return encargados;
     }
}
