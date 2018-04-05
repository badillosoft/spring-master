/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import superjuices.data.ClienteData;

/**
 *
 * @author alejandro
 */
public class ClienteRepository {
    
    //La funcion que nos permitira insertar un nuevo cliente
    public static void insertar(ClienteData cliente) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" INSERT INTO clientes(nombre, direccion) VALUES(?, ?); ");
        
        st.setString(1, cliente.getNombre());
        st.setString(2, cliente.getDireccion());
        
        st.executeUpdate();
        
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            cliente.setId(rs.getInt(1));
        }
        
        System.out.println("Se ha insertado un nuevo Cliente:"+cliente.getNombre());
        
    }
    
    public static void actualizar(ClienteData cliente) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" UPDATE clientes SET nombre=?, direccion=? WHERE id=?; ");
        
        st.setString(1, cliente.getNombre());
        st.setString(2, cliente.getDireccion());
        
        st.setInt(3, cliente.getId());
        
        st.executeUpdate();
        
        System.out.println("Se actualizo Cliente con Id:"+cliente.getId());
    }
    
    public static void eliminar(ClienteData cliente) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" DELETE FROM clientes WHERE id=?  ");
        
        st.setInt(1, cliente.getId());
        
        st.executeUpdate();
        
        System.out.println("Se eliminó cliente con ID:"+cliente.getId());
    }
    
    public static ClienteData buscarById(int id) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery("SELECT * FROM clientes WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        ClienteData cliente = new ClienteData();
        cliente.setId(id);
        
        if(rs.next()) {
            cliente.setNombre(rs.getString("nombre"));
            cliente.setDireccion(rs.getString("direccion"));
        }
        
        return cliente;
        
    }
    
}
