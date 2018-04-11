package com.badillosoft.repositories;

import com.badillosoft.beans.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository {
	
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

	public void insertar(ClienteBean cliente) throws SQLException {
		PreparedStatement ps = getPreparedStatement("INSERT INTO clientes (nombre, correo) VALUES (?, ?);");

		ps.setString(1, cliente.getNombre());
		ps.setString(2, cliente.getCorreo());

		ps.executeUpdate();
		
		Integer id = getInsertedId(ps);
		
		ps.close();

		cliente.setId(id);
	}

	public void actualizar(ClienteBean cliente) throws SQLException {
		PreparedStatement ps = getPreparedStatement("UPDATE clientes SET nombre=?, correo=? WHERE id=?;");

		ps.setString(1, cliente.getNombre());
		ps.setString(2, cliente.getCorreo());
		ps.setInt(3, cliente.getId());

		ps.executeUpdate();
		
		ps.close();
	}

	public void eliminar(ClienteBean cliente) throws SQLException {
		PreparedStatement ps = getPreparedStatement("DELETE FROM clientes WHERE id=?;");

		ps.setInt(1, cliente.getId());

		ps.executeUpdate();
		
		ps.close();
	}

	public ClienteBean buscarAleatorio() throws SQLException {
		PreparedStatement ps = getPreparedStatement("SELECT * FROM clientes ORDER BY RAND() LIMIT 1;");

		ResultSet rs = ps.executeQuery();

		ClienteBean cliente = null;

		if (rs.next()) {
			cliente = new ClienteBean();
			Integer id = rs.getInt("id");
			String nombre = rs.getString("nombre");
			String correo = rs.getString("correo");

			cliente.setId(id);
			cliente.setNombre(nombre);
			cliente.setCorreo(correo);
		}
		
		rs.close();
		ps.close();

		return cliente;
	}

	public ClienteBean buscarPorId(Integer id) throws SQLException {
		PreparedStatement ps = getPreparedStatement("SELECT * FROM clientes WHERE id=?;");

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		ClienteBean cliente = null;

		if (rs.next()) {
			cliente = new ClienteBean();
			cliente.setId(id);
			
			String nombre = rs.getString("nombre");
			String correo = rs.getString("correo");

			cliente.setNombre(nombre);
			cliente.setCorreo(correo);
		}
		
		rs.close();
		ps.close();

		return cliente;
	}

	public List<ClienteBean> buscarPorNombre(String like) throws SQLException {
		PreparedStatement ps = getPreparedStatement("SELECT * FROM clientes WHERE nombre like ?;");

		ps.setString(1, like);

		ResultSet rs = ps.executeQuery();

		List<ClienteBean> clientes = new ArrayList<>();

		while (rs.next()) {
			Integer id = rs.getInt("id");
			String nombre = rs.getString("nombre");
			String correo = rs.getString("correo");

			ClienteBean cliente = new ClienteBean();
			cliente.setId(id);
			cliente.setNombre(nombre);
			cliente.setCorreo(correo);

			clientes.add(cliente);
		}
		
		rs.close();
		ps.close();

		return clientes;
	}

}
