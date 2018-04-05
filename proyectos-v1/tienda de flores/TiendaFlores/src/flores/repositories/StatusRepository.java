package flores.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import flores.data.StatusData;

public class StatusRepository {
	
	public static void insertar(StatusData status)throws SQLException {
		
		PreparedStatement st = DBManager.generateQuery("insert into status (descripcion) values ('?');");
		
		st.setString(1, status.getDescripcion());
		
		int id  = DBManager.excuteInsert(st);
		
		status.setId(id);
		
	}

	public static void actualizar(StatusData status)  throws SQLException{
		PreparedStatement st = DBManager.generateQuery("update status set descripcion=? where id=?;");
		st.setString(1,status.getDescripcion());
		st.setInt(2,status.getId());
		
		st.executeUpdate();
		
	}
	
	public static void eliminar(StatusData status) throws SQLException {
		PreparedStatement st = DBManager.generateQuery("delete from status where id=?;");
		st.setInt(1, status.getId());
		st.executeUpdate();
	}

}
