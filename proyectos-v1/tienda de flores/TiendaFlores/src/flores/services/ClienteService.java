package flores.services;

import flores.data.*;

public class ClienteService {
	
	public static void consulta(OrdenDiseño orden) {
		StatusData statusData = new StatusData();
		statusData.setText("el cliente hace la consulta");
		orden.setStatus(statusData);
		
		ExpertoService.diseño(orden);
		
	}
	
public static void aprobar(OrdenDiseño orden) {
	StatusData statusData = new StatusData();
	statusData.setText("el cliente aprueba el diseño");
	orden.setStatus(statusData);
	
	VendedorService.pedir(orden);
}

public static void cobrar(OrdenDiseño orden) {
	CobroData cobro = new CobroData();
	cobro.setCobrado(true);
	
	StatusData status = new StatusData();
	status.setText("el clientes acepta el cobro");
	orden.setStatus(status);
	
	VendedorService.pagar(orden);
	
}

public static void instalacion(OrdenDiseño orden) {
	StatusData status = new StatusData();
	status.setText("el cliente recibe su instalacion");
	orden.setStatus(status);
	
}

}
