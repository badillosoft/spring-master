package flores.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import flores.data.*;




public class ClienteRepository {
	
	public static void insertar(ClienteData cliente)throws SQLException {
			
			PreparedStatement st = DBManager.generateQuery("insert into clientes (nombre,correo) values (?,?);");
			
			st.setString(1, cliente.getNombre());
			st.setString(2,cliente.getCorreo());
			
			int id  = DBManager.excuteInsert(st);
			
			cliente.setId(id);
			
		}
	public static void actualizar(ClienteData cliente)  throws SQLException{
		PreparedStatement st = DBManager.generateQuery("update clientes set nombre=? correo=? where id=?;");
		st.setString(1,cliente.getNombre());
		st.setString(2, cliente.getCorreo());
		st.setInt(3, cliente.getId());
		st.executeUpdate();
		
	}
	
	public static void eliminar(ClienteData cliente) throws SQLException {
		PreparedStatement st = DBManager.generateQuery("delete from clientes where id=?;");
		st.setInt(1, cliente.getId());
		st.executeUpdate();
	}

}
