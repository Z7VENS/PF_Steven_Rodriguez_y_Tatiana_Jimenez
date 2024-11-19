package gui;

import sql.BibliotecaDAO;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private BibliotecaDAO bibliotecaDAO;

    @SuppressWarnings("unused")
    public MainWindow() {
        bibliotecaDAO = new BibliotecaDAO();
        setTitle("Sistema de Gestión de Biblioteca");
        setSize(600, 500); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Habilitar tooltips globalmente
        ToolTipManager.sharedInstance().setEnabled(true);

        // Panel principal con imagen de fondo
        JPanel mainPanel = new BackgroundPanel("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\imagen222.jpg");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Alineación vertical para todo el panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40)); // Reducir el margen superior

        // Panel para título y logo
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false); // Hacer transparente para ver la imagen de fondo

        // Título
        TitleLabel titleLabel = new TitleLabel("Universidad de Costa Rica", 36, Color.white, Color.BLUE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);

        // Logo debajo del título
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\logoucr.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(Box.createVerticalStrut(20)); // Espacio entre título y logo
        titlePanel.add(logoLabel);

        // Añadir el panel de título y logo al panel principal
        mainPanel.add(titlePanel);

        // Espacio adicional antes de los botones para moverlos hacia abajo
        mainPanel.add(Box.createVerticalStrut(40));  // Espacio entre título/logo y botones

        // Panel central con los botones
        JPanel buttonPanelWrapper = new JPanel(); // Nuevo contenedor para centrar los botones
        buttonPanelWrapper.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centrado de botones
        buttonPanelWrapper.setOpaque(false); // Hacerlo transparente

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Establecemos BoxLayout en vertical
        buttonPanel.setOpaque(false); // Hacerlo transparente

        // Crear botones con íconos
        JButton btnPrestarEquipo = createButtonWithIcon("     Prestar Equipo", "C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image3.png");
        JButton btnDevolverEquipo = createButtonWithIcon("   Devolver Equipo", "C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image2.png");
        JButton btnVerPrestamosActivos = createButtonWithIcon("     Ver Préstamos ", "C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image4.png");

        // Añadir tooltips para los botones
        btnPrestarEquipo.setToolTipText("Presiona para prestar un equipo.");
        btnDevolverEquipo.setToolTipText("Presiona para devolver un equipo.");
        btnVerPrestamosActivos.setToolTipText("Presiona para ver los préstamos activos.");

        // Agregar botones al panel
        buttonPanel.add(btnPrestarEquipo);
        buttonPanel.add(Box.createVerticalStrut(10)); // Espacio entre botones
        buttonPanel.add(btnDevolverEquipo);
        buttonPanel.add(Box.createVerticalStrut(10)); // Espacio entre botones
        buttonPanel.add(btnVerPrestamosActivos);

        // Establecemos un tamaño preferido para el panel de botones
        buttonPanel.setPreferredSize(new Dimension(300, 200)); // Ajustar el tamaño del panel para los botones

        // Añadir el panel de botones al panel envolvente
        buttonPanelWrapper.add(buttonPanel);

        // Añadir el panel de botones al panel principal
        mainPanel.add(buttonPanelWrapper);

        // Panel para el pie de página con el texto "Bienvenido al Sistema de Préstamos..."
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
        footerPanel.setOpaque(false); // Hacerlo transparente para que el fondo se vea

        // Pie de página con mensaje de bienvenida
        JLabel footerLabel = new JLabel("Bienvenido al Sistema de Préstamos Electrónicos de la Biblioteca", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 16));  // Fuente en cursiva
        footerLabel.setForeground(Color.white); // Color del texto
        footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(footerLabel);

        // Añadir el panel de pie de página al panel principal
        mainPanel.add(Box.createVerticalStrut(20)); // Espacio entre los botones y el pie de página
        mainPanel.add(footerPanel);

        add(mainPanel);

        // Acciones de los botones
        btnPrestarEquipo.addActionListener(e -> abrirVentanaPrestarEquipo());
        btnDevolverEquipo.addActionListener(e -> abrirVentanaDevolverEquipo());
        btnVerPrestamosActivos.addActionListener(e -> abrirVentanaPrestamosActivos());
    }

    // Método para crear botones con texto e ícono
    private JButton createButtonWithIcon(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14)); // Fuente más grande para texto
        button.setBackground(Color.blue); // Fondo azul
        button.setForeground(Color.white); // Letras blancas
        button.setFocusPainted(false); // Desactivar foco visual
        button.setPreferredSize(new Dimension(200, 50)); // Tamaño uniforme para todos los botones
        button.setMinimumSize(new Dimension(200, 50));  // Tamaño mínimo igual
        button.setMaximumSize(new Dimension(200, 50)); // Tamaño máximo igual
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Aseguramos que el texto esté centrado
        button.setBorder(BorderFactory.createLineBorder(Color.white, 2)); // Borde celeste

        // Configurar el ícono
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledIcon));
        button.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto a la derecha del ícono
        button.setIconTextGap(10); // Espacio entre ícono y texto

        return button;
    }

    // Métodos para abrir ventanas específicas
    private void abrirVentanaPrestarEquipo() {
        new PrestarEquipoWindow(bibliotecaDAO).setVisible(true);
    }

    private void abrirVentanaDevolverEquipo() {
        new DevolverEquipoWindow(bibliotecaDAO).setVisible(true);
    }

    private void abrirVentanaPrestamosActivos() {
        new PrestamoActivoWindow().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}

// Clase personalizada para mostrar una imagen de fondo con transparencia
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        // Carga la imagen de fondo
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dibuja la imagen en el fondo, ajustada al tamaño del panel
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Dibuja un filtro blanco con 50% de transparencia (alpha = 128)
        Color semiTransparentWhite = new Color(255, 255, 255, 50); // Blanco con 50% de opacidad
        g2d.setColor(semiTransparentWhite);
        g2d.fillRect(0, 0, getWidth(), getHeight()); // Aplica el filtro sobre la imagen
    }
}

// Clase personalizada para un título con borde de color
class TitleLabel extends JLabel {
    private final Color outlineColor;
    private final Color textColor;
    private final int fontSize;

    public TitleLabel(String text, int fontSize, Color outlineColor, Color textColor) {
        super(text, JLabel.CENTER);
        this.fontSize = fontSize;
        this.outlineColor = outlineColor;
        this.textColor = textColor;
        setFont(new Font("Arial", Font.BOLD, fontSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibuja el borde (outline) en celeste
        g2d.setFont(getFont());
        g2d.setColor(outlineColor);
        g2d.drawString(getText(), getWidth() / 2 - g2d.getFontMetrics().stringWidth(getText()) / 2, getHeight() / 2 + fontSize / 2 - 2);

        // Dibuja el texto principal en blanco sobre el borde
        g2d.setColor(textColor);
        g2d.drawString(getText(), getWidth() / 2 - g2d.getFontMetrics().stringWidth(getText()) / 2 + 1, getHeight() / 2 + fontSize / 2 - 3);
    }
}
