/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import servicio.ProyectoServicio;
import servicio.UsuarioServicio;

/**
 *
 * @author Ben
 */
public class Tarea {
    private int id;
    private String nombreUsuario;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private String estado;
    private String prioridad;
    private int idProyecto;
    private int idUsuario;
    
    // Estos atributos te ayudarán a evitar las consultas repetidas
    private Usuario usuario;
    private Proyecto proyecto;

    public Tarea() {}

    public Tarea(int id, String nombre, String descripcion, String fechaInicio, String fechaFin,
                 String estado, String prioridad, int idProyecto, int idUsuario) {
        this.id = id;
        this.nombreUsuario = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.prioridad = prioridad;
        this.idProyecto = idProyecto;
        this.idUsuario = idUsuario;
        
        // Carga los objetos completos de Usuario y Proyecto
        this.usuario = new UsuarioServicio().obtenerUsuarioPorId(idUsuario);  // Esto es eficiente solo si lo haces una vez
        this.proyecto = new ProyectoServicio().obtenerProyectoPorId(idProyecto);  // Igual aquí
    }

    public String getUsuarioNombre() {
        return usuario != null ? usuario.getNombre() : "Desconocido";
    }

    public String getProyectoNombre() {
        return proyecto != null ? proyecto.getNombre() : "Desconocido";
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombreUsuario; }
    public void setNombre(String nombre) { this.nombreUsuario = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public int getIdProyecto() { return idProyecto; }
    public void setIdProyecto(int idProyecto) { this.idProyecto = idProyecto; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
}
