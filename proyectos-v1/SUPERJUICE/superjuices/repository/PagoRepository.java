/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import superjuices.data.PagoData;

/**
 *
 * @author alejandro
 */
public class PagoRepository {
    
    //La funcion que nos permitira insertar un nuevo cliente
    public static void insertar(PagoData pago) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" INSERT INTO pagos(token_paypal, completado) VALUES( ?, ?); ");
        
        st.setString(1, pago.getToken_paypal());
        st.setShort(2, (short)pago.getCompletado());
        
        st.executeUpdate();
        
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            pago.setId(rs.getInt(1));
        }
        
        System.out.println("Se ha insertado un nuevo pago con ID:"+pago.getId());
        
    }
    
    public static void actualizar(PagoData pago) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" UPDATE pagos SET token_paypal=?, completado=? WHERE id=?; ");
        
        
        st.setString(1, pago.getToken_paypal());
        st.setShort(2, pago.getCompletado());
        
        st.setInt(3, pago.getId());
        
        st.executeUpdate();
        
        System.out.println("Se actualizó la informacion de un pago con ID:"+pago.getId());
    }
    
    public static void eliminar(PagoData pago) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" DELETE FROM pagos WHERE id=?; ");
        
        st.setInt(1, pago.getId());
        
        st.executeUpdate();
        
        System.out.println("Se eliminó pago con ID:"+pago.getId());
    }
    
    public static PagoData buscarById(int id) throws SQLException {
        
        PreparedStatement st = DBManager.myQuery(" SELECT * FROM pagos WHERE id=?; ");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        PagoData pago = new PagoData();
        pago.setId(id);
        
        if(rs.next()) {
            pago.setToken_paypal(rs.getString("token_paypal"));
            pago.setCompletado(rs.getShort("completado"));
        }
        
        return pago;
        
    }
    
}
