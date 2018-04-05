package supercupcake.repositories;

import supercupcake.data.*;
import java.sql.*;

public class VendedorRepository {
    
    public static void insertar(VendedorData vendedor) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO vendedores (nombre, correo) VALUES (?, ?);");
        
        st.setString(1, vendedor.getNombre());
        st.setString(2, vendedor.getCorreo());
        
        int id = DBManager.executeInsert(st);
        
        vendedor.setId(id);
    }
    
    public static void actualizar(VendedorData vendedor) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE vendedores SET nombre=?, correo=? WHERE id=?;");
        
        st.setString(1, vendedor.getNombre());
        st.setString(2, vendedor.getCorreo());
        st.setInt(3, vendedor.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(VendedorData vendedor) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM vendedores WHERE id=?;");
        
        st.setInt(1, vendedor.getId());
        
        st.executeUpdate();
    }
    
    public static VendedorData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM vendedores WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        VendedorData vendedor = new VendedorData();
        vendedor.setId(id);
        
        if (rs.next()) {
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            
            vendedor.setNombre(nombre);
            vendedor.setCorreo(correo);
        } else {
            return null;
        }
        
        return vendedor;
    }
    
}
