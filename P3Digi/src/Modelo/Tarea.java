package Modelo;

import java.time.LocalDateTime;

public class Tarea {

    private int id;
    private String titulo;
    private String descripcion;
    private int prioridad;
    private String estado;
    private LocalDateTime fechaCreacion;

    // Constructor vacío (necesario para crear objetos sin datos)
    public Tarea() {}

    // Constructor para crear nuevas tareas (sin id ni fecha)
    public Tarea(String titulo, String descripcion, int prioridad, String estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    // Constructor completo (para cuando leemos desde la BD)
    public Tarea(int id, String titulo, String descripcion, int prioridad, String estado, LocalDateTime fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getPrioridad() { return prioridad; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Título: " + titulo +
                " | Prioridad: " + prioridad +
                " | Estado: " + estado +
                " | Fecha: " + fechaCreacion +
                "\nDescripción: " + descripcion;
    }
}
