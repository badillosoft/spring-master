/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.services;

import lecturaadomicilio.data.CobroData;
import lecturaadomicilio.data.OrdenEventoData;
import lecturaadomicilio.data.StatusData;

/**
 *
 * @author Pablo
 */
public class ClienteService {
    
    public static void cobrar(OrdenEventoData orden){
        StatusData status = new StatusData();
        status.setNotificar("El cliente reliza el pago");
        orden.setRegistro(status);
        
        CobroData cobro = new CobroData();
        cobro.setCobrado(true);
        orden.setCobro(cobro);
        
        PlataformaService.pagarPlataforma(orden);
        
    }
    
    public static void ConfirmarEvento(OrdenEventoData orden){
        StatusData status = new StatusData();
        status.setNotificar("La plataforma notifica al cliente sobre su evento-orador");
        orden.setRegistro(status);
        
        PlataformaService.avisarInicioEvento(orden);
        }
    
    
}
