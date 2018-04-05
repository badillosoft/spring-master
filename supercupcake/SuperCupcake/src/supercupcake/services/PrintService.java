/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supercupcake.services;

import supercupcake.data.*;

/**
 *
 * @author alan
 */
public class PrintService {
    
    public static void print(OrdenData orden) {
        System.out.println("------------------------------------------------");
        System.out.printf("Orden: %d\n", orden.getId());
        PrintService.print(orden.getEstatus());
        PrintService.print(orden.getCliente());
        PrintService.print(orden.getVendedor());
        PrintService.print(orden.getCocina());
        PrintService.print(orden.getCobro());
        System.out.println("------------------------------------------------");
    }
    
    public static void print(ClienteData cliente) {
        if (cliente == null) {
            System.out.println("Cliente: NO ASIGNADO");
            return;
        }
        System.out.printf("Cliente [%d]:  %s (%s)\n", cliente.getId(), cliente.getNombre(), cliente.getCorreo());
    }
    
    public static void print(VendedorData vendedor) {
        if (vendedor == null) {
            System.out.println("Vendedor: NO ASIGNADO");
            return;
        }
        System.out.printf("Vendedor [%d]: %s (%s)\n", vendedor.getId(), vendedor.getNombre(), vendedor.getCorreo());
    }
    
    public static void print(CocinaData cocina) {
        if (cocina == null) {
            System.out.println("Cocina: NO ASIGNADA");
            return;
        }
        System.out.printf("Cocina [%d]: %s [%s]\n", cocina.getId(), cocina.getNombre(), cocina.getDireccion());
    }
    
    public static void print(EstatusData estatus) {
        if (estatus == null) {
            System.out.println("Estatus: NO ASIGNADO");
            return;
        }
        System.out.printf("Estatus [%d]: %s\n", estatus.getId(), estatus.getDescripcion());
    }
    
    public static void print(CobroData cobro) {
        if (cobro == null) {
            System.out.println("Cobro: NO ASIGNADO");
            return;
        }
        System.out.printf("Cobro [%d]: $%.2f Token: [%s/%s]\n", cobro.getId(), cobro.getTotal(), cobro.getToken_paypal(), cobro.isCompletado());
    }
    
}
