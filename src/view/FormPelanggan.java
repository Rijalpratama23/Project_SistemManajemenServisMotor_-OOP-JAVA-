package view;

import dao.PelangganDAO;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Pelanggan;

public class FormPelanggan extends JFrame {
    private JTextField tfNama, tfTelpon;
    private JTextArea taAlamat;
    private JTable table;
    private DefaultTableModel tableModel;

    private PelangganDAO dao = new PelangganDAO();
    private int selectedId = -1;

    public FormPelanggan() {
        setTitle("Manajemen Pelanggan");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Form Input
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Data Pelanggan"));

        tfNama = new JTextField();
        tfTelpon = new JTextField();
        taAlamat = new JTextArea(2, 20);

        inputPanel.add(new JLabel("Nama:"));
        inputPanel.add(tfNama);
        inputPanel.add(new JLabel("No Telpon:"));
        inputPanel.add(tfTelpon);
        inputPanel.add(new JLabel("Alamat:"));
        inputPanel.add(new JScrollPane(taAlamat));

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");

        inputPanel.add(btnTambah);
        inputPanel.add(btnUpdate);

        add(inputPanel, BorderLayout.NORTH);

        // Panel Tabel
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Telpon", "Alamat"}, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Tombol Hapus & Kembali
        JButton btnHapus = new JButton("Hapus");
        JButton btnKembali = new JButton("Kembali");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnHapus);
        bottomPanel.add(btnKembali);
        add(bottomPanel, BorderLayout.SOUTH);

        // Load awal data
        loadTable();

        // Event klik tabel
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    selectedId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                    tfNama.setText(tableModel.getValueAt(row, 1).toString());
                    tfTelpon.setText(tableModel.getValueAt(row, 2).toString());
                    taAlamat.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        // Action Listeners
        btnTambah.addActionListener(e -> tambahData());
        btnUpdate.addActionListener(e -> updateData());
        btnHapus.addActionListener(e -> hapusData());
        btnKembali.addActionListener(e -> {
            new MenuUtama();
            dispose();
        });

        setVisible(true);
    }

    private void tambahData() {
        Pelanggan p = new Pelanggan(
                tfNama.getText(),
                tfTelpon.getText(),
                taAlamat.getText()
        );
        dao.insertPelanggan(p);
        loadTable();
        resetForm();
    }

    private void updateData() {
        if (selectedId != -1) {
            Pelanggan p = new Pelanggan(selectedId, tfNama.getText(), tfTelpon.getText(), taAlamat.getText());
            dao.updatePelanggan(p);
            loadTable();
            resetForm();
            selectedId = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate.");
        }
    }

    private void hapusData() {
        if (selectedId != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao.deletePelanggan(selectedId);
                loadTable();
                resetForm();
                selectedId = -1;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus.");
        }
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        List<Pelanggan> list = dao.getAllPelanggan();
        for (Pelanggan p : list) {
            tableModel.addRow(new Object[]{
                    p.getIdPelanggan(),
                    p.getNama(),
                    p.getNoTelpon(),
                    p.getAlamat()
            });
        }
    }

    private void resetForm() {
        tfNama.setText("");
        tfTelpon.setText("");
        taAlamat.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormPelanggan::new);
    }
}
