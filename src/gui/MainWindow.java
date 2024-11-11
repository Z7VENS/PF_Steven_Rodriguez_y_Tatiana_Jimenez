package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;

    public MainWindow() {
        bibliotecaDAO = new BibliotecaDAO();
        setTitle("Sistema de Gestión de Biblioteca");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel("Universidad de Costa Rica", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        // Tati aca hice la Creación de botones con iconos y estilos, los iconos no estan agregados, se lo dejo a usted
        JButton addStudentButton = createButton("Agregar Estudiante", "add_icon.png");
        JButton btnPrestarEquipo = createButton("Prestar Equipo", "equipment_icon.png");
        JButton btnDevolverEquipo = createButton("Devolver Equipo", "return_equipment_icon.png");

        // Configuración de acciones
        addStudentButton.addActionListener(e -> abrirVentanaAgregarEstudiante());
        btnPrestarEquipo.addActionListener(e -> abrirVentanaPrestarEquipo());
        btnDevolverEquipo.addActionListener(e -> abrirVentanaDevolverEquipo());

        // Añadir botones al panel
        buttonPanel.add(addStudentButton);
        buttonPanel.add(btnPrestarEquipo);
        buttonPanel.add(btnDevolverEquipo);

        // Añadir el panel de botones al panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Pie de página con mensaje de bienvenida
        JLabel footerLabel = new JLabel("Bienvenido al Sistema de Biblioteca", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setIcon(new ImageIcon(iconPath)); // Tati aca se coloca el icono en la misma carpeta o ajusta la ruta
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        return button;
    }

    private void abrirVentanaAgregarEstudiante() {
        new AddStudentWindow(bibliotecaDAO).setVisible(true);
    }

    private void abrirVentanaPrestarEquipo() {
        new PrestarEquipoWindow(bibliotecaDAO).setVisible(true);
    }

    private void abrirVentanaDevolverEquipo() {
        new DevolverEquipoWindow(bibliotecaDAO).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
