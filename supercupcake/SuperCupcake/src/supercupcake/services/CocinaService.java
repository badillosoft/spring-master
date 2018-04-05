package supercupcake.services;

import supercupcake.data.OrdenData;
import supercupcake.data.EstatusData;

public class CocinaService {
    
    public static void pedir(OrdenData orden) {
        // La cocina recibe la orden para preparar los cupcakes
        
        EstatusData status = new EstatusData();
        status.setText("La cocina está preparando los cupcakes");
        orden.setStatus(status);
        
        VendedorService.entregar(orden);
    }
    
}
