package supercupcake.data;

public class OrdenCupcakesData {
    
    int id;
    OrdenData orden;
    CupcakeData cupcake;
    int multiplicador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrdenData getOrden() {
        return orden;
    }

    public void setOrden(OrdenData orden) {
        this.orden = orden;
    }

    public CupcakeData getCupcake() {
        return cupcake;
    }

    public void setCupcake(CupcakeData cupcake) {
        this.cupcake = cupcake;
    }

    public int getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }
    
}
