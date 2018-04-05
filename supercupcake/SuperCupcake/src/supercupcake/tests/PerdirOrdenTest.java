package supercupcake.tests;

import java.sql.SQLException;
import supercupcake.data.*;
import supercupcake.repositories.*;
import supercupcake.services.*;

public class PerdirOrdenTest {
    
    public static void main(String[] args) throws SQLException {
        DBManager.connect("jdbc:mysql://localhost/super_cupcakes", "root", "kmmx");
        
        System.out.println("-- Buscar un cliente aleatorio");
        // 1. Cliente aleatorio
        ClienteData cliente = ClienteRepository.buscarAleatorio();
        
        PrintService.print(cliente);
        System.out.println("-*");
        
        System.out.println("-- Crear una nueva orden para el cliente");
        // 2. Crear una nueva orden
        OrdenData orden = new OrdenData();
        orden.setCliente(cliente);
        
        PrintService.print(orden);
        System.out.println("-*");
        
        System.out.println("-- Cliente pide la orden al Vendedor");
        // 3. Pedir orden al vendedor
        VendedorService.pedir(orden);
        
        PrintService.print(orden);
        System.out.println("-*");
        
        // 4. Cobrar al cliente
        ClienteService.cobrar(orden);
        
        PrintService.print(orden);
    }
    
}
