package supercupcake.tests;

import java.sql.SQLException;
import java.util.*;
import supercupcake.data.*;
import supercupcake.repositories.*;
import supercupcake.services.*;

public class RastrearOrdenTest {
    
    public static void main(String[] args) throws SQLException {
        DBManager.connect("jdbc:mysql://localhost/super_cupcakes", "root", "kmmx");
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("ID de la orden: ");
        int id_orden = sc.nextInt();
        
        OrdenData orden = OrdenRepository.buscarPorId(id_orden);
        
        OrdenRepository.cargarLista(orden);
        
        PrintService.print(orden);
    }
    
}
