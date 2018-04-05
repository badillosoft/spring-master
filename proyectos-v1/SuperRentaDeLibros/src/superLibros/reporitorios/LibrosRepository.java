
package superLibros.reporitorios;

import RentaLibros.data.LibroData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zaira
 */
public class LibrosRepository {
    public static void insertar(LibroData libro) throws SQLException{
        PreparedStatement st= DBManager.generateQuery("INSERT INTO libros (nombre, autor, editorial) VALUES(?,?,?);");
        
        st.setString(1, libro.getNombre());
        st.setString(2, libro.getAutor());
        st.setString(3, libro.getEditorial());
       
        int id= DBManager.executeInsert(st);
        libro.setId(id);   
    }
    public static void actualizar(LibroData libro)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("UPDATE libros SET nombre = ?, autor =?, editorial =? WHERE id=?");
        st.setString(1, libro.getNombre());
        st.setString(2, libro.getAutor());
        st.setString(3, libro.getEditorial());
        st.setInt(4, libro.getId());
        st.executeUpdate();
    }
    
    public static void eliminar(LibroData libro)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("DELETE FROM libros  WHERE id=?");
        st.setInt(1, libro.getId());
        st.executeUpdate();
    }
     
    public static LibroData buscarPorId(int id) throws SQLException{
            PreparedStatement st= DBManager.generateQuery("SELECT * FROM libros  WHERE id=?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            LibroData libro = new LibroData();
            libro.setId(id);
            if(rs.next()){
                String nombre = rs.getString("nombre");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");
                libro.setId(id);
                libro.setNombre(nombre);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
            }
            return libro;
    }
    
    public static LibroData buscarPorNombre(String like) throws SQLException{
            PreparedStatement st= DBManager.generateQuery("SELECT * FROM libros  WHERE nombre like?;");
            st.setString(1, like);
            ResultSet rs = st.executeQuery();
            
            List<LibroData> libros = new ArrayList();
            
            while(rs.next()){
                int id= rs.getInt("id");
                String nombre = rs.getString("nombre");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");
                LibroData libro = new LibroData();
                libro.setId(id);
                libro.setNombre(nombre);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                
                libros.add(libro);
            }
            
           return (LibroData) libros;
     }
}
