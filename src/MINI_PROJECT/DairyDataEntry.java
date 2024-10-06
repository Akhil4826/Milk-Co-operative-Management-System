package MINI_PROJECT;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class DairyDataEntry extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField farmerIDField;
    private JTextField dateField;
    private JTextField quantityField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton showDataButton;
    private JButton backButton; // Added back button
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane; // Moved scrollPane declaration here

    private static final String DB_URL = "jdbc:mysql://localhost:3306/milk";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root@123";

    public DairyDataEntry() {
        setTitle("Dairy Data Entry");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Font customFont = new Font("Arial", Font.BOLD, 16);

        JPanel inputPanel = new JPanel(new GridLayout(1, 1));
        inputPanel.setBackground(Color.lightGray);

        JLabel farmerIDLabel = new JLabel("Farmer ID:");
        farmerIDLabel.setFont(customFont);
        farmerIDLabel.setForeground(Color.BLUE);
        farmerIDField = new JTextField();
        inputPanel.add(farmerIDLabel);
        inputPanel.add(farmerIDField);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(customFont);
        dateLabel.setForeground(Color.BLUE);
        dateField = new JTextField();
        dateField.setPreferredSize(new Dimension(1000, 40)); // Set preferred size for dateField
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(customFont);
        quantityLabel.setForeground(Color.BLUE);
        quantityField = new JTextField();
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);

        saveButton = new JButton("Save");
        saveButton.setBackground(Color.GREEN);
        saveButton.setFont(customFont);
        updateButton = new JButton("Update");
        updateButton.setBackground(Color.ORANGE);
        updateButton.setFont(customFont);
        backButton = new JButton("Back"); // Initialize back button
        backButton.setBackground(Color.RED); // Set button color
        backButton.setFont(customFont); // Set button font

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData();
            }
        });

        inputPanel.add(saveButton);
        inputPanel.add(updateButton);
        inputPanel.add(backButton); // Add back button to inputPanel
        add(inputPanel, BorderLayout.NORTH);

        // Initialize tableModel
        tableModel = new DefaultTableModel(); // Initialize tableModel here

        // Use a modern UI table with no editable cells
        dataTable = new JTable(tableModel); // Pass tableModel to JTable constructor
        dataTable.setBackground(Color.WHITE);

        // Add mouse listener to table to capture row selection
        dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                fillFields(selectedRow);
            }
        });

        scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);

        showDataButton = new JButton("Show Dairy Data");
        showDataButton.setBackground(Color.CYAN);
        showDataButton.setFont(customFont);
        add(showDataButton, BorderLayout.SOUTH);

        showDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showData();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose the window when back button is clicked
            }
        });

        setVisible(true);
        updateTable(); // Add this to show data immediately when the window is opened
    }

    // Method to fill fields with data from selected row
    private void fillFields(int selectedRow) {
        if (selectedRow != -1) { // Check if a row is selected
            farmerIDField.setText(dataTable.getValueAt(selectedRow, 0).toString());
            dateField.setText(dataTable.getValueAt(selectedRow, 2).toString());
            quantityField.setText(dataTable.getValueAt(selectedRow, 1).toString());
        }
    }
    

    // Method to save data
 // Method to save data
    private void saveData() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String farmerID = farmerIDField.getText();
            String date = dateField.getText();
            String quantity = quantityField.getText();

            // Check if the date field is empty
            if (date.isEmpty()) {
                // If date is empty, set it to the current timestamp
                date = getCurrentTimestamp();
            }

            String query = "INSERT INTO daily_data_entry (fid, daily_date, quantity) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, farmerID);
            preparedStatement.setString(2, date); // Use the modified date value
            preparedStatement.setString(3, quantity);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Data saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                farmerIDField.setText("");
                dateField.setText("");
                quantityField.setText("");
                updateTable(); // Update the table after saving data
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save data", "Error", JOptionPane.ERROR_MESSAGE);
            }

            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to get current timestamp
    private String getCurrentTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }

    // Method to display data in the table
 // Edit this code to show the data in the form of a JTable

    private void showData() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM daily_data_entry";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Clear existing table data
            DefaultTableModel tableModel = new DefaultTableModel();
            dataTable.setModel(tableModel);

            int columnCount = metaData.getColumnCount();
            // Add columns to the table model
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the table model
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(rowData);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to update data in the table
    private void updateData() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String farmerID = farmerIDField.getText();
            String date = dateField.getText();
            String quantity = quantityField.getText();

            if (farmerID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the Farmer ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder queryBuilder = new StringBuilder("UPDATE daily_data_entry SET ");
            boolean hasSetClause = false;

            if (!date.isEmpty()) {
                queryBuilder.append("daily_date=?, ");
                hasSetClause = true;
            }
            if (!quantity.isEmpty()) {
                queryBuilder.append("quantity=?, ");
                hasSetClause = true;
            }

            if (hasSetClause) {
                queryBuilder.setLength(queryBuilder.length() - 2);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter at least one field to update", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            queryBuilder.append(" WHERE fid=?");

            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
            int parameterIndex = 1;

            if (!date.isEmpty()) {
                preparedStatement.setString(parameterIndex++, date);
            }
            if (!quantity.isEmpty()) {
                preparedStatement.setString(parameterIndex++, quantity);
            }

            preparedStatement.setString(parameterIndex, farmerID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Data updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                farmerIDField.setText("");
                dateField.setText("");
                quantityField.setText("");
                updateTable(); // Update the table after updating data
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update data. No records found for the provided Farmer ID", "Error", JOptionPane.ERROR_MESSAGE);
            }

            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to update the table
    private void updateTable() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showData(); // Call showData method to update the table
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DairyDataEntry();
            }
        });
    }
}
