/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;
import dao.UsuarioDAO;
import modelo.Usuario;
/**
 *
 * @author Ben
 */
public class UsuarioServicio {

 
    private UsuarioDAO dao = new UsuarioDAO();

    public boolean registrarUsuario(Usuario u) {
        if (u.getNombre().isEmpty() || u.getCorreo().isEmpty()) {
            return false;
        }
        return dao.agregarUsuario(u);
    }

    public boolean actualizarUsuario(Usuario u) {
        return dao.actualizarUsuario(u);
    }

    public boolean eliminarUsuario(int id) {
        return dao.eliminarUsuario(id);
    }

    public List<Usuario> listarUsuarios() {
        return dao.listarUsuarios();
    }
}
