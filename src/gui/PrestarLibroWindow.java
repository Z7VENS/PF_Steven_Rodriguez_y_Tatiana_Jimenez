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
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con márgenes
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel("Formulario de Préstamo de Libro", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        carnetField = createInputField("Carnet:", inputPanel);
        idLibroField = createInputField("ID Libro:", inputPanel);
        fechaPrestamoField = createInputField("Fecha de Préstamo:", inputPanel);
        fechaDevolucionField = createInputField("Fecha de Devolución:", inputPanel);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Botón de prestar con estilo
        JButton btnPrestar = new JButton("Prestar Libro");
        btnPrestar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnPrestar.setBackground(new Color(0, 123, 255));
        btnPrestar.setForeground(Color.WHITE);
        btnPrestar.setFocusPainted(false);
        btnPrestar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        btnPrestar.addActionListener(e -> prestarLibro());

        // Panel inferior para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnPrestar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JTextField createInputField(String labelText, JPanel parentPanel) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        parentPanel.add(label);
        parentPanel.add(textField);
        return textField;
    }

    private void prestarLibro() {
        String carnet = carnetField.getText();
        String idLibroText = idLibroField.getText();
        String fechaPrestamo = fechaPrestamoField.getText();
        String fechaDevolucion = fechaDevolucionField.getText();

        // Validación de campos
        if (carnet.isEmpty() || idLibroText.isEmpty() || fechaPrestamo.isEmpty() || fechaDevolucion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idLibro = Integer.parseInt(idLibroText);
            bibliotecaDAO.prestarLibro(carnet, idLibro, fechaPrestamo, fechaDevolucion);
            JOptionPane.showMessageDialog(this, "Libro prestado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de Libro debe ser un número.", "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al prestar libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
