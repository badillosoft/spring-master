package supercupcake.repositories;

import supercupcake.data.*;
import java.sql.*;
import java.util.*;

public class OrdenRepository {
    
    public static void insertar(OrdenData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO ordenes (cliente, vendedor, estatus) VALUES (?, ?, ?);");
        
        st.setInt(1, orden.getCliente().getId());
        st.setInt(2, orden.getVendedor().getId());
        st.setInt(3, orden.getEstatus().getId());
        
        int id = DBManager.executeInsert(st);
        
        orden.setId(id);
        
        OrdenRepository.insertarListaCupcakes(orden);
    }
    
    public static void insertarListaCupcakes(OrdenData orden) throws SQLException {
        Map<Integer, Integer> cupcake_table = new HashMap();
        
        for (CupcakeData cupcake : orden.getCupcakes()) {
            if (cupcake_table.containsKey(cupcake.getId())) {
                int multiplicador = cupcake_table.get(cupcake.getId());
                cupcake_table.put(cupcake.getId(), multiplicador + 1);
                continue;
            }
            cupcake_table.put(cupcake.getId(), 1);
        }
        
        for (int id_cupcake : cupcake_table.keySet()) {
            int multiplicador = cupcake_table.get(id_cupcake);
            OrdenCupcakesData orden_cupcakes = new OrdenCupcakesData();
            orden_cupcakes.setOrden(orden);
            CupcakeData cupcake = new CupcakeData();
            cupcake.setId(id_cupcake);
            orden_cupcakes.setCupcake(cupcake);
            orden_cupcakes.setMultiplicador(multiplicador);
            OrdenCupcakesRepository.insertar(orden_cupcakes);
        }
    }
    
    public static void actualizar(OrdenData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE ordenes SET cliente=?, vendedor=?, cocina=?, cobro=?, pago=?, estatus=? WHERE id=?;");
        
        st.setInt(1, orden.getCliente().getId());
        st.setInt(2, orden.getVendedor().getId());
        if (orden.getCocina() != null) {
            st.setInt(3, orden.getCocina().getId());
        } else {
            st.setNull(3, java.sql.Types.INTEGER);
        }
        if (orden.getCobro() != null) {
            st.setInt(4, orden.getCobro().getId());
        } else {
            st.setNull(4, java.sql.Types.INTEGER);
        }
        if (orden.getPago() != null) {
            st.setInt(5, orden.getPago().getId());
        } else {
            st.setNull(5, java.sql.Types.INTEGER);
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
    
    public static void cargarLista(OrdenData orden) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM orden_cupcakes WHERE orden=?");
        
        st.setInt(1, orden.getId());
        
        ResultSet rs = st.executeQuery();
        
        List<CupcakeData> cupcakes = new ArrayList();
        
        while (rs.next()) {
            int id_cupcake = rs.getInt("cupcake");
            int multiplicador = rs.getInt("multiplicador");
            
            CupcakeData cupcake = CupcakeRepository.buscarPorId(id_cupcake);
            
            for (int i = 0; i < multiplicador; i++) {
                cupcakes.add(cupcake);
            }
        }
        
        orden.setCupcakes(cupcakes);
    }
    
    public static double calcularTotal(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT SUM(A.multiplicador * B.precio) AS total FROM orden_cupcakes AS A LEFT JOIN cupcakes AS B ON A.cupcake=B.id WHERE A.orden=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        if (rs.next()) {
            double total = rs.getInt("total");
            return total;
        }
        
        return -1;
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
