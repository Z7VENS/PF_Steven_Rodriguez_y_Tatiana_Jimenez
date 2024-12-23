package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PrestarEquipoWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTextField carnetField;
    private JTextField idEquipoField;
    private JTextField fechaPrestamoField;
    private JTextField fechaDevolucionField;

    public PrestarEquipoWindow(BibliotecaDAO dao) {
        this.bibliotecaDAO = dao;
        setTitle("Prestar Equipo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

       
        JPanel mainPanel = new BackgroundPanel("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\imagen5.jpg");
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

       
        JLabel titleLabel = new JLabel("Formulario de Préstamo de Equipo", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

      
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setOpaque(false);

        carnetField = createInputField("Carnet:", inputPanel);
        idEquipoField = createInputField("ID Equipo:", inputPanel);
        fechaPrestamoField = createInputField("Fecha de Préstamo:", inputPanel);
        fechaDevolucionField = createInputField("Fecha de Devolución:", inputPanel);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

       
        JButton btnPrestar = new JButton("Prestar Equipo");
        btnPrestar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnPrestar.setBackground(new Color(16, 138, 53));
        btnPrestar.setForeground(Color.WHITE);
        btnPrestar.setFocusPainted(false);
        btnPrestar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

       
        ImageIcon icon = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image7.png"); // Cambia la ruta según tu archivo de ícono
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        btnPrestar.setIcon(new ImageIcon(img));

        btnPrestar.addActionListener(e -> prestarEquipo());

    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnPrestar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JTextField createInputField(String labelText, JPanel parentPanel) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        parentPanel.add(label);
        parentPanel.add(textField);
        return textField;
    }

    private void prestarEquipo() {
        String carnet = carnetField.getText();
        String idEquipoText = idEquipoField.getText();
        String fechaPrestamo = fechaPrestamoField.getText();
        String fechaDevolucion = fechaDevolucionField.getText();

        // Validación de campos
        if (carnet.isEmpty() || idEquipoText.isEmpty() || fechaPrestamo.isEmpty() || fechaDevolucion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idEquipo = Integer.parseInt(idEquipoText);
            bibliotecaDAO.prestarEquipo(carnet, idEquipo, fechaPrestamo, fechaDevolucion);
            JOptionPane.showMessageDialog(this, "Equipo prestado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de Equipo debe ser un número.", "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al prestar equipo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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

            
            Color overlayColor = new Color(0, 0, 0, 150); 
            g2d.setColor(overlayColor);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrestarEquipoWindow(new BibliotecaDAO()).setVisible(true));
    }
}
