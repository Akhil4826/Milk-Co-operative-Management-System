package MINI_PROJECT;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
		public class EmployeeDashboard extends JFrame {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		    private String employeeIdd; // Assuming the logged-in farmer's ID is 1 for demonstration
		    
			public EmployeeDashboard(int employeeId) {
			     this.employeeIdd = String.valueOf(employeeId);
		
		        setTitle("Employee Dashboard");
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
		                    new LoginPage().setVisible(true); // Open the login page
		                    dispose(); // Close the dashboard window
		                } else {
			                new EmployeeDashboard(employeeId).setVisible(true);
	  // when "No" is pressed, do nothing
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
		                ImageIcon imageIcon = new ImageIcon("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Employee dashboardd.png");
		                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
		            }
		        };
		        backgroundPanel.setLayout(null);
		
		        JMenuBar menuBar = new JMenuBar();

		        // Step 2: Create menus
		        JMenu fileMenu = new JMenu("File");
		        JMenu viewMenu = new JMenu("View");
		        JMenu helpMenu = new JMenu("Help");

		        // Step 3: Add menu items to the menus
		        // File menu items
		        JMenuItem logoutMenuItem = new JMenuItem("Logout");
		        logoutMenuItem.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                closeAllWindows();
		                new LoginPage().setVisible(true);
		                dispose();
		            }
		        });
		        fileMenu.add(logoutMenuItem);

		        
		        
		        JMenuItem viewManagersButton = new JMenuItem("Managers");
		        viewManagersButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                ManagersDisplay managersDisplay = new ManagersDisplay(); // Instantiate ManagersDisplay
		                managersDisplay.setVisible(true); // Show ManagersDisplay
		            }
		        });
		        viewMenu.add(viewManagersButton);
		        
		        
		        // View menu items
		        JMenuItem fetchComplaintsMenuItem = new JMenuItem("Customer Complaints");
		        fetchComplaintsMenuItem.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                new fetchAndDisplayComplaints(getemployeeVillageId(employeeId)).setVisible(true);
		            }
		        });
		        viewMenu.add(fetchComplaintsMenuItem);

		        // Help menu items
		        JMenuItem aboutMenuItem = new JMenuItem("About");
		        aboutMenuItem.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                JOptionPane.showMessageDialog(EmployeeDashboard.this, "This is the Employee Dashboard.");
		            }
		        });
		        helpMenu.add(aboutMenuItem);

		        // Add menus to the menu bar
		        menuBar.add(fileMenu);
		        menuBar.add(viewMenu);
		        menuBar.add(helpMenu);

		        // Set the menu bar to the frame
		        setJMenuBar(menuBar);
		        
		        
		        JMenuItem achievementsButton = new JMenuItem("Achievements");		       
		        achievementsButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                JFrame frame = new JFrame("Milk Achievements");
		                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		                JPanel panel = new JPanel();
		                panel.setLayout(new GridBagLayout());
		                GridBagConstraints constraints = new GridBagConstraints();

		                JLabel label = new JLabel("Select an achievement:");
		                label.setFont(new Font("Arial", Font.BOLD, 12));
		                label.setForeground(Color.WHITE);
		                label.setOpaque(rootPaneCheckingEnabled);
		                label.setBackground(Color.DARK_GRAY);
		                constraints.gridx = 0;
		                constraints.gridy = 0;
		                constraints.anchor = GridBagConstraints.WEST;
		                panel.add(label, constraints);

		                JComboBox<String> achievementsComboBox = new JComboBox<String>();
		                achievementsComboBox.setForeground(Color.RED);
		                achievementsComboBox.setFont(new Font("Arial", Font.BOLD, 12));
		                achievementsComboBox.setBackground(Color.WHITE);
		                achievementsComboBox.setPreferredSize(new Dimension(400, 60));
		                achievementsComboBox.addItem("Farmer with the most milk");
		                achievementsComboBox.addItem("Village with the most milk");

		                achievementsComboBox.addActionListener(new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent e) {
		                        JComboBox<String> comboBox = extracted(e);
		                        String selectedAchievement = (String) comboBox.getSelectedItem();

		                        if (selectedAchievement.equals("Farmer with the most milk")) {
		                            displayMostMilkFarmer();
		                            frame.dispose();
		                        } else if (selectedAchievement.equals("Village with the most milk")) {
		                            MyDatabaseManager dbManager = new MyDatabaseManager();
		                            dbManager.displayMostvillageMilk(); // Call the displayMostvillageMilk method
		                            frame.dispose();
		                        }
		                    }

							@SuppressWarnings("unchecked")
							private JComboBox<String> extracted(ActionEvent e) {
								return (JComboBox<String>) e.getSource();
							}
		                });

		                constraints.gridx = 1;
		                constraints.gridy = 0;
		                constraints.anchor = GridBagConstraints.WEST;
		                panel.add(achievementsComboBox, constraints);

		                frame.add(panel);
		                frame.pack();
		                frame.setLocationRelativeTo(null);
		                frame.setVisible(true);
		            }
		        });
		       viewMenu.add(achievementsButton);

		        JMenuItem loggedInEmployeeDataButton = new JMenuItem("Logged in Employee Data");
		        loggedInEmployeeDataButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                // Handle displaying complete information of the logged-in employee
		                EmployeeData employeeData = new EmployeeData();
		                employeeData.displayEmployeeData(employeeId); // Call the method to display employee data
		            }
		        });
		        viewMenu.add(loggedInEmployeeDataButton);

	
		        
		        // Welcome message with employee's name
		        String employeeName = getEmployeeName(employeeId);
		        JLabel welcomeLabel = new JLabel("Welcome to the Employee Dashboard !!, " + employeeName.toUpperCase() + "!!");
		        welcomeLabel.setFont(new Font("Garamond", Font.BOLD, 30)); // Change font to Playfair Display
		        welcomeLabel.setForeground(Color.BLUE);
		        welcomeLabel.setBounds(250, 40, 4000, 40);
		        backgroundPanel.add(welcomeLabel);
		
		        // Logout button
		        JButton logoutButton = new JButton("Logout");
		        logoutButton.setForeground(Color.RED);
		        logoutButton.setBounds(1450, 10, 80, 30);
		        // Customizing button
		        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
		        logoutButton.setBackground(Color.WHITE);
		        logoutButton.setBorderPainted(false);
		        // End customization
		        logoutButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                closeAllWindows();
		                new LoginPage().setVisible(true);
		                dispose();
		            }
		        });
		        backgroundPanel.add(logoutButton);
	
		        
		        JButton fetchComplaintsButton = new JButton("Fetch Complaints");
		        fetchComplaintsButton.setForeground(Color.RED);
	
		        fetchComplaintsButton.setBounds(1290, 10, 150, 30);
		        fetchComplaintsButton.setFont(new Font("Arial", Font.BOLD, 12));
		        fetchComplaintsButton.setBackground(Color.WHITE);
		        fetchComplaintsButton.setBorderPainted(false);
		        fetchComplaintsButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	new	fetchAndDisplayComplaints(getemployeeVillageId(employeeId)).setVisible(true);
		            }
		        });
		        backgroundPanel.add(fetchComplaintsButton);
		
		
		        // Buttons for different functionalities
		        JButton addFarmerButton = createButton("Add Farmer", "///C:/Users/akhil/eclipse-workspace/MINI_PROJECT/src/add_farmer_icon.png", 212, 230);
		        addFarmerButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                new dataentry().setVisible(true);
		            }
		        });
		        backgroundPanel.add(addFarmerButton);
		
		        JButton viewFarmersButton = createButton("View Farmers", "///C:/Users/akhil/eclipse-workspace/MINI_PROJECT/src/indian-villageEEE.jpg", 668, 230);
		        viewFarmersButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                new FarmerTable();
		            }
		        });
		        backgroundPanel.add(viewFarmersButton);
		
		        JButton staffDataButton = createButton("Staff Data", "///C:/Users/akhil/eclipse-workspace/MINI_PROJECT/src/MINI_PROJECT/PP.png"
		        		+ "", 1160, 230);
		        staffDataButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                new StaffData().setVisible(true);
		            }
		        });
		        backgroundPanel.add(staffDataButton);
		        
		      
		        JButton employeeDetailsButton =  new JButton("Contacts");
		        employeeDetailsButton.setForeground(Color.RED);
		        employeeDetailsButton.setFont(new Font("Arial", Font.BOLD, 12));
		        employeeDetailsButton.setBackground(Color.WHITE);
		        employeeDetailsButton.setBounds(1180, 10, 100, 30);
		        employeeDetailsButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                // Handle opening EmployeeDetailsWindow
		                EmployeeDetailsWindow employeeDetailsWindow = new EmployeeDetailsWindow(getemployeeVillageId(employeeId),employeeIdd);
		                employeeDetailsWindow.setVisible(true);
		            }
		        });
		        backgroundPanel.add(employeeDetailsButton);
		
		        JButton dairyRecordButton = createButton("Dairy Record", "C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\dairy record.jpg", 204, 530);
		        dairyRecordButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                new DairyDataEntry().setVisible(true);
		            }
		        });
		        backgroundPanel.add(dairyRecordButton);
		
		        JButton animalInfoButton = createButton("", "C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\animal logo.png", 658, 530);
		        animalInfoButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                new AnimalInfoData().setVisible(true);
		            }
		        });
		        backgroundPanel.add(animalInfoButton);
		
		        JButton generateBillButton = createButton("Billing History", "C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\bill 1.png", 1150, 530);
		        generateBillButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                new FarmerBill().setVisible(true);
		            }
		        });
		              
		        backgroundPanel.add(generateBillButton);
		        
		        
		        setContentPane(backgroundPanel);

		        
		        
		       
			}
			private void displayMostMilkFarmer() {
			    try {
			        // Establish connection to the database
			        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
			        
			        // Define the SQL query to retrieve data of the farmer who produces the most milk
			        String query = "SELECT * FROM farmer WHERE id = (SELECT fid FROM daily_data_entry GROUP BY fid ORDER BY SUM(quantity) DESC LIMIT 1)";
			        
			        // Create a prepared statement
			        PreparedStatement stmt = conn.prepareStatement(query);
			        
			        // Execute the query
			        ResultSet rs = stmt.executeQuery();
			        
			        // Create a JTable to display the results
			        JTable table = new JTable();
			        DefaultTableModel model = new DefaultTableModel(new String[]{"Farmer ID", "Farmer Name","Farmer Phone","Farmer Village ID"}, 0);
			        table.setModel(model);
			        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
			        table.getTableHeader().setBackground(Color.YELLOW);
			        table.getTableHeader().setForeground(Color.BLACK);
			        table.setFont(new Font("Arial", Font.PLAIN, 12));
			        table.setRowHeight(30);
			        
			        // Populate the table with data
			        while (rs.next()) {
			            model.addRow(new Object[]{rs.getInt("id"), rs.getString("fname"),rs.getString("ph"),rs.getString("f_vid")});
			            // Add more fields as needed
			        }
			        
			        // Close resources
			        rs.close();
			        stmt.close();
			        conn.close();
			        
			        // Display the table in a frame
			        JFrame frame = new JFrame("Most Milk Producing Farmer");
			        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        frame.setPreferredSize(new Dimension(400, 300));
			        frame.pack();
			        frame.setLocationRelativeTo(null);
			        
			        JScrollPane scrollPane = new JScrollPane(table);
			        frame.getContentPane().add(scrollPane);
			        
			        frame.setVisible(true);
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}
			
			 private void closeAllWindows() {
			        Window[] windows = Window.getWindows();
			        for (Window window : windows) {
			            if (window instanceof JFrame) {
			                window.dispose();
			            }
			        }
			    }
			 
			 
		    private JButton createButton(String buttonText, String iconName, int x, int y) {
		        ImageIcon icon = new ImageIcon(iconName);
		        JButton button = new JButton(buttonText, icon);
		        button.setVerticalTextPosition(SwingConstants.BOTTOM);
		        button.setHorizontalTextPosition(SwingConstants.CENTER);
		        button.setBounds(x, y, 220, 205);
		        return button;
		    }
		
		    private String getEmployeeName(int employeeId) {
		        String employeeName = "";
		        try {
		            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
		            String sql = "SELECT ename FROM employee WHERE eid = ?";
		            PreparedStatement stmt = conn.prepareStatement(sql);
		            stmt.setInt(1, employeeId);
		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		                employeeName = rs.getString("ename");
		            }
		            rs.close();
		            stmt.close();
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return employeeName;
		    }
		    private int getemployeeVillageId(int employeeId) {
		        int employeeVillageId = 1;
		        try {
		            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
		            String sql = "SELECT e_vid FROM employee WHERE eid = ?";
		            PreparedStatement stmt = conn.prepareStatement(sql);
		            stmt.setInt(1, employeeId);
		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		            	employeeVillageId = rs.getInt("e_vid");
		            }
		            rs.close();
		            stmt.close();
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return employeeVillageId;
		    }
		    
		
		    public static void main(String[] args) {
		        int employeeid = 12345;
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                new EmployeeDashboard(employeeid).setVisible(true);
		            }
		        });
		    }
		}
