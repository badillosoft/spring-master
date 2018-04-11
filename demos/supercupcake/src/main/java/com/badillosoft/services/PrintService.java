package com.badillosoft.services;

import com.badillosoft.beans.*;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PrintService {
    
    public  void print(OrdenBean orden) {
        System.out.printf("Orden: %d\n", orden.getId());
        print(orden.getEstatus());
        print(orden.getCliente());
        print(orden.getVendedor());
        print(orden.getCocina());
        print(orden.getCobro());
        print(orden.getPago());
        print(orden.getCupcakes());
    }
    
    public  void print(ClienteBean cliente) {
        if (cliente == null) {
            System.out.println("Cliente: NO ASIGNADO");
            return;
        }
        System.out.printf("Cliente [%d]:  %s (%s)\n", cliente.getId(), cliente.getNombre(), cliente.getCorreo());
    }
    
    public  void print(VendedorBean vendedor) {
        if (vendedor == null) {
            System.out.println("Vendedor: NO ASIGNADO");
            return;
        }
        System.out.printf("Vendedor [%d]: %s (%s)\n", vendedor.getId(), vendedor.getNombre(), vendedor.getCorreo());
    }
    
    public  void print(CocinaBean cocina) {
        if (cocina == null) {
            System.out.println("Cocina: NO ASIGNADA");
            return;
        }
        System.out.printf("Cocina [%d]: %s [%s]\n", cocina.getId(), cocina.getNombre(), cocina.getDireccion());
    }
    
    public  void print(EstatusBean estatus) {
        if (estatus == null) {
            System.out.println("Estatus: NO ASIGNADO");
            return;
        }
        System.out.printf("Estatus [%d]: %s\n", estatus.getId(), estatus.getDescripcion());
    }
    
    public  void print(CobroBean cobro) {
        if (cobro == null) {
            System.out.println("Cobro: NO ASIGNADO");
            return;
        }
        System.out.printf("Cobro [%d]: $%.2f Token: [%s/%s]\n", cobro.getId(), cobro.getTotal(), cobro.getToken_paypal(), cobro.isCompletado());
    }
    
    public  void print(PagoBean pago) {
        if (pago == null) {
            System.out.println("Pago: NO ASIGNADO");
            return;
        }
        System.out.printf("Pago [%d]: Token: [%s/%s]\n", pago.getId(), pago.getToken_paypal(), pago.isCompletado());
    }
    
    public  void print(List<CupcakeBean> cupcakes) {
        if (cupcakes == null) {
            System.out.println("Cupcakes: NO ASIGNADO");
            return;
        }
        System.out.printf("Cupcakes: %d\n", cupcakes.size());
        for (CupcakeBean cupcake : cupcakes) {
            print(cupcake);
        }
    }
    
    public  void print(CupcakeBean cupcake) {
        if (cupcake == null) {
            System.out.println("Cupcakes: NO ASIGNADO");
            return;
        }
        System.out.printf("Cupcake [%d]: %s $%.2f\n", cupcake.getId(), cupcake.getTipo(), cupcake.getPrecio());
    }
    
}
