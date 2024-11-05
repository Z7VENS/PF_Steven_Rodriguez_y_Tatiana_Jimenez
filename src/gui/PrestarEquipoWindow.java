package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PrestarEquipoWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTextField carnetField;
    private JTextField idEquipoField;
    private JTextField fechaPrestamoField;
    private JTextField fechaDevolucionField;

    public PrestarEquipoWindow(BibliotecaDAO dao) {
        this.bibliotecaDAO = dao;
        setTitle("Prestar Equipo");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JLabel carnetLabel = new JLabel("Carnet:");
        JLabel idEquipoLabel = new JLabel("ID Equipo:");
        JLabel fechaPrestamoLabel = new JLabel("Fecha Préstamo:");
        JLabel fechaDevolucionLabel = new JLabel("Fecha Devolución:");

        carnetField = new JTextField(10);
        idEquipoField = new JTextField(10);
        fechaPrestamoField = new JTextField(10);
        fechaDevolucionField = new JTextField(10);

        JButton btnPrestar = new JButton("Prestar");
        btnPrestar.addActionListener(e -> prestarEquipo());

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(carnetLabel);
        panel.add(carnetField);
        panel.add(idEquipoLabel);
        panel.add(idEquipoField);
        panel.add(fechaPrestamoLabel);
        panel.add(fechaPrestamoField);
        panel.add(fechaDevolucionLabel);
        panel.add(fechaDevolucionField);
        panel.add(new JLabel());
        panel.add(btnPrestar);

        add(panel);
    }

    private void prestarEquipo() {
        String carnet = carnetField.getText();
        int idEquipo = Integer.parseInt(idEquipoField.getText());
        String fechaPrestamo = fechaPrestamoField.getText();
        String fechaDevolucion = fechaDevolucionField.getText();

        try {
            bibliotecaDAO.prestarEquipo(carnet, idEquipo, fechaPrestamo, fechaDevolucion);
            JOptionPane.showMessageDialog(this, "Equipo prestado exitosamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
