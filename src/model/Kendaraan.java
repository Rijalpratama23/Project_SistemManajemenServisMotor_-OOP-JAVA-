package model;

public class Kendaraan {
    private int idKendaraan;
    private int idPelanggan;
    private String merk;
    private String tipe;
    private String platNomor;
    private int tahun;

    public Kendaraan(int idKendaraan, int idPelanggan, String merk, String tipe, String platNomor, int tahun) {
        this.idKendaraan = idKendaraan;
        this.idPelanggan = idPelanggan;
        this.merk = merk;
        this.tipe = tipe;
        this.platNomor = platNomor;
        this.tahun = tahun;
    }

    public Kendaraan(int idPelanggan, String merk, String tipe, String platNomor, int tahun) {
        this(0, idPelanggan, merk, tipe, platNomor, tahun);
    }

    public int getIdKendaraan() { return idKendaraan; }
    public int getIdPelanggan() { return idPelanggan; }
    public String getMerk() { return merk; }
    public String getTipe() { return tipe; }
    public String getPlatNomor() { return platNomor; }
    public int getTahun() { return tahun; }

    public void setIdKendaraan(int id) { this.idKendaraan = id; }
}
