package MINI_PROJECT;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GenerateBill extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField farmerIDField;
    private JTextField fromDateField;
    private JTextField toDateField;
    private JLabel totalAmountLabel;
    private JTable billTable;
    private DefaultTableModel tableModel;

    public GenerateBill(int farmerID) {
        setTitle("Farmer Bill");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 1, 1, 1));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(topPanel, BorderLayout.NORTH);

        // Farmer ID
        JLabel farmerIDLabel = new JLabel("<html><font size='5'>Farmer ID:</font></html>");
        farmerIDLabel.setForeground(new Color(53, 140, 214));
        topPanel.add(farmerIDLabel);
        farmerIDField = new JTextField(10);
        farmerIDField.setText(String.valueOf(farmerID));
        farmerIDField.setEditable(false);
        farmerIDField.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(farmerIDField);
        // From Date
        JLabel fromDateLabel = new JLabel("<html><font size='5'>From-Date (yyyy/MM/dd):</font></html>");
        fromDateLabel.setForeground(new Color(53, 140, 214));
        fromDateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(fromDateLabel);
        fromDateField = new JTextField(10);
        fromDateField.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(fromDateField);

        // To Date
        JLabel toDateLabel = new JLabel("<html><font size='5'>To-Date (yyyy/MM/dd):</font></html>");
        toDateLabel.setForeground(new Color(53, 140, 214));
        toDateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(toDateLabel);
        toDateField = new JTextField(10);
        toDateField.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(toDateField);

        // Generate Bill Button
        JButton generateBillButton = new JButton("<html><font size='5'>Generate Bill</font></html>");
        generateBillButton.setBackground(new Color(255, 193, 7));
        generateBillButton.setForeground(Color.BLACK);
        generateBillButton.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(generateBillButton);
        generateBillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fromDate = fromDateField.getText();
                String toDate = toDateField.getText();

                try {
                    double totalAmount = generateBill(farmerID, fromDate, toDate);
                    if (totalAmount <= 0) {
                        JOptionPane.showMessageDialog(GenerateBill.this, "<html><font size='5'>No pending bills to generate.</font></html>", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        totalAmountLabel.setText("<html><font size='5'>Total Amount: " + totalAmount + "</font></html>");
                        updateBillTable(farmerID, fromDate, toDate);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(GenerateBill.this, "<html><font size='5'>Error: " + ex.getMessage() + "</font></html>", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Perform Payment Button
        JButton performPaymentButton = new JButton("<html><font size='5'>Perform Payment</font></html>");
        performPaymentButton.setBackground(new Color(76, 175, 80));
        performPaymentButton.setForeground(Color.WHITE);
        performPaymentButton.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(performPaymentButton);
        performPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performPayment performPayment = new performPayment(farmerID);
                performPayment.executePayment();
            }
        });
        // Back Button
        JButton backButton = new JButton("<html><font size='5'>Back</font></html>");
        backButton.setBackground(new Color(255, 87, 34));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
            }
        });

        // Center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        // Total Amount Label
        totalAmountLabel = new JLabel("<html><font size='5'>Total Amount: 0</font></html>");
        totalAmountLabel.setForeground(new Color(244, 67, 54));
        totalAmountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        centerPanel.add(totalAmountLabel, BorderLayout.NORTH);

        // Table
        String[] columnNames = {"Farmer Name", "Bill ID", "Date", "Amount"};
        tableModel = new DefaultTableModel(columnNames, 0);
        billTable = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (row % 2 == 0) {
                    component.setBackground(Color.white);
                } else {
                    component.setBackground(new Color(255, 235, 238));
                }

                return component;
            }
        };
        billTable.setFont(new Font("Roboto", Font.PLAIN, 20));
        billTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 22));
        billTable.setRowHeight(40);

        JScrollPane scrollPane = new JScrollPane(billTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private double generateBill(int farmerID, String fromDate, String toDate) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/milk";
        String user = "root";
        String password = "root@123";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT SUM(amount) AS Total_Amount FROM bill WHERE farmer_id = ? AND dt BETWEEN ? AND ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, farmerID);
            statement.setString(2, fromDate);
            statement.setString(3, toDate);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("Total_Amount");
            } else {
                throw new SQLException("No data found for the specified criteria.");
            }
        }
    }
    private void updateBillTable(int farmerID, String fromDate, String toDate) throws SQLException {
        tableModel.setRowCount(0);
        String url = "jdbc:mysql://localhost:3306/milk";
        String user = "root";
        String password = "root@123";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT farmer_name, bill_id, dt, amount FROM bill WHERE farmer_id = ? AND dt BETWEEN ? AND ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, farmerID);
            statement.setString(2, fromDate);
            statement.setString(3, toDate);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String farmerName = resultSet.getString("farmer_name");
                String billID = resultSet.getString("bill_id");
                String date = resultSet.getString("dt");
                double amount = resultSet.getDouble("amount");
                Object[] row = {farmerName, billID, date, amount};
                tableModel.addRow(row);
            }
        }
    }

    public static void main(String[] args) {
        int loggedInFarmerID = 1;

        SwingUtilities.invokeLater(() -> {
            try {
                new GenerateBill(loggedInFarmerID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
