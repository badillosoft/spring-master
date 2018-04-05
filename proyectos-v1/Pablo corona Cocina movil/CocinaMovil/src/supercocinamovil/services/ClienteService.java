
package supercocinamovil.services;

import supercocinamovil.data.CobroData;
import supercocinamovil.data.OrdenComidaData;
import supercocinamovil.data.StatusData;

/**
 *
 * @author Pablo
 */
public class ClienteService {
    
     public static void arribar(OrdenComidaData orden){
        
        // el cliente recibe sus cupcakes
        StatusData status = new StatusData();
        status.setNotificar("La cocina arriba al evento :) ");
        
        orden.setRegistro(status); // se ajusta el nuevo status
    }
    
    public static void cobrar(OrdenComidaData orden){
        // se le solicita cobro al cliente , entonces el cliente le paga al vendedor
        CobroData cobro = new CobroData();
        //cobro.setCobrado(true);   // el cliente ajusta los datos de cobro
        
        StatusData registro = new StatusData();
        registro.setNotificar("El cliente realiza el pago");
        orden.setRegistro(registro);
        
        PlataformaService.pagar(orden);
        
    }
    
}
