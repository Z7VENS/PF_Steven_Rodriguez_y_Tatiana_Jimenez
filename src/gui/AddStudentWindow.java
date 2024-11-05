package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTextField carnetField;
    private JTextField nombreField;
    private JTextField emailField;
    private JTextField telefonoField;

    public AddStudentWindow(BibliotecaDAO bibliotecaDAO) { // Recibe BibliotecaDAO
        this.bibliotecaDAO = bibliotecaDAO;

        setTitle("Agregar Estudiante");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Campos para capturar los datos del estudiante
        add(new JLabel("Carnet:"));
        carnetField = new JTextField();
        add(carnetField);

        add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        add(telefonoField);

        // Botón para agregar el estudiante
        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEstudiante();
            }
        });
        add(addButton);
    }

    private void agregarEstudiante() {
        String carnet = carnetField.getText();
        String nombre = nombreField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();

        try {
            bibliotecaDAO.agregarEstudiante(carnet, nombre, email, telefono);
            JOptionPane.showMessageDialog(this, "Estudiante agregado exitosamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
