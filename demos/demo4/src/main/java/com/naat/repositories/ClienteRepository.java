package com.naat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naat.beans.ClienteBean;

@Repository
public class ClienteRepository {

	@Autowired
	DataSource dataSource;
	
	public boolean insertar(ClienteBean cliente) throws SQLException {
		Connection conn = dataSource.getConnection();
		
		String query = "INSERT INTO clientes (nombre) VALUES (?)";
		
		PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, cliente.getNombre());
		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		
		if (rs.next()) {
			Integer id = rs.getInt(1);
			cliente.setId(id);
			return true;
		}
		
		return false;
	}
	
}
