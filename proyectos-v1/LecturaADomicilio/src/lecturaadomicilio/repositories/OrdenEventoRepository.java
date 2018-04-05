/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lecturaadomicilio.data.ClienteData;
import lecturaadomicilio.data.CobroData;
import lecturaadomicilio.data.OradorData;
import lecturaadomicilio.data.OrdenEventoData;
import lecturaadomicilio.data.PagoOradorData;
import lecturaadomicilio.data.PagoPlataformaData;
import lecturaadomicilio.data.StatusData;

/**
 *
 * @author Pablo
 */
public class OrdenEventoRepository {
    public static void insertar(OrdenEventoData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO ordenes (cliente, orador, cobro, pago_plataforma, pago_orador, estatus) VALUES (?, ?, ?, ?, ?, ?);");
        
        st.setObject(1, orden.getCliente());
        st.setObject(2, orden.getOrador());
        st.setObject(3, orden.getCobro());
        st.setObject(4, orden.getPago_plataforma());
        st.setObject(5, orden.getPago_orador());
        st.setObject(6, orden.getRegistro());

        
        int id = DBManager.executeInsert(st);
        
        orden.setId(id);
    }
    
    public static void actualizar(OrdenEventoData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE ordenes SET cliente=?, orador=?, cobro=?, pago_plataforma=?, pago_orador=?, estatus=? WHERE id=?;");
        
        st.setInt(1, orden.getCliente().getId());
        st.setInt(2, orden.getOrador().getId());
        st.setInt(3, orden.getCobro().getId());
        st.setInt(4, orden.getPago_plataforma().getId());
        st.setInt(5, orden.getPago_orador().getId());
        st.setInt(6, orden.getRegistro().getId());
        st.setInt(7, orden.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(OrdenEventoData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM ordenes WHERE id=?;");
        
        st.setInt(1, orden.getId());
        
        st.executeUpdate();
    }
    
    public static OrdenEventoData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM ordenes WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        OrdenEventoData orden = new OrdenEventoData();
        orden.setId(id);
        
        if (rs.next()) {
            
            int id_cliente = rs.getInt("cliente");
            ClienteData cliente = ClienteRepository.buscarPorId(id_cliente);
            
            int id_orador = rs.getInt("orador");
            OradorData orador = OradorRepository.buscarPorId(id_orador);
            
            int id_cobro = rs.getInt("cobro");
            CobroData cobro = CobroRepository.buscarPorId(id_cobro);
            
            int id_pago_plataforma = rs.getInt("pago_plataforma");
            PagoPlataformaData pago_plataforma = PagoPlataformaRepository.buscarPorId(id_pago_plataforma);
            
            int id_pago_orador = rs.getInt("pago_orador");
            PagoOradorData pago_orador = PagoOradorRepository.buscarPorId(id_pago_orador);
            
            int id_estatus = rs.getInt("estatus");      
            StatusData estatus = StatusRepository.buscarPorId(id_estatus);            
                    
            orden.setCliente(cliente);
            orden.setOrador(orador);
            orden.setCobro(cobro);
            orden.setPago_plataforma(pago_plataforma);
            orden.setPago_orador(pago_orador);
            orden.setRegistro(estatus);
            
        }
        
        return orden;
    }
}
