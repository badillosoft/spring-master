package supercupcake.repositories;

import supercupcake.data.*;
import java.sql.*;

public class CocinaRepository {
    
    public static void insertar(CocinaData cocina) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO cocinas (nombre, direccion) VALUES (?, ?);");
        
        st.setString(1, cocina.getNombre());
        st.setString(2, cocina.getDireccion());
        
        int id = DBManager.executeInsert(st);
        
        cocina.setId(id);
    }
    
    public static void actualizar(CocinaData cocina) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE cocinas SET nombre=?, direccion=? WHERE id=?;");
        
        st.setString(1, cocina.getNombre());
        st.setString(2, cocina.getDireccion());
        st.setInt(3, cocina.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(CocinaData cocina) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM cocinas WHERE id=?;");
        
        st.setInt(1, cocina.getId());
        
        st.executeUpdate();
    }
    
    public static CocinaData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM cocinas WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        CocinaData cocina = new CocinaData();
        cocina.setId(id);
        
        if (rs.next()) {
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            
            cocina.setNombre(nombre);
            cocina.setDireccion(direccion);
        } else {
            return null;
        }
        
        return cocina;
    }
    
    public static CocinaData buscarAleatorio() throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM cocinas ORDER BY RAND() LIMIT 1;");
        
        ResultSet rs = st.executeQuery();
        
        CocinaData cocina = new CocinaData();
        
        if (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            
            cocina.setId(id);
            cocina.setNombre(nombre);
            cocina.setDireccion(direccion);
        } else {
            return null;
        }
        
        return cocina;
    }
    
}
