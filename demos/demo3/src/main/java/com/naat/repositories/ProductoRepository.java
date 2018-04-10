package com.naat.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naat.beans.ProductoBean;

@Repository
public class ProductoRepository {

	@Autowired
	DataSource dataSource;
	
	public void insertar(ProductoBean producto) throws SQLException {
		Connection conn = dataSource.getConnection();
		
		String query = "INSERT INTO productos (nombre, sku) VALUES (?, ?);";
		
		PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, producto.getNombre());
		ps.setString(2, producto.getSku());
		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		
		if (rs.next()) {
			Integer id = rs.getInt(1);
			producto.setId(id);
		}
	}
	
}
