package dao;

import database.Koneksi;
import model.DataServis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataServisDAO {

    public void insert(DataServis ds) {
        String sql = "INSERT INTO data_servis (id_kendaraan, id_montir, tanggal_servis, keluhan, total_biaya) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ds.getIdKendaraan());
            stmt.setInt(2, ds.getIdMontir());
            stmt.setDate(3, new java.sql.Date(ds.getTanggalServis().getTime()));
            stmt.setString(4, ds.getKeluhan());
            stmt.setDouble(5, ds.getTotalBiaya());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal insert: " + e.getMessage());
        }
    }

    public List<DataServis> getAll() {
        List<DataServis> list = new ArrayList<>();
        String sql = "SELECT * FROM data_servis";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DataServis ds = new DataServis(
                        rs.getInt("id_servis"),
                        rs.getInt("id_kendaraan"),
                        rs.getInt("id_montir"),
                        rs.getDate("tanggal_servis"),
                        rs.getString("keluhan"),
                        rs.getDouble("total_biaya")
                );
                list.add(ds);
            }

        } catch (SQLException e) {
            System.out.println("Gagal getAll: " + e.getMessage());
        }

        return list;
    }

    public void update(DataServis ds) {
        String sql = "UPDATE data_servis SET id_kendaraan=?, id_montir=?, tanggal_servis=?, keluhan=?, total_biaya=? WHERE id_servis=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ds.getIdKendaraan());
            stmt.setInt(2, ds.getIdMontir());
            stmt.setDate(3, new java.sql.Date(ds.getTanggalServis().getTime()));
            stmt.setString(4, ds.getKeluhan());
            stmt.setDouble(5, ds.getTotalBiaya());
            stmt.setInt(6, ds.getIdServis());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal update: " + e.getMessage());
        }
    }

    public void delete(int idServis) {
        String sql = "DELETE FROM data_servis WHERE id_servis=?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idServis);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal delete: " + e.getMessage());
        }
    }
}
