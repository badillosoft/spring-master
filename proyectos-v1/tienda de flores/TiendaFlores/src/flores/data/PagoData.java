package flores.data;

public class PagoData {
	int id;
	String token_paypal;
	int completado;
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

	public int getCompletado() {
		return completado;
	}

	public void setCompletado(int completado) {
		this.completado = completado;
	}

	boolean pagado;
	

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
}
