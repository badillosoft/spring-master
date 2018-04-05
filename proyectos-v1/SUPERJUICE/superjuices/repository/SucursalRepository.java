/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import superjuices.data.SucursalData;

/**
 *
 * @author alejandro
 */
public class SucursalRepository {
    
    //La funcion que nos permitira insertar un nuevo cliente
    public static void insertar(SucursalData sucursal) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" INSERT INTO sucursales(nombre, direccion) VALUES( ?, ?); ");
        
        st.setString(1, sucursal.getNombre());
        st.setString(2, sucursal.getDireccion());
        
        st.executeUpdate();
        
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            sucursal.setId(rs.getInt(1));
        }
        
        System.out.println("Se ha insertado una nueva Sucursal:"+sucursal.getNombre());
        
    }
    
    public static void actualizar(SucursalData sucursal) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" UPDATE sucursales SET nombre=?, direccion=? WHERE id=?; ");
        
        st.setString(1, sucursal.getNombre());
        st.setString(2, sucursal.getDireccion());
        
        st.setInt(3, sucursal.getId());
        
        st.executeUpdate();
        
        System.out.println("Se actualizó sucursal con ID:"+sucursal.getId());
    }
    
    public static void eliminar(SucursalData sucursal) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" DELETE FROM sucursales WHERE id=?  ");
        
        st.setInt(1, sucursal.getId());
        
        st.executeUpdate();
        
        System.out.println("Se eliminó sucursal con ID:"+sucursal.getId());
    }
    
    public static SucursalData buscarById(int id) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery("SELECT * FROM sucursales WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        SucursalData sucursal = new SucursalData();
        sucursal.setId(id);
        
        if(rs.next()) {
            sucursal.setNombre(rs.getString("nombre"));
            sucursal.setDireccion(rs.getString("direccion"));
        }
        
        return sucursal;
        
    }
    
}
