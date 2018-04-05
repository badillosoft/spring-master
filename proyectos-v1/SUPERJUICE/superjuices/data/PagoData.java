/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.data;

/**
 *
 * @author alejandro
 */
public class PagoData {
    
    int id;
    String token_paypal;
    short completado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken_paypal() {
        return token_paypal;
    }

    public void setToken_paypal(String token_paypal) {
        this.token_paypal = token_paypal;
    }

    public short getCompletado() {
        return completado;
    }

    public void setCompletado(short completado) {
        this.completado = completado;
    }
    
}
