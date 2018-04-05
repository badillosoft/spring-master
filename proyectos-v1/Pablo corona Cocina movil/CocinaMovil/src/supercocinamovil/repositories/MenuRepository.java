
package supercocinamovil.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import supercocinamovil.data.MenuData;

public class MenuRepository {
    
    public static void insertar(MenuData menu) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("INSERT INTO menus (catalogo, precio) VALUES (?, ?);");
        
        st.setString(1, menu.getCatalogo());
        st.setFloat(2, menu.getPrecio());
          
        int id = DBManager.executeInsert(st);
        
        menu.setId(id);
    }
    
    public static void actualizar(MenuData menu) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("UPDATE menus SET catalogo=?, precio=? WHERE id=?;");
        
        st.setString(1, menu.getCatalogo());
        st.setFloat(2, menu.getPrecio());
        
        st.executeUpdate();
    }
    
    public static void eliminar(MenuData menu) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("DELETE FROM menus WHERE id=?;");
        
        st.setInt(1, menu.getId());
        
        st.executeUpdate();
    }
    
    public static MenuData buscarPorId(int id) throws SQLException {
        PreparedStatement st = DBManager.generateQuery("SELECT * FROM menu WHERE id=?;");
        
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        
        MenuData menu = new MenuData();
        menu.setId(id);
        
        if (rs.next()) {
            String catalogo = rs.getString("catalogo");
            int precio = rs.getInt(1);
            
            menu.setCatalogo(catalogo);
            menu.setPrecio(precio);
        }
        
        return menu;
    }
    
}