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
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new BackgroundPanel("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\imagen222.jpg");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        TitleLabel titleLabel = new TitleLabel("Universidad de Costa Rica", 36, Color.white, Color.BLUE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\logoucr.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(Box.createVerticalStrut(20));
        titlePanel.add(logoLabel);
        mainPanel.add(titlePanel);

        mainPanel.add(Box.createVerticalStrut(40));

        JPanel buttonPanelWrapper = new JPanel();
        buttonPanelWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanelWrapper.setOpaque(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton btnPrestarEquipo = createButtonWithIcon("     Prestar Equipo", "C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image3.png");
        JButton btnDevolverEquipo = createButtonWithIcon("   Devolver Equipo", "C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image2.png");
        JButton btnVerPrestamosActivos = createButtonWithIcon("     Ver Préstamos ", "C:\\Users\\Lenovo\\Downloads\\biblioteca-app\\biblioteca-app\\biblioteca-app\\src\\gui\\image4.png");

        btnPrestarEquipo.setToolTipText("Presiona para prestar un equipo.");
        btnDevolverEquipo.setToolTipText("Presiona para devolver un equipo.");
        btnVerPrestamosActivos.setToolTipText("Presiona para ver los préstamos activos.");

        buttonPanel.add(btnPrestarEquipo);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnDevolverEquipo);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(btnVerPrestamosActivos);
        buttonPanel.setPreferredSize(new Dimension(300, 200));
        buttonPanelWrapper.add(buttonPanel);
        mainPanel.add(buttonPanelWrapper);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
        footerPanel.setOpaque(false);

        JLabel footerLabel = new JLabel("Bienvenido al Sistema de Préstamos Electrónicos de la Biblioteca", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 16));
        footerLabel.setForeground(Color.white);
        footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(footerLabel);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(footerPanel);

        add(mainPanel);

        btnPrestarEquipo.addActionListener(e -> abrirVentanaPrestarEquipo());
        btnDevolverEquipo.addActionListener(e -> abrirVentanaDevolverEquipo());
        btnVerPrestamosActivos.addActionListener(e -> abrirVentanaPrestamosActivos());
    }

    private JButton createButtonWithIcon(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(Color.blue);
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50));
        button.setMinimumSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createLineBorder(Color.white, 2));

        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledIcon));
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(10);

        return button;
    }

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

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        Color semiTransparentWhite = new Color(255, 255, 255, 50);
        g2d.setColor(semiTransparentWhite);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

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

        g2d.setFont(getFont());
        g2d.setColor(outlineColor);
        g2d.drawString(getText(), getWidth() / 2 - g2d.getFontMetrics().stringWidth(getText()) / 2, getHeight() / 2 + fontSize / 2 - 2);

        g2d.setColor(textColor);
        g2d.drawString(getText(), getWidth() / 2 - g2d.getFontMetrics().stringWidth(getText()) / 2 + 1, getHeight() / 2 + fontSize / 2 - 3);
    }
}
