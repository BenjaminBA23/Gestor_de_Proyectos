# 🗂️ Sistema de Gestión de Proyectos (Java + Swing + SQL Server)

Este es un sistema académico de escritorio desarrollado en Java con Swing que permite gestionar proyectos, usuarios y tareas. El sistema se conecta a una base de datos SQL Server mediante JDBC, siguiendo una arquitectura por capas.

---

## 🧩 Funcionalidades

- 🔹 CRUD de **proyectos** con nombre, descripción, fechas.
- 🔹 CRUD de **usuarios** con nombre y correo.
- 🔹 CRUD de **tareas**, asignadas a usuarios y proyectos, con estado y prioridad.
- 🔹 Relación entre tareas, proyectos y usuarios.
- 🔹 Validación de campos.
- 🔹 Visualización en tablas.
- 🔹 Autoactualización de combobox sin reiniciar.
- 🔹 Control de edición con botón “Editar”.
- 🔹 Reseteo de formularios tras actualizar.

---

## 🖼️ Interfaz

El sistema cuenta con una única ventana principal con pestañas:

- **Proyectos**: Gestión de proyectos.
- **Usuarios**: Gestión de miembros del equipo.
- **Tareas**: Asignación y seguimiento de tareas.

---

## 🏗️ Tecnologías utilizadas

- ✅ Java 8+
- ✅ Swing (GUI)
- ✅ SQL Server 2022
- ✅ JDBC (conexión a base de datos)
- ✅ NetBeans IDE 25
- ✅ Apache Ant (build)

---

## 🗃️ Estructura del proyecto

GestorProyectos/
├── dao/
│ ├── ProyectoDAO.java
│ ├── UsuarioDAO.java
│ └── TareaDAO.java
├── modelo/
│ ├── Proyecto.java
│ ├── Usuario.java
│ └── Tarea.java
├── servicio/
│ ├── ProyectoServicio.java
│ ├── UsuarioServicio.java
│ └── TareaServicio.java
├── vista/
│ └── VentanaPrincipal.java
└── utilidades/
└── ConexionDB.java

yaml
Copiar
Editar

---

## 🗄️ Base de Datos

Las tablas utilizadas son:

- `usuarios(id, nombre, correo)`
- `proyectos(id, nombre, descripcion, fechainicio, fechaFin)`
- `tareas(id, nombre, descripcion, fechainicio, fechaFin, estado, prioridad, idProyecto, idUsuario)`

Incluye scripts para resetear y popular la base con datos realistas.

---

## ⚙️ Cómo ejecutar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/GestorProyectos.git
Abre el proyecto en NetBeans.

Asegúrate de tener una base de datos SQL Server en ejecución.

Configura ConexionDB.java con tus datos:

java
Copiar
Editar
private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=gestor_proyectos;encrypt=true;trustServerCertificate=true";
private static final String USUARIO = "sa";
private static final String CLAVE = "112306";
