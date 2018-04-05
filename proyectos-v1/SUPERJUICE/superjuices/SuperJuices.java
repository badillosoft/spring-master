/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices;

import java.sql.SQLException;
import superjuices.data.ClienteData;
import superjuices.data.CobroData;
import superjuices.data.EstatusData;
import superjuices.data.EstatusRepository;
import superjuices.data.JugoData;
import superjuices.data.PagoData;
import superjuices.data.RepartidorData;
import superjuices.data.SucursalData;
import superjuices.repository.ClienteRepository;
import superjuices.repository.CobroRepository;
import superjuices.repository.DBManager;
import superjuices.repository.JugoRepository;
import superjuices.repository.PagoRepository;
import superjuices.repository.RepartidorRepository;
import superjuices.repository.SucursalRepository;

/**
 *
 * @author alejandro
 */
public class SuperJuices {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        DBManager.connect("jdbc:mysql://localhost/superjuice", "root", "root");
        
        /*
        ClienteData cliente = new ClienteData();
        
        cliente.setId(6);
        cliente.setNombre("Victor el loco");
        cliente.setDireccion("Cerrada 1ro de Mayo");
        */
        
        //ClienteRepository.eliminar(cliente);
        
        /*
        ClienteData res = ClienteRepository.buscarById(4);
        System.out.println(res.getId());
        System.out.println(res.getNombre());
        System.out.println(res.getDireccion());
        */
        
        /*
        SucursalData sucursal = new SucursalData();
        sucursal.setId(4);
        sucursal.setNombre("Vallejos");
        sucursal.setDireccion("Av. Politecnico Num.102");
        
        SucursalRepository.eliminar(sucursal);
        */
        
        /*
        SucursalData sucursal = SucursalRepository.buscarById(1);
        System.out.println(sucursal.getId());
        System.out.println(sucursal.getNombre());
        System.out.println(sucursal.getDireccion());
        */
        
        /*
        RepartidorData repartidor = new RepartidorData();
        repartidor.setId(4);
        repartidor.setNombre("HectorX");
        repartidor.setPlacas("8T1PAX");
        repartidor.setDireccion("Matamoros Num.243X");
        
        RepartidorRepository.eliminar(repartidor);
        */
        /*
        RepartidorData repartidor = RepartidorRepository.buscarById(1);
        System.out.println(repartidor.getId());
        System.out.println(repartidor.getNombre());
        System.out.println(repartidor.getPlacas());
        System.out.println(repartidor.getDireccion());
        */
        
        /*
        JugoData jugo = new JugoData();
        jugo.setId(16);
        jugo.setSabor("ChocolateX");
        jugo.setTam("ChicoX");
        jugo.setPrecio(15);
        
        JugoRepository.eliminar(jugo);
        */
        /*
        JugoData jugo = JugoRepository.buscarById(1);
        System.out.println(jugo.getId());
        System.out.println(jugo.getSabor());
        System.out.println(jugo.getTam());
        System.out.println(jugo.getPrecio());
        */
        
        /*
        CobroData cobro = new CobroData();
        cobro.setId(2);
        cobro.setTotal(1000);
        cobro.setToken_paypal("TFUU4597X");
        cobro.setCompletado((short)0);
        
        CobroRepository.eliminar(cobro);
        */
        /*
        CobroData cobro = CobroRepository.buscarById(1);
        System.out.println(cobro.getId());
        System.out.println(cobro.getTotal());
        System.out.println(cobro.getToken_paypal());
        System.out.println(cobro.getCompletado());
        */
        
        
        /*
        PagoData pago = new PagoData();
        pago.setId(2);
        pago.setToken_paypal("ITVS3094X");
        pago.setCompletado((short)0);
        
        PagoRepository.eliminar(pago);
        */
        /*
        PagoData pago = PagoRepository.buscarById(1);
        System.out.println(pago.getId());
        System.out.println(pago.getToken_paypal());
        System.out.println(pago.getCompletado());
        */
        
        
        /*
        EstatusData estatus = new EstatusData();
        estatus.setId(6);
        estatus.setEstado("Estado de prueba editado");
        EstatusRepository.eliminar(estatus);
        */
        /*
        EstatusData estatus = EstatusRepository.buscarById(1);
        System.out.println(estatus.getId());
        System.out.println(estatus.getEstado());
        */
        
        
        
        
        
    }
    
}
