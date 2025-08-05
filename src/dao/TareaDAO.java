/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Tarea;
import utilidades.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ben
 */
public class TareaDAO {

    public List<Tarea> listarTareas() {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tarea t = new Tarea();
                t.setId(rs.getInt("id"));
                t.setNombre(rs.getString("nombre"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setFechaInicio(rs.getString("fechainicio"));
                t.setFechaFin(rs.getString("fechaFin"));
                t.setEstado(rs.getString("estado"));
                t.setPrioridad(rs.getString("prioridad"));
                t.setIdProyecto(rs.getInt("idProyecto"));
                t.setIdUsuario(rs.getInt("id_usuario"));

                lista.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean agregarTarea(Tarea t) {
        String sql = "INSERT INTO tareas (nombre, descripcion, fechainicio, fechaFin, estado, prioridad, idProyecto, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setString(2, t.getDescripcion());
            ps.setString(3, t.getFechaInicio());
            ps.setString(4, t.getFechaFin());
            ps.setString(5, t.getEstado());
            ps.setString(6, t.getPrioridad());
            ps.setInt(7, t.getIdProyecto());
            ps.setInt(8, t.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarTarea(int id) {
        String sql = "DELETE FROM tareas WHERE id = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarTarea(Tarea t) {
        String sql = "UPDATE tareas SET nombre=?, descripcion=?, fechainicio=?, fechaFin=?, estado=?, prioridad=?, idProyecto=?, id_usuario=? WHERE id=?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setString(2, t.getDescripcion());
            ps.setString(3, t.getFechaInicio());
            ps.setString(4, t.getFechaFin());
            ps.setString(5, t.getEstado());
            ps.setString(6, t.getPrioridad());
            ps.setInt(7, t.getIdProyecto());
            ps.setInt(8, t.getIdUsuario());
            ps.setInt(9, t.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
