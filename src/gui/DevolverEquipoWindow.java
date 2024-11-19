package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DevolverEquipoWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTextField idPrestamoField;

    @SuppressWarnings("unused")
    public DevolverEquipoWindow(BibliotecaDAO dao) {
        this.bibliotecaDAO = dao;
        setTitle("Devolver Equipo");
        setSize(600, 200);  
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new BackgroundPanel("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\BEFT_01.jpeg");
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Formulario de Devolución de Equipo", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE); 
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        inputPanel.setOpaque(false);  
        JLabel idPrestamoLabel = new JLabel("ID Préstamo:");
        idPrestamoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        idPrestamoLabel.setForeground(Color.WHITE); 
        idPrestamoField = new JTextField();
        idPrestamoField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(idPrestamoLabel);
        inputPanel.add(idPrestamoField);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Crear el botón y agregar un ícono al lado izquierdo del texto
        JButton btnDevolver = new JButton("Devolver Equipo");
        btnDevolver.setFont(new Font("Arial", Font.PLAIN, 14));
        btnDevolver.setBackground(new Color(17, 80, 124)); 
        btnDevolver.setForeground(Color.white); 
        btnDevolver.setFocusPainted(false);
        btnDevolver.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        // Agregar icono al botón
        ImageIcon icon = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image2.png");
        Image scaledIcon = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnDevolver.setIcon(new ImageIcon(scaledIcon));
        btnDevolver.setHorizontalTextPosition(SwingConstants.RIGHT); // Coloca el texto a la derecha del ícono

        btnDevolver.addActionListener(e -> devolverEquipo());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);  
        buttonPanel.add(btnDevolver);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void devolverEquipo() {
        String idPrestamoText = idPrestamoField.getText();

        UIManager.put("OptionPane.background", new Color(18, 43, 116));
        UIManager.put("Panel.background", new Color(18, 43, 116));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(142, 190, 223));
        UIManager.put("Button.foreground", Color.BLACK);

        if (idPrestamoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el ID del préstamo.", "Campo Incompleto", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idPrestamo = Integer.parseInt(idPrestamoText);
            bibliotecaDAO.devolverEquipo(idPrestamo);
            JOptionPane.showMessageDialog(this, "Equipo devuelto exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Préstamo debe ser un número válido.", "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al devolver equipo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("OptionPane.messageForeground", null);
        UIManager.put("Button.background", null);
        UIManager.put("Button.foreground", null);
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            this.backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.setColor(new Color(0, 0, 0, 170));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DevolverEquipoWindow(new BibliotecaDAO()).setVisible(true));
    }
}
