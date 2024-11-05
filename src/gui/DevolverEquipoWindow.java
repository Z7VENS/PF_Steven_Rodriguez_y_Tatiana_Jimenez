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
        setSize(300, 150);
        setLocationRelativeTo(null);

        JLabel idPrestamoLabel = new JLabel("ID Préstamo:");

        idPrestamoField = new JTextField(10);

        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.addActionListener(e -> devolverEquipo());

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(idPrestamoLabel);
        panel.add(idPrestamoField);
        panel.add(new JLabel());
        panel.add(btnDevolver);

        add(panel);
    }

    private void devolverEquipo() {
        int idPrestamo = Integer.parseInt(idPrestamoField.getText());

        try {
            bibliotecaDAO.devolverEquipo(idPrestamo);
            JOptionPane.showMessageDialog(this, "Equipo devuelto exitosamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
