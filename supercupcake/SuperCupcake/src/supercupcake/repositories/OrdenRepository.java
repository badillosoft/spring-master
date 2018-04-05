package supercupcake.repositories;

import supercupcake.data.*;
import java.sql.*;

public class OrdenRepository {
    
    public static void insertar(OrdenData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO ordenes (cliente, vendedor, estatus) VALUES (?, ?, ?);");
        
        st.setInt(1, orden.getCliente().getId());
        st.setInt(2, orden.getVendedor().getId());
        st.setInt(3, orden.getEstatus().getId());
        
        int id = DBManager.executeInsert(st);
        
        orden.setId(id);
    }
    
    public static void actualizar(OrdenData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE ordenes SET cliente=?, vendedor=?, cocina=?, cobro=?, pago=?. estatus=? WHERE id=?;");
        
        st.setInt(1, orden.getCliente().getId());
        st.setInt(2, orden.getVendedor().getId());
        if (orden.getCocina() != null) {
            st.setInt(3, orden.getCocina().getId());
        }
        if (orden.getCobro() != null) {
            st.setInt(4, orden.getCobro().getId());
        }
        if (orden.getPago() != null) {
            st.setInt(5, orden.getPago().getId());
        }
        st.setInt(6, orden.getEstatus().getId());
        st.setInt(7, orden.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(OrdenData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM ordenes WHERE id=?;");
        
        st.setInt(1, orden.getId());
        
        st.executeUpdate();
    }
    
    public static OrdenData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM ordenes WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        OrdenData orden = new OrdenData();
        orden.setId(id);
        
        if (rs.next()) {
            int id_cliente = rs.getInt("cliente");
            int id_vendedor = rs.getInt("vendedor");
            int id_cocina = rs.getInt("cocina");
            int id_cobro = rs.getInt("cobro");
            int id_pago = rs.getInt("pago");
            int id_estatus = rs.getInt("estatus");
            
            ClienteData cliente = ClienteRepository.buscarPorId(id_cliente);
            VendedorData vendedor = VendedorRepository.buscarPorId(id_vendedor);
            CocinaData cocina = CocinaRepository.buscarPorId(id_cocina);
            CobroData cobro = CobroRepository.buscarPorId(id_cobro);
            PagoData pago = PagoRepository.buscarPorId(id_pago);
            EstatusData estatus = EstatusRepository.buscarPorId(id_estatus);
            
            orden.setCliente(cliente);
            orden.setVendedor(vendedor);
            orden.setCocina(cocina);
            orden.setCobro(cobro);
            orden.setPago(pago);
            orden.setEstatus(estatus);
        } else {
            return null;
        }
        
        return orden;
    }
    
}
