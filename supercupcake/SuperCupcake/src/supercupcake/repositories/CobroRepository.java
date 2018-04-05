package supercupcake.repositories;

import supercupcake.data.*;
import java.sql.*;

public class CobroRepository {
    
    public static void insertar(CobroData cobro) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO cobros (total, token_paypal, completado) VALUES (?, ?);");
        
        st.setDouble(1, cobro.getTotal());
        st.setString(2, cobro.getToken_paypal());
        st.setBoolean(3, cobro.isCompletado());
        
        int id = DBManager.executeInsert(st);
        
        cobro.setId(id);
    }
    
    public static void actualizar(CobroData cobro, OrdenData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE cobros SET total=?, token_paypal=?, completado=? WHERE id=?;");
        
        st.setDouble(1, cobro.getTotal());
        st.setString(2, cobro.getToken_paypal());
        st.setBoolean(3, cobro.isCompletado());
        st.setInt(4, cobro.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(CobroData cobro) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM cobros WHERE id=?;");
        
        st.setInt(1, cobro.getId());
        
        st.executeUpdate();
    }
    
    public static double calcularTotal(OrdenData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("select sum(orden_cupcakes.multiplicador * cupcakes.precio) as total from orden_cupcakes left join cupcakes on orden_cupcakes.cupcake=cupcakes.id where orden_cupcakes.orden=?");
        
        st.setInt(1, orden.getId());
        
        ResultSet rs = st.executeQuery();
        
        if (rs.next()) {
            double total = rs.getDouble("total");
            return total;
        }
        
        return -1;
    }
    
    public static CobroData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM cobros WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        CobroData cobro = new CobroData();
        cobro.setId(id);
        
        if (rs.next()) {
            double total = rs.getDouble("total");
            String token_paypal = rs.getString("token_paypal");
            boolean completado = rs.getBoolean("completado");
            
            cobro.setTotal(total);
            cobro.setToken_paypal(token_paypal);
            cobro.setCompletado(completado);
        } else {
            return null;
        }
        
        return cobro;
    }
    
}
