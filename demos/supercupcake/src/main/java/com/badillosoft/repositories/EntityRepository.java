package com.badillosoft.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.badillosoft.beans.IEntityBean;

@Repository
public class EntityRepository {

	@Autowired
	DataSource dataSource;
	
	public void insertEntity(IEntityBean entity) throws Exception {
		Connection conn = dataSource.getConnection();
		
		String query = String.format("INSERT INTO %s (%s) VALUES (%s)", this.getTableName(), this.getFieldNames(), this.getValueTemplate());
		
		PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		
		this.adjustInsertPreparedStatement(ps, entity);
		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		
		if (rs.next()) {
			Integer id = rs.getInt(1);
			entity.setId(id);
			
			this.adjustInsertEntity(rs, entity);
		}
		
	}
	
	private String getTableName() throws Exception {
		throw new Exception("Not implemented yet");
	}
	
	private String getFieldNames() throws Exception {
		throw new Exception("Not implemented yet");
	}
	
	private String getValueTemplate() throws Exception {
		int n = StringUtils.countOccurrencesOf(this.getFieldNames(), ",");
		
		String template = "?";
		
		for (int i = 0; i < n; i++) {
			template += ", ?";
		}
		
		return template;
	}
	
	private void adjustInsertPreparedStatement(PreparedStatement ps, IEntityBean entity) throws SQLException {
		// TODO: Adjust PreparedStatement
	}
	
	private void adjustInsertEntity(ResultSet rs, IEntityBean entity) throws SQLException {
		// TODO: Adjust PreparedStatement
	}
	
}
