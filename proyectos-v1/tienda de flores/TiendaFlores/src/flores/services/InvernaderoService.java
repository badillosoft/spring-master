package flores.services;

import flores.data.OrdenDiseño;
import flores.data.StatusData;

public class InvernaderoService {
	
	public static void pedir(OrdenDiseño orden) {
		StatusData status = new StatusData();
		status.setText("El invernadero acepta la orden");
		orden.setStatus(status);
		
		ExpertoService.entregar(orden);
		
	}
	

}
