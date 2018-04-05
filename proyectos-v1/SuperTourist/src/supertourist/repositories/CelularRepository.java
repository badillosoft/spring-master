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
import supertourist.data.CelularData;

/**
 *
 * @author diego
 */
public class CelularRepository {
    
    public void insertar(CelularData celular){
        String query = QueryUtils.Celular.INSERT;        
        PreparedStatement st = DbManager.generateQuery(query);
        try{
            st.setString(1, celular.getModel());
        }catch(SQLException err){
            System.out.println(err.getMessage());
        } 
        int id = DbManager.executeInsert(st);
        celular.setId(id);
    }
    
    public void update(CelularData celular) throws SQLException{
        String query = QueryUtils.Celular.UPDATE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setString(1, celular.getModel());
        st.setInt(2, celular.getId());
        st.executeUpdate();
    }
    
     public void delete(CelularData celular) throws SQLException{
        String query = QueryUtils.Celular.DELETE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setInt(1, celular.getId());
        st.executeUpdate();
    }
     
     public static CelularData searchById(int id) throws SQLException{
         String query = QueryUtils.Celular.SEARCH_ID;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setInt(1,id);
         
        ResultSet rs = st.executeQuery();
        
        CelularData celular = new CelularData();
        celular.setId(id);
        
        if(rs.next()){
            String model = rs.getString(QueryUtils.Celular.FIRST_FIELD);
            
            celular.setModel(model);
        } 
            
         return celular;
     }
     
     public static List<CelularData> searchByModel(String modelSearch) throws SQLException{
         String query = QueryUtils.Celular.SEARCH_INTERESTING_FIELD;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setString(1,modelSearch);
         
        ResultSet rs = st.executeQuery();
        
        List<CelularData> celulares = new ArrayList();
        
        while(rs.next()){
            String model = rs.getString(QueryUtils.Celular.FIRST_FIELD);
            int id = rs.getInt("id");
            
            CelularData celular = new CelularData();
            celular.setId(id);
            celular.setModel(model);
            celulares.add(celular);
        } 
            
         return celulares;
     }
}
