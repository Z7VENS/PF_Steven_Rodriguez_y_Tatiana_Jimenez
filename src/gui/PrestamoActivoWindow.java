package gui;

import sql.BibliotecaDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrestamoActivoWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;

    public PrestamoActivoWindow() {
        this.bibliotecaDAO = new BibliotecaDAO();

        setTitle("Préstamos Activos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panel para ingresar el carnet
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel carnetLabel = new JLabel("Ingrese su carnet:");
        JTextField carnetField = new JTextField(10); // Campo para ingresar el carnet
        JButton btnCargarPrestamos = new JButton("Cargar Préstamos");

        inputPanel.add(carnetLabel);
        inputPanel.add(carnetField);
        inputPanel.add(btnCargarPrestamos);

        // Lista de préstamos activos
        JList<String> prestamosList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        prestamosList.setModel(listModel);
        JScrollPane scrollPane = new JScrollPane(prestamosList);

        // Agregar los componentes al panel principal
        panel.add(inputPanel, BorderLayout.NORTH);
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

        // Acción para cargar los préstamos activos al hacer clic en el botón
        btnCargarPrestamos.addActionListener(e -> {
            String carnet = carnetField.getText().trim();
            if (!carnet.isEmpty()) {
                // Cargar los préstamos activos desde la base de datos
                List<String> prestamosActivos = bibliotecaDAO.obtenerPrestamosActivos(carnet);
                listModel.clear(); // Limpiar la lista antes de agregar los nuevos préstamos

                for (String prestamo : prestamosActivos) {
                    listModel.addElement(prestamo); // Agregar cada préstamo a la lista
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un carnet válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrestamoActivoWindow().setVisible(true));
    }
}