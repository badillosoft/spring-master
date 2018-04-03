package supercupcake.repositories;

import supercupcake.data.ClienteData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    
    public static void insertar(ClienteData cliente) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO clientes (nombre, correo) VALUES (?, ?);");
        
        st.setString(1, cliente.getNombre());
        st.setString(2, cliente.getCorreo());
        
        int id = DBManager.executeInsert(st);
        
        cliente.setId(id);
    }
    
    public static void actualizar(ClienteData cliente) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE clientes SET nombre=?, correo=? WHERE id=?;");
        
        st.setString(1, cliente.getNombre());
        st.setString(2, cliente.getCorreo());
        st.setInt(3, cliente.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(ClienteData cliente) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM clientes WHERE id=?;");
        
        st.setInt(1, cliente.getId());
        
        st.executeUpdate();
    }
    
    public static ClienteData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM clientes WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        ClienteData cliente = new ClienteData();
        cliente.setId(id);
        
        if (rs.next()) {
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            
            cliente.setNombre(nombre);
            cliente.setCorreo(correo);
        }
        
        return cliente;
    }
    
    public static List<ClienteData> buscarPorNombre(String like) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM clientes WHERE nombre like ?;");
        
        st.setString(1, like);
        
        ResultSet rs = st.executeQuery();
        
        List<ClienteData> clientes = new ArrayList();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            
            ClienteData cliente = new ClienteData();
            cliente.setId(id);
            cliente.setNombre(nombre);
            cliente.setCorreo(correo);
            
            clientes.add(cliente);
        }
        
        return clientes;
    }
    
}
