package model;

public class Montir {
    private int idMontir;
    private String nama;
    private String spesialis;

    public Montir(String nama, String spesialis) {
        this.nama = nama;
        this.spesialis = spesialis;
    }

    public Montir(int idMontir, String nama, String spesialis) {
        this.idMontir = idMontir;
        this.nama = nama;
        this.spesialis = spesialis;
    }

    public int getIdMontir() {
        return idMontir;
    }

    public String getNama() {
        return nama;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setIdMontir(int idMontir) {
        this.idMontir = idMontir;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }
}
