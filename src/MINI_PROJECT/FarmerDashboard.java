	package MINI_PROJECT;
	
	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	public class FarmerDashboard extends JFrame {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int loggedInFarmerId; // Assuming the logged-in farmer's ID is 1 for demonstration
	    private int loggedInFarmerID; // Assuming the logged-in farmer's ID is 1 for demonstration
	    private String loggedInFarmerIdd; // Assuming the logged-in farmer's ID is 1 for demonstration
	    public FarmerDashboard(int farmerId) {
	        this.loggedInFarmerId = farmerId;
	        this.loggedInFarmerID = farmerId;
	     this.loggedInFarmerIdd = String.valueOf(farmerId);

	        setTitle("Farmer Dashboard");
	        setSize(600, 400);
	        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setLocationRelativeTo(null);
	
	        
	        addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                int option = JOptionPane.showConfirmDialog(null,
	                        "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
	                if (option == JOptionPane.YES_OPTION) {
	                    // Add logout functionality here
	                	closeAllWindows();
	                    new LoginPage().setVisible(true); // Open the login page
	                    dispose(); // Close the dashboard window
	                } else {
		                new FarmerDashboard(farmerId).setVisible(true);
  // If "No" is pressed, do nothing and let the Farmer Dashboard stay open
	                }
	            }
	        });

	        JPanel backgroundPanel = new JPanel() {
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                ImageIcon imageIcon = new ImageIcon("///C:/Users/akhil/eclipse-workspace/MINI_PROJECT/farmer dashboardd.png");
	                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
	            }
	        };
	        backgroundPanel.setLayout(null);
	        
	
	       /* JLabel welcomeLabel = new JLabel("Welcome to the Farmer Dashboard !! "+ farmerName.toUpperCase() + "!!");
	        welcomeLabel.setFont(new Font("Garamond", Font.BOLD, 30)); // Change font to Playfair Display
	
	        welcomeLabel.setBounds(250, 10, 5000, 100);
	        welcomeLabel.setForeground(Color.BLUE);
	        backgroundPanel.add(welcomeLabel);
	*/
	      
	        // Create square buttons and enlarge them
	        int buttonSize = 220;
	        int buttonGap = 20;
	        // Logout button
	        JButton logoutButton = new JButton("Logout");
	        logoutButton.setForeground(Color.RED);
	        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
	        logoutButton.setBackground(Color.WHITE);
	        logoutButton.setBounds(1450, 10, 80, 30);
	        logoutButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Add logout functionality here
	                // For example, navigate back to the login page
	            	closeAllWindows();
	                new LoginPage().setVisible(true); // Open the login page
	                dispose(); // Close the dashboard window
	
	            }
	        });
	        backgroundPanel.add(logoutButton);
	        JButton farmerButton = createButton("View Farmer Data", "C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\farmer logo.png", 640, -100 + 2 * (buttonSize + buttonGap), buttonSize, buttonSize);
	        farmerButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	displayFarmerData displayFarmerData=new displayFarmerData(loggedInFarmerId);
	            	displayFarmerData.setVisible(true);
	            }
	        });
	        backgroundPanel.add(farmerButton);
	
	        JButton billButton = createButton("Bill", "C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\bill 1.png", 1130, -100  + 2 * (buttonSize + buttonGap), buttonSize, buttonSize);
	        billButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                GenerateBill GenerateBill = new GenerateBill(loggedInFarmerID);
	                GenerateBill.setVisible(true);
	            }
	        });
	        backgroundPanel.add(billButton);
	
	        JButton animalInfoButton = createButton("Animal Info", "C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\animal logo.png", 170, -100 + 2 * (buttonSize + buttonGap), buttonSize, buttonSize);
	        animalInfoButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                AnimalInfo animalInfo = new AnimalInfo(loggedInFarmerId);
	                animalInfo.setVisible(true);
	            }
	        });
	        
	        JButton employeeDetailsButton =  new JButton("Contacts");
	        employeeDetailsButton.setForeground(Color.RED);
	        
	        employeeDetailsButton.setForeground(Color.RED);
	        employeeDetailsButton.setFont(new Font("Arial", Font.BOLD, 12));
	        employeeDetailsButton.setBackground(Color.WHITE);
	        
	        employeeDetailsButton.setBounds(1065, 10, 100, 30);
	        employeeDetailsButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Handle opening EmployeeDetailsWindow
	                EmployeeDetailsWindow employeeDetailsWindow = new EmployeeDetailsWindow(getVillageId(loggedInFarmerId),loggedInFarmerIdd);
	                employeeDetailsWindow.setVisible(true);
	            }
	        });
	        backgroundPanel.add(employeeDetailsButton);

	        backgroundPanel.add(animalInfoButton);
	        
	        JButton complaintButton = createButton("Complaint", "C:\\Users\\akhil\\OneDrive\\Documents\\complaint_icon.png", 50, 50 + 2 * (buttonSize + buttonGap), buttonSize, buttonSize);
	        complaintButton.setForeground(Color.RED);
	        complaintButton.setFont(new Font("Arial", Font.BOLD, 12));
	        complaintButton.setBackground(Color.WHITE);
	        complaintButton.setBounds(1340, 10, 100, 30);

	        complaintButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Handle complaint action
	                Complaint complaint = new Complaint(loggedInFarmerId, getVillageId(loggedInFarmerId));
	                complaint.setVisible(true);
	            }
	        });
	        backgroundPanel.add(complaintButton);
	        JButton yourAccountButton = new JButton("Your Account");
	        yourAccountButton.setForeground(Color.RED);
	        yourAccountButton.setFont(new Font("Arial", Font.BOLD, 12));
	        yourAccountButton.setBackground(Color.WHITE);
	        yourAccountButton.setBounds(1179, 10, 150, 30);
	        yourAccountButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	FarmerAccount FarmerAccount = new FarmerAccount(loggedInFarmerId);
	            	FarmerAccount.setVisible(true); // Show FarmerAccount functionalities
	            }
	        });
	        backgroundPanel.add(yourAccountButton);
	        setContentPane(backgroundPanel);
	    }
	    private JButton createButton(String buttonText, String imagePath, int x, int y, int width, int height) {
	        JButton button = new JButton(buttonText);
	        int increasedWidth = width + 50; // Increase button width by 50 pixels
	        int increasedHeight = height + 20; // Increase button height by 20 pixels
	        button.setBounds(x, y, increasedWidth, increasedHeight); // Set the increased width and height
	        if (imagePath != null) {
	            ImageIcon icon = new ImageIcon(imagePath);
	            Image image = icon.getImage().getScaledInstance(increasedWidth, increasedHeight, Image.SCALE_SMOOTH);
	            button.setIcon(new ImageIcon(image));
	        }
	        return button;
	    }
	    private void closeAllWindows() {
	        Window[] windows = Window.getWindows();
	        for (Window window : windows) {
	            if (window instanceof JFrame) {
	                window.dispose();
	            }
	        }
	    }
	
	
	    private int getVillageId(int farmerId) {
	        int villageId = 0;
	        try {
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
	            String sql = "SELECT f_vid FROM farmer WHERE id = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, farmerId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                villageId = rs.getInt("f_vid");
	            }
	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return villageId;
	    }
	    
	    
	    public static void main(String[] args) {
	        int farmerId = 4;
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new FarmerDashboard(farmerId).setVisible(true);
	            }
	        });
	    }
	}
