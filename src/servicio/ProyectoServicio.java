/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import dao.ProyectoDAO;
import java.util.List;
import modelo.Proyecto;

/**
 *
 * @author Ben
 */
public class ProyectoServicio {

    private ProyectoDAO dao = new ProyectoDAO();

    public boolean registrarProyecto(Proyecto p) {
        if (p.getNombre().isEmpty() || p.getFechaInicio().isEmpty() || p.getFechaFin().isEmpty()) {
            return false;
        }
        return dao.agregarProyecto(p);
    }
    public Proyecto obtenerProyectoPorId(int id) {
        return dao.obtenerProyectoPorId(id);  // Suponiendo que tienes este método en ProyectoDAO
    }

    public boolean actualizarProyecto(Proyecto p) {
        return dao.actualizarProyecto(p);
    }

    public boolean eliminarProyecto(int id) {
        return dao.eliminarProyecto(id);
    }

    public List<Proyecto> listarProyectos() {
        return dao.listarProyectos();
    }
}
