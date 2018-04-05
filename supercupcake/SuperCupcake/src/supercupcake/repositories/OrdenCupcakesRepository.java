package supercupcake.repositories;

import supercupcake.data.*;
import java.sql.*;

public class OrdenCupcakesRepository {
    
    public static void insertar(OrdenCupcakesData orden_cupcakes) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO orden_cupcakes (orden, cupcake, multiplicador) VALUES (?, ?, ?);");
        
        st.setInt(1, orden_cupcakes.getOrden().getId());
        st.setInt(2, orden_cupcakes.getCupcake().getId());
        st.setInt(3, orden_cupcakes.getMultiplicador()); 
        
        int id = DBManager.executeInsert(st);
        
        orden_cupcakes.setId(id);
    }
    
    public static void actualizar(OrdenCupcakesData orden_cupcakes) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE orden_cupcakes SET orden=?, cupcake=?, multiplicador=? WHERE id=?;");
        
        st.setInt(1, orden_cupcakes.getOrden().getId());
        st.setInt(2, orden_cupcakes.getCupcake().getId());
        st.setInt(3, orden_cupcakes.getMultiplicador());
        st.setInt(4, orden_cupcakes.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(OrdenCupcakesData orden_cupcakes) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM orden_cupcakes WHERE id=?;");
        
        st.setInt(1, orden_cupcakes.getId());
        
        st.executeUpdate();
    }
    
    public static OrdenCupcakesData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM orden_cupcakes WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        OrdenCupcakesData orden_cupcakes = new OrdenCupcakesData();
        orden_cupcakes.setId(id);
        
        if (rs.next()) {
            int id_orden = rs.getInt("orden");
            int id_cupcake = rs.getInt("cupcake");
            int multiplicador = rs.getInt("multiplicador");
            
            //OrdenData orden = OrdenRepository.buscarPorId(id_orden);
            CupcakeData cupcake = CupcakeRepository.buscarPorId(id_orden);
            
            //orden_cupcakes.setOrden(orden);
            orden_cupcakes.setCupcake(cupcake);
            orden_cupcakes.setMultiplicador(multiplicador);
        } else {
            return null;
        }
        
        return orden_cupcakes;
    }
    
}
