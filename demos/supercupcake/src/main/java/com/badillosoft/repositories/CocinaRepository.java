package com.badillosoft.repositories;

import com.badillosoft.beans.*;
import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CocinaRepository {
	
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
    
    public  void insertar(CocinaBean cocina) throws SQLException {
        PreparedStatement ps = getPreparedStatement("INSERT INTO cocinas (nombre, direccion) VALUES (?, ?);");
        
        ps.setString(1, cocina.getNombre());
        ps.setString(2, cocina.getDireccion());
        
        ps.executeUpdate();
        
        Integer id = getInsertedId(ps);
        
        cocina.setId(id);
        
        ps.close();
    }
    
    public  void actualizar(CocinaBean cocina) throws SQLException {
        PreparedStatement ps = getPreparedStatement("UPDATE cocinas SET nombre=?, direccion=? WHERE id=?;");
        
        ps.setString(1, cocina.getNombre());
        ps.setString(2, cocina.getDireccion());
        ps.setInt(3, cocina.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  void eliminar(CocinaBean cocina) throws SQLException {
        PreparedStatement ps = getPreparedStatement("DELETE FROM cocinas WHERE id=?;");
        
        ps.setInt(1, cocina.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  CocinaBean buscarPorId(int id) throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM cocinas WHERE id=?;");
        
        if (ps == null) {
        	return null;
        }
        
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        CocinaBean cocina = null;
        
        if (rs.next()) {
        	cocina = new CocinaBean();
            cocina.setId(id);
        	
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            
            cocina.setNombre(nombre);
            cocina.setDireccion(direccion);
        }
        
        rs.close();
        ps.close();
        
        return cocina;
    }
    
    public  CocinaBean buscarAleatorio() throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM cocinas ORDER BY RAND() LIMIT 1;");
        
        ResultSet rs = ps.executeQuery();
        
        CocinaBean cocina = null;
        
        if (rs.next()) {
        	cocina = new CocinaBean();
        	
            Integer id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            
            cocina.setId(id);
            cocina.setNombre(nombre);
            cocina.setDireccion(direccion);
        }
        
        rs.close();
        ps.close();
        
        return cocina;
    }
    
}
