package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;

    public MainWindow() {
        bibliotecaDAO = new BibliotecaDAO();
        setTitle("Sistema de Biblioteca");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addStudentButton = new JButton("Agregar Estudiante");
        JButton btnPrestarLibro = new JButton("Prestar Libro");
        JButton btnDevolverLibro = new JButton("Devolver Libro");
        JButton btnPrestarEquipo = new JButton("Prestar Equipo");
        JButton btnDevolverEquipo = new JButton("Devolver Equipo");

        addStudentButton.addActionListener(e -> abrirVentanaAgregarEstudiante());
        btnPrestarLibro.addActionListener(e -> abrirVentanaPrestarLibro());
        btnDevolverLibro.addActionListener(e -> abrirVentanaDevolverLibro());
        btnPrestarEquipo.addActionListener(e -> abrirVentanaPrestarEquipo());
        btnDevolverEquipo.addActionListener(e -> abrirVentanaDevolverEquipo());

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.add(addStudentButton);     
        panel.add(btnPrestarLibro);
        panel.add(btnDevolverLibro);
        panel.add(btnPrestarEquipo);
        panel.add(btnDevolverEquipo);

        add(panel);
    }

    private void abrirVentanaAgregarEstudiante() {
        new AddStudentWindow(bibliotecaDAO).setVisible(true);
    }

    private void abrirVentanaPrestarLibro() {
        new PrestarLibroWindow(bibliotecaDAO).setVisible(true);
    }

    private void abrirVentanaDevolverLibro() {
        new DevolverLibroWindow(bibliotecaDAO).setVisible(true);
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
