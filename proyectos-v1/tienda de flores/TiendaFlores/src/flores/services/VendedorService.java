package flores.services;

import flores.data.OrdenDiseño;
import flores.data.PagoData;
import flores.data.StatusData;

public class VendedorService {
	public static void pedir(OrdenDiseño orden){
			StatusData status = new StatusData();
			status.setText("el vendedor recibe la orden");
			orden.setStatus(status);
			
			ClienteService.cobrar(orden);
	}
	public static void pagar(OrdenDiseño orden) {
		StatusData status = new StatusData();
		status.setText("el vendedor procesa el pago");
		orden.setStatus(status);
		
		PagoData pago = new PagoData();
		pago.setPagado(true);
		orden.setPago(pago);
		
		ExpertoService.pedir(orden);
	}
}
