/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import dao.TareaDAO;
import java.util.List;
import modelo.Tarea;

/**
 *
 * @author Ben
 */
public class TareaServicio {

    private TareaDAO dao = new TareaDAO();

    public boolean registrarTarea(Tarea t) {
        if (t.getNombre().isEmpty() || t.getFechaInicio().isEmpty() || t.getFechaFin().isEmpty()) {
            return false;
        }
        return dao.agregarTarea(t);
    }

    public boolean actualizarTarea(Tarea t) {
        return dao.actualizarTarea(t);
    }

    public boolean eliminarTarea(int id) {
        return dao.eliminarTarea(id);
    }

    public List<Tarea> listarTareas() {
        return dao.listarTareas();
    }
}
