package supercupcake.services;

import supercupcake.data.OrdenData;
import supercupcake.data.EstatusData;
import supercupcake.data.CobroData;

public class ClienteService {
    
    public static void cobrar(OrdenData orden) {
        // Se le solicita cobro al cliente, entonces el cliente le paga al vendedor
        
        EstatusData status = new EstatusData();
        status.setText("El cliente acepta el cobro (lo configura)");
        orden.setStatus(status);
        
        // El cliente ajusta los datos de cobro
        CobroData cobro = new CobroData();
        cobro.setCobrado(true);
        orden.setCobro(cobro);
        
        VendedorService.pagar(orden);
    }
    
    public static void entregar(OrdenData orden) {
        // El cliente recibe sus cupcakes
        
        EstatusData status = new EstatusData();
        status.setText("El cliente recibe sus cupcakes :)");
        orden.setStatus(status);
    }
    
}
