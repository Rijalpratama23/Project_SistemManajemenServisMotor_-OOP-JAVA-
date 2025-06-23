package view;

import dao.MontirDAO;
import model.Montir;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FormMontir extends JFrame {
    private JTextField tfNama, tfSpesialis;
    private JTable table;
    private DefaultTableModel tableModel;

    private MontirDAO dao = new MontirDAO();
    private int selectedId = -1;

    public FormMontir() {
        setTitle("Manajemen Montir");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Input
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Data Montir"));

        tfNama = new JTextField();
        tfSpesialis = new JTextField();

        inputPanel.add(new JLabel("Nama:"));
        inputPanel.add(tfNama);
        inputPanel.add(new JLabel("Spesialis:"));
        inputPanel.add(tfSpesialis);

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        inputPanel.add(btnTambah);
        inputPanel.add(btnUpdate);

        add(inputPanel, BorderLayout.NORTH);

        // Tabel
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Spesialis"}, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel bawah
        JButton btnHapus = new JButton("Hapus");
        JButton btnKembali = new JButton("Kembali");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnHapus);
        bottomPanel.add(btnKembali);
        add(bottomPanel, BorderLayout.SOUTH);

        // Load data
        loadTable();

        // Klik tabel
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    selectedId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                    tfNama.setText(tableModel.getValueAt(row, 1).toString());
                    tfSpesialis.setText(tableModel.getValueAt(row, 2).toString());
                }
            }
        });

        // Event tombol
        btnTambah.addActionListener(e -> tambahMontir());
        btnUpdate.addActionListener(e -> updateMontir());
        btnHapus.addActionListener(e -> hapusMontir());
        btnKembali.addActionListener(e -> {
            new MenuUtama();
            dispose();
        });

        setVisible(true);
    }

    private void tambahMontir() {
        Montir m = new Montir(
                tfNama.getText(),
                tfSpesialis.getText()
        );
        dao.insertMontir(m);
        loadTable();
        resetForm();
    }

    private void updateMontir() {
        if (selectedId != -1) {
            Montir m = new Montir(selectedId, tfNama.getText(), tfSpesialis.getText());
            dao.updateMontir(m);
            loadTable();
            resetForm();
            selectedId = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate.");
        }
    }

    private void hapusMontir() {
        if (selectedId != -1) {
            int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                dao.deleteMontir(selectedId);
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
        List<Montir> list = dao.getAllMontir();
        for (Montir m : list) {
            tableModel.addRow(new Object[]{
                    m.getIdMontir(),
                    m.getNama(),
                    m.getSpesialis()
            });
        }
    }

    private void resetForm() {
        tfNama.setText("");
        tfSpesialis.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormMontir::new);
    }
}
