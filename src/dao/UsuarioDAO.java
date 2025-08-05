/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import modelo.Usuario;
import utilidades.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ben
 */
public class UsuarioDAO {
    private Connection connection;

    // Constructor que establece la conexión a la base de datos
    public UsuarioDAO() {
        connection = ConexionDB.conectar();  // Suponiendo que tienes esta clase de conexión
    }

    // Método para obtener un usuario por su ID
    public Usuario obtenerUsuarioPorId(int id) {
        Usuario u = null;
        try {
            // Consulta SQL para obtener un usuario por su ID
            String query = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);  // Establece el ID en el parámetro de la consulta
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Crea un objeto Usuario con los datos obtenidos de la base de datos
                u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();  // Maneja posibles errores de la consulta
        }
        return u;
    }

    // Método para agregar un usuario
    public boolean agregarUsuario(Usuario u) {
        try {
            String query = "INSERT INTO usuarios (nombre, correo) VALUES (?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, u.getNombre());
            pst.setString(2, u.getCorreo());
            
            int filasInsertadas = pst.executeUpdate();  // Ejecuta la inserción
            return filasInsertadas > 0;  // Si se insertaron filas, devuelve true
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un usuario
    public boolean actualizarUsuario(Usuario u) {
        try {
            String query = "UPDATE usuarios SET nombre = ?, correo = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, u.getNombre());
            pst.setString(2, u.getCorreo());
            pst.setInt(3, u.getId());
            
            int filasActualizadas = pst.executeUpdate();  // Ejecuta la actualización
            return filasActualizadas > 0;  // Si se actualizó la fila, devuelve true
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un usuario
    public boolean eliminarUsuario(int id) {
        try {
            String query = "DELETE FROM usuarios WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id);  // Establece el ID del usuario a eliminar
            
            int filasEliminadas = pst.executeUpdate();  // Ejecuta la eliminación
            return filasEliminadas > 0;  // Si se eliminaron filas, devuelve true
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Método para listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        try {
            String query = "SELECT * FROM usuarios";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                // Crea un nuevo objeto Usuario y lo agrega a la lista
                Usuario u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;  // Devuelve la lista de usuarios
    }
}
