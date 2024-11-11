package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrestamoActivoWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTextField carnetField;

    public PrestamoActivoWindow() {
        this.bibliotecaDAO = new BibliotecaDAO();

        setTitle("Préstamos Activos");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con márgenes
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel("Consulta de Préstamos Activos", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de ingreso del carnet
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JLabel carnetLabel = new JLabel("Carnet:");
        carnetLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        carnetField = new JTextField(10);
        carnetField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnCargarPrestamos = new JButton("Cargar Préstamos");
        btnCargarPrestamos.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCargarPrestamos.setBackground(new Color(0, 123, 255));
        btnCargarPrestamos.setForeground(Color.WHITE);
        btnCargarPrestamos.setFocusPainted(false);

        inputPanel.add(carnetLabel);
        inputPanel.add(carnetField);
        inputPanel.add(new JLabel()); // Espacio vacío
        inputPanel.add(btnCargarPrestamos);

        // Lista de préstamos activos
        JList<String> prestamosList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        prestamosList.setModel(listModel);
        JScrollPane scrollPane = new JScrollPane(prestamosList);
        scrollPane.setPreferredSize(new Dimension(350, 150));

        // Panel inferior con botón de cerrar
        JPanel bottomPanel = new JPanel();
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCerrar.setBackground(new Color(220, 53, 69));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> dispose());
        bottomPanel.add(btnCerrar);

        // Acción para cargar los préstamos activos
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

        // Añadir todo al frame
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrestamoActivoWindow().setVisible(true));
    }
}
