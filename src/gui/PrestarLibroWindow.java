package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PrestarLibroWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTextField carnetField;
    private JTextField idLibroField;
    private JTextField fechaPrestamoField;
    private JTextField fechaDevolucionField;

    public PrestarLibroWindow(BibliotecaDAO dao) {
        this.bibliotecaDAO = dao;
        setTitle("Prestar Libro");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JLabel carnetLabel = new JLabel("Carnet:");
        JLabel idLibroLabel = new JLabel("ID Libro:");
        JLabel fechaPrestamoLabel = new JLabel("Fecha Préstamo:");
        JLabel fechaDevolucionLabel = new JLabel("Fecha Devolución:");

        carnetField = new JTextField(10);
        idLibroField = new JTextField(10);
        fechaPrestamoField = new JTextField(10);
        fechaDevolucionField = new JTextField(10);

        JButton btnPrestar = new JButton("Prestar");
        btnPrestar.addActionListener(e -> prestarLibro());

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(carnetLabel);
        panel.add(carnetField);
        panel.add(idLibroLabel);
        panel.add(idLibroField);
        panel.add(fechaPrestamoLabel);
        panel.add(fechaPrestamoField);
        panel.add(fechaDevolucionLabel);
        panel.add(fechaDevolucionField);
        panel.add(new JLabel());
        panel.add(btnPrestar);

        add(panel);
    }

    private void prestarLibro() {
        String carnet = carnetField.getText();
        int idLibro = Integer.parseInt(idLibroField.getText());
        String fechaPrestamo = fechaPrestamoField.getText();
        String fechaDevolucion = fechaDevolucionField.getText();

        try {
            bibliotecaDAO.prestarLibro(carnet, idLibro, fechaPrestamo, fechaDevolucion);
            JOptionPane.showMessageDialog(this, "Libro prestado exitosamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

