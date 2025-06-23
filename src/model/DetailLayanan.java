package model;

public class DetailLayanan {
    private int idDetail;
    private int idServis;
    private String nama;
    private String layanan;
    private double harga;

    public DetailLayanan(int idDetail, int idServis, String nama, String layanan, double harga) {
        this.idDetail = idDetail;
        this.idServis = idServis;
        this.nama = nama;
        this.layanan = layanan;
        this.harga = harga;
    }

    public DetailLayanan(int idServis, String nama, String layanan, double harga) {
        this(0, idServis, nama, layanan, harga);
    }

    public int getIdDetail() { return idDetail; }
    public int getIdServis() { return idServis; }
    public String getNama() { return nama; }
    public String getLayanan() { return layanan; }
    public double getHarga() { return harga; }

    public void setIdDetail(int id) { this.idDetail = id; }
}
