package supercupcake.services;

import java.sql.SQLException;
import supercupcake.data.*;
import supercupcake.repositories.*;

public class CocinaService {
    
    public static void pedir(OrdenData orden) throws SQLException {
        // La cocina recibe la orden para preparar los cupcakes
        CocinaData cocina = CocinaRepository.buscarAleatorio();
        orden.setCocina(cocina);
        
        EstatusData estatus = EstatusRepository.buscarPorId(8);
        orden.setEstatus(estatus);
        
        OrdenRepository.actualizar(orden);
    }
    
}
