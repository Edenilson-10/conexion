/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import java.util.logging.Logger;
import sun.util.logging.PlatformLogger;
import sun.util.logging.PlatformLogger.Level;

/**
 *
 * @author Victor.Morales
 */
public class Conexion {
    private String user;
    private String pass;
    private String driver;
    private String url;
    
    private Connection cnx;
    
    public static Conexion instance;
    
    public synchronized static Conexion conectar() throws SQLException, ClassNotFoundException {
        if (instance == null)
        {
            return new Conexion();
        }
        return instance;
        
    }
    
    private Conexion() throws SQLException, ClassNotFoundException{
        cargarCredenciales();
        try {
            //le enviamos el drive que usara para la conexion a la base de datos
            Class.forName(this.driver);
            cnx = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
        }catch (ClassNotFoundException |SQLException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //credenciales de mi servidor de bases de datos
    private void cargarCredenciales() {
        user = "root";
        pass = "";
        driver = "com.mysql.jbc.Driver";
        url = "jbc:mysql://localhost/filtros";
    }
    public Connection getCnx(){
        return cnx;
    }
    
    public void cerrarConexion(){
        instance = null;
    }
}//fin de la coneccion
