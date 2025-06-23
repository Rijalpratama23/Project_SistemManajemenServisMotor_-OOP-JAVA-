package dao;

import database.Koneksi;
import model.Montir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MontirDAO {

    public void insertMontir(Montir m) {
        String sql = "INSERT INTO montir (nama, spesialis) VALUES (?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNama());
            stmt.setString(2, m.getSpesialis());
            stmt.executeUpdate();
            System.out.println("Data montir berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data montir: " + e.getMessage());
        }
    }

    public List<Montir> getAllMontir() {
        List<Montir> list = new ArrayList<>();
        String sql = "SELECT * FROM montir";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Montir m = new Montir(
                        rs.getInt("id_montir"),
                        rs.getString("nama"),
                        rs.getString("spesialis")
                );
                list.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data montir: " + e.getMessage());
        }
        return list;
    }

    public void updateMontir(Montir m) {
        String sql = "UPDATE montir SET nama = ?, spesialis = ? WHERE id_montir = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNama());
            stmt.setString(2, m.getSpesialis());
            stmt.setInt(3, m.getIdMontir());
            stmt.executeUpdate();
            System.out.println("Data montir berhasil diperbarui.");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui data montir: " + e.getMessage());
        }
    }

    public void deleteMontir(int idMontir) {
        String sql = "DELETE FROM montir WHERE id_montir = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMontir);
            stmt.executeUpdate();
            System.out.println("Data montir berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data montir: " + e.getMessage());
        }
    }
}
