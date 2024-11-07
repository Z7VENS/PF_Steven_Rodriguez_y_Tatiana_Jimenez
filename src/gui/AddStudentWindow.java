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

    public AddStudentWindow(BibliotecaDAO bibliotecaDAO) {
        this.bibliotecaDAO = bibliotecaDAO;

        setTitle("Agregar Nuevo Estudiante");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con márgenes
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título de la ventana
        JLabel titleLabel = new JLabel("Agregar Estudiante", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de entrada de datos con GridLayout
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        carnetField = createInputField("Carnet:", inputPanel);
        nombreField = createInputField("Nombre:", inputPanel);
        emailField = createInputField("Email:", inputPanel);
        telefonoField = createInputField("Teléfono:", inputPanel);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Botón de agregar con estilo
        JButton addButton = new JButton("Agregar Estudiante");
        addButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addButton.setBackground(new Color(0, 123, 255));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEstudiante();
            }
        });

        // Panel inferior para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JTextField createInputField(String labelText, JPanel parentPanel) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        parentPanel.add(label);
        parentPanel.add(textField);
        return textField;
    }

    private void agregarEstudiante() {
        String carnet = carnetField.getText();
        String nombre = nombreField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();

        if (carnet.isEmpty() || nombre.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            bibliotecaDAO.agregarEstudiante(carnet, nombre, email, telefono);
            JOptionPane.showMessageDialog(this, "Estudiante agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
