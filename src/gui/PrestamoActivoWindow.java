package gui;

import sql.BibliotecaDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrestamoActivoWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private String carnetUsuario;

    public PrestamoActivoWindow(String carnet) {
        this.bibliotecaDAO = new BibliotecaDAO();
        this.carnetUsuario = carnet;

        setTitle("Préstamos Activos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Lista de préstamos activos
        JList<String> prestamosList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Cargar los préstamos activos desde la base de datos
        List<String> prestamosActivos = bibliotecaDAO.obtenerPrestamosActivos(carnetUsuario);
        for (String prestamo : prestamosActivos) {
            listModel.addElement(prestamo); // Agregar cada préstamo a la lista
        }

        prestamosList.setModel(listModel);
        JScrollPane scrollPane = new JScrollPane(prestamosList);

        // Agregar el JScrollPane al panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón de cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        // Panel inferior
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnCerrar);

        // Añadir paneles al frame
        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrestamoActivoWindow("123456789").setVisible(true)); // Ejemplo de carnet
    }
}