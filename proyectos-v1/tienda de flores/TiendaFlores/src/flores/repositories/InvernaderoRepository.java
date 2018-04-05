package flores.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import flores.data.*;

public class InvernaderoRepository {
		
	public static void insertar(InvernaderoData invernadero)throws SQLException {
		
		PreparedStatement st = DBManager.generateQuery("insert into invernadero (nombre,direccion) values (?,?);");
		
		st.setString(1, invernadero.getNombre());
		st.setString(2,invernadero.getDireccion());
		
		int id  = DBManager.excuteInsert(st);
		
		invernadero.setId(id);
		
	}
	public static void actualizar(InvernaderoData invernadero)  throws SQLException{
		PreparedStatement st = DBManager.generateQuery("update invernadero set nombre=? direccion=? where id=?;");
		st.setString(1,invernadero.getNombre());
		st.setString(2, invernadero.getDireccion());
		st.setInt(3, invernadero.getId());
		st.executeUpdate();
		
	}
	
	public static void eliminar(InvernaderoData invernadero) throws SQLException {
		PreparedStatement st = DBManager.generateQuery("delete from invernadero where id=?;");
		st.setInt(1, invernadero.getId());
		st.executeUpdate();
	}
}
