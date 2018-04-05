/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import superjuices.data.OrdenData;

/**
 *
 * @author alejandro
 */
public class OrdenRepository {
    
    //La funcion que nos permitira insertar un nuevo cliente
    public static void insertar(OrdenData orden) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" INSERT INTO ordenes(cliente, sucursal, repartidor, cobro, pago, estatus) VALUES( ?, ?, ?, ?, ?, ?); ");
        
        st.setInt(1, orden.getCliente().getId());
        st.setInt(2, orden.getSucursal().getId());
        st.setInt(3, orden.getRepartidor().getId());
        st.setInt(4, orden.getCobro().getId());
        st.setInt(5, orden.getPago().getId());
        st.setInt(6, orden.getEstatus().getId());
        
        st.executeUpdate();
        
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            orden.setId(rs.getInt(1));
        }
        
        System.out.println("Se ha insertado una nueva orden de jugo:"+orden.getId());
        
    }
    // TODO: TERMINAR EL OrdenRepository
    /*public static void actualizar(OrdenData orden) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" UPDATE jugos SET sabor=?, tam=?, precio=? WHERE id=?; ");
        
        st.setString(1, jugo.getSabor());
        st.setString(2, jugo.getTam());
        st.setInt(3, jugo.getPrecio());
        
        st.setInt(4, jugo.getId());
        
        st.executeUpdate();
        
        System.out.println("Se actualizó la informacion de un jugo con ID:"+jugo.getId());
    }
    
    public static void eliminar(OrdenData orden) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" DELETE FROM jugos WHERE id=?; ");
        
        st.setInt(1, jugo.getId());
        
        st.executeUpdate();
        
        System.out.println("Se eliminó jugo con ID:"+jugo.getId());
    }
    
    public static JugoData buscarById(int id) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" SELECT * FROM jugos WHERE id=?; ");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        OrdenData orden = new JugoData();
        jugo.setId(id);
        
        if(rs.next()) {
            jugo.setSabor(rs.getString("sabor"));
            jugo.setTam(rs.getString("tam"));
            jugo.setPrecio(rs.getInt("precio"));
        }
        
        return jugo;
        
    }*/
    
}
