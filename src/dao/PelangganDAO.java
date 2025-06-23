package dao;

import database.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pelanggan;

public class PelangganDAO {

    // CREATE
    public void insertPelanggan(Pelanggan p) {
        String sql = "INSERT INTO pelanggan (nama, no_telpon, alamat) VALUES (?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getNoTelpon());
            stmt.setString(3, p.getAlamat());

            stmt.executeUpdate();
            System.out.println("Data pelanggan berhasil ditambahkan.");

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data pelanggan: " + e.getMessage());
        }
    }

    // READ (All)
    public List<Pelanggan> getAllPelanggan() {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pelanggan p = new Pelanggan(
                    rs.getInt("id_pelanggan"),
                    rs.getString("nama"),
                    rs.getString("no_telpon"),
                    rs.getString("alamat")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pelanggan: " + e.getMessage());
        }

        return list;
    }

    // UPDATE
    public void updatePelanggan(Pelanggan p) {
        String sql = "UPDATE pelanggan SET nama = ?, no_telpon = ?, alamat = ? WHERE id_pelanggan = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getNoTelpon());
            stmt.setString(3, p.getAlamat());
            stmt.setInt(4, p.getIdPelanggan());

            stmt.executeUpdate();
            System.out.println("Data pelanggan berhasil diperbarui.");

        } catch (SQLException e) {
            System.out.println("Gagal memperbarui data pelanggan: " + e.getMessage());
        }
    }

    // DELETE
    public void deletePelanggan(int idPelanggan) {
    String sql = "DELETE FROM pelanggan WHERE id_pelanggan = ?";
    try (Connection conn = Koneksi.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idPelanggan);
        stmt.executeUpdate();
        System.out.println("Data pelanggan berhasil dihapus.");

    } catch (SQLException e) {
        System.out.println("Gagal menghapus data pelanggan: " + e.getMessage());
    }
    }
}