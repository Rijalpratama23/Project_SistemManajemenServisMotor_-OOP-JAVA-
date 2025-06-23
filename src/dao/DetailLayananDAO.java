package dao;

import database.Koneksi;
import model.DetailLayanan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailLayananDAO {

    public void insert(DetailLayanan d) {
        String sql = "INSERT INTO detail_layanan (id_servis, nama, layanan, harga) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, d.getIdServis());
            stmt.setString(2, d.getNama());
            stmt.setString(3, d.getLayanan());
            stmt.setDouble(4, d.getHarga());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal insert: " + e.getMessage());
        }
    }

    public List<DetailLayanan> getAll() {
        List<DetailLayanan> list = new ArrayList<>();
        String sql = "SELECT * FROM detail_layanan";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new DetailLayanan(
                        rs.getInt("id_detail"),
                        rs.getInt("id_servis"),
                        rs.getString("nama"),
                        rs.getString("layanan"),
                        rs.getDouble("harga")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Gagal getAll: " + e.getMessage());
        }
        return list;
    }

    public void update(DetailLayanan d) {
        String sql = "UPDATE detail_layanan SET id_servis=?, nama=?, layanan=?, harga=? WHERE id_detail=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, d.getIdServis());
            stmt.setString(2, d.getNama());
            stmt.setString(3, d.getLayanan());
            stmt.setDouble(4, d.getHarga());
            stmt.setInt(5, d.getIdDetail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal update: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM detail_layanan WHERE id_detail=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal delete: " + e.getMessage());
        }
    }
}
