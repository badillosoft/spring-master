package supercupcake.repositories;

import supercupcake.data.*;
import java.sql.*;

public class CupcakeRepository {
    
    public static void insertar(CupcakeData cupcake) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO cupcakes (tipo, precio) VALUES (?, ?);");
        
        st.setString(1, cupcake.getTipo());
        st.setDouble(2, cupcake.getPrecio());
        
        int id = DBManager.executeInsert(st);
        
        cupcake.setId(id);
    }
    
    public static void actualizar(CupcakeData cupcake) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE cupcakes SET tipo=?, precio=? WHERE id=?;");
        
        st.setString(1, cupcake.getTipo());
        st.setDouble(2, cupcake.getPrecio());
        st.setInt(3, cupcake.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(CupcakeData cupcake) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM cupcakes WHERE id=?;");
        
        st.setInt(1, cupcake.getId());
        
        st.executeUpdate();
    }
    
    public static CupcakeData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM cupcakes WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        CupcakeData cupcake = new CupcakeData();
        cupcake.setId(id);
        
        if (rs.next()) {
            String tipo = rs.getString("tipo");
            double precio = rs.getDouble("precio");
            
            cupcake.setTipo(tipo);
            cupcake.setPrecio(precio);
        } else {
            return null;
        }
        
        return cupcake;
    }
    
    public static CupcakeData buscarAleatorio() throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM cupcakes ORDER BY RAND() LIMIT 1;");
        
        ResultSet rs = st.executeQuery();
        
        CupcakeData cupcake = new CupcakeData();
        
        if (rs.next()) {
            int id = rs.getInt("id");
            String tipo = rs.getString("tipo");
            double precio = rs.getDouble("precio");
            
            cupcake.setId(id);
            cupcake.setTipo(tipo);
            cupcake.setPrecio(precio);
        } else {
            return null;
        }
        
        return cupcake;
    }
    
}
