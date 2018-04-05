
package supercocinamovil.services;

import supercocinamovil.data.OrdenComidaData;
import supercocinamovil.data.PagoData;
import supercocinamovil.data.StatusData;

/**
 *
 * @author Pablo
 */
public class PlataformaService {
    
    public static void pagar(OrdenComidaData orden){
        // el vendedor procesa el pago
        StatusData estado = new StatusData();
        estado.setNotificar("La plataforma procesa el pago");
        orden.setRegistro(estado);
        
        PagoData pago = new PagoData();
        pago.setPagado(true);
        orden.setPago(pago);// se le indica el nuevo pago
        
        PlataformaService.fijarDia(orden);
        
    }
    
    public static void fijarDia(OrdenComidaData orden){
        
        StatusData estado = new StatusData();
        estado.setNotificar("Se fija el dia de evento: 18-03-2018");
        orden.setRegistro(estado);
        
        CocinaService.notificar(orden);
        
    }
    
    public static void seleccionar(OrdenComidaData orden){
        
        StatusData status = new StatusData();
        status.setNotificar("El cliente selecciona el menú");
        orden.setRegistro(status);
        
        ClienteService.cobrar(orden);
    }
}
