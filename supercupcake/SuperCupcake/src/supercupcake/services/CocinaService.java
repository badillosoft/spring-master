package supercupcake.services;

import supercupcake.data.OrdenData;
import supercupcake.data.StatusData;

public class CocinaService {
    
    public static void pedir(OrdenData orden) {
        // La cocina recibe la orden para preparar los cupcakes
        
        StatusData status = new StatusData();
        status.setText("La cocina está preparando los cupcakes");
        orden.setStatus(status);
        
        VendedorService.entregar(orden);
    }
    
}
