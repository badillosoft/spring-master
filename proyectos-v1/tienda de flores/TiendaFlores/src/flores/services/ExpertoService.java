package flores.services;

import flores.data.*;

public class ExpertoService {
	
	
	public static void diseño(OrdenDiseño orden) {
		StatusData statusData = new StatusData();
		statusData.setText("el experto envia el diseño");
		orden.setStatus(statusData);
		
		ClienteService.aprobar(orden);
	}
	
	public static void pedir(OrdenDiseño orden) {
		StatusData status = new StatusData();
		status.setText("el experto pide las cosas al invernadero");
		orden.setStatus(status);
		
		InvernaderoService.pedir(orden);
	}
	
	public static void entregar(OrdenDiseño orden) {
		StatusData status = new StatusData();
		status.setText("el experto recibe las flores");
		orden.setStatus(status);
		
		ClienteService.instalacion(orden);
	}

}
