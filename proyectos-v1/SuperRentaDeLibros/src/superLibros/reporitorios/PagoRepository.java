/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superLibros.reporitorios;

import RentaLibros.data.PagoData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author zaira
 */
public class PagoRepository {
    public static void insertar(PagoData pago) throws SQLException{
        PreparedStatement st= DBManager.generateQuery("INSERT INTO pago (idCobro, completado) VALUES(?,?);");
        st.setDouble(1, pago.getIdCobro());
        st.setInt(2, pago.getCompletado());
       
        int id= DBManager.executeInsert(st);
        pago.setId(id);   
    }
    public static void actualizar(PagoData pago)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("UPDATE pago SET idCobro = ?, completado =? WHERE id=?");
        st.setDouble(1, pago.getIdCobro());
        st.setInt(2, pago.getCompletado());
        st.setInt(3, pago.getId());
        st.executeUpdate();
    }
    
    public static void eliminar(PagoData pago)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("DELETE FROM pago  WHERE id=?");
        st.setInt(1, pago.getId());
        st.executeUpdate();
    }
     
    public static PagoData buscarPorId(int id) throws SQLException{
            PreparedStatement st= DBManager.generateQuery("SELECT * FROM pago  WHERE id=?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            PagoData pago = new PagoData();
            pago.setId(id);
            if(rs.next()){
                int idCobro = rs.getInt("idCobro");
                int completado = rs.getInt("completado");
                pago.setId(id);
                pago.setIdCobro(idCobro);
                pago.setCompletado(completado);
            }
            return pago;
    }
    
}
