package MINI_PROJECT;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EmployeeDetailsWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public EmployeeDetailsWindow(int villageId, String loggedInUserId) {
        setTitle("Employee Details");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set background color
        getContentPane().setBackground(new Color(240, 240, 255));

        // Initialize components
        JTextArea employeeDetailsTextArea = new JTextArea(20, 50);
        JButton showDetailsButton = new JButton("Show Employee Details");
        JButton sendMessageButton = new JButton("Show Messages");
        JButton backButton = new JButton("Back");

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Font textAreaFont = new Font("Arial", Font.PLAIN, 14);

        employeeDetailsTextArea.setFont(textAreaFont);
        showDetailsButton.setFont(buttonFont);
        sendMessageButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        // Customize button colors
        showDetailsButton.setBackground(new Color(59, 89, 182));
        sendMessageButton.setBackground(new Color(59, 89, 182));
        backButton.setBackground(new Color(59, 89, 182));

        showDetailsButton.setForeground(Color.WHITE);
        sendMessageButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);

        showDetailsButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        sendMessageButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Set layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 255));
        panel.add(new JScrollPane(employeeDetailsTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 255));
        buttonPanel.add(showDetailsButton);
        buttonPanel.add(sendMessageButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // Action listener for show details button
        showDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch and display employee details for the village
                String details = fetchEmployeeDetails(villageId);
                employeeDetailsTextArea.setText(details);
            }
        });

        // Action listener for send message button
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the message sender window with loggedInUserId
                new MessageSender(loggedInUserId).setVisible(true);
            }
        });

        // Action listener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window and go back to the previous one
                dispose();
            }
        });

        setLocationRelativeTo(null);
    }

    // Method to fetch employee details
    private String fetchEmployeeDetails(int villageId) {
        StringBuilder details = new StringBuilder();
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
            // Prepare SQL statement to fetch employee details by village ID
            String sql = "SELECT * FROM employee WHERE e_vid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, villageId);
            ResultSet rs = stmt.executeQuery();
            // Append employee details to the string builder
            while (rs.next()) {
                int empId = rs.getInt("eid");
                String empName = rs.getString("ename");
                String empContact = rs.getString("phno");
                // Append employee details with colorful and classy formatting
                details.append("Employee ID: ").append(empId).append("\n")
                        .append("Name: ").append(empName).append("\n")
                        .append("Contact: ").append(empContact).append("\n\n");
            }
            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return details.toString();
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Example farmer login ID
                String loggedInUserId = "farmer123"; // Set the logged-in user ID here
                new EmployeeDetailsWindow(123, loggedInUserId).setVisible(true);
            }
        });
    }
}
