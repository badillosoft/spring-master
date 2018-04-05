package supercupcake.services;

import java.sql.SQLException;
import supercupcake.data.*;
import supercupcake.repositories.*;

public class ClienteService {
    
    public static void cobrar(OrdenData orden) throws SQLException {
        // Se le solicita cobro al cliente, entonces el cliente le paga al vendedor
        double total = OrdenRepository.calcularTotal(orden.getId());
        
        CobroData cobro = new CobroData();
        cobro.setTotal(total);
        cobro.setToken_paypal("XXX123");
        cobro.setCompletado(true);
        CobroRepository.insertar(cobro);
        orden.setCobro(cobro);
        
        EstatusData estatus = EstatusRepository.buscarPorId(5);
        orden.setEstatus(estatus);
        
        OrdenRepository.actualizar(orden);
    }
    
    public static void entregar(OrdenData orden) throws SQLException {
        // El cliente recibe sus cupcakes
        
        EstatusData estatus = EstatusRepository.buscarPorId(12);
        orden.setEstatus(estatus);
        
        OrdenRepository.actualizar(orden);
    }
    
}
