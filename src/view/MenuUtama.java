package view;

import java.awt.*;
import javax.swing.*;

public class MenuUtama extends JFrame {

    public MenuUtama() {
        setTitle("Sistem Manajemen Servis Motor");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Tombol menu
        JButton btnPelanggan = new JButton("Manajemen Pelanggan");
        JButton btnMontir = new JButton("Manajemen Montir");
        JButton btnKendaraan = new JButton("Manajemen Kendaraan");
        JButton btnDetailLayanan = new JButton("Manajemen Detail Layanan");
        JButton btnDataServis = new JButton("Manajemen Data Servis");
        JButton btnKeluar = new JButton("Keluar");

        // Tambahkan ke tampilan
        add(btnPelanggan);
        add(btnMontir);
        add(btnKendaraan);
        add(btnDetailLayanan);
        add(btnDataServis);
        add(btnKeluar);

        // Aksi tombol
        btnPelanggan.addActionListener(e -> {
            new FormPelanggan(); // buka form pelanggan
            dispose();           // tutup menu utama
        });

        btnMontir.addActionListener(e -> {
            new FormMontir();    // buka form montir
            dispose();
        });

        btnKendaraan.addActionListener(e -> {
            new FormKendaraan();
            dispose();
        });

        btnDetailLayanan.addActionListener(e -> {
            new FormDetailLayanan();
            dispose();
        });

        btnDataServis.addActionListener(e -> {
            new FormDataServis();
            dispose();
        });

        btnKeluar.addActionListener(e -> {
            System.exit(0);      // keluar dari aplikasi
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuUtama::new);
    }
}
