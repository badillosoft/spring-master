package flores.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import flores.data.*;

public class VendedorRepository {
	
	public static void insertar(VendedorData vendedor)throws SQLException {
			
			PreparedStatement st = DBManager.generateQuery("insert into vendedores (nombre,correo) values (?,?);");
			
			st.setString(1, vendedor.getNombre());
			st.setString(2,vendedor.getCorreo());
			
			int id  = DBManager.excuteInsert(st);
			
			vendedor.setId(id);
			
		}
	
	public static void actualizar(VendedorData vendedor)  throws SQLException{
		PreparedStatement st = DBManager.generateQuery("update vendedores set nombre=? correo=? where id=?;");
		st.setString(1,vendedor.getNombre());
		st.setString(2, vendedor.getCorreo());
		st.setInt(3, vendedor.getId());
		st.executeUpdate();
		
	}
	
	public static void eliminar(VendedorData vendedor) throws SQLException {
		PreparedStatement st = DBManager.generateQuery("delete from vendedores where id=?;");
		st.setInt(1, vendedor.getId());
		st.executeUpdate();
	}

}
