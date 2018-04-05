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
import supertourist.data.UsuarioData;

/**
 *
 * @author diego
 */
public class UsuarioRepository {
    
    public void insertar(UsuarioData usuario){
        String query = QueryUtils.Usuario.INSERT;        
        PreparedStatement st = DbManager.generateQuery(query);
        try{
            st.setString(1, usuario.getName());
            st.setString(2, usuario.getEmail());
        }catch(SQLException err){
            System.out.println(err.getMessage());
        }
       
        int id = DbManager.executeInsert(st);
        usuario.setId(id);
    }
    
    public void update(UsuarioData usuario) throws SQLException{
        String query = QueryUtils.Usuario.UPDATE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setString(1, usuario.getName());
        st.setString(2, usuario.getEmail());
        st.setInt(3, usuario.getId());
        st.executeUpdate();
    }
    
     public void delete(UsuarioData usuario) throws SQLException{
        String query = QueryUtils.Usuario.DELETE;
        PreparedStatement st = DbManager.generateQuery(query);
        st.setInt(1, usuario.getId());
        st.executeUpdate();
    }
     
     public static UsuarioData searchById(int id) throws SQLException{
         String query = QueryUtils.Usuario.SEARCH_ID;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setInt(1,id);
         
        ResultSet rs = st.executeQuery();
        
        UsuarioData usuario = new UsuarioData();
        usuario.setId(id);
        
        if(rs.next()){
            String name = rs.getString(QueryUtils.Usuario.FIRST_FIELD);
            String email = rs.getString(QueryUtils.Usuario.SECOND_FIELD);
            
            usuario.setName(name);
            usuario.setEmail(email);
        } 
            
         return usuario;
     }
     
     public static List<UsuarioData> searchByName(String nameSearch) throws SQLException{
         String query = QueryUtils.Usuario.SEARCH_INTERESTING_FIELD;
         PreparedStatement st = DbManager.generateQuery(query);
         st.setString(1,nameSearch);
         
        ResultSet rs = st.executeQuery();
        
        List<UsuarioData> usuarios = new ArrayList();
        
        while(rs.next()){
            String name = rs.getString(QueryUtils.Usuario.FIRST_FIELD);
            String email = rs.getString(QueryUtils.Usuario.SECOND_FIELD);
            int id = rs.getInt("id");
            
            UsuarioData usuario = new UsuarioData();
            usuario.setId(id);
            usuario.setName(name);
            usuario.setEmail(email);
            usuarios.add(usuario);
        } 
            
         return usuarios;
     }
}
