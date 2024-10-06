package MINI_PROJECT;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class fetchAndDisplayComplaints extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private JTable complaintsTable;
    private JButton fetchButton;
    private JComboBox<String> filterComboBox;
    private JTextField searchField;
    private JButton searchButton;
    private JButton resolveButton;
    private JButton backButton; // Added back button
    private JButton displayResponsibleEmployeesButton; // Added button for displaying responsible employee ids

    private int employeeVillageId; // Added employee village id field

    public fetchAndDisplayComplaints(int employeeVillageId) {
        this.employeeVillageId = employeeVillageId; // Set employee village id
        setTitle("Complaints Dashboard");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Background image
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Home page.png"); // Replace with your image file path
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel filterLabel = new JLabel("Filter:");
        filterComboBox = new JComboBox<>(new String[]{"All", "Resolved"});
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        fetchButton = new JButton("Fetch Complaints");
        resolveButton = new JButton("Resolve Selected");
        backButton = new JButton("Back"); // Added back button
        displayResponsibleEmployeesButton = new JButton("Display Responsible Employees"); // Added button to display responsible employee ids

        topPanel.add(filterLabel);
        topPanel.add(filterComboBox);
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(fetchButton);
        topPanel.add(resolveButton);
        topPanel.add(backButton); // Added back button to the top panel
        topPanel.add(displayResponsibleEmployeesButton); // Added display responsible employees button

        mainPanel.add(topPanel, BorderLayout.NORTH);

        complaintsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(complaintsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Transparent table
        complaintsTable.setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        fetchButton.setBackground(Color.BLUE);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setFocusPainted(false);

        searchButton.setBackground(Color.GREEN);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);

        resolveButton.setBackground(Color.RED);
        resolveButton.setForeground(Color.WHITE);
        resolveButton.setFocusPainted(false);

        backButton.setBackground(Color.GRAY); // Set back button background color
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        displayResponsibleEmployeesButton.setBackground(Color.ORANGE); // Set display responsible employees button background color
        displayResponsibleEmployeesButton.setForeground(Color.WHITE);
        displayResponsibleEmployeesButton.setFocusPainted(false);

        filterLabel.setFont(new Font("Arial", Font.BOLD, 18));
        filterComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        fetchButton.setFont(new Font("Arial", Font.BOLD, 16));
        resolveButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set back button font
        displayResponsibleEmployeesButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set display responsible employees button font

        complaintsTable.setFont(new Font("Arial", Font.PLAIN, 16));

        fetchButton.addActionListener(e -> fetchComplaints());

        searchButton.addActionListener(e -> searchComplaints());

        resolveButton.addActionListener(e -> resolveSelectedComplaints());

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window when back button is clicked
            }
        });

        displayResponsibleEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayResponsibleEmployees(); // Call method to display responsible employee ids
            }
        });

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void fetchComplaints() {
        String filter = (String) filterComboBox.getSelectedItem();
        String searchKeyword = searchField.getText();
        String query = "SELECT c.complaint_id, c.complaint_text, c.status " +
                "FROM complaint c " +
                "JOIN farmer f ON c.id = f.id " +
                "WHERE";

        // Add filter condition based on status
        if (!filter.equals("All")) {
            query += " c.status = ?";
        } else {
            query += " 1=1"; // Placeholder for future conditions
        }

        // Add search condition based on description
        if (!searchKeyword.isEmpty()) {
            query += " AND c.complaint_text LIKE ?";
        }

        // Add condition to filter only pending complaints
        if (filter.equals("Pending")) {
            query += " AND c.status = 'Pending'";
        }

        // Add condition to filter complaints for the employee's village
        query += " AND f.f_vid = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Bind parameters
            int paramIndex = 1;
            if (!filter.equals("All")) {
                stmt.setString(paramIndex++, filter);
            }
            if (!searchKeyword.isEmpty()) {
                stmt.setString(paramIndex++, "%" + searchKeyword + "%");
            }
            stmt.setInt(paramIndex++, employeeVillageId); // Set employee village id parameter

            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            DefaultTableModel tableModel = new DefaultTableModel();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }
            Set<String> uniqueComplaints = new HashSet<>(); // Track unique complaints by complaint_text and status

            while (rs.next()) {
                // Check if the complaint is already in the set
                String complaintText = rs.getString("complaint_text");
                String status = rs.getString("status");
                String complaintKey = complaintText + ":" + status;
                if (!uniqueComplaints.contains(complaintKey)) {
                    uniqueComplaints.add(complaintKey); // Add to the set
                    Object[] rowData = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = rs.getObject(i);
                    }
                    tableModel.addRow(rowData);
                }
            }

            complaintsTable.setModel(tableModel);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private void searchComplaints() {
        fetchComplaints(); // Just re-fetch with the updated search keyword
    }

    private void resolveSelectedComplaints() {
        int[] selectedRows = complaintsTable.getSelectedRows();
        for (int row : selectedRows) {
            String complaintId = complaintsTable.getValueAt(row, 0).toString();
            markComplaintAsResolved(complaintId);
        }
        fetchComplaints(); // Refresh the table after resolving complaints
    }

    private void markComplaintAsResolved(String complaintId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
             Statement stmt = conn.createStatement()) {
            String sql = "UPDATE complaint SET status = 'Resolved' WHERE complaint_id = " + complaintId;
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void displayResponsibleEmployees() {
        // Method to display responsible employee information
        StringBuilder responsibleEmployeesInfo = new StringBuilder();
        String query = "SELECT eid, ename, phno FROM employee WHERE e_vid = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeVillageId); // Set the village id parameter

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("eid");
                String employeeName = rs.getString("ename");
                String employeeInfo = rs.getString("phno");
                responsibleEmployeesInfo.append("<html><b><font color='blue'>Employee ID:</font></b> ").append(employeeId)
                        .append("<br><b><font color='green'>Name:</font></b> ").append(employeeName)
                        .append("<br><b><font color='red'>Info:</font></b> ").append(employeeInfo)
                        .append("<br><br>");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String htmlMessage = "<html><body style='width: 300px'>" +
                "<h1 style='color: #800080;'>Responsible Employees Info:</h1>" +
                "<p>" + responsibleEmployeesInfo.toString() + "</p></body></html>";

        // Create a JOptionPane with the message
        JOptionPane optionPane = new JOptionPane(htmlMessage, JOptionPane.INFORMATION_MESSAGE);

        // Create a JScrollPane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(optionPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create a JDialog from the JOptionPane
        JDialog dialog = new JDialog();
        dialog.setTitle("Responsible Employees Info");
        dialog.add(scrollPane);
        dialog.setSize(1000, 600); // Set the size manually
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        int employeeVillageId = 1; // Replace with actual employee village id
        SwingUtilities.invokeLater(() -> new fetchAndDisplayComplaints(employeeVillageId));
    }
}
