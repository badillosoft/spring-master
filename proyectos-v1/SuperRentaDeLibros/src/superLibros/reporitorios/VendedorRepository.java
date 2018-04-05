/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superLibros.reporitorios;

import RentaLibros.data.VendedorData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zaira
 */
public class VendedorRepository {
    public static void insertar(VendedorData vendedor) throws SQLException{
        PreparedStatement st= DBManager.generateQuery("INSERT INTO vendedor (nombre, correo) VALUES(?,?);");
        
        st.setString(1, vendedor.getNombre());
        st.setString(2, vendedor.getCorreo());
       
        int id= DBManager.executeInsert(st);
        vendedor.setId(id);   
    }
    public static void actualizar(VendedorData vendedor)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("UPDATE vendedor SET nombre = ?, correo =? WHERE id=?");
        st.setString(1, vendedor.getNombre());
        st.setString(2, vendedor.getCorreo());
        st.setInt(3, vendedor.getId());
        st.executeUpdate();
    }
    
    public static void eliminar(VendedorData vendedor)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("DELETE FROM vendedor  WHERE id=?");
        st.setInt(1, vendedor.getId());
        st.executeUpdate();
    }
     
    public static VendedorData buscarPorId(int id) throws SQLException{
            PreparedStatement st= DBManager.generateQuery("SELECT * FROM vendedor  WHERE id=?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            VendedorData vendedor = new VendedorData();
            vendedor.setId(id);
            if(rs.next()){
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                vendedor.setId(id);
                vendedor.setNombre(nombre);
                vendedor.setNombre(correo);
            }
            return vendedor;
    }
    
    public static List<VendedorData> buscarPorNombre(String like) throws SQLException{
            PreparedStatement st= DBManager.generateQuery("SELECT * FROM vendedor  WHERE nombre like?;");
            st.setString(1, like);
            ResultSet rs = st.executeQuery();
            
            List<VendedorData> vendedores = new ArrayList();
            
            while(rs.next()){
                int id= rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                VendedorData vendedor = new VendedorData();
                vendedor.setId(id);
                vendedor.setNombre(nombre);
                vendedor.setNombre(correo);
                
                vendedores.add(vendedor);
            }
            
           return  vendedores;
     }
}
