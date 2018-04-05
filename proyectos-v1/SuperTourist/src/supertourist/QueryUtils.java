/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supertourist;

/**
 *
 * @author diego
 */
public class QueryUtils {
    
    public static final String INSERT = "INSERT INTO ";
    public static final String UPDATE = "UPDATE ";
    public static final String DELETE = "DELETE FROM ";
    public static final String SELECT_ALL_FIELDS = "SELECT * FROM ";
    public static final String SET = "SET ";
    public static final String EQUAL = "=?";
    public static final String TYPE_VARCHAR128 = " varchar(128) "; 
    public static final String TYPE_INT = " int ";
    public static final String COMMA = ",";
    public static final String OPEN_PARENTHESIS ="(";
    public static final String CLOSE_PARENTHESIS = ") ";
    public static final String TWO_FIELDS = "values(?,?);";
    public static final String ONE_FIELD = "values(?);";
    
    
    public static class Almacen{
        public static final String TABLE_NAME ="almacenes ";
        public static final String FIRST_FIELD = "sucursal";
        public static final String SECOND_FIELD = "address";
        public static final String CONDITION_ID = " WHERE id=?;";
        public static final String INTERESTING_FIELD = " WHERE " + FIRST_FIELD + " LIKE ?";
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(id int primary key auto_increment,"
                + FIRST_FIELD + TYPE_VARCHAR128 +  "not null,"
                + SECOND_FIELD + TYPE_VARCHAR128 + "not null);";
        
        public static final String INSERT = QueryUtils.INSERT + TABLE_NAME + OPEN_PARENTHESIS
                + FIRST_FIELD + COMMA + SECOND_FIELD 
                + CLOSE_PARENTHESIS + TWO_FIELDS;
        public static final String UPDATE = QueryUtils.UPDATE + TABLE_NAME + SET + FIRST_FIELD
                + EQUAL + COMMA + SECOND_FIELD + EQUAL + CONDITION_ID;
        public static final String DELETE = QueryUtils.DELETE + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_ID = SELECT_ALL_FIELDS + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_INTERESTING_FIELD = SELECT_ALL_FIELDS + TABLE_NAME + INTERESTING_FIELD;
           
    }
    public static class Asesor{
        public static final String TABLE_NAME ="asesores ";
        public static final String FIRST_FIELD = "name";
        public static final String SECOND_FIELD = "email";
        public static final String CONDITION_ID = " WHERE id=?;";
        public static final String INTERESTING_FIELD = " WHERE " + FIRST_FIELD + " LIKE ?";
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(id int primary key auto_increment,"
                + FIRST_FIELD + TYPE_VARCHAR128 +  "not null,"
                + SECOND_FIELD + TYPE_VARCHAR128 + "not null);";
        
        public static final String INSERT = QueryUtils.INSERT + TABLE_NAME + OPEN_PARENTHESIS
                + FIRST_FIELD + COMMA + SECOND_FIELD 
                + CLOSE_PARENTHESIS + TWO_FIELDS;
        public static final String UPDATE = QueryUtils.UPDATE + TABLE_NAME + SET + FIRST_FIELD
                + EQUAL + COMMA + SECOND_FIELD + EQUAL + CONDITION_ID;
        public static final String DELETE = QueryUtils.DELETE + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_ID = SELECT_ALL_FIELDS + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_INTERESTING_FIELD = SELECT_ALL_FIELDS + TABLE_NAME + INTERESTING_FIELD;
    }
    
    public static class Celular{
        public static final String TABLE_NAME ="celulares ";
        public static final String FIRST_FIELD = "model";
        public static final String CONDITION_ID = " WHERE id=?;";
        public static final String INTERESTING_FIELD = " WHERE " + FIRST_FIELD + " LIKE ?";
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(id int primary key auto_increment,"
                + FIRST_FIELD + TYPE_VARCHAR128 +  "not null);";
        
        public static final String INSERT = QueryUtils.INSERT + TABLE_NAME + OPEN_PARENTHESIS
                + FIRST_FIELD  
                + CLOSE_PARENTHESIS + ONE_FIELD;
        public static final String UPDATE = QueryUtils.UPDATE + TABLE_NAME + SET + FIRST_FIELD
                + EQUAL + CONDITION_ID;
        public static final String DELETE = QueryUtils.DELETE + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_ID = SELECT_ALL_FIELDS + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_INTERESTING_FIELD = SELECT_ALL_FIELDS + TABLE_NAME + INTERESTING_FIELD; 
    }
    public static class Encargado{
        public static final String TABLE_NAME ="encargados ";
        public static final String FIRST_FIELD = "name";
        public static final String SECOND_FIELD = "email";
        public static final String CONDITION_ID = " WHERE id=?;";
        public static final String INTERESTING_FIELD = " WHERE " + FIRST_FIELD + " LIKE ?";
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(id int primary key auto_increment,"
                + FIRST_FIELD + TYPE_VARCHAR128 +  "not null,"
                + SECOND_FIELD + TYPE_VARCHAR128 + "not null);";
        
        public static final String INSERT = QueryUtils.INSERT + TABLE_NAME + OPEN_PARENTHESIS
                + FIRST_FIELD + COMMA + SECOND_FIELD 
                + CLOSE_PARENTHESIS + TWO_FIELDS;
        public static final String UPDATE = QueryUtils.UPDATE + TABLE_NAME + SET + FIRST_FIELD
                + EQUAL + COMMA + SECOND_FIELD + EQUAL + CONDITION_ID;
        public static final String DELETE = QueryUtils.DELETE + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_ID = SELECT_ALL_FIELDS + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_INTERESTING_FIELD = SELECT_ALL_FIELDS + TABLE_NAME + INTERESTING_FIELD;
    }
    
    //TODO implementar correctamente
    public static class OrdenServicio{
        public static final String TABLE_NAME ="ordenes ";
        public static final String FIRST_FIELD = "sucription";
        public static final String SECOND_FIELD = "user";
        public static final String THIRD_FIELD = "almacen";
        public static final String FOURTH_FIELD = "celular";
        public static final String FIFTH_FIELD = "encargado";
        public static final String SIXTH_FIELD = "prestadores";
        public static final String SEVENTH_FIELD = "asesor";
        public static final String CONDITION_ID = " WHERE id=?;";
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(id int primary key auto_increment,"
                + FIRST_FIELD + TYPE_INT +  "not null,"
                + SECOND_FIELD + TYPE_INT +  "not null,"
                + THIRD_FIELD + TYPE_INT +  "not null,"
                + FOURTH_FIELD + TYPE_INT +  "not null,"
                + FIFTH_FIELD + TYPE_INT +  "not null,"
                + SIXTH_FIELD + TYPE_INT +  "not null,"
                + SEVENTH_FIELD + TYPE_INT + "not null);";
        
        public static final String INSERT = QueryUtils.INSERT + TABLE_NAME + OPEN_PARENTHESIS
                + FIRST_FIELD + COMMA + SECOND_FIELD 
                + CLOSE_PARENTHESIS + TWO_FIELDS;
        public static final String UPDATE = QueryUtils.UPDATE + TABLE_NAME + SET + FIRST_FIELD
                + EQUAL + COMMA + SECOND_FIELD + EQUAL + CONDITION_ID;
        public static final String DELETE = QueryUtils.DELETE + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_ID = SELECT_ALL_FIELDS + TABLE_NAME + CONDITION_ID;  
    }
    public static class PrestadorServicio{
        public static final String TABLE_NAME ="prestadores ";
        public static final String FIRST_FIELD = "name";
        public static final String SECOND_FIELD = "service";
        public static final String CONDITION_ID = " WHERE id=?;";
        public static final String INTERESTING_FIELD = " WHERE " + SECOND_FIELD + " LIKE ?";
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(id int primary key auto_increment,"
                + FIRST_FIELD + TYPE_VARCHAR128 +  "not null,"
                + SECOND_FIELD + TYPE_VARCHAR128 + "not null);";
        
        public static final String INSERT = QueryUtils.INSERT + TABLE_NAME + OPEN_PARENTHESIS
                + FIRST_FIELD + COMMA + SECOND_FIELD 
                + CLOSE_PARENTHESIS + TWO_FIELDS;
        public static final String UPDATE = QueryUtils.UPDATE + TABLE_NAME + SET + FIRST_FIELD
                + EQUAL + COMMA + SECOND_FIELD + EQUAL + CONDITION_ID;
        public static final String DELETE = QueryUtils.DELETE + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_ID = SELECT_ALL_FIELDS + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_INTERESTING_FIELD = SELECT_ALL_FIELDS + TABLE_NAME + INTERESTING_FIELD;
    }
    public static class Suscripcion{
        public static final String TABLE_NAME ="suscripciones ";
        public static final String FIRST_FIELD = "usuario";
        public static final String CONDITION_ID = " WHERE id=?;";
        public static final String INTERESTING_FIELD = " WHERE " + FIRST_FIELD + " =?";
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(id int primary key auto_increment,"
                + FIRST_FIELD + TYPE_INT +  "not null);";
        
        public static final String INSERT = QueryUtils.INSERT + TABLE_NAME + OPEN_PARENTHESIS
                + FIRST_FIELD  
                + CLOSE_PARENTHESIS + ONE_FIELD;
        public static final String UPDATE = QueryUtils.UPDATE + TABLE_NAME + SET + FIRST_FIELD
                + EQUAL + CONDITION_ID;
        public static final String DELETE = QueryUtils.DELETE + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_ID = SELECT_ALL_FIELDS + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_INTERESTING_FIELD = SELECT_ALL_FIELDS + TABLE_NAME + INTERESTING_FIELD;
    }
    public static class Usuario{
        public static final String TABLE_NAME ="usuarios ";
        public static final String FIRST_FIELD = "name";
        public static final String SECOND_FIELD = "email";
        public static final String CONDITION_ID = " WHERE id=?;";
        public static final String INTERESTING_FIELD = " WHERE " + SECOND_FIELD + " LIKE ?";
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(id int primary key auto_increment,"
                + FIRST_FIELD + TYPE_VARCHAR128 +  "not null,"
                + SECOND_FIELD + TYPE_VARCHAR128 + "not null);";
        
        public static final String INSERT = QueryUtils.INSERT + TABLE_NAME + OPEN_PARENTHESIS
                + FIRST_FIELD + COMMA + SECOND_FIELD 
                + CLOSE_PARENTHESIS + TWO_FIELDS;
        public static final String UPDATE = QueryUtils.UPDATE + TABLE_NAME + SET + FIRST_FIELD
                + EQUAL + COMMA + SECOND_FIELD + EQUAL + CONDITION_ID;
        public static final String DELETE = QueryUtils.DELETE + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_ID = SELECT_ALL_FIELDS + TABLE_NAME + CONDITION_ID;
        public static final String SEARCH_INTERESTING_FIELD = SELECT_ALL_FIELDS + TABLE_NAME + INTERESTING_FIELD;
    }
}
