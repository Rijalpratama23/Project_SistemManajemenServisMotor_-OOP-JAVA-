package model;

import java.util.Date;

public class DataServis {
    private int idServis;
    private int idKendaraan;
    private int idMontir;
    private Date tanggalServis;
    private String keluhan;
    private double totalBiaya;

    public DataServis(int idServis, int idKendaraan, int idMontir, Date tanggalServis, String keluhan, double totalBiaya) {
        this.idServis = idServis;
        this.idKendaraan = idKendaraan;
        this.idMontir = idMontir;
        this.tanggalServis = tanggalServis;
        this.keluhan = keluhan;
        this.totalBiaya = totalBiaya;
    }

    public DataServis(int idKendaraan, int idMontir, Date tanggalServis, String keluhan, double totalBiaya) {
        this(0, idKendaraan, idMontir, tanggalServis, keluhan, totalBiaya);
    }

    // Getter dan Setter
    public int getIdServis() { return idServis; }
    public void setIdServis(int idServis) { this.idServis = idServis; }

    public int getIdKendaraan() { return idKendaraan; }
    public void setIdKendaraan(int idKendaraan) { this.idKendaraan = idKendaraan; }

    public int getIdMontir() { return idMontir; }
    public void setIdMontir(int idMontir) { this.idMontir = idMontir; }

    public Date getTanggalServis() { return tanggalServis; }
    public void setTanggalServis(Date tanggalServis) { this.tanggalServis = tanggalServis; }

    public String getKeluhan() { return keluhan; }
    public void setKeluhan(String keluhan) { this.keluhan = keluhan; }

    public double getTotalBiaya() { return totalBiaya; }
    public void setTotalBiaya(double totalBiaya) { this.totalBiaya = totalBiaya; }
}
