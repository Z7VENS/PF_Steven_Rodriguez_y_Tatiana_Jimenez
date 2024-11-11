package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginWindow() {
        bibliotecaDAO = new BibliotecaDAO();
        setTitle("Login de Colaborador");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Acceso al Sistema de Biblioteca", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de campos de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField = new JTextField(15);
        
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField = new JPasswordField(15);

        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Botón de login
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setIcon(new ImageIcon("login_icon.png")); // tati aca agregue un icono para el boton
        loginButton.setFocusPainted(false);
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new LoginButtonListener());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        buttonPanel.add(loginButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText().trim();
            String contraseña = new String(passwordField.getPassword()).trim();
            
            System.out.println("Intentando login con email: " + email + " y contraseña: " + contraseña);
    
            try {
                if (bibliotecaDAO.validarLoginColaborador(email, contraseña)) {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Login exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    new MainWindow().setVisible(true); // Abrir MainWindow si el login es exitoso
                    dispose(); // Cerrar la ventana de login
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(LoginWindow.this, "Error al validar el login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        });
    }
}