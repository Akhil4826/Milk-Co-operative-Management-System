package MINI_PROJECT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Complaint extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton fileComplaintButton;
    private JButton backButton; // Added back button

    public Complaint(int loggedInFarmerId, int villageId) {
        setTitle("Farmer Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel() {
            private static final long serialVersionUID = 1L;

            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\2.png");
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        contentPane.setOpaque(false);
        setContentPane(contentPane);

        fileComplaintButton = new JButton("File a Complaint");
        backButton = new JButton("Back"); // Initialized back button

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        fileComplaintButton.setFont(buttonFont);
        backButton.setFont(buttonFont); // Set font for back button

        fileComplaintButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE); // Set foreground color for back button

        fileComplaintButton.setBackground(Color.BLUE);
        backButton.setBackground(Color.RED); // Set background color for back button

        fileComplaintButton.setFocusPainted(false);
        backButton.setFocusPainted(false); // Set focus painted for back button

        fileComplaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isComplaintAlreadyFiled(loggedInFarmerId)) {
                    JOptionPane.showMessageDialog(null, "You have already filed a complaint.");
                    FileComplaintWindow complaintWindow = new FileComplaintWindow(loggedInFarmerId, villageId);
                    complaintWindow.setVisible(true);
                } else {
                    FileComplaintWindow complaintWindow = new FileComplaintWindow(loggedInFarmerId, villageId);
                    complaintWindow.setVisible(true);
                }
            }
        });

        backButton.addActionListener(new ActionListener() { // Action listener for back button
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1)); // Adjusted layout to accommodate back button
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(200, 300, 200, 300)); // Adjusted border
        buttonPanel.add(fileComplaintButton);
        buttonPanel.add(backButton); // Added back button to panel
        add(buttonPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean isComplaintAlreadyFiled(int farmerId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
            String sql = "SELECT * FROM complaint WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, farmerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rs.close();
                stmt.close();
                conn.close();
                return true;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

class FileComplaintWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public FileComplaintWindow(int loggedInFarmerId, int villageId) {
        setTitle("File a Complaint");
        setSize(400, 200);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionTextArea = new JTextArea(5, 30);
        JButton submitButton = new JButton("Submit");

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        descriptionLabel.setFont(labelFont);
        submitButton.setFont(labelFont);
        descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 14));

        descriptionLabel.setForeground(Color.BLACK);
        descriptionTextArea.setForeground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(Color.BLUE);

        setLayout(new BorderLayout());
        JPanel complaintPanel = new JPanel();
        complaintPanel.setLayout(new GridLayout(2, 1));
        complaintPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        complaintPanel.add(descriptionLabel);
        complaintPanel.add(descriptionTextArea);
        add(complaintPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = descriptionTextArea.getText();
                int[] responsibleEmployeeIds = getResponsibleEmployeeIdsForVillage(villageId);
                submitComplaint(loggedInFarmerId, description, responsibleEmployeeIds);
                JOptionPane.showMessageDialog(null, "Complaint submitted successfully to all responsible employees.");
                dispose();
            }
        });

        setLocationRelativeTo(null);
    }

    private int[] getResponsibleEmployeeIdsForVillage(int villageId) {
        List<Integer> employeeIds = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
            String sql = "SELECT eid FROM employee WHERE e_vid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, villageId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employeeIds.add(rs.getInt("eid"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employeeIds.stream().mapToInt(Integer::intValue).toArray();
    }

    private void submitComplaint(int farmerId, String description, int[] employeeIds) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
            String sql = "INSERT INTO complaint (id, complaint_text, employee_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Check if the complaint already exists
            sql = "SELECT COUNT(*) as complaint_count FROM complaint WHERE id = ? AND complaint_text = ?";
            PreparedStatement checkStmt = conn.prepareStatement(sql);
            checkStmt.setInt(1, farmerId);
            checkStmt.setString(2, description);
            ResultSet checkRs = checkStmt.executeQuery();
            checkRs.next();
            int complaintCount = checkRs.getInt("complaint_count");
            checkStmt.close();
            checkRs.close();

            // If the complaint doesn't exist, insert the new record
            if (complaintCount == 0) {
                stmt.setInt(1, farmerId);
                stmt.setString(2, description);

                // Loop through employee IDs and execute the statement for each employee
                for (int employeeId : employeeIds) {
                    stmt.setInt(3, employeeId);
                    stmt.executeUpdate();
                }
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }}