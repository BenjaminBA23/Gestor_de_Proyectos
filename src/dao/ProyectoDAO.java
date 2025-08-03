/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.util.List;
import modelo.Proyecto;
import utilidades.ConexionDB;

/**
 *
 * @author Ben
 */
public class ProyectoDAO {
    public List<Proyecto> listarProyectos() {
        List<Proyecto> lista = new ArrayList<>();
        String sql = "SELECT * FROM proyectos";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proyecto p = new Proyecto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFechaInicio(rs.getString("fecha_inicio"));
                p.setFechaFin(rs.getString("fecha_fin"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
