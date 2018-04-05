/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import superjuices.data.RepartidorData;

/**
 *
 * @author alejandro
 */
public class RepartidorRepository {
    
    //La funcion que nos permitira insertar un nuevo cliente
    public static void insertar(RepartidorData repartidor) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" INSERT INTO repartidores(nombre, placas, direccion) VALUES( ?, ?, ?); ");
        
        st.setString(1, repartidor.getNombre());
        st.setString(2, repartidor.getPlacas());
        st.setString(3, repartidor.getDireccion());
        
        st.executeUpdate();
        
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            repartidor.setId(rs.getInt(1));
        }
        
        System.out.println("Se ha insertado un nuevo repartidor:"+repartidor.getNombre());
        
    }
    
    public static void actualizar(RepartidorData repartidor) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" UPDATE repartidores SET nombre=?, placas=?, direccion=? WHERE id=?; ");
        
        st.setString(1, repartidor.getNombre());
        st.setString(2, repartidor.getPlacas());
        st.setString(3, repartidor.getDireccion());
        
        st.setInt(4, repartidor.getId());
        
        st.executeUpdate();
        
        System.out.println("Se actualizó repartidor con ID:"+repartidor.getId());
    }
    
    public static void eliminar(RepartidorData repartidor) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" DELETE FROM repartidores WHERE id=?; ");
        
        st.setInt(1, repartidor.getId());
        
        st.executeUpdate();
        
        System.out.println("Se eliminó repartidor con ID:"+repartidor.getId());
    }
    
    public static RepartidorData buscarById(int id) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" SELECT * FROM repartidores WHERE id=?; ");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        RepartidorData repartidor = new RepartidorData();
        repartidor.setId(id);
        
        if(rs.next()) {
            repartidor.setNombre(rs.getString("nombre"));
            repartidor.setPlacas(rs.getString("placas"));
            repartidor.setDireccion(rs.getString("direccion"));
        }
        
        return repartidor;
        
    }
    
}
