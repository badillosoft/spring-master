package com.badillosoft.repositories;

import com.badillosoft.beans.*;
import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstatusRepository {
	
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
    
    public  void insertar(EstatusBean estatus) throws SQLException {
        PreparedStatement ps = getPreparedStatement("INSERT INTO estatus (descripcion) VALUES (?);");
        
        ps.setString(1, estatus.getDescripcion());
        
        ps.executeUpdate();
        
        Integer id = getInsertedId(ps);
        
        estatus.setId(id);
        
        ps.close();
    }
    
    public  void actualizar(EstatusBean estatus) throws SQLException {
        PreparedStatement ps = getPreparedStatement("UPDATE estatus SET descripcion=? WHERE id=?;");
        
        ps.setString(1, estatus.getDescripcion());
        ps.setInt(2, estatus.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  void eliminar(EstatusBean estatus) throws SQLException {
        PreparedStatement ps = getPreparedStatement("DELETE FROM estatus WHERE id=?;");
        
        ps.setInt(1, estatus.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  EstatusBean buscarPorId(Integer id) throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM estatus WHERE id=?;");
        
        if (ps == null) {
        	return null;
        }
        
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        EstatusBean estatus = null;
        
        if (rs.next()) {
        	estatus = new EstatusBean();
            estatus.setId(id);
        	
            String descripcion = rs.getString("descripcion");
            
            estatus.setDescripcion(descripcion);
        }
        
        ps.close();
        
        return estatus;
    }
    
}
