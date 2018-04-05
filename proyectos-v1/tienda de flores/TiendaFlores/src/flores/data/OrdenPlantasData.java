package flores.data;

public class OrdenPlantasData {
	int id;
	OrdenDiseño orden;
	PlantasData planta;
	int multiplicador;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrdenDiseño getOrden() {
		return orden;
	}
	public void setOrden(OrdenDiseño orden) {
		this.orden = orden;
	}
	public PlantasData getPlanta() {
		return planta;
	}
	public void setPlanta(PlantasData planta) {
		this.planta = planta;
	}
	public int getMultiplicador() {
		return multiplicador;
	}
	public void setMultiplicador(int multiplicador) {
		this.multiplicador = multiplicador;
	}
	
	
}
