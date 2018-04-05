/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lecturaadomicilio.data.OradorData;

/**
 *
 * @author Pablo
 */
public class OradorRepository {
    
    public static void insertar(OradorData orador) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO clientes (nombre, correo, tipo, precio) VALUES (?, ?, ?, ?);");
        
        st.setString(1, orador.getNombre());
        st.setString(2, orador.getCorreo());
        st.setString(3, orador.getTipo());
        st.setDouble(4, orador.getPrecio());
        
        int id = DBManager.executeInsert(st);
        
        orador.setId(id);
    }
    
    public static void actualizar(OradorData orador) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE clientes SET nombre=?, correo=?, tipo=?, precio=? WHERE id=?;");
        
        st.setString(1, orador.getNombre());
        st.setString(2, orador.getCorreo());
        st.setString(3, orador.getTipo());
        st.setDouble(4, orador.getPrecio());
        st.setInt(4, orador.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(OradorData orador) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM clientes WHERE id=?;");
        
        st.setInt(1, orador.getId());
        
        st.executeUpdate();
    }
    
    public static OradorData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM clientes WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        OradorData orador = new OradorData();
        orador.setId(id);
        
        if (rs.next()) {
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            String tipo = rs.getString("tipo");
            double precio = rs.getDouble("precio");
            
            orador.setNombre(nombre);
            orador.setCorreo(correo);
            orador.setTipo(tipo);
            orador.setPrecio(precio);
        }
        
        return orador;
    }
    
    public static List<OradorData> buscarPorNombre(String like) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM clientes WHERE nombre like ?;");
        
        st.setString(1, like);
        
        ResultSet rs = st.executeQuery();
        
        List<OradorData> oradores = new ArrayList();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            String tipo = rs.getString("tipo");
            double precio = rs.getDouble("precio");
           
            OradorData orador = new OradorData();
            orador.setId(id);
            orador.setNombre(nombre);
            orador.setCorreo(correo);
            orador.setTipo(tipo);
            orador.setPrecio(precio);
            
            oradores.add(orador);
        }
        
        return oradores;
    }
    
    public static List<OradorData> buscarPorTipo(String like) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM clientes WHERE tipo like ?;");
        
        st.setString(1, like);
        
        ResultSet rs = st.executeQuery();
        
        List<OradorData> oradores = new ArrayList();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            String tipo = rs.getString("tipo");
            double precio = rs.getDouble("precio");
           
            OradorData orador = new OradorData();
            orador.setId(id);
            orador.setNombre(nombre);
            orador.setCorreo(correo);
            orador.setTipo(tipo);
            orador.setPrecio(precio);
            
            oradores.add(orador);
        }
        
        return oradores;
    }
    
}