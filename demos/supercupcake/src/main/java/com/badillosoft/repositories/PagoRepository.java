package com.badillosoft.repositories;

import com.badillosoft.beans.*;
import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PagoRepository {
	
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
    
    public  void insertar(PagoBean pago) throws SQLException {
        PreparedStatement ps = getPreparedStatement("INSERT INTO pagos (token_paypal, completado) VALUES (?, ?);");
        
        ps.setString(1, pago.getToken_paypal());
        ps.setBoolean(2, pago.isCompletado());
        
        ps.executeUpdate();
        
        Integer id = getInsertedId(ps);
        
        pago.setId(id);
        
        ps.close();
    }
    
    public  void actualizar(PagoBean pago) throws SQLException {
        PreparedStatement ps = getPreparedStatement("UPDATE pagos SET token_paypal=?, completado=? WHERE id=?;");
        
        ps.setString(1, pago.getToken_paypal());
        ps.setBoolean(2, pago.isCompletado());
        ps.setInt(3, pago.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  void eliminar(PagoBean pago) throws SQLException {
        PreparedStatement ps = getPreparedStatement("DELETE FROM pagos WHERE id=?;");
        
        ps.setInt(1, pago.getId());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public  PagoBean buscarPorId(Integer id) throws SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT * FROM pagos WHERE id=?;");
        
        if (ps == null) {
        	return null;
        }
        
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        PagoBean pago = null;
        
        if (rs.next()) {
        	pago = new PagoBean();
            pago.setId(id);
        	
            String token_paypal = rs.getString("token_paypal");
            boolean completado = rs.getBoolean("completado");
            
            pago.setToken_paypal(token_paypal);
            pago.setCompletado(completado);
        }
        
        rs.close();
        ps.close();
        
        return pago;
    }
    
}
