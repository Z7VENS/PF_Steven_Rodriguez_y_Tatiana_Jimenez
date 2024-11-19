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
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(new BackgroundPanel("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\IMG_6535.jpg"));
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        // Logo principal
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\logoucr1.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(logoLabel);

        headerPanel.add(Box.createVerticalStrut(10));

        // Título
        JLabel titleLabel = new JLabel("Acceso al Sistema", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(titleLabel);

        headerPanel.add(Box.createVerticalStrut(10));

        // Ícono debajo del título (ajustado a un tamaño mediano)
        ImageIcon belowIcon = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image1.png");
        Image scaledBelowIcon = belowIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Tamaño mediano
        belowIcon = new ImageIcon(scaledBelowIcon);
        JLabel belowIconLabel = new JLabel(belowIcon);
        belowIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(belowIconLabel);

        headerPanel.add(Box.createVerticalStrut(20));

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        emailLabel.setForeground(Color.WHITE);
        
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, 40));  // Aumento del tamaño del campo de texto
        emailField.setFont(new Font("Arial", Font.PLAIN, 18)); // Aumento del tamaño de la fuente

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE);
        
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 40));  // Aumento del tamaño del campo de texto
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18)); // Aumento del tamaño de la fuente

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(passwordField, gbc);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Panel de botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton loginButton = new JButton("Iniciar Sesión");

        // Ícono del botón
        ImageIcon loginIcon = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image.png");
        Image scaledLoginIcon = loginIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        loginIcon = new ImageIcon(scaledLoginIcon);
        loginButton.setIcon(loginIcon);

        loginButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setFocusPainted(false);
        loginButton.setBackground(new Color(16, 88, 138));
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
