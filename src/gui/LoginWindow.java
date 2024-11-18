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
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(new BackgroundPanel("C:\\Users\\imzzz\\OneDrive\\Escritorio\\biblioteca-app\\biblioteca-app\\src\\resources\\IMG_6535.jpeg"));
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\imzzz\\OneDrive\\Escritorio\\biblioteca-app\\biblioteca-app\\src\\resources\\Logoucr.jpeg");
        Image scaledImage = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(logoLabel);

        headerPanel.add(Box.createVerticalStrut(10));

        JLabel titleLabel = new JLabel("Acceso al Sistema", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(titleLabel);

        headerPanel.add(Box.createVerticalStrut(20));

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setForeground(Color.WHITE);
        emailField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        inputPanel.add(new JLabel(""), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        inputPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(passwordField, gbc);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setFocusPainted(false);
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(250, 50));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        loginButton.addActionListener(new LoginButtonListener());

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        buttonPanel.add(loginButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText().trim();
            String contraseña = new String(passwordField.getPassword()).trim();

            try {
                if (bibliotecaDAO.validarLoginColaborador(email, contraseña)) {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Login exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    new MainWindow().setVisible(true);
                    dispose();
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

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            this.backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0, 0, 0, 90));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}