/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.services;

import lecturaadomicilio.data.OrdenEventoData;
import lecturaadomicilio.data.PagoOradorData;
import lecturaadomicilio.data.StatusData;

/**
 *
 * @author Pablo
 */
public class OradorService {
    
    public static void pagarOrador(OrdenEventoData orden){
        StatusData status = new StatusData();
        status.setNotificar("La plataforma paga el evento al orador");
        orden.setRegistro(status);
        
        PagoOradorData pago = new PagoOradorData();
        pago.setPagado(true);
        orden.setPago_orador(pago);
        
        ClienteService.ConfirmarEvento(orden);
    }
    
    public static void notificarOrador(OrdenEventoData orden){
        //La plataforma notifica al orador
        StatusData status = new StatusData();
        status.setNotificar("La plataforma notifica el evento al orador");     
        orden.setRegistro(status);
        
        PlataformaService.confirmarOrador(orden);
        
        //PlataformaService.notificarOrador(orden);
        
    }
    
}
