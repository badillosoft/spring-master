/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RentaLibros.test;

import RentaLibros.data.MembresiaData;
import RentaLibros.data.SuscriptorData;
import RentaLibros.data.VendedorData;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import superLibros.reporitorios.DBManager;
import superLibros.reporitorios.MembresiaRepository;
import superLibros.reporitorios.SuscriptorRepository;
import superLibros.reporitorios.VendedorRepository;

/**
 *
 * @author zaira
 */
public class SuscriptorRepositoryTest {
    public static void main(String args[]) throws SQLException{
        DBManager.connect("jdbc:mysql://localhost:8889/RentaDeLibros", "root", "root");
        /*
        SuscriptorData suscriptor = new SuscriptorData();
        suscriptor.setNombre("PEPE EL TORO");
        suscriptor.setCorreo("pepe@gmail.com");
        SuscriptorRepository.insertar(suscriptor);
        System.out.println(suscriptor.getId());
        */
        /*SuscriptorData suscriptor = new SuscriptorData();
        suscriptor.setId(1);
        suscriptor.setNombre("Carlos");
        suscriptor.setCorreo("Carlos@gmail.com");
        SuscriptorRepository.actualizar(suscriptor);*/
        /*
        SuscriptorData cliente = new SuscriptorData();
        cliente.setId(1);
        
        SuscriptorRepository.eliminar(cliente);*/
        /*
         List<SuscriptorData> clientes =  SuscriptorRepository.buscarPorNombre("%");
        
        for (SuscriptorData cliente : clientes) {
            System.out.println(cliente.getId());
            System.out.println(cliente.getNombre());
            System.out.println(cliente.getCorreo());
            System.out.println("--------------------------------------");
        }*/
        /*
        VendedorData vendedor = new VendedorData();
        vendedor.setNombre("Jose");
        vendedor.setCorreo("josevendedor@gmail.com");
        VendedorRepository.insertar(vendedor);
        System.out.println(vendedor.getId());*/
        /*
        VendedorData vendedor = new VendedorData();
        vendedor.setId(2);
        vendedor.setNombre("Carlos cruz");
        vendedor.setCorreo("CarlosCRUZ@gmail.com");
        VendedorRepository.actualizar(vendedor);*/
        /*
        VendedorData vendedor = new VendedorData();
        vendedor.setId(1);
        
        VendedorRepository.eliminar(vendedor);*/
        /*
         List<VendedorData> clientes =  VendedorRepository.buscarPorNombre("%");
        
        for (VendedorData cliente : clientes) {
            System.out.println(cliente.getId());
            System.out.println(cliente.getNombre());
            System.out.println(cliente.getCorreo());
            System.out.println("--------------------------------------");
        }*/
        /*
        MembresiaData membresia = new MembresiaData();
        membresia.setIdCliente(2);
        membresia.setVigencia("2018-04-06");
        MembresiaRepository.insertar(membresia);
        System.out.println(membresia.getId());*/
        /*
        MembresiaData membresia = new MembresiaData();
        membresia.setId(1);
        membresia.setIdCliente(3);
        membresia.setVigencia("2018-04-20");
        MembresiaRepository.actualizar(membresia);*/
        /*
        MembresiaData membresia = new MembresiaData();
        membresia.setId(2);
        
        MembresiaRepository.eliminar(membresia);*/
        /*
        MembresiaData cliente = MembresiaRepository.buscarPorId(1);
        System.out.println(cliente.getId());
        System.out.println(cliente.getIdCliente());
         System.out.println(cliente.getVigencia());*/
        
        
        
          
    }
}
