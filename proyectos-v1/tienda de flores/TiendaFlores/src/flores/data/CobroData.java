package flores.data;

public class CobroData {
	
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

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	float total;
	
	boolean cobrado;

	public boolean isCobrado() {
		return cobrado;
	}

	public void setCobrado(boolean cobrado) {
		this.cobrado = cobrado;
	}
}
