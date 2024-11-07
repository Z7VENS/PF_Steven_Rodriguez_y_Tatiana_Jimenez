package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DevolverEquipoWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTextField idPrestamoField;

    public DevolverEquipoWindow(BibliotecaDAO dao) {
        this.bibliotecaDAO = dao;
        setTitle("Devolver Equipo");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con márgenes
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel("Formulario de Devolución de Equipo", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JLabel idPrestamoLabel = new JLabel("ID Préstamo:");
        idPrestamoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        idPrestamoField = new JTextField();
        idPrestamoField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(idPrestamoLabel);
        inputPanel.add(idPrestamoField);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Botón de devolver con estilo
        JButton btnDevolver = new JButton("Devolver Equipo");
        btnDevolver.setFont(new Font("Arial", Font.PLAIN, 14));
        btnDevolver.setBackground(new Color(220, 53, 69));
        btnDevolver.setForeground(Color.WHITE);
        btnDevolver.setFocusPainted(false);
        btnDevolver.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        btnDevolver.addActionListener(e -> devolverEquipo());

        // Panel inferior para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnDevolver);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void devolverEquipo() {
        String idPrestamoText = idPrestamoField.getText();

        // Validación del campo
        if (idPrestamoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el ID del préstamo.", "Campo Incompleto", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idPrestamo = Integer.parseInt(idPrestamoText);
            bibliotecaDAO.devolverEquipo(idPrestamo);
            JOptionPane.showMessageDialog(this, "Equipo devuelto exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Préstamo debe ser un número válido.", "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al devolver equipo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
