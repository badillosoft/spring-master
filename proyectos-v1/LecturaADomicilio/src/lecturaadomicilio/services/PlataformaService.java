/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.services;

import lecturaadomicilio.data.ClienteData;
import lecturaadomicilio.data.OrdenEventoData;
import lecturaadomicilio.data.PagoPlataformaData;
import lecturaadomicilio.data.StatusData;

/**
 *
 * @author Pablo
 */
public class PlataformaService {
    
    public static void solicitarOrador(OrdenEventoData orden){
        StatusData status = new StatusData();
        status.setNotificar("El cliente solicita orador para su evento");
        orden.setRegistro(status);
        
        OradorService.notificarOrador(orden);
        //PlataformaService.avisarInicioEvento(orden);
        //ClienteService.solicitarOrador(orden);
    }
    
    public static void pagarPlataforma(OrdenEventoData orden){
        //La plataforma recibe el pago
        StatusData status = new StatusData();
        status.setNotificar("Se procesa el pago para la plataforma");
        orden.setRegistro(status);
        
        PagoPlataformaData pago = new PagoPlataformaData();
        pago.setPagado(true);
        orden.setPago_plataforma(pago);
        
        OradorService.pagarOrador(orden);
    }
    
    public static void confirmarOrador(OrdenEventoData orden){
        //El orador confirma a la platadorma su disponibilidad
        StatusData status = new StatusData();
        status.setNotificar("El orador confirma a la plataforma su disponibilidad al avento");
        orden.setRegistro(status);
        
        ClienteService.cobrar(orden);
    }
    
    public static void avisarInicioEvento(OrdenEventoData orden){
        StatusData status = new StatusData();
        status.setNotificar("El orador avisa sobre el inicio del evento");
        orden.setRegistro(status);
    }
}
