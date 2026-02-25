package Controladores;

import DAO.TareaDAO;
import Modelo.Tarea;

import java.util.List;

public class ControladorTarea {

    private TareaDAO tareaDAO;
    private IAService iaService;

    public ControladorTarea() {
        this.tareaDAO = new TareaDAO();
        this.iaService = new IAService();
    }

    // CREAR TAREA (con prioridad sugerida por IA)
    public boolean crearTarea(String titulo, String descripcion) {

        // 1. Pedimos a la IA la prioridad
        int prioridadSugerida = iaService.sugerirPrioridad(descripcion);

        // 2. Creamos la tarea con estado por defecto "pendiente"
        Tarea tarea = new Tarea(titulo, descripcion, prioridadSugerida, "pendiente");

        // 3. Guardamos en la BD
        return tareaDAO.crear(tarea);
    }

    // LISTAR TAREAS
    public List<Tarea> listarTareas() {
        return tareaDAO.listar();
    }

    // MODIFICAR TAREA
    public boolean modificarTarea(int id, String nuevoTitulo, String nuevaDescripcion, int nuevaPrioridad, String nuevoEstado) {

        Tarea tarea = new Tarea(id, nuevoTitulo, nuevaDescripcion, nuevaPrioridad, nuevoEstado, null);

        return tareaDAO.modificar(tarea);
    }

    // ELIMINAR TAREA
    public boolean eliminarTarea(int id) {
        return tareaDAO.eliminar(id);
    }

    // BUSCAR TAREAS
    public List<Tarea> buscarTareas(String texto) {
        return tareaDAO.buscar(texto);
    }
}
