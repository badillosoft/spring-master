package supercupcake.tests;

import java.sql.SQLException;
import java.util.List;
import supercupcake.data.ClienteData;
import supercupcake.repositories.ClienteRepository;
import supercupcake.repositories.DBManager;
import supercupcake.repositories.OrdenRepository;

public class ClienteRepositoryTest {
    
    public static void main(String[] args) throws SQLException {
        
        DBManager.connect("jdbc:mysql://localhost/super_cupcakes", "root", "kmmx");
        
        /*ClienteData cliente = new ClienteData();
        cliente.setNombre("Pepe el toro");
        cliente.setCorreo("pepe@gmail.com");
        
        ClienteRepository.insertar(cliente);
        
        System.out.println(cliente.getId());*/
        
        /*ClienteData cliente = new ClienteData();
        cliente.setId(4);
        cliente.setNombre("Carlos Sánchez");
        cliente.setCorreo("carlos@gmail.com");
        
        ClienteRepository.actualizar(cliente);*/
        
        /*ClienteData cliente = new ClienteData();
        cliente.setId(3);
        
        ClienteRepository.eliminar(cliente);*/
        
        /*ClienteData cliente = ClienteRepository.buscarPorId(4);
        
        System.out.println(cliente.getId());
        System.out.println(cliente.getNombre());
        System.out.println(cliente.getCorreo());*/
        
        /*List<ClienteData> clientes = ClienteRepository.buscarPorNombre("%");
        
        for (ClienteData cliente : clientes) {
            System.out.println(cliente.getId());
            System.out.println(cliente.getNombre());
            System.out.println(cliente.getCorreo());
            System.out.println("--------------------------------------");
        }*/
        
        double total = OrdenRepository.calcularTotal(1);
        
        System.out.printf("Total: %f\n", total);
    }
    
}
