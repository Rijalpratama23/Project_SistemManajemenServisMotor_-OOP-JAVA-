package view;

import dao.DataServisDAO;
import model.DataServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormDataServis extends JFrame {
    private final DataServisDAO dao = new DataServisDAO();
    private final DefaultTableModel tableModel = new DefaultTableModel(
        new String[]{"ID", "ID Kendaraan", "ID Montir", "Tanggal", "Keluhan", "Total Biaya"}, 0);
    private JTable table;
    private JTextField tfIdKendaraan, tfIdMontir, tfTanggal, tfKeluhan, tfBiaya;
    private int selectedId = -1;

    public FormDataServis() {
        setTitle("Form Data Servis");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel input
        JPanel panelForm = new JPanel(new GridLayout(6, 2));
        tfIdKendaraan = new JTextField();
        tfIdMontir = new JTextField();
        tfTanggal = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        tfKeluhan = new JTextField();
        tfBiaya = new JTextField();

        panelForm.add(new JLabel("ID Kendaraan:"));
        panelForm.add(tfIdKendaraan);
        panelForm.add(new JLabel("ID Montir:"));
        panelForm.add(tfIdMontir);
        panelForm.add(new JLabel("Tanggal Servis:"));
        panelForm.add(tfTanggal);
        panelForm.add(new JLabel("Keluhan:"));
        panelForm.add(tfKeluhan);
        panelForm.add(new JLabel("Total Biaya:"));
        panelForm.add(tfBiaya);

        JButton btnSimpan = new JButton("Simpan");
        JButton btnHapus = new JButton("Hapus");
        JButton btnKembali = new JButton("Kembali");

        panelForm.add(btnSimpan);
        panelForm.add(btnHapus);

        add(panelForm, BorderLayout.NORTH);

        // Tabel
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);
        add(btnKembali, BorderLayout.SOUTH);

        // Load Data
        loadData();

        // Event Listener
        btnSimpan.addActionListener(e -> saveData());
        btnHapus.addActionListener(e -> deleteData());
        btnKembali.addActionListener(e -> {
            new MenuUtama();
            dispose();
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                tfIdKendaraan.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                tfIdMontir.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                tfTanggal.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                tfKeluhan.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                tfBiaya.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
            }
        });

        setVisible(true);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        for (DataServis ds : dao.getAll()) {
            tableModel.addRow(new Object[]{
                ds.getIdServis(), ds.getIdKendaraan(), ds.getIdMontir(),
                new SimpleDateFormat("yyyy-MM-dd").format(ds.getTanggalServis()),
                ds.getKeluhan(), ds.getTotalBiaya()
            });
        }
    }

    private void saveData() {
        try {
            DataServis ds = new DataServis(
                selectedId,
                Integer.parseInt(tfIdKendaraan.getText()),
                Integer.parseInt(tfIdMontir.getText()),
                new SimpleDateFormat("yyyy-MM-dd").parse(tfTanggal.getText()),
                tfKeluhan.getText(),
                Double.parseDouble(tfBiaya.getText())
            );

            if (selectedId == -1) {
                dao.insert(ds);
            } else {
                dao.update(ds);
            }

            clearForm();
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Format input salah atau ada kolom kosong");
        }
    }

    private void deleteData() {
        if (selectedId != -1) {
            dao.delete(selectedId);
            clearForm();
            loadData();
        }
    }

    private void clearForm() {
        selectedId = -1;
        tfIdKendaraan.setText("");
        tfIdMontir.setText("");
        tfTanggal.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        tfKeluhan.setText("");
        tfBiaya.setText("");
    }
}
