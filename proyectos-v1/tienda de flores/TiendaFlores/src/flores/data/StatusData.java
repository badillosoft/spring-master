package flores.data;

public class StatusData {
	int id;
	String descripcion;

	public String getText() {
		return descripcion;
	}

	public void setText(String text) {
		descripcion = text;
		System.out.println(this.descripcion);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
