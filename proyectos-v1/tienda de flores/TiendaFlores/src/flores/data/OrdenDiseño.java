package flores.data;

import java.util.List;

public class OrdenDiseño {
	int id;
	ClienteData cliente;
	VendedorData vendedor;
	ExpertoData experto;
	List<PlantasData> diseño;
	InvernaderoData invernadero;
	StatusData status;
	PagoData pago;
	CobroData cobro;
	
	public InvernaderoData getInvernadero() {
		return invernadero;
	}
	public void setInvernadero(InvernaderoData invernadero) {
		this.invernadero = invernadero;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ClienteData getCliente() {
		return cliente;
	}
	public void setCliente(ClienteData cliente) {
		this.cliente = cliente;
	}
	public VendedorData getVendedor() {
		return vendedor;
	}
	public void setVendedor(VendedorData vendedor) {
		this.vendedor = vendedor;
	}
	public ExpertoData getExperto() {
		return experto;
	}
	public void setExperto(ExpertoData experto) {
		this.experto = experto;
	}
	public List<PlantasData> getDiseño() {
		return diseño;
	}
	public void setDiseño(List<PlantasData> diseño) {
		this.diseño = diseño;
	}
	public StatusData getStatus() {
		return status;
	}
	public void setStatus(StatusData status) {
		this.status = status;
	}
	public PagoData getPago() {
		return pago;
	}
	public void setPago(PagoData pago) {
		this.pago = pago;
	}
	public CobroData getCobro() {
		return cobro;
	}
	public void setCobro(CobroData cobro) {
		this.cobro = cobro;
	}
	
	
	
}
