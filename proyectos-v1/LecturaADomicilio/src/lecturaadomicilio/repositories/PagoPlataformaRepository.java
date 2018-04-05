/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lecturaadomicilio.data.PagoPlataformaData;

/**
 *
 * @author Pablo
 */
public class PagoPlataformaRepository {
    public static void insertar(PagoPlataformaData pago_plataforma) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO pagos_plataforma (token_paypal, completado) VALUES (?, ?);");
        
        st.setString(1, pago_plataforma.getToken_paypal());
        st.setBoolean(2, pago_plataforma.isCompletado());
        
        int id = DBManager.executeInsert(st);
        
        pago_plataforma.setId(id);
    }
    
    public static void actualizar(PagoPlataformaData pago_plataforma) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE pagos_plataforma SET token_paypal=?, completado=? WHERE id=?;");
        
        st.setString(1, pago_plataforma.getToken_paypal());
        st.setBoolean(2, pago_plataforma.isCompletado());
        st.setInt(3, pago_plataforma.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(PagoPlataformaData pago_plataforma) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM pagos_plataforma WHERE id=?;");
        
        st.setInt(1, pago_plataforma.getId());
        
        st.executeUpdate();
    }
    
    public static PagoPlataformaData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM pagos_plataforma WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        PagoPlataformaData pago_plataforma = new PagoPlataformaData();
        pago_plataforma.setId(id);
        
        if (rs.next()) {
            
            String token_paypal = rs.getString("token_paypal");
            boolean completado = rs.getBoolean("completado");
                    
            pago_plataforma.setToken_paypal(token_paypal);
            pago_plataforma.setCompletado(completado);
        }
        
        return pago_plataforma;
    }
    
}
