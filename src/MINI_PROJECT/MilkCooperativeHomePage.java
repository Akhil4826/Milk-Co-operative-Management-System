package MINI_PROJECT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MilkCooperativeHomePage extends JFrame {
    private static final long serialVersionUID = 1L;

    // Declare titleLabel and descriptionLabel as class fields
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JPanel panel;
    public MilkCooperativeHomePage() {
        setTitle("Milk Cooperative Homepage");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Load background image
                    BufferedImage backgroundImage = ImageIO.read(new File("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Home page.png"));
                    // Draw the image at the specified location and size
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

                    // Calculate the average color of the background
                    Color backgroundColor = calculateAverageColor(backgroundImage);

                    // Determine the best font color that suits the background
                    Color fontColor = getBestFontColor(backgroundColor);
                    titleLabel.setForeground(fontColor);
                    descriptionLabel.setForeground(fontColor);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel("Welcome to Milk Cooperative");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        descriptionLabel = new JLabel("<html>We are dedicated to supporting local dairy farmers<br>and providing quality milk products to our community.</html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(descriptionLabel, gbc);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
        // Add animation - Ripple effect animation
        animateRippleEffect(panel);

        // Add Navigation Menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(51, 51, 51));

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("Montserrat", Font.BOLD, 20));
        menu.setForeground(Color.WHITE);

        JMenuItem aboutUsItem = new JMenuItem("About Us");
        aboutUsItem.setFont(new Font("Montserrat", Font.PLAIN, 16));
        aboutUsItem.setForeground(Color.WHITE);

        JMenuItem consumerInformationItem = new JMenuItem("Reach Us");
        consumerInformationItem.setFont(new Font("Montserrat", Font.PLAIN, 16));
        consumerInformationItem.setForeground(Color.WHITE);

        JMenuItem changeBackgroundItem = new JMenuItem("Change Background Image");
        changeBackgroundItem.setFont(new Font("Montserrat", Font.PLAIN, 16));
        changeBackgroundItem.setForeground(Color.WHITE);

        JMenuItem changeThemeItem = new JMenuItem("Change Theme");
        changeThemeItem.setFont(new Font("Montserrat", Font.PLAIN, 16));
        changeThemeItem.setForeground(Color.WHITE);

        menu.add(aboutUsItem);
        menu.add(consumerInformationItem);
        menu.add(changeBackgroundItem);
        menu.add(changeThemeItem);

        menuBar.add(menu);

        // Create a panel for the login button
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(51, 51, 51));
        loginPanel.setLayout(new BorderLayout());
        loginPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER); // Pushes the button to the right
        loginPanel.add(createLoginButton(), BorderLayout.LINE_END); // Aligns the button to the right

        menuBar.add(loginPanel); // Add the login panel to the menu bar

        setJMenuBar(menuBar);

        aboutUsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for About Us
                // Create and display the About Us window
                AboutUsWindow aboutUsWindow = new AboutUsWindow();
                aboutUsWindow.setVisible(true);
                setVisible(false); // Hide the current frame
            }
        });

        consumerInformationItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ContactInformation();
            }
        });

        changeBackgroundItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeBackgroundImage();
            }
        });

        changeThemeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeTheme();
            }
        });
    }

    private void animateRippleEffect(JPanel panel2) {
		// TODO Auto-generated method stub
		
	}

	private Color calculateAverageColor(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        long sumRed = 0;
        long sumGreen = 0;
        long sumBlue = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                sumRed += pixelColor.getRed();
                sumGreen += pixelColor.getGreen();
                sumBlue += pixelColor.getBlue();
            }
        }

        int totalPixels = width * height;
        int avgRed = (int) (sumRed / totalPixels);
        int avgGreen = (int) (sumGreen / totalPixels);
        int avgBlue = (int) (sumBlue / totalPixels);

        return new Color(avgRed, avgGreen, avgBlue);
    }

    private Color getBestFontColor(Color backgroundColor) {
        // Determine the best font color based on the background color
        // Example implementation: if background color is dark, use white font color; if light, use black font color
        // Here's a simplified approach:
        int threshold = 127;
        int brightness = (int) (backgroundColor.getRed() * 0.299 + backgroundColor.getGreen() * 0.587 + backgroundColor.getBlue() * 0.114);
        return brightness > threshold ? Color.BLACK : Color.WHITE;
    }

    // Method to create the login button
    private JButton createLoginButton() {
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBackground(Color.ORANGE);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);

        // Add hover effect to the login button
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(Color.YELLOW);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(Color.ORANGE);
            }
        });

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the login page in the same window
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose(); // Close the current frame
            }
        });

        return loginButton;
    }

    private void changeBackgroundImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Choose Background Image");
        
        // Add file filter for image files
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif", "bmp"));

        // Add action listener for custom button in file chooser dialog
        JButton selectFromCollectionButton = new JButton("Select from Collection");
        fileChooser.setAccessory(selectFromCollectionButton);
        selectFromCollectionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open a dialog to choose from a collection of images
                // Implement your logic here to allow the user to select from a collection of images
            }
        });

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage backgroundImage = ImageIO.read(selectedFile);
                Graphics g = panel.getGraphics();
                g.drawImage(backgroundImage, 0, 0, panel.getWidth(), panel.getHeight(), null);
                g.dispose();

                // Recalculate font color based on the new background image
                Color backgroundColor = calculateAverageColor(backgroundImage);
                Color fontColor = getBestFontColor(backgroundColor);
                titleLabel.setForeground(fontColor);
                descriptionLabel.setForeground(fontColor);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void changeTheme() {
        // Implement theme change functionality here
        // You can toggle between dark and light themes, and adjust colors accordingly
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MilkCooperativeHomePage::new);
    }
}
