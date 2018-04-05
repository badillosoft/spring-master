/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lecturaadomicilio.data.CobroData;
import lecturaadomicilio.data.CobroData;

/**
 *
 * @author Pablo
 */
public class CobroRepository {
    public static void insertar(CobroData cobro) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO cobros (total, token_paypal, completado) VALUES (?, ?, ?);");
        
        st.setDouble(1, cobro.getTotal());
        st.setString(2, cobro.getToken_paypal());
        st.setBoolean(3, cobro.isCompletado());
        
        int id = DBManager.executeInsert(st);
        
        cobro.setId(id);
    }
    
    public static void actualizar(CobroData cobro) throws SQLException {
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
        }
        
        return cobro;
    }
}
