package DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Modelo.Tarea;
import Utilidades.ConexionBD;

public class TareaDAO {

    // CREAR TAREA
    public boolean crear(Tarea tarea) {
        String sql = "INSERT INTO tareas (titulo, descripcion, prioridad, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setInt(3, tarea.getPrioridad());
            stmt.setString(4, tarea.getEstado());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al crear tarea: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS LAS TAREAS
    public List<Tarea> listar() {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas ORDER BY fecha_creacion DESC";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarea t = new Tarea(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getInt("prioridad"),
                        rs.getString("estado"),
                        rs.getTimestamp("fecha_creacion").toLocalDateTime()
                );
                lista.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar tareas: " + e.getMessage());
        }

        return lista;
    }

    // MODIFICAR TAREA
    public boolean modificar(Tarea tarea) {
        String sql = "UPDATE tareas SET titulo=?, descripcion=?, prioridad=?, estado=? WHERE id=?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setInt(3, tarea.getPrioridad());
            stmt.setString(4, tarea.getEstado());
            stmt.setInt(5, tarea.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al modificar tarea: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR TAREA
    public boolean eliminar(int id) {
        String sql = "DELETE FROM tareas WHERE id=?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar tarea: " + e.getMessage());
            return false;
        }
    }

    // BUSCAR TAREAS POR TEXTO
    public List<Tarea> buscar(String texto) {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas WHERE titulo LIKE ? OR descripcion LIKE ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String filtro = "%" + texto + "%";
            stmt.setString(1, filtro);
            stmt.setString(2, filtro);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tarea t = new Tarea(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getInt("prioridad"),
                        rs.getString("estado"),
                        rs.getTimestamp("fecha_creacion").toLocalDateTime()
                );
                lista.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar tareas: " + e.getMessage());
        }

        return lista;
    }
}
