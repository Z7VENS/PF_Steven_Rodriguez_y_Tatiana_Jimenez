package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrestamoActivoWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTable prestamosTable;
    private JScrollPane scrollPane;

    public PrestamoActivoWindow(String carnet) {
        bibliotecaDAO = new BibliotecaDAO();
        setTitle("Préstamos Activos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Préstamos Activos", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Tabla de préstamos
        prestamosTable = new JTable();
        loadPrestamosActivos(carnet);

        // Scroll para la tabla
        scrollPane = new JScrollPane(prestamosTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Agregar el panel principal a la ventana
        add(mainPanel);
    }

    // Método para cargar los préstamos activos
    private void loadPrestamosActivos(String carnet) {
        try {
            ResultSet resultSet = bibliotecaDAO.obtenerPrestamosActivos(carnet);

            // Crear el modelo de la tabla con las columnas
            String[] columnas = {"ID Préstamo", "ID Libro", "ID Equipo", "Fecha Préstamo", "Fecha Devolución"};
            Object[][] data = new Object[0][5];

            // Contar las filas
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }

            // Crear el arreglo de datos
            data = new Object[rowCount][5];
            resultSet.beforeFirst();
            int rowIndex = 0;
            while (resultSet.next()) {
                data[rowIndex][0] = resultSet.getInt("id_prestamo");
                data[rowIndex][1] = resultSet.getInt("id_libro");
                data[rowIndex][2] = resultSet.getInt("id_equipo");
                data[rowIndex][3] = resultSet.getDate("fecha_prestamo");
                data[rowIndex][4] = resultSet.getDate("fecha_devolucion");
                rowIndex++;
            }

            // Crear el modelo de la tabla
            prestamosTable.setModel(new javax.swing.table.DefaultTableModel(data, columnas));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los préstamos activos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String carnet = "123456789"; // Cambiar por el carnet del usuario actual
            PrestamoActivoWindow window = new PrestamoActivoWindow(carnet);
            window.setVisible(true);
        });
    }
}