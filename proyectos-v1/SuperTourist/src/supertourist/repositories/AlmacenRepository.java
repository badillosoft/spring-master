/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supertourist.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supertourist.QueryUtils;
import supertourist.data.AlmacenData;

/**
 *
 * @author diego
 */
public class AlmacenRepository {
        
    public void insertar(AlmacenData almacen){
        String query = QueryUtils.Almacen.INSERT;        
        PreparedStatement st = DbManager.generateQuery(query);
        try{
            st.setString(1, almacen.getSucursal());
            st.setString(2, almacen.getAddress());
        }catch(SQLException err){
            System.out.println(err.getMessage());
        }
       
        int id = DbManager.executeInsert(st);
        almacen.setId(id);
    }
    
    public void update(AlmacenData almacen) throws SQLException{
        String query = QueryUtils.Almacen.UPDATE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setString(1, almacen.getSucursal());
        st.setString(2, almacen.getAddress());
        st.setInt(3, almacen.getId());
        st.executeUpdate();
    }
    
     public void delete(AlmacenData almacen) throws SQLException{
        String query = QueryUtils.Almacen.DELETE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setInt(1, almacen.getId());
        st.executeUpdate();
    }
     
     public static AlmacenData searchById(int id) throws SQLException{
         String query = QueryUtils.Almacen.SEARCH_ID;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setInt(1,id);
         
        ResultSet rs = st.executeQuery();
        
        AlmacenData almacen = new AlmacenData();
        almacen.setId(id);
        
        if(rs.next()){
            String sucursal = rs.getString(QueryUtils.Almacen.FIRST_FIELD);
            String address = rs.getString(QueryUtils.Almacen.SECOND_FIELD);
            
            almacen.setSucursal(sucursal);
            almacen.setAddress(address);
        } 
            
         return almacen;
     }
     
     public static List<AlmacenData> searchBySucursal(String sucursalSearch) throws SQLException{
         String query = QueryUtils.Almacen.SEARCH_INTERESTING_FIELD;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setString(1,sucursalSearch);
         
        ResultSet rs = st.executeQuery();
        
        List<AlmacenData> almacenes = new ArrayList();
        
        while(rs.next()){
            String sucursal = rs.getString(QueryUtils.Almacen.FIRST_FIELD);
            String address = rs.getString(QueryUtils.Almacen.SECOND_FIELD);
            int id = rs.getInt("id");
            
            AlmacenData almacen = new AlmacenData();
            almacen.setId(id);
            almacen.setSucursal(sucursal);
            almacen.setAddress(address);
            almacenes.add(almacen);
        } 
            
         return almacenes;
     }
}
