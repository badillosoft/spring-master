package supercupcake.repositories;

import supercupcake.data.*;
import java.sql.*;

public class EstatusRepository {
    
    public static void insertar(EstatusData estatus) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO estatus (descripcion) VALUES (?);");
        
        st.setString(1, estatus.getDescripcion());
        
        int id = DBManager.executeInsert(st);
        
        estatus.setId(id);
    }
    
    public static void actualizar(EstatusData estatus) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE estatus SET descripcion=? WHERE id=?;");
        
        st.setString(1, estatus.getDescripcion());
        st.setInt(2, estatus.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(EstatusData estatus) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM estatus WHERE id=?;");
        
        st.setInt(1, estatus.getId());
        
        st.executeUpdate();
    }
    
    public static EstatusData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM estatus WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        EstatusData estatus = new EstatusData();
        estatus.setId(id);
        
        if (rs.next()) {
            String descripcion = rs.getString("descripcion");
            
            estatus.setDescripcion(descripcion);
        } else {
            return null;
        }
        
        return estatus;
    }
    
}
