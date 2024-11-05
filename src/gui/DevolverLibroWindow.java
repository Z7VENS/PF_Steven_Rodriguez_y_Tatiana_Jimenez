package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DevolverLibroWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTextField idPrestamoField;

    public DevolverLibroWindow(BibliotecaDAO dao) {
        this.bibliotecaDAO = dao;
        setTitle("Devolver Libro");
        setSize(300, 150);
        setLocationRelativeTo(null);

        JLabel idPrestamoLabel = new JLabel("ID Préstamo:");

        idPrestamoField = new JTextField(10);

        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.addActionListener(e -> devolverLibro());

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(idPrestamoLabel);
        panel.add(idPrestamoField);
        panel.add(new JLabel());
        panel.add(btnDevolver);

        add(panel);
    }

    private void devolverLibro() {
        int idPrestamo = Integer.parseInt(idPrestamoField.getText());

        try {
            bibliotecaDAO.devolverLibro(idPrestamo);
            JOptionPane.showMessageDialog(this, "Libro devuelto exitosamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
