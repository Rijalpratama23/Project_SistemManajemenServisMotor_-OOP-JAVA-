package view;

import dao.KendaraanDAO;
import model.Kendaraan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormKendaraan extends JFrame {
    private JTextField tfIdPelanggan, tfMerk, tfTipe, tfPlat, tfTahun;
    private JTable table;
    private DefaultTableModel tableModel;
    private KendaraanDAO dao = new KendaraanDAO();
    private int selectedId = -1;

    public FormKendaraan() {
        setTitle("Manajemen Kendaraan");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel input
        JPanel panel = new JPanel(new GridLayout(6, 2));
        tfIdPelanggan = new JTextField();
        tfMerk = new JTextField();
        tfTipe = new JTextField();
        tfPlat = new JTextField();
        tfTahun = new JTextField();

        panel.add(new JLabel("ID Pelanggan:"));
        panel.add(tfIdPelanggan);
        panel.add(new JLabel("Merk:"));
        panel.add(tfMerk);
        panel.add(new JLabel("Tipe:"));
        panel.add(tfTipe);
        panel.add(new JLabel("Plat Nomor:"));
        panel.add(tfPlat);
        panel.add(new JLabel("Tahun:"));
        panel.add(tfTahun);

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        panel.add(btnTambah);
        panel.add(btnUpdate);

        add(panel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "ID Pelanggan", "Merk", "Tipe", "Plat", "Tahun"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom panel
        JButton btnHapus = new JButton("Hapus");
        JButton btnKembali = new JButton("Kembali");
        JPanel bawah = new JPanel();
        bawah.add(btnHapus);
        bawah.add(btnKembali);
        add(bawah, BorderLayout.SOUTH);

        // Event
        btnTambah.addActionListener(e -> {
            Kendaraan k = new Kendaraan(
                    Integer.parseInt(tfIdPelanggan.getText()),
                    tfMerk.getText(),
                    tfTipe.getText(),
                    tfPlat.getText(),
                    Integer.parseInt(tfTahun.getText())
            );
            dao.insert(k);
            loadTable();
            resetForm();
        });

        btnUpdate.addActionListener(e -> {
            if (selectedId != -1) {
                Kendaraan k = new Kendaraan(
                        selectedId,
                        Integer.parseInt(tfIdPelanggan.getText()),
                        tfMerk.getText(),
                        tfTipe.getText(),
                        tfPlat.getText(),
                        Integer.parseInt(tfTahun.getText())
                );
                dao.update(k);
                loadTable();
                resetForm();
                selectedId = -1;
            }
        });

        btnHapus.addActionListener(e -> {
            if (selectedId != -1) {
                dao.delete(selectedId);
                loadTable();
                resetForm();
                selectedId = -1;
            }
        });

        btnKembali.addActionListener(e -> {
            new MenuUtama();
            dispose();
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                selectedId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                tfIdPelanggan.setText(tableModel.getValueAt(row, 1).toString());
                tfMerk.setText(tableModel.getValueAt(row, 2).toString());
                tfTipe.setText(tableModel.getValueAt(row, 3).toString());
                tfPlat.setText(tableModel.getValueAt(row, 4).toString());
                tfTahun.setText(tableModel.getValueAt(row, 5).toString());
            }
        });

        loadTable();
        setVisible(true);
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        for (Kendaraan k : dao.getAll()) {
            tableModel.addRow(new Object[]{
                    k.getIdKendaraan(), k.getIdPelanggan(), k.getMerk(), k.getTipe(), k.getPlatNomor(), k.getTahun()
            });
        }
    }

    private void resetForm() {
        tfIdPelanggan.setText("");
        tfMerk.setText("");
        tfTipe.setText("");
        tfPlat.setText("");
        tfTahun.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormKendaraan::new);
    }
}
