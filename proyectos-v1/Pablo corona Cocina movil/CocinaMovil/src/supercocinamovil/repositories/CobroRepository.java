
package supercocinamovil.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supercocinamovil.data.CobroData;

public class CobroRepository {
    
    public static void insertar(CobroData cobro) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO cobros (total, token_paypal, completado) VALUES (?, ?, ?);");
        
        st.setFloat(1, cobro.getTotal());
        st.setString(2, cobro.getToken_paypal());
        st.setInt (3, cobro.getCompletado());
        
        int id = DBManager.executeInsert(st);
        
        cobro.setId(id);
    }
    
    public static void actualizar(CobroData cobro) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE cobro SET total=?, token_paypal=?, completado=? WHERE id=?;");
        
       st.setFloat(1, cobro.getTotal());
        st.setString(2, cobro.getToken_paypal());
        st.setInt (3, cobro.getCompletado());
        
        st.executeUpdate();
    }
    
    public static void eliminar(CobroData cobro) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM cobros WHERE id=?;");
        
        st.setInt(1, cobro.getId());
        
        st.executeUpdate();
    }
    
    public static CobroData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM cobros WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        CobroData cobro = new CobroData();
        cobro.setId(id);
        
        if (rs.next()) {
            
            float total = rs.getFloat(1);
            String token = rs.getString("token_paypal");
            int completado = rs.getInt(1);
            
            
            cobro.setTotal(total);
            cobro.setToken_paypal(token);
            cobro.setCompletado(completado);
        }
        
        return cobro;
    }
    
}