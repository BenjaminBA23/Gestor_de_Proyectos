/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ben
 */
public class ConexionDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=gestor_proyectos;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "sa";
    private static final String CONTRASENA = "112306";

    public static java.sql.Connection conectar() {
        java.sql.Connection conexion = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexion exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontro el driver JDBC de SQL Server.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }
    
}

