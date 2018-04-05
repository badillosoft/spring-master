package flores.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import flores.data.OrdenPlantasData;

public class CobroRepository {
	public static void consultaCliente(Co orden)throws SQLException {
			
			PreparedStatement st = DBManager.generateQuery("insert into cobros (total, token_paypal, completado) values (\n" + 
					"	(select sum(orden_plantas.multiplicador * plantas.precio) as total from orden_plantas left join plantas on orden_plantas.planta=plantas.id where orden_plantas.orden=1),\n" + 
					"	'ABC123',\n" + 
					"	1);");
			
			st.setObject(1, orden.getOrden().getId());
			st.setObject(2, orden.getPlanta().getId());
			st.setInt(3,orden.getId());
			
			
			int id  = DBManager.excuteInsert(st);
			
			orden.setId(id);
	
	}

}
