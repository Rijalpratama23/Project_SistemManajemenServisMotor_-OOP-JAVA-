package dao;

import database.Koneksi;
import model.Kendaraan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KendaraanDAO {

    public void insert(Kendaraan k) {
        String sql = "INSERT INTO kendaraan (id_pelanggan, merk, tipe, plat_nomor, tahun) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, k.getIdPelanggan());
            stmt.setString(2, k.getMerk());
            stmt.setString(3, k.getTipe());
            stmt.setString(4, k.getPlatNomor());
            stmt.setInt(5, k.getTahun());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal insert: " + e.getMessage());
        }
    }

    public List<Kendaraan> getAll() {
        List<Kendaraan> list = new ArrayList<>();
        String sql = "SELECT * FROM kendaraan";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Kendaraan(
                        rs.getInt("id_kendaraan"),
                        rs.getInt("id_pelanggan"),
                        rs.getString("merk"),
                        rs.getString("tipe"),
                        rs.getString("plat_nomor"),
                        rs.getInt("tahun")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Gagal getAll: " + e.getMessage());
        }
        return list;
    }

    public void update(Kendaraan k) {
        String sql = "UPDATE kendaraan SET id_pelanggan=?, merk=?, tipe=?, plat_nomor=?, tahun=? WHERE id_kendaraan=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, k.getIdPelanggan());
            stmt.setString(2, k.getMerk());
            stmt.setString(3, k.getTipe());
            stmt.setString(4, k.getPlatNomor());
            stmt.setInt(5, k.getTahun());
            stmt.setInt(6, k.getIdKendaraan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal update: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM kendaraan WHERE id_kendaraan=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal delete: " + e.getMessage());
        }
    }
}
