
package supercocinamovil.services;

import supercocinamovil.data.OrdenComidaData;
import supercocinamovil.data.StatusData;

/**
 *
 * @author Pablo
 */
public class CocinaService {
    
    public static void notificar(OrdenComidaData orden){
        // la cocina recibe la orden para preparar los cupcakes
        
        StatusData status = new StatusData();
        status.setNotificar("Ls cocina recibe la orden y dia de evento");
        orden.setRegistro(status);
        
        ClienteService.arribar(orden);
    }
    
    
}
