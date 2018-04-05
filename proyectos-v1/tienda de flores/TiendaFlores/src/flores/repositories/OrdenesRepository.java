package flores.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import flores.data.*;

public class OrdenesRepository {
	
	public static void consultaCliente(OrdenDiseño diseño)throws SQLException {
			
			PreparedStatement st = DBManager.generateQuery("insert into ordenes(cliente,experto,status) values(?,?,?);");
			
			st.setObject(1, diseño.getCliente().getId());
			st.setObject(2, diseño.getExperto().getId());
			st.setObject(3, diseño.getStatus().getId());
			
			
			int id  = DBManager.excuteInsert(st);
			
			diseño.setId(id);
	
	}
	
	public static void actualizarStatus(OrdenDiseño diseño)throws SQLException {
		
		PreparedStatement st = DBManager.generateQuery("update ordenes set status=? where id=?;");
		
		st.setObject(1, diseño.getStatus().getId());
		st.setInt(2, diseño.getId());
		
		st.executeUpdate();

	}

}
