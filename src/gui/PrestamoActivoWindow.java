package gui;

import sql.BibliotecaDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrestamoActivoWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;
    private JTextField carnetField;
    private DefaultListModel<String> listModel;

    @SuppressWarnings("unused")
    public PrestamoActivoWindow() {
        this.bibliotecaDAO = new BibliotecaDAO();

        setTitle("Préstamos Activos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

       
        setContentPane(new BackgroundPanel("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\IMG_6533.jpg")); 
        setLayout(new BorderLayout());

        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      
        JLabel titleLabel = new JLabel("Consulta de Préstamos Activos", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de ingreso del carnet
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setOpaque(false);

        JLabel carnetLabel = new JLabel("Carnet:");
        carnetLabel.setFont(new Font("Arial", Font.BOLD, 16));
        carnetLabel.setForeground(Color.WHITE);
        carnetField = new JTextField(15);
        carnetField.setFont(new Font("Arial", Font.PLAIN, 14));

       
        JButton btnCargarPrestamos = createButtonWithIcon("Cargar Préstamos", "C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image6.png");
        btnCargarPrestamos.setBackground(new Color(16, 88, 138 ));
        inputPanel.add(carnetLabel);
        inputPanel.add(carnetField);
        inputPanel.add(new JLabel());
        inputPanel.add(btnCargarPrestamos);

       
        listModel = new DefaultListModel<>();
        JList<String> prestamosList = new JList<>(listModel);
        prestamosList.setFont(new Font("Arial", Font.PLAIN, 14));
        prestamosList.setOpaque(false); 
        prestamosList.setForeground(Color.DARK_GRAY);
        
      
        JPanel transparentPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f)); 
                g2d.setColor(new Color(255, 255, 255, 200)); 
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        transparentPanel.setOpaque(false); 
        transparentPanel.add(new JScrollPane(prestamosList), BorderLayout.CENTER);
        transparentPanel.setPreferredSize(new Dimension(350, 200));

       
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        
     
        JButton btnCerrar = createButtonWithIcon("Cerrar", "C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image5.png");
      btnCerrar.setBackground((Color.red));
        btnCerrar.addActionListener(e -> dispose());
        
        bottomPanel.add(btnCerrar);

        
        btnCargarPrestamos.addActionListener(e -> cargarPrestamos());

      
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(transparentPanel, BorderLayout.CENTER); 
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void cargarPrestamos() {
        String carnet = carnetField.getText().trim();
        if (!carnet.isEmpty()) {
            List<String> prestamosActivos = bibliotecaDAO.obtenerPrestamosActivos(carnet);
            listModel.clear(); 

            if (prestamosActivos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron préstamos activos para el carnet ingresado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (String prestamo : prestamosActivos) {
                    listModel.addElement(prestamo); 
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un carnet válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private JButton createButtonWithIcon(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

       
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        button.setIcon(new ImageIcon(scaledIcon));
        button.setHorizontalTextPosition(SwingConstants.RIGHT); 
        button.setIconTextGap(10); 

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrestamoActivoWindow().setVisible(true));
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
