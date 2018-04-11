package com.badillosoft.repositories;

import com.badillosoft.beans.*;
import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VendedorRepository {
	
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
    
    public  void insertar(VendedorBean vendedor) throws SQLException {
        PreparedStatement ps = getPreparedStatement("INSERT INTO vendedores (nombre, correo) VALUES (?, ?);");
        
        ps.setString(1, vendedor.getNombre());
        ps.setString(2, vendedor.getCorreo());
        
        ps.executeUpdate();
        
        Integer id = getInsertedId(ps);
        
        vendedor.setId(id);
        
        ps.close();
    }
    
    public  void actualizar(VendedorBean vendedor) throws SQLException {
        PreparedStatement ps = getPreparedStatement("UPDATE vendedores SET nombre=?, correo=? WHERE id=?;");
        
        ps.setString(1, vendedor.getNombre());
        ps.setString(2, vendedor.getCorreo());
        ps.setInt(3, vendedor.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  void eliminar(VendedorBean vendedor) throws SQLException {
        PreparedStatement ps = getPreparedStatement("DELETE FROM vendedores WHERE id=?;");
        
        ps.setInt(1, vendedor.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  VendedorBean buscarPorId(Integer id) throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM vendedores WHERE id=?;");
        
        if (ps == null) {
        	return null;
        }
        
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        VendedorBean vendedor = null;
        
        if (rs.next()) {
        	vendedor = new VendedorBean();
            vendedor.setId(id);
        	
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            
            vendedor.setNombre(nombre);
            vendedor.setCorreo(correo);
        }
        
        rs.close();
        ps.close();
        
        return vendedor;
    }
    
    public  VendedorBean buscarAleatorio() throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM vendedores ORDER BY RAND() LIMIT 1;");
        
        ResultSet rs = ps.executeQuery();
        
        VendedorBean vendedor = null;
        
        if (rs.next()) {
        	vendedor = new VendedorBean();
        	
            Integer id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            
            vendedor.setId(id);
            vendedor.setNombre(nombre);
            vendedor.setCorreo(correo);
        }
        
        rs.close();
        ps.close();
        
        return vendedor;
    }
    
}
