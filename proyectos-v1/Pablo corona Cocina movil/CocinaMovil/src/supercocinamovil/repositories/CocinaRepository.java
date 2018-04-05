
package supercocinamovil.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supercocinamovil.data.CocinaData;

public class CocinaRepository {
    
    public static void insertar(CocinaData cocina) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO cocinas_movil (nombre, registro, orden) VALUES (?, ?, ?);");
        
        st.setString(1, cocina.getNombre());
        st.setInt(2, cocina.getRegistro());
        st.setInt(3, cocina.getOrden());
        
        int id = DBManager.executeInsert(st);
        
        cocina.setId(id);
    }
    
    public static void actualizar(CocinaData cocina) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE cocinas_movil SET nombre=?, registro=?, orden=? WHERE id=?;");
        
        st.setString(1, cocina.getNombre());
        st.setInt(2, cocina.getRegistro());
        st.setInt(3, cocina.getOrden());
        
        st.executeUpdate();
    }
    
    public static void eliminar(CocinaData cocina) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM cocinas_movil WHERE id=?;");
        
        st.setInt(1, cocina.getId());
        
        st.executeUpdate();
    }
    
    public static CocinaData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM cocinas_movil WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        CocinaData cocina = new CocinaData();
        cocina.setId(id);
        
        if (rs.next()) {
            String nombre = rs.getString("nombre");
            int registro = rs.getInt(1);
            int orden = rs.getInt(1);
            
            cocina.setNombre(nombre);
            cocina.setRegistro(registro);
            cocina.setOrden(orden);
        }
        
        return cocina;
    }
    
    /*public static List<CocinaData> buscarPorNombre(String like) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM cocinas_movil WHERE nombre like ?;");
        
        st.setString(1, like);
        
        ResultSet rs = st.executeQuery();
        
        List<CocinaData> cocinas new ArrayList();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            int registro = rs.getInt(1);
            int orden = rs.getInt(1);
            
            CocinaData cocina = new CocinaData();
            cocina.setId(id);
            cocina.setNombre(nombre);
            cocina.setRegistro(registro);
            cocina.setOrden(orden);
            
            cocinas.add(cocina);
        }
        
        return cocinas;
    }*/
    
}