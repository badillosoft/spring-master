package flores.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import flores.data.*;

public class OrdenPlantasRepository {
	public static void consultaCliente(OrdenPlantasData orden)throws SQLException {
		
		PreparedStatement st = DBManager.generateQuery("insert into orden_plantas (orden, planta, multiplicador) values (?, ?, ?);");
		
		st.setObject(1, orden.getOrden().getId());
		st.setObject(2, orden.getPlanta().getId());
		st.setInt(3,orden.getId());
		
		
		int id  = DBManager.excuteInsert(st);
		
		orden.setId(id);

}

}
