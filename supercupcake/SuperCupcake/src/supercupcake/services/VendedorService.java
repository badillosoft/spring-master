package supercupcake.services;

import java.sql.SQLException;
import supercupcake.data.*;
import supercupcake.repositories.*;

public class VendedorService {
    
    public static void pedir(OrdenData orden) throws SQLException {
        // El cliente le pide la orden al vendedor
        
        // 1. Asignar un vendedor "ALEATORIO"
        VendedorData vendedor = VendedorRepository.buscarAleatorio();
        orden.setVendedor(vendedor);
        
        // 2. Recuperar el estatus adecuado
        EstatusData estatus = EstatusRepository.buscarPorId(3);
        orden.setEstatus(estatus);
        
        // 4. Actualizar la base de datos
        OrdenRepository.insertar(orden);
        
        // 3. Tomar la lista de cupcakes y guardar en OrdenCupcakesRepository
        OrdenRepository.insertarListaCupcakes(orden);
    }
    
    public static void pagar(OrdenData orden) throws SQLException {
        // El vendor procesa el pago
        
        // 1. Generar los datos del pago
        PagoData pago = new PagoData();
        pago.setToken_paypal("ABC123");
        pago.setCompletado(true);
        PagoRepository.insertar(pago);
        orden.setPago(pago);
        
        EstatusData estatus = EstatusRepository.buscarPorId(6);
        orden.setEstatus(estatus);
        
        OrdenRepository.actualizar(orden);
    }
    
    public static void entregar(OrdenData orden) throws SQLException {
        // El vendedor recibe la orden de la cocina
        
        EstatusData estatus = EstatusRepository.buscarPorId(11);
        orden.setEstatus(estatus);
        
        OrdenRepository.actualizar(orden);
    }
    
}
