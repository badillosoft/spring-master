/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superLibros.reporitorios;

import RentaLibros.data.MembresiaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;



/**
 *
 * @author zaira
 */
public class MembresiaRepository {

    public static void insertar(MembresiaData membresia) throws SQLException{
        PreparedStatement st= DBManager.generateQuery("INSERT INTO membresia (idCliente, vigencia) VALUES(?,?);");
        
        st.setInt(1, membresia.getIdCliente());
        st.setString(2, membresia.getVigencia());
        int id= DBManager.executeInsert(st);
        membresia.setId(id);   
    }
    public static void actualizar(MembresiaData membresia)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("UPDATE membresia SET idCliente = ?, vigencia =? WHERE id=?");
        st.setInt(1, membresia.getIdCliente());
        st.setString(2, membresia.getVigencia());
        st.setInt(3, membresia.getId());
        st.executeUpdate();
    }
    
    public static void eliminar(MembresiaData membresia)  throws SQLException{
        PreparedStatement st= DBManager.generateQuery("DELETE FROM membresia  WHERE id=?");
        st.setInt(1, membresia.getId());
        st.executeUpdate();
    }
     
    public static MembresiaData buscarPorId(int id) throws SQLException{
            PreparedStatement st= DBManager.generateQuery("SELECT * FROM membresia  WHERE id=?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            MembresiaData membresia = new MembresiaData();
            membresia.setId(id);
            if(rs.next()){
                int idCliente = rs.getInt("idCliente");
                String vigencia = rs.getString("vigencia");
                membresia.setId(id);
                membresia.setIdCliente(idCliente);
                membresia.setVigencia(vigencia);
            }
            return membresia;
    }
    
    
}
