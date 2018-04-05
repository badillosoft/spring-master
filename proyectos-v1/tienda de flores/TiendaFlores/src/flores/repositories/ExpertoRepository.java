package flores.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import flores.data.*;

public class ExpertoRepository {
	
	public static void insertar(ExpertoData experto)throws SQLException {
		
		PreparedStatement st = DBManager.generateQuery("insert into expertos (nombre,correo) values (?,?);");
		
		st.setString(1, experto.getNombre());
		st.setString(2,experto.getCorreo());
		
		int id  = DBManager.excuteInsert(st);
		
		experto.setId(id);
		
	}
	public static void actualizar(ExpertoData experto)  throws SQLException{
		PreparedStatement st = DBManager.generateQuery("update expertos set nombre=? correo=? where id=?;");
		st.setString(1,experto.getNombre());
		st.setString(2, experto.getCorreo());
		st.setInt(3, experto.getId());
		st.executeUpdate();
		
	}
	
	public static void eliminar(ExpertoData experto) throws SQLException {
		PreparedStatement st = DBManager.generateQuery("delete from expertos where id=?;");
		st.setInt(1, experto.getId());
		st.executeUpdate();
	}
}
