package MINI_PROJECT;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FarmerBill extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/milk";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root@123";

    private JTextField farmerIDField;
    private JTextField fromDateField;
    private JTextField toDateField;
    private JLabel totalAmountLabel;
    private JTable billTable;
    private DefaultTableModel tableModel;

    public FarmerBill() {
        setTitle("Farmer Bill");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creating table columns
        String[] columnNames = {"ID Type", "Bill ID/Receipt ID", "Farmer ID", "Date", "Farmer Name", "Quantity", "Amount"};
        tableModel = new DefaultTableModel(columnNames, 0);
        billTable = new JTable(tableModel);
        billTable.setFont(new Font("Arial", Font.PLAIN, 14));
        billTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        billTable.setForeground(Color.blue);
        JScrollPane scrollPane = new JScrollPane(billTable);
        add(scrollPane, BorderLayout.CENTER);

        // Top panel with input fields and buttons
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.lightGray);
        add(topPanel, BorderLayout.NORTH);

        JLabel farmerIDLabel = new JLabel("Farmer ID:");
        farmerIDLabel.setFont(new Font("Arial", Font.BOLD, 14));
        farmerIDLabel.setForeground(Color.darkGray);
        topPanel.add(farmerIDLabel);
        farmerIDField = new JTextField(10);
        topPanel.add(farmerIDField);

        JLabel fromDateLabel = new JLabel("From-Date (yyyy/MM/dd):");
        fromDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fromDateLabel.setForeground(Color.darkGray);
        topPanel.add(fromDateLabel);
        fromDateField = new JTextField(10);
        topPanel.add(fromDateField);

        JLabel toDateLabel = new JLabel("To-Date (yyyy/MM/dd):");
        toDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        toDateLabel.setForeground(Color.darkGray);
        topPanel.add(toDateLabel);
        toDateField = new JTextField(10);
        topPanel.add(toDateField);

        JButton generateBillButton = new JButton("Show Data");
        generateBillButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateBillButton.setBackground(Color.green);
        generateBillButton.setForeground(Color.white);
        topPanel.add(generateBillButton);
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int farmerID = Integer.parseInt(farmerIDField.getText());
                String fromDate = fromDateField.getText();
                String toDate = toDateField.getText();

                try {
                    showFarmerBillHistory(farmerID, fromDate, toDate);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(FarmerBill.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.red);
        backButton.setForeground(Color.white);
        topPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
            }
        });

        setVisible(true);
        updateTable(); // Load initial bill data
    }

    private void showFarmerBillHistory(int farmerID, String fromDate, String toDate) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        	String query = "SELECT 'Receipt' AS id_type, reciept_id AS id, farmer_id, dt, farmer_name, quantity, amount FROM reciept " +
                    "WHERE farmer_id = ? AND dt BETWEEN ? AND ? " +
                    "UNION ALL " +
                    "SELECT 'Bill' AS id_type, bill_id AS id, farmer_id, dt, farmer_name, quantity, amount FROM bill " +
                    "WHERE farmer_id = ? AND dt BETWEEN ? AND ? " +
                    "ORDER BY id ASC";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, farmerID);
            statement.setString(2, fromDate);
            statement.setString(3, toDate);
            statement.setInt(4, farmerID);
            statement.setString(5, fromDate);
            statement.setString(6, toDate);

            ResultSet resultSet = statement.executeQuery();

            tableModel.setRowCount(0); // Clear table before updating

            double totalAmount = 0;
            while (resultSet.next()) {
                String idType = resultSet.getString("id_type");
                int id = resultSet.getInt("id");
                String date = resultSet.getString("dt");
                String farmerName = resultSet.getString("farmer_name");
                double quantity = resultSet.getDouble("quantity");
                double amount = resultSet.getDouble("amount");

                tableModel.addRow(new Object[]{idType, id, farmerID, date, farmerName, quantity, amount});

                totalAmount += amount;
            }

            totalAmountLabel.setText("Total Amount: " + totalAmount);
        }
    }

    private void updateTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        	String query = "SELECT 'Receipt' AS id_type, reciept_id AS id, farmer_id, dt, farmer_name, quantity, amount FROM reciept " +
                    "UNION ALL " +
                    "SELECT 'Bill' AS id_type, bill_id AS id, farmer_id, dt, farmer_name, quantity, amount FROM bill " +
                    "ORDER BY id ASC";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String idType = resultSet.getString("id_type");
                int id = resultSet.getInt("id");
                int farmerID = resultSet.getInt("farmer_id");
                String regDate = resultSet.getString("dt");
                String farmer_name = resultSet.getString("farmer_name");
                double quantity = resultSet.getDouble("quantity");
                double amount = resultSet.getDouble("amount");

                tableModel.addRow(new Object[]{idType, id, farmerID, regDate, farmer_name, quantity, amount});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating table: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FarmerBill::new);
    }
}
