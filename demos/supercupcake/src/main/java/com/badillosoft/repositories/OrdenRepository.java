package com.badillosoft.repositories;

import com.badillosoft.beans.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class OrdenRepository {
	
	@Autowired
	DataSource dataSource;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	CobroRepository cobroRepository;
	@Autowired
	CocinaRepository cocinaRepository;
	@Autowired
	CupcakeRepository cupcakeRepository;
	@Autowired
	EstatusRepository estatusRepository;
	@Autowired
	@Lazy
	OrdenCupcakesRepository ordenCupcakesRepository;
	@Autowired
	PagoRepository pagoRepository;
	@Autowired
	VendedorRepository vendedorRepository;
	
	private PreparedStatement getPreparedStatement(String query) {
		try {
			Connection conn = dataSource.getConnection();
			return conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Integer getInsertedId(PreparedStatement ps) {
		try {
			ResultSet rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				int id = rs.getInt(1);
				rs.close();
				return id;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public  void insertar(OrdenBean orden) throws SQLException {
        PreparedStatement ps = getPreparedStatement("INSERT INTO ordenes (estatus) VALUES (?);");
        
        ps.setInt(1, orden.getEstatus().getId());
        
        ps.executeUpdate();
        
        Integer id = getInsertedId(ps);
        
        orden.setId(id);
        
        ps.close();
    }
    
    public  void insertarListaCupcakes(OrdenBean orden) throws SQLException {
        Map<Integer, Integer> cupcake_table = new HashMap<>();
        
        for (CupcakeBean cupcake : orden.getCupcakes()) {
            if (cupcake_table.containsKey(cupcake.getId())) {
                int multiplicador = cupcake_table.get(cupcake.getId());
                cupcake_table.put(cupcake.getId(), multiplicador + 1);
                continue;
            }
            cupcake_table.put(cupcake.getId(), 1);
        }
        
        for (Integer id_cupcake : cupcake_table.keySet()) {
            int multiplicador = cupcake_table.get(id_cupcake);
            OrdenCupcakesBean orden_cupcakes = new OrdenCupcakesBean();
            orden_cupcakes.setOrden(orden);
            CupcakeBean cupcake = new CupcakeBean();
            cupcake.setId(id_cupcake);
            orden_cupcakes.setCupcake(cupcake);
            orden_cupcakes.setMultiplicador(multiplicador);
            ordenCupcakesRepository.insertar(orden_cupcakes);
        }
    }
    
    public  void actualizar(OrdenBean orden) throws SQLException {
        PreparedStatement ps = getPreparedStatement("UPDATE ordenes SET cliente=?, vendedor=?, cocina=?, cobro=?, pago=?, estatus=? WHERE id=?;");
        
        if (orden.getCliente() != null) {
        	ps.setInt(1, orden.getCliente().getId());
        } else {
            ps.setNull(1, java.sql.Types.INTEGER);
        }
        if (orden.getVendedor() != null) {
        	ps.setInt(2, orden.getVendedor().getId());
        } else {
            ps.setNull(2, java.sql.Types.INTEGER);
        }
        if (orden.getCocina() != null) {
            ps.setInt(3, orden.getCocina().getId());
        } else {
            ps.setNull(3, java.sql.Types.INTEGER);
        }
        if (orden.getCobro() != null) {
            ps.setInt(4, orden.getCobro().getId());
        } else {
            ps.setNull(4, java.sql.Types.INTEGER);
        }
        if (orden.getPago() != null) {
            ps.setInt(5, orden.getPago().getId());
        } else {
            ps.setNull(5, java.sql.Types.INTEGER);
        }
        ps.setInt(6, orden.getEstatus().getId());
        ps.setInt(7, orden.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  void eliminar(OrdenBean orden) throws SQLException {
        PreparedStatement ps = getPreparedStatement("DELETE FROM ordenes WHERE id=?;");
        
        ps.setInt(1, orden.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  void cargarLista(OrdenBean orden) throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM orden_cupcakes WHERE orden=?");
        
        ps.setInt(1, orden.getId());
        
        ResultSet rs = ps.executeQuery();
        
        List<CupcakeBean> cupcakes = new ArrayList<>();
        
        while (rs.next()) {
            Integer id_cupcake = rs.getInt("cupcake");
            int multiplicador = rs.getInt("multiplicador");
            
            CupcakeBean cupcake = cupcakeRepository.buscarPorId(id_cupcake);
            
            for (int i = 0; i < multiplicador; i++) {
                cupcakes.add(cupcake);
            }
        }
        
        orden.setCupcakes(cupcakes);
        
        rs.close();
        ps.close();
    }
    
    public Double calcularTotal(Integer id) throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT SUM(A.multiplicador * B.precio) AS total FROM orden_cupcakes AS A LEFT JOIN cupcakes AS B ON A.cupcake=B.id WHERE A.orden=?;");
        
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        Double total = null;
        
        if (rs.next()) {
            total = rs.getDouble("total");
        }
        
        rs.close();
        ps.close();
        
        return total;
    }
    
    public  OrdenBean buscarPorId(Integer id) throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM ordenes WHERE id=?;");
        
        if (ps == null) {
        	return null;
        }
        
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        OrdenBean orden = null;
        
        if (rs.next()) {
        	orden = new OrdenBean();
            orden.setId(id);
        	
            Integer id_cliente = rs.getInt("cliente");
            Integer id_vendedor = rs.getInt("vendedor");
            Integer id_cocina = rs.getInt("cocina");
            Integer id_cobro = rs.getInt("cobro");
            Integer id_pago = rs.getInt("pago");
            Integer id_estatus = rs.getInt("estatus");
            
            ClienteBean cliente = clienteRepository.buscarPorId(id_cliente);
            VendedorBean vendedor = vendedorRepository.buscarPorId(id_vendedor);
            CocinaBean cocina = cocinaRepository.buscarPorId(id_cocina);
            CobroBean cobro = cobroRepository.buscarPorId(id_cobro);
            PagoBean pago = pagoRepository.buscarPorId(id_pago);
            EstatusBean estatus = estatusRepository.buscarPorId(id_estatus);
            
            orden.setCliente(cliente);
            orden.setVendedor(vendedor);
            orden.setCocina(cocina);
            orden.setCobro(cobro);
            orden.setPago(pago);
            orden.setEstatus(estatus);
            
            cargarLista(orden);
        }

        rs.close();
        ps.close();
        
        return orden;
    }
    
}
