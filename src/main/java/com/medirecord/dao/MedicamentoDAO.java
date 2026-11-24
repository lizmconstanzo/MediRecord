package com.medirecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medirecord.model.Medicamento;
import com.medirecord.util.ConexionDB;

public class MedicamentoDAO {

    // Guardar un nuevo medicamento en MySQL
    public boolean insertar(Medicamento med) {
        String sql = "INSERT INTO medicamentos (usuario_id, nombre, dosis, presentacion, stock_actual, fecha_vencimiento) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, med.getUsuarioId());
            ps.setString(2, med.getNombre());
            ps.setString(3, med.getDosis());
            ps.setString(4, med.getPresentacion());
            ps.setInt(5, med.getStockActual());
            ps.setDate(6, med.getFechaVencimiento());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar medicamento: " + e.getMessage());
            return false;
        }
    }

    // Consultar todos los medicamentos de un usuario específico
    public List<Medicamento> listarPorUsuario(int usuarioId) {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT id, usuario_id, nombre, dosis, presentacion, stock_actual, fecha_vencimiento "
                   + "FROM medicamentos WHERE usuario_id = ? ORDER BY nombre ASC";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medicamento med = new Medicamento();
                    med.setId(rs.getInt("id"));
                    med.setUsuarioId(rs.getInt("usuario_id"));
                    med.setNombre(rs.getString("nombre"));
                    med.setDosis(rs.getString("dosis"));
                    med.setPresentacion(rs.getString("presentacion"));
                    med.setStockActual(rs.getInt("stock_actual"));
                    med.setFechaVencimiento(rs.getDate("fecha_vencimiento"));

                    lista.add(med);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar medicamentos: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar un medicamento existente
    public boolean actualizar(Medicamento med) {
        String sql = "UPDATE medicamentos "
                   + "SET nombre = ?, dosis = ?, presentacion = ?, stock_actual = ?, fecha_vencimiento = ? "
                   + "WHERE id = ? AND usuario_id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, med.getNombre());
            ps.setString(2, med.getDosis());
            ps.setString(3, med.getPresentacion());
            ps.setInt(4, med.getStockActual());
            ps.setDate(5, med.getFechaVencimiento());
            ps.setInt(6, med.getId());
            ps.setInt(7, med.getUsuarioId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar medicamento: " + e.getMessage());
            return false;
        }
    }
}
