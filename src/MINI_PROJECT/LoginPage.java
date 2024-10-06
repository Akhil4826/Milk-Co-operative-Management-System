package MINI_PROJECT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginPage extends JFrame implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    JLabel userLabel, passwordLabel, message;
    JTextField userTextField;
    JPasswordField passwordField;
    JButton loginButton;
    Connection connection;
    private JCheckBox showPasswordCheckBox;
    private AnimatedEyes animatedEyes;

    class AnimatedEyes extends JPanel {
        private static final long serialVersionUID = 1L;
        private boolean hideEyes = false;

        public void setHideEyes(boolean hideEyes) {
            this.hideEyes = hideEyes;
            repaint(); // Update the panel to reflect the changes
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            // Draw the eyes
            int eyeRadius = 10;
            int x1 = 300, x2 = 400; // Adjusted X-coordinates of the eyes
            int y = 50; // Y-coordinate of the eyes
            if (!hideEyes) {
                g2d.fillOval(x1 - eyeRadius, y - eyeRadius, 2 * eyeRadius, 2 * eyeRadius);
                g2d.fillOval(x2 - eyeRadius, y - eyeRadius, 2 * eyeRadius, 2 * eyeRadius);
            } else {
                // Draw closed eyes (lines)
                g2d.drawLine(x1 - eyeRadius, y, x1 + eyeRadius, y);
                g2d.drawLine(x2 - eyeRadius, y, x2 + eyeRadius, y);
            }
        } 
    }

    public LoginPage() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
            Image backgroundImage = ImageIO.read(new File("///C:/Users/akhil/eclipse-workspace/MINI_PROJECT/src/MINI_PROJECT/1.png"));
            setContentPane(new JPanel() {
                private static final long serialVersionUID = 1L;
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            });
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database. Please check your connection and try again.", "Database Connection Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
        
        setTitle("Login Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font labelFont = new Font("Arial", Font.BOLD, 28);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 26);
        @SuppressWarnings("unused")
		Font buttonFont = new Font("Arial", Font.BOLD, 26);
        Font messageFont = new Font("Arial", Font.ITALIC, 20);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8, 8);

        userLabel = new JLabel("Username:");
        userLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userLabel, gbc);

        userTextField = new JTextField(15);
        userTextField.setFont(textFieldFont);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(userTextField, gbc);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setFont(textFieldFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);
        showPasswordCheckBox = new JCheckBox("Show password");
        showPasswordCheckBox.setFont(labelFont);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(showPasswordCheckBox, gbc);

        showPasswordCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 32)); // Increase font size to 32
        loginButton.setBackground(new Color(34, 139, 34)); // Green color
        loginButton.setForeground(Color.WHITE); // White text color
        loginButton.setBorder(BorderFactory.createEmptyBorder(2, 14, 2, 14)); // Add padding to the button
        loginButton.setOpaque(true); // Make the button opaque
        loginButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(loginButton, gbc);
        message = new JLabel();
        message.setFont(messageFont);
        message.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        add(message, gbc);

        setupMenuBar();
        
        // Add key listener to the password field
        passwordField.addKeyListener(this);
        
        animatedEyes = new AnimatedEyes();
        gbc.gridx = 2; // Adjust the grid position
        gbc.gridy = 0; // Adjust the grid position
        add(animatedEyes, gbc);
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(51, 51, 51));

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("Montserrat", Font.BOLD, 20));
        menu.setForeground(Color.WHITE);

        JMenuItem aboutUsItem = new JMenuItem("About Us");
        aboutUsItem.setFont(new Font("Montserrat", Font.PLAIN, 16));
        aboutUsItem.setForeground(Color.WHITE);

        JMenuItem consumerInformationItem = new JMenuItem("Reach Out to Us");
        consumerInformationItem.setFont(new Font("Montserrat", Font.PLAIN, 16));
        consumerInformationItem.setForeground(Color.WHITE);

        JMenuItem homePageItem = new JMenuItem("Home Page");
        homePageItem.setFont(new Font("Montserrat", Font.PLAIN, 16));
        homePageItem.setForeground(Color.WHITE);

        JMenuItem additionalPanelItem = new JMenuItem("Additional Panel");
        additionalPanelItem.setFont(new Font("Montserrat", Font.PLAIN, 16));
        additionalPanelItem.setForeground(Color.WHITE);

        menu.add(consumerInformationItem);
        menu.add(homePageItem);
        menu.add(aboutUsItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        aboutUsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AboutUsWindow aboutUsWindow = new AboutUsWindow();
                aboutUsWindow.setVisible(true);
            }
        });

        consumerInformationItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ContactInformation();
            }
        });

        homePageItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MilkCooperativeHomePage().setVisible(true);
            }
        });

        additionalPanelItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to handle the additional panel item
                // Insert your code here to handle the action of adding an additional panel
            }
        });
    }
    
    // ActionListener implementation
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            login();
        }
    }
    
    // KeyListener implementation
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            login();	
        }
    }
    
    // KeyListener implementation (unused)
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == userTextField) {
            animatedEyes.setHideEyes(false); // Show eyes when typing the username
        } else if (e.getSource() == passwordField) {
            animatedEyes.setHideEyes(true); // Hide eyes when typing the password
        }
    }
    public void keyTyped(KeyEvent e) {}

    private void login() {
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM employee WHERE eid=? AND phno=?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                message.setText("Login successful as Employee!");
                int employeeid = rs.getInt("eid");
                EmployeeDashboard employeeDashboard = new EmployeeDashboard(employeeid);
                employeeDashboard.setVisible(true);
                dispose();
            } else {
                pst = connection.prepareStatement("SELECT * FROM farmer WHERE id=? AND ph=?");
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    message.setText("Login successful as Farmer!");
                    int farmerId = rs.getInt("id");
                    FarmerDashboard farmerDashboard = new FarmerDashboard(farmerId);
                    farmerDashboard.setVisible(true);
                    dispose();
                } else {
                    //message.setText("Invalid username or password!!!");
                    // Suggesting the user to check their username or password
                    JOptionPane.showMessageDialog(this, "Invalid username or password. Please check your credentials and try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to execute database query. Please try again.", "Database Query Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
