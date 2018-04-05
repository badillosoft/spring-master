package superLibros.reporitorios;

import RentaLibros.data.StandData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author zaira
 */
public class StandRepository {
    public static void insertar(StandData stand) throws SQLException{
        PreparedStatement st= DBManager.generateQuery("INSERT INTO stand (nombre, localizacion) VALUES(?,?);");
        st.setString(1, stand.getNombre());
        st.setString(2, stand.getLocalizacion());
       
        int id= DBManager.executeInsert(st);
        stand.setId(id);   
    }
    public static void actualizar(StandData stand)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("UPDATE stand SET nombre = ?, localizacion =? WHERE id=?");
        st.setString(1, stand.getNombre());
        st.setString(2, stand.getLocalizacion());
        st.setInt(3, stand.getId());
        st.executeUpdate();
    }
    
    public static void eliminar(StandData stand)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("DELETE FROM stand  WHERE id=?");
        st.setInt(1, stand.getId());
        st.executeUpdate();
    }
     
    public static StandData buscarPorId(int id) throws SQLException{
            PreparedStatement st= DBManager.generateQuery("SELECT * FROM stand  WHERE id=?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            StandData stand = new StandData();
            stand.setId(id);
            if(rs.next()){
                String nombre = rs.getString("nombre");
                String localizacion = rs.getString("localizacion");
                stand.setId(id);
                stand.setNombre(nombre);
                stand.setLocalizacion(localizacion);
            }
            return stand;
    }
}
