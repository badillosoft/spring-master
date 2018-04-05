package supercupcake.repositories;

import supercupcake.data.*;
import java.sql.*;

public class PagoRepository {
    
    public static void insertar(PagoData pago) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO pagos (token_paypal, completado) VALUES (?, ?);");
        
        st.setString(1, pago.getToken_paypal());
        st.setBoolean(2, pago.isCompletado());
        
        int id = DBManager.executeInsert(st);
        
        pago.setId(id);
    }
    
    public static void actualizar(PagoData pago) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE pagos SET token_paypal=?, completado=? WHERE id=?;");
        
        st.setString(1, pago.getToken_paypal());
        st.setBoolean(2, pago.isCompletado());
        st.setInt(3, pago.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(PagoData pago) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM pagos WHERE id=?;");
        
        st.setInt(1, pago.getId());
        
        st.executeUpdate();
    }
    
    public static PagoData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM pagos WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        PagoData pago = new PagoData();
        pago.setId(id);
        
        if (rs.next()) {
            double total = rs.getDouble("total");
            String token_paypal = rs.getString("token_paypal");
            boolean completado = rs.getBoolean("completado");
            
            pago.setToken_paypal(token_paypal);
            pago.setCompletado(completado);
        } else {
            return null;
        }
        
        return pago;
    }
    
}
