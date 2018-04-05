/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superLibros.reporitorios;

import RentaLibros.data.SuscriptorData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zaira
 */
public class SuscriptorRepository {
    public static void insertar(SuscriptorData suscriptor) throws SQLException{
        PreparedStatement st= DBManager.generateQuery("INSERT INTO suscriptores (nombre, correo) VALUES(?,?);");
        
        st.setString(1, suscriptor.getNombre());
        st.setString(2, suscriptor.getCorreo());
       
        int id= DBManager.executeInsert(st);
        suscriptor.setId(id);   
    }
    public static void actualizar(SuscriptorData suscriptor)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("UPDATE suscriptores SET nombre = ?, correo =? WHERE id=?");
        st.setString(1, suscriptor.getNombre());
        st.setString(2, suscriptor.getCorreo());
        st.setInt(3, suscriptor.getId());
        st.executeUpdate();
    }
    
    public static void eliminar(SuscriptorData suscriptor)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("DELETE FROM suscriptores  WHERE id=?");
        st.setInt(1, suscriptor.getId());
        st.executeUpdate();
    }
     
    public static SuscriptorData buscarPorId(int id) throws SQLException{
            PreparedStatement st= DBManager.generateQuery("SELECT * FROM suscriptores  WHERE id=?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            SuscriptorData suscriptor = new SuscriptorData();
            suscriptor.setId(id);
            if(rs.next()){
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                suscriptor.setId(id);
                suscriptor.setNombre(nombre);
                suscriptor.setNombre(correo);
            }
            return suscriptor;
    }
    
    public static List<SuscriptorData> buscarPorNombre(String like) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM Suscriptores WHERE nombre like ?;");
        
        st.setString(1, like);
        
        ResultSet rs = st.executeQuery();
        
        List<SuscriptorData> Suscriptores = new ArrayList();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            
            SuscriptorData Suscriptor = new SuscriptorData();
            Suscriptor.setId(id);
            Suscriptor.setNombre(nombre);
            Suscriptor.setCorreo(correo);
            
            Suscriptores.add(Suscriptor);
        }
        
        return Suscriptores;
    }
}
