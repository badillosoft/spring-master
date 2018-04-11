package com.badillosoft.repositories;

import com.badillosoft.beans.*;
import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CobroRepository {

	@Autowired
	DataSource dataSource;

	private PreparedStatement getPreparedStatement(String query) {
		try {
			Connection conn = dataSource.getConnection();
			return conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertar(CobroBean cobro) throws SQLException {
		PreparedStatement ps = getPreparedStatement(
				"INSERT INTO cobros (total, token_paypal, completado) VALUES (?, ?, ?);");

		ps.setDouble(1, cobro.getTotal());
		ps.setString(2, cobro.getToken_paypal());
		ps.setBoolean(3, cobro.isCompletado());
		
		ps.executeUpdate();

		Integer id = getInsertedId(ps);

		cobro.setId(id);
		
		ps.close();
	}

	public void actualizar(CobroBean cobro, OrdenBean orden) throws SQLException {
		PreparedStatement ps = getPreparedStatement(
				"UPDATE cobros SET total=?, token_paypal=?, completado=? WHERE id=?;");

		ps.setDouble(1, cobro.getTotal());
		ps.setString(2, cobro.getToken_paypal());
		ps.setBoolean(3, cobro.isCompletado());
		ps.setInt(4, cobro.getId());

		ps.executeUpdate();
		
		ps.close();
	}

	public void eliminar(CobroBean cobro) throws SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM cobros WHERE id=?;");

		ps.setInt(1, cobro.getId());

		ps.executeUpdate();
		
		ps.close();
	}

	public Double calcularTotal(OrdenBean orden) throws SQLException {
		PreparedStatement ps = getPreparedStatement(
				"select sum(orden_cupcakes.multiplicador * cupcakes.precio) as total from orden_cupcakes left join cupcakes on orden_cupcakes.cupcake=cupcakes.id where orden_cupcakes.orden=?");

		ps.setInt(1, orden.getId());

		ResultSet rs = ps.executeQuery();

		Double total = null;
		
		if (rs.next()) {
			total = rs.getDouble("total");
		}
		
		rs.close();
		ps.close();

		return total;
	}

	public CobroBean buscarPorId(int id) throws SQLException {
		PreparedStatement ps = getPreparedStatement("SELECT * FROM cobros WHERE id=?;");

		if (ps == null) {
        	return null;
        }
		
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		CobroBean cobro = null;

		if (rs.next()) {
			cobro = new CobroBean();
			cobro.setId(id);
			
			double total = rs.getDouble("total");
			String token_paypal = rs.getString("token_paypal");
			boolean completado = rs.getBoolean("completado");

			cobro.setTotal(total);
			cobro.setToken_paypal(token_paypal);
			cobro.setCompletado(completado);
		}
		
		rs.close();
		ps.close();

		return cobro;
	}

}
