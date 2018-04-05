
package supercocinamovil.repositories;

import supercocinamovil.data.ClienteData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supercocinamovil.data.RegistroData;

public class RegistroRepository {
    
    public static void insertar(RegistroData registro) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO Registros (cliente, fecha) VALUES (?, ?);");
        
        st.setInt(1, registro.getCliente());
        st.setString(2, registro.getFecha());
        
        int id = DBManager.executeInsert(st);
        
        registro.setId(id);
    }
    
    public static void actualizar(RegistroData registro) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE Registros SET cliente=?, fecha=? WHERE id=?;");
        
        st.setInt(1, registro.getCliente());
        st.setString(2, registro.getFecha());
        st.setInt(3, registro.getId());
        
        st.executeUpdate();
    }
    
    public static void eliminar(RegistroData registro) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM registros WHERE id=?;");
        
        st.setInt(1, registro.getId());
        
        st.executeUpdate();
    }
    
    public static RegistroData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM registros WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        RegistroData registro = new RegistroData();
        registro.setId(id);
        
        if (rs.next()) {
            int cliente = rs.getInt(1);
            String fecha = rs.getString("fecha");
            
            registro.setCliente(cliente);
            registro.setFecha(fecha);
        }
        
        return registro;
    }
    
}