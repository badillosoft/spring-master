/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supercupcake.services;

import java.util.List;
import supercupcake.data.*;

/**
 *
 * @author alan
 */
public class PrintService {
    
    public static void print(OrdenData orden) {
        System.out.printf("Orden: %d\n", orden.getId());
        PrintService.print(orden.getEstatus());
        PrintService.print(orden.getCliente());
        PrintService.print(orden.getVendedor());
        PrintService.print(orden.getCocina());
        PrintService.print(orden.getCobro());
        PrintService.print(orden.getPago());
        PrintService.print(orden.getCupcakes());
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
    
    public static void print(PagoData pago) {
        if (pago == null) {
            System.out.println("Pago: NO ASIGNADO");
            return;
        }
        System.out.printf("Pago [%d]: Token: [%s/%s]\n", pago.getId(), pago.getToken_paypal(), pago.isCompletado());
    }
    
    public static void print(List<CupcakeData> cupcakes) {
        if (cupcakes == null) {
            System.out.println("Cupcakes: NO ASIGNADO");
            return;
        }
        System.out.printf("Cupcakes: %d\n", cupcakes.size());
        for (CupcakeData cupcake : cupcakes) {
            PrintService.print(cupcake);
        }
    }
    
    public static void print(CupcakeData cupcake) {
        if (cupcake == null) {
            System.out.println("Cupcakes: NO ASIGNADO");
            return;
        }
        System.out.printf("Cupcake [%d]: %s $%.2f\n", cupcake.getId(), cupcake.getTipo(), cupcake.getPrecio());
    }
    
}
