package model;

public class Pelanggan {
    private int idPelanggan;
    private String nama;
    private String noTelpon;
    private String alamat;

    // Constructor
    public Pelanggan(int idPelanggan, String nama, String noTelpon, String alamat) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.noTelpon = noTelpon;
        this.alamat = alamat;
    }

    // Constructor tanpa ID (untuk insert)
    public Pelanggan(String nama, String noTelpon, String alamat) {
        this.nama = nama;
        this.noTelpon = noTelpon;
        this.alamat = alamat;
    }

    // Getter & Setter
    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
