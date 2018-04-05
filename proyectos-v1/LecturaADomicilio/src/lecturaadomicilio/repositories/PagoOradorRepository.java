/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lecturaadomicilio.data.PagoOradorData;

/**
 *
 * @author Pablo
 */
public class PagoOradorRepository {
    public static void insertar(PagoOradorData pago_orador) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO pagos_orador (token_paypal, completado) VALUES (?, ?);");
        
        st.setString(1, pago_orador.getToken_paypal());
        st.setBoolean(2, pago_orador.isCompletado());
        
        int id = DBManager.executeInsert(st);
        
        pago_orador.setId(id);
    }
    
    public static void actualizar(PagoOradorData pago_orador) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE pagos_orador SET token_paypal=?, completado=? WHERE id=?;");
        
        st.setString(1, pago_orador.getToken_paypal());
        st.setBoolean(2, pago_orador.isCompletado());
        st.setInt(3, pago_orador.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(PagoOradorData pago_orador) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM pagos_orador WHERE id=?;");
        
        st.setInt(1, pago_orador.getId());
        
        st.executeUpdate();
    }
    
    public static PagoOradorData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM pagos_orador WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        PagoOradorData pago_orador = new PagoOradorData();
        pago_orador.setId(id);
        
        if (rs.next()) {
            
            String token_paypal = rs.getString("token_paypal");
            boolean completado = rs.getBoolean("completado");
                    
            pago_orador.setToken_paypal(token_paypal);
            pago_orador.setCompletado(completado);
        }
        
        return pago_orador;
    }
}
