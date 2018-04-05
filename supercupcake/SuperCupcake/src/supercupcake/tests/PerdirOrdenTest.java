package supercupcake.tests;

import java.sql.SQLException;
import java.util.*;
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
        
        System.out.println("-- Ajustar los cupcakes que quiere el cliente");
        List<CupcakeData> cupcakes = new ArrayList();
        
        // seleccionamos 10 cupcakes aleatorios
        
        for (int i = 0; i < 10; i++) {
            cupcakes.add(CupcakeRepository.buscarAleatorio());
        }
        
        orden.setCupcakes(cupcakes);
        
        PrintService.print(orden);
        System.out.println("-*");
        
        System.out.println("-- Cliente pide la orden al Vendedor");
        // 3. Pedir orden al vendedor
        VendedorService.pedir(orden);
        
        PrintService.print(orden);
        System.out.println("-*");
        
        System.out.println("-- Cliente ajusta los datos de cobro");
        // 4. Cobrar al cliente
        ClienteService.cobrar(orden);
        
        PrintService.print(orden);
        System.out.println("-*");
        
        System.out.println("-- Vendedor procesa el pago");
        VendedorService.pagar(orden);
        
        PrintService.print(orden);
        System.out.println("-*");
        
        System.out.println("-- Vendedor pide la orden a Cocina");
        CocinaService.pedir(orden);
        
        PrintService.print(orden);
        System.out.println("-*");
        
        System.out.println("-- Cocina envia la orden al Vendedor");
        VendedorService.entregar(orden);
        
        PrintService.print(orden);
        System.out.println("-*");
        
        System.out.println("-- Vendedor envia la orden al Cliente");
        ClienteService.entregar(orden);
        
        PrintService.print(orden);
        System.out.println("-*");
    }
    
}
