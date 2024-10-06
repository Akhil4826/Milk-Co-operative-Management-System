package MINI_PROJECT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class AboutUsWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private String imagePath = "C:\\Users\\akhil\\Downloads\\2.png";
    private String[] galleryImagePaths = {
            "C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\download.jpeg",
            "C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\industrial-co-operative-society-milk-advertising-lorry-darl-ER5F0D.jpg",
            "C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\topimg_28477_amul.jpg"
    };

    public AboutUsWindow() {
        setTitle("About Us");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        GridBagConstraints constraints = new GridBagConstraints();
        
        
        JLabel backButton = new JLabel(new ImageIcon("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\window-back-button-vector-back-button-icon-transparent-11563239253u1plug6jgv.png")); // Set the back button icon
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new MilkCooperativeHomePage().setVisible(true);
            }
        });
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovering over the button

        // Adjust the size of the button to fit the logo
        ImageIcon icon = new ImageIcon("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\window-back-button-vector-back-button-icon-transparent-11563239253u1plug6jgv.png");
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        backButton.setIcon(new ImageIcon(image));

        // Create a panel to hold the back button
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout to align the button to the left
        backButtonPanel.setOpaque(false); // Make the panel transparent
        backButtonPanel.add(backButton); // Add the back button to the panel

        // Add the back button panel to the main panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(backButtonPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(backButton, constraints);
        JLabel titleText = new JLabel("<html><body style='font-family: Arial, sans-serif; font-size: 32px; font-weight: bold; color: white; background-color: rgba(0, 0, 0, 0.5); padding: 10px;'>"
                + "Dairy farm Milk Co-operative"
                + "</body></html>");
        titleText.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleText, BorderLayout.CENTER);
        titlePanel.setOpaque(false);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titlePanel, constraints);

        JTextArea descriptionText = new JTextArea(
                "Ministry of Cooperation was created by transferring the existing entries related to cooperation and cooperative in the business of the erstwhile Ministry of Agriculture, Cooperation and Farmers Welfare...");
        descriptionText.setEditable(false);
        descriptionText.setForeground(new Color(0, 0, 102, 200));
        descriptionText.setFont(new Font("Arial", Font.BOLD, 18));
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);
        descriptionText.setOpaque(false);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(descriptionText, constraints);

        JTextArea visionText = new JTextArea(
                "The main mandate of the Ministry is realization of vision 'Cooperation to Prosperity', strengthening of cooperative movement in the country...");
        visionText.setEditable(false);
        visionText.setForeground(new Color(0, 0, 102, 200));
        visionText.setFont(new Font("Arial", Font.BOLD, 18));
        visionText.setLineWrap(true);
        visionText.setWrapStyleWord(true);
        visionText.setOpaque(false);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(visionText, constraints);

        JPanel galleryPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        for (String imagePath : galleryImagePaths) {
            ImageIcon galleryIcon = new ImageIcon(imagePath);
            Image galleryImage = galleryIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            JLabel galleryLabel = new JLabel(new ImageIcon(galleryImage));
            galleryPanel.add(galleryLabel);
        }

        JPanel mainPanel1 = new JPanel(new BorderLayout());
        mainPanel1.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel galleryPanel1 = new JPanel(new GridLayout(0, 3, 10, 10));
        for (String imagePath : galleryImagePaths) {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image1 = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image1));
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Implement download functionality for the selected image
                    File imageFile = new File(imagePath);
                    try {
                        Desktop.getDesktop().open(imageFile);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error: Unable to download gallery image.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            galleryPanel1.add(imageLabel);
        }
        mainPanel1.add(galleryPanel1, BorderLayout.CENTER);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(mainPanel1, constraints);

        JTextArea aboutText = new JTextArea(
                "Our company has many happy farmers and their data are displayed. We take pride in supporting our farmers.");
        aboutText.setEditable(false);
        aboutText.setForeground(new Color(0, 0, 102, 200));
        aboutText.setFont(new Font("Arial", Font.BOLD, 18));
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setOpaque(false);

        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(aboutText, constraints);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton farmersButton = new JButton("View Farmers Data");
        farmersButton.setForeground(Color.WHITE);
        farmersButton.setBackground(new Color(59, 89, 182));
        farmersButton.setFocusPainted(false);
        farmersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open FarmerAnimalBarChart data
                FarmerAnimalBarChart.showFarmersData();
            }
        });
        buttonPanel.add(farmersButton);

        JButton employeeButton = new JButton("View Employee Data");
        employeeButton.setForeground(Color.WHITE);
        employeeButton.setBackground(new Color(59, 89, 182));
        employeeButton.setFocusPainted(false);
        employeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open EmployeeVisualization data
                EmployeeVisualization.showEmployeeData();
            }
        });
        buttonPanel.add(employeeButton);

        JButton billButton = new JButton("View Bill & Farmer Data");
        billButton.setForeground(Color.WHITE);
        billButton.setBackground(new Color(59, 89, 182));
        billButton.setFocusPainted(false);
        billButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open BillVisualization data
                BillVisualization.showBillData();
            }
        });
        buttonPanel.add(billButton);

        constraints.gridx = 0;
        constraints.gridy = 12;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(buttonPanel, constraints);

        JButton infoButton = new JButton("Information Document");
        infoButton.setForeground(Color.WHITE);
        infoButton.setBackground(new Color(59, 89, 182));
        infoButton.setFocusPainted(false);
        infoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File("C:\\Users\\akhil\\Downloads\\Your Group.pdf"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 25;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        mainPanel.add(infoButton, constraints);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        setVisible(true);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(51, 51, 51));

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("Montserrat", Font.BOLD, 20));
        menu.setForeground(Color.WHITE);

        JMenuItem consumerInformationItem = new JMenuItem("Reach out to Us");
        consumerInformationItem.setFont(new Font("Montserrat", Font.PLAIN, 16));
        consumerInformationItem.setForeground(Color.WHITE);

        menu.add(consumerInformationItem);

        menuBar.add(menu);
        
        // Create a panel for the login button
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(51, 51, 51));
        loginPanel.setLayout(new BorderLayout());
        loginPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER); // Pushes the button to the right
        loginPanel.add(createLoginButton(), BorderLayout.LINE_END); // Aligns the button to the right

        menuBar.add(loginPanel); // Add the login panel to the menu bar

        setJMenuBar(menuBar);

        consumerInformationItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ContactInformation();
            }
        });
    }
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
                // Open the login page
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose(); // Close the current frame
            }
        });

        // Add button press animation effect
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a timer for animation
                Timer timer = new Timer(100, new ActionListener() {
                    private boolean isYellow = false;

                    public void actionPerformed(ActionEvent e) {
                        // Perform animation by changing button color
                        if (isYellow) {
                            loginButton.setBackground(Color.ORANGE);
                        } else {
                            loginButton.setBackground(Color.YELLOW);
                        }
                        isYellow = !isYellow;
                    }
                });
                timer.setRepeats(true); // Execute animation repeatedly
                timer.setInitialDelay(0); // Start animation immediately
                timer.start(); // Start the timer
            }
        });
        
        return loginButton;
    }

    public static void main(String[] args) {
        new AboutUsWindow();
    }
}
