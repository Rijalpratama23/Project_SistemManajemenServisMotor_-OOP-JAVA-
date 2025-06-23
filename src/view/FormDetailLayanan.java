package view;

import dao.DetailLayananDAO;
import model.DetailLayanan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormDetailLayanan extends JFrame {

    private JTextField tfIdServis, tfNama, tfLayanan, tfHarga;
    private JTable table;
    private DefaultTableModel tableModel;
    private DetailLayananDAO dao = new DetailLayananDAO();
    private int selectedId = -1;

    public FormDetailLayanan() {
        setTitle("Manajemen Detail Layanan");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Input panel
        JPanel panel = new JPanel(new GridLayout(5, 2));
        tfIdServis = new JTextField();
        tfNama = new JTextField();
        tfLayanan = new JTextField();
        tfHarga = new JTextField();

        panel.add(new JLabel("ID Servis:"));
        panel.add(tfIdServis);
        panel.add(new JLabel("Nama:"));
        panel.add(tfNama);
        panel.add(new JLabel("Layanan:"));
        panel.add(tfLayanan);
        panel.add(new JLabel("Harga:"));
        panel.add(tfHarga);

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        panel.add(btnTambah);
        panel.add(btnUpdate);

        add(panel, BorderLayout.NORTH);

        // Tabel
        tableModel = new DefaultTableModel(new String[]{"ID", "ID Servis", "Nama", "Layanan", "Harga"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom panel
        JPanel bawah = new JPanel();
        JButton btnHapus = new JButton("Hapus");
        JButton btnKembali = new JButton("Kembali");
        bawah.add(btnHapus);
        bawah.add(btnKembali);
        add(bawah, BorderLayout.SOUTH);

        // Event listener
        btnTambah.addActionListener(e -> {
            DetailLayanan d = new DetailLayanan(
                    Integer.parseInt(tfIdServis.getText()),
                    tfNama.getText(),
                    tfLayanan.getText(),
                    Double.parseDouble(tfHarga.getText())
            );
            dao.insert(d);
            loadTable();
            resetForm();
        });

        btnUpdate.addActionListener(e -> {
            if (selectedId != -1) {
                DetailLayanan d = new DetailLayanan(
                        selectedId,
                        Integer.parseInt(tfIdServis.getText()),
                        tfNama.getText(),
                        tfLayanan.getText(),
                        Double.parseDouble(tfHarga.getText())
                );
                dao.update(d);
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
                tfIdServis.setText(tableModel.getValueAt(row, 1).toString());
                tfNama.setText(tableModel.getValueAt(row, 2).toString());
                tfLayanan.setText(tableModel.getValueAt(row, 3).toString());
                tfHarga.setText(tableModel.getValueAt(row, 4).toString());
            }
        });

        loadTable();
        setVisible(true);
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        for (DetailLayanan d : dao.getAll()) {
            tableModel.addRow(new Object[]{
                    d.getIdDetail(), d.getIdServis(), d.getNama(), d.getLayanan(), d.getHarga()
            });
        }
    }

    private void resetForm() {
        tfIdServis.setText("");
        tfNama.setText("");
        tfLayanan.setText("");
        tfHarga.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormDetailLayanan::new);
    }
}
