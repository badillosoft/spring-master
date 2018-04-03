package supercupcake.services;

import supercupcake.data.OrdenData;
import supercupcake.data.PagoData;
import supercupcake.data.StatusData;

public class VendedorService {
    
    public static void pedir(OrdenData orden) {
        // El cliente le pide la orden al vendedor
        
        StatusData status = new StatusData();
        status.setText("El vendor recibe la orden");
        orden.setStatus(status);
        
        ClienteService.cobrar(orden);
    }
    
    public static void pagar(OrdenData orden) {
        // El vendor procesa el pago
        
        StatusData status = new StatusData();
        status.setText("El vendor procesa el pago");
        orden.setStatus(status);
        
        PagoData pago = new PagoData();
        pago.setPagado(true);
        orden.setPago(pago);
        
        CocinaService.pedir(orden);
    }
    
    public static void entregar(OrdenData orden) {
        // El vendedor recibe la orden de la cocina
        
        StatusData status = new StatusData();
        status.setText("El vendor recibe los cupcakes de la cocina");
        orden.setStatus(status);
        
        ClienteService.entregar(orden);
    }
    
}
