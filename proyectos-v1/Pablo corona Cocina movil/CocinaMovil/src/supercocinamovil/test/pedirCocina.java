
package supercocinamovil.test;

import supercocinamovil.data.MenuData;
import supercocinamovil.data.CocinaData;
import supercocinamovil.data.OrdenComidaData;
import supercocinamovil.data.ClienteData;
import supercocinamovil.services.PlataformaService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class pedirCocina {
    
    public static void main(String[] args) {
        OrdenComidaData orden = new OrdenComidaData();
        orden.setId("1");
        
        ClienteData cliente = new ClienteData();
        //cliente.setId("Pablo");
        orden.setCliente(cliente);
        
        CocinaData cocina = new CocinaData();
        //cocina.setId("La condesa");
        orden.setCocina(cocina);
        
        MenuData m1 = new MenuData();
       // m1.setMenu("Tacos");
        MenuData m2 = new MenuData();
        
       // m2.setMenu("Hamburguesas");
        
        List<MenuData> menus = new ArrayList();
        
        menus.add(m1);
        menus.add(m2);
        orden.setMenu(menus);
        
        PlataformaService.seleccionar(orden);

    }
}
