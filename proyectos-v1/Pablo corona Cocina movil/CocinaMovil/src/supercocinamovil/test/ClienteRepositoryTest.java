package supercocinamovil.test;

import java.sql.SQLException;
import java.util.List;
import supercocinamovil.data.ClienteData;
import supercocinamovil.repositories.ClienteRepository;
import supercocinamovil.repositories.DBManager;

public class ClienteRepositoryTest {
    
    public static void main(String[] args) throws SQLException {
        
        DBManager.connect("jdbc:mysql://localhost/super_cocina_movil", "root", "1986");
        
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
        
        List<ClienteData> clientes = ClienteRepository.buscarPorNombre("%");
        
        for (ClienteData cliente : clientes) {
            System.out.println(cliente.getId());
            System.out.println(cliente.getNombre());
            System.out.println(cliente.getCorreo());
            System.out.println("--------------------------------------");
        }
        
    }
    
}
