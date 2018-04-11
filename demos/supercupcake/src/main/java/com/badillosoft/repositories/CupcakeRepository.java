package com.badillosoft.repositories;

import com.badillosoft.beans.*;
import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CupcakeRepository {
	
	@Autowired
	DataSource dataSource;
	
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
    
    public  void insertar(CupcakeBean cupcake) throws SQLException {
        PreparedStatement ps = getPreparedStatement("INSERT INTO cupcakes (tipo, precio) VALUES (?, ?);");
        
        ps.setString(1, cupcake.getTipo());
        ps.setDouble(2, cupcake.getPrecio());
        
        ps.executeUpdate();
        
        Integer id = getInsertedId(ps);
        
        cupcake.setId(id);
        
        ps.close();
    }
    
    public  void actualizar(CupcakeBean cupcake) throws SQLException {
        PreparedStatement ps = getPreparedStatement("UPDATE cupcakes SET tipo=?, precio=? WHERE id=?;");
        
        ps.setString(1, cupcake.getTipo());
        ps.setDouble(2, cupcake.getPrecio());
        ps.setInt(3, cupcake.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  void eliminar(CupcakeBean cupcake) throws SQLException {
        PreparedStatement ps = getPreparedStatement("DELETE FROM cupcakes WHERE id=?;");
        
        ps.setInt(1, cupcake.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  CupcakeBean buscarPorId(Integer id) throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM cupcakes WHERE id=?;");
        
        if (ps == null) {
        	return null;
        }
        
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        CupcakeBean cupcake = null;
        
        if (rs.next()) {
        	cupcake = new CupcakeBean();
            cupcake.setId(id);
        	
            String tipo = rs.getString("tipo");
            double precio = rs.getDouble("precio");
            
            cupcake.setTipo(tipo);
            cupcake.setPrecio(precio);
        }
        
        rs.close();
        ps.close();
        
        return cupcake;
    }
    
    public  CupcakeBean buscarAleatorio() throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM cupcakes ORDER BY RAND() LIMIT 1;");
        
        ResultSet rs = ps.executeQuery();
        
        CupcakeBean cupcake = null;
        
        if (rs.next()) {
        	cupcake = new CupcakeBean();
        	
            Integer id = rs.getInt("id");
            String tipo = rs.getString("tipo");
            double precio = rs.getDouble("precio");
            
            cupcake.setId(id);
            cupcake.setTipo(tipo);
            cupcake.setPrecio(precio);
        }
        
        rs.close();
        ps.close();
        
        return cupcake;
    }
    
}

