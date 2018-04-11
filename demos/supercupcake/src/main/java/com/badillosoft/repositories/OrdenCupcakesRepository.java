package com.badillosoft.repositories;

import com.badillosoft.beans.*;
import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class OrdenCupcakesRepository {
	@Autowired
	DataSource dataSource;
	@Autowired
	CupcakeRepository cupcakeRepository;
	@Autowired
	@Lazy
	OrdenRepository ordenRepository;
	
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
    
    public  void insertar(OrdenCupcakesBean ordenCupcakes) throws SQLException {
        PreparedStatement ps = getPreparedStatement("INSERT INTO orden_cupcakes (orden, cupcake, multiplicador) VALUES (?, ?, ?);");
        
        ps.setInt(1, ordenCupcakes.getOrden().getId());
        ps.setInt(2, ordenCupcakes.getCupcake().getId());
        ps.setInt(3, ordenCupcakes.getMultiplicador()); 
        
        ps.executeUpdate();
        
        Integer id = getInsertedId(ps);
        
        ordenCupcakes.setId(id);
        
        ps.close();
    }
    
    public  void actualizar(OrdenCupcakesBean ordenCupcakes) throws SQLException {
        PreparedStatement ps = getPreparedStatement("UPDATE orden_cupcakes SET orden=?, cupcake=?, multiplicador=? WHERE id=?;");
        
        ps.setInt(1, ordenCupcakes.getOrden().getId());
        ps.setInt(2, ordenCupcakes.getCupcake().getId());
        ps.setInt(3, ordenCupcakes.getMultiplicador());
        ps.setInt(4, ordenCupcakes.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  void eliminar(OrdenCupcakesBean ordenCupcakes) throws SQLException {
        PreparedStatement ps = getPreparedStatement("DELETE FROM orden_cupcakes WHERE id=?;");
        
        ps.setInt(1, ordenCupcakes.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  OrdenCupcakesBean buscarPorId(Integer id) throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM orden_cupcakes WHERE id=?;");
        
        if (ps == null) {
        	return null;
        }
        
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        OrdenCupcakesBean ordenCupcakes = null;
        
        if (rs.next()) {
        	ordenCupcakes = new OrdenCupcakesBean();
            ordenCupcakes.setId(id);
        	
            Integer id_orden = rs.getInt("orden");
            Integer id_cupcake = rs.getInt("cupcake");
            int multiplicador = rs.getInt("multiplicador");
            
            OrdenBean orden = ordenRepository.buscarPorId(id_orden);
            CupcakeBean cupcake = cupcakeRepository.buscarPorId(id_cupcake);
            
            ordenCupcakes.setOrden(orden);
            ordenCupcakes.setCupcake(cupcake);
            ordenCupcakes.setMultiplicador(multiplicador);
        }
        
        rs.close();
        ps.close();
        
        return ordenCupcakes;
    }
    
}

