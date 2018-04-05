/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.test;

import lecturaadomicilio.data.ClienteData;
import lecturaadomicilio.data.OradorData;
import lecturaadomicilio.data.OrdenEventoData;
import lecturaadomicilio.services.PlataformaService;

/**
 *
 * @author Pablo
 */
public class Orador {
    
    public static void main(String[] args) {
        
        OrdenEventoData orden = new OrdenEventoData();
        orden.setId("Orden01");
        
        ClienteData cliente = new ClienteData();
        cliente.setId("Pablo");
        
        OradorData orador = new OradorData();
        orador.setId("Marcela Sabio");
        
        PlataformaService.solicitarOrador(orden);
    }
    
}
