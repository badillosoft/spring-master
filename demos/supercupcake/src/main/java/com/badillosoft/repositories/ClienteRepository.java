package com.badillosoft.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.badillosoft.beans.ClienteBean;
import com.badillosoft.beans.IEntityBean;

public class ClienteRepository extends EntityRepository {

	@SuppressWarnings("unused")
	private String getTableName() {
		return "clientes";
	}
	
	@SuppressWarnings("unused")
	private String getFieldNames() {
		return "nombre, correo";
	}
	
	@SuppressWarnings("unused")
	private void adjustInsertPreparedStatement(PreparedStatement ps, IEntityBean entity) throws SQLException {
		ClienteBean cliente = (ClienteBean) entity;
		
		ps.setString(1, cliente.getNombre());
		ps.setString(2, cliente.getCorreo());
	}
	
	@SuppressWarnings("unused")
	private void adjustInsertEntity(ResultSet rs, IEntityBean entity) throws SQLException {
		ClienteBean cliente = (ClienteBean) entity;
		
		cliente.setNombre(rs.getString("nombre"));
		cliente.setCorreo(rs.getString("correo"));
	}
	
}
