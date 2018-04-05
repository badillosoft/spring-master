package supercupcake.data;

public class CobroData {
    
    int id;
    double total;
    String token_paypal;
    boolean completado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getToken_paypal() {
        return token_paypal;
    }

    public void setToken_paypal(String token_paypal) {
        this.token_paypal = token_paypal;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
    
}
