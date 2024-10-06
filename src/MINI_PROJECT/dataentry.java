package MINI_PROJECT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class dataentry extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField farmerIDField;
    private JTextField firstNameField;
    private JTextField phoneField;
    private JTextField villageIDField;
    private JTextField milkTypeField;
    private JTextField animalIDField;
    private JTextField milkPriceField;
    private JTextField regDateField;
    private JTable farmerTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> searchCriteriaComboBox;
    private JTextField searchField;

    public dataentry() {
        setTitle("Manage Farmer Information");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
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
        Font robotoFont = new Font("Roboto", Font.PLAIN, 16);

        JLabel searchCriteriaLabel = new JLabel("Search by:");
        searchCriteriaLabel.setBounds(765, 10, 100, 25);
        searchCriteriaLabel.setFont(robotoFont);
        searchCriteriaLabel.setForeground(Color.BLUE);
        contentPane.add(searchCriteriaLabel);

        String[] searchCriteriaOptions = {"Farmer ID", "First Name", "Phone", "Village ID", "Milk Type", "Animal ID", "Registered Date"};
        searchCriteriaComboBox = new JComboBox<>(searchCriteriaOptions);
        searchCriteriaComboBox.setBounds(850, 10, 200, 30);
        contentPane.add(searchCriteriaComboBox);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(330, 10, 100, 25);
        searchLabel.setFont(robotoFont);
        searchLabel.setForeground(Color.BLUE);
        contentPane.add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(440, 10, 200, 30);
        contentPane.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(650, 10, 100, 30);
        searchButton.addActionListener(e -> searchFarmer());
        contentPane.add(searchButton);

        JLabel farmerIDLabel = new JLabel("Farmer ID:");
        farmerIDLabel.setBounds(10, 10, 100, 25);
        farmerIDLabel.setFont(robotoFont);
        farmerIDLabel.setForeground(Color.BLUE);
        contentPane.add(farmerIDLabel);

        
        farmerIDField = new JTextField();
        farmerIDField.setBounds(120, 10, 200, 30);
        contentPane.add(farmerIDField);

        JLabel firstNameLabel = new JLabel("Farmer Name:");
        firstNameLabel.setBounds(10, 50, 100, 35);
        firstNameLabel.setFont(robotoFont);
        firstNameLabel.setForeground(Color.BLUE);
        contentPane.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(120, 50, 200, 30);
        contentPane.add(firstNameField);

        JLabel phoneLabel = new JLabel("Phone No:");
        phoneLabel.setBounds(10, 90, 100, 25);
        phoneLabel.setFont(robotoFont);
        phoneLabel.setForeground(Color.BLUE);
        contentPane.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(120, 90, 200, 30);
        contentPane.add(phoneField);

        JLabel villageIDLabel = new JLabel("Village ID:");
        villageIDLabel.setBounds(10, 130, 100, 25);
        villageIDLabel.setFont(robotoFont);
        villageIDLabel.setForeground(Color.BLUE);
        contentPane.add(villageIDLabel);

        villageIDField = new JTextField();
        villageIDField.setBounds(120, 130, 200, 30);
        contentPane.add(villageIDField);

        JLabel milkTypeLabel = new JLabel("Milk Type:");
        milkTypeLabel.setBounds(10, 170, 100, 25);
        milkTypeLabel.setFont(robotoFont);
        milkTypeLabel.setForeground(Color.BLUE);
        contentPane.add(milkTypeLabel);

        milkTypeField = new JTextField();
        milkTypeField.setBounds(120, 170, 200, 30);
        contentPane.add(milkTypeField);
        
        JLabel milkPriceLabel = new JLabel("Milk Price:");
        milkPriceLabel.setBounds(10, 250, 100, 25);
        milkPriceLabel.setFont(robotoFont);
        milkPriceLabel.setForeground(Color.BLUE);
        contentPane.add(milkPriceLabel);

        milkPriceField = new JTextField();
        milkPriceField.setBounds(120, 250, 200, 30);
        contentPane.add(milkPriceField);
        
       
        JLabel regDateLabel = new JLabel("Rtd Date:");
        regDateLabel.setBounds(10, 290, 100, 25);
        regDateLabel.setFont(robotoFont);
        regDateLabel.setForeground(Color.BLUE);
        contentPane.add(regDateLabel);

        regDateField = new JTextField();
        regDateField.setBounds(120, 290, 200, 30);
        contentPane.add(regDateField);
        

        JLabel animalIDLabel = new JLabel("Animal ID:");
        animalIDLabel.setBounds(10, 210, 100, 25);
        animalIDLabel.setFont(robotoFont);
        animalIDLabel.setForeground(Color.BLUE);
        contentPane.add(animalIDLabel);

        animalIDField = new JTextField();
        animalIDField.setBounds(120, 210, 200, 30);
        contentPane.add(animalIDField);
        
        
       
        

        Color buttonColor = new Color(0, 102, 204); // Blue color
        Color fontColor = Color.WHITE; // White font color

        JButton saveButton = new JButton("Add");
        saveButton.setBounds(10, 340, 120, 35);
        saveButton.setFont(robotoFont);
        saveButton.setBackground(buttonColor);
        saveButton.setForeground(fontColor);
        saveButton.addActionListener(e -> saveFarmer());
        contentPane.add(saveButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(140, 340, 120, 35);
        updateButton.setFont(robotoFont);
        updateButton.setBackground(buttonColor);
        updateButton.setForeground(fontColor);
        updateButton.addActionListener(e -> updateFarmer());
        contentPane.add(updateButton);

        JButton cancelButton = new JButton("Back");
        cancelButton.setBounds(270, 340, 120, 35);
        cancelButton.setFont(robotoFont);
        cancelButton.setBackground(buttonColor);
        cancelButton.setForeground(fontColor);
        cancelButton.addActionListener(e -> dispose());
        contentPane.add(cancelButton);

        JButton showDataButton = new JButton("Show Data");
        showDataButton.setBounds(400, 340, 150, 35);
        showDataButton.setFont(robotoFont);
        showDataButton.setBackground(buttonColor);
        showDataButton.setForeground(fontColor);
        showDataButton.addActionListener(e -> showFarmerData());
        contentPane.add(showDataButton);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(562, 340, 120, 35);
        deleteButton.setFont(robotoFont);
        deleteButton.setBackground(buttonColor);
        deleteButton.setForeground(fontColor);
        deleteButton.addActionListener(e -> deleteSelectedFarmer()); // Action listener to call deleteSelectedFarmer method
        contentPane.add(deleteButton);

        String[] columnNames = {"Farmer ID", "First Name", "Phone", "Village ID", "Milk Type","Milk Price","Registered Date", "Animal ID", "Action"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        farmerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(farmerTable);
        scrollPane.setBounds(70, 450, 1260, 230);
        contentPane.add(scrollPane);

        farmerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = farmerTable.rowAtPoint(evt.getPoint());
                int col = farmerTable.columnAtPoint(evt.getPoint());
                if (col == 8) { // Check if the clicked column is the action column
                    deleteFarmer(row);
                } else {
                    // Retrieve data from the clicked row and fill the fields
                    farmerIDField.setText(String.valueOf(farmerTable.getValueAt(row, 0)));
                    firstNameField.setText(String.valueOf(farmerTable.getValueAt(row, 1)));
                    phoneField.setText(String.valueOf(farmerTable.getValueAt(row, 2)));
                    villageIDField.setText(String.valueOf(farmerTable.getValueAt(row, 3)));
                    milkTypeField.setText(String.valueOf(farmerTable.getValueAt(row, 4)));
                    milkPriceField.setText(String.valueOf(farmerTable.getValueAt(row, 5)));
                    regDateField.setText(String.valueOf(farmerTable.getValueAt(row, 6)));
                    animalIDField.setText(String.valueOf(farmerTable.getValueAt(row, 7)));
                }
            }
        });

        setLayout(null);
        setVisible(true);

        updateTable();
    }

    private void saveFarmer() {
        if (isInputValid()) {
            int farmerID = Integer.parseInt(farmerIDField.getText());
            String firstName = firstNameField.getText();
            String phone = phoneField.getText();
            int villageID = Integer.parseInt(villageIDField.getText());
            String milkType = milkTypeField.getText();
            String milkPrice = milkPriceField.getText();
            String regDate = regDateField.getText().isEmpty() ? getCurrentTimestamp() : regDateField.getText();
            String animalID = animalIDField.getText();

            // Check if any field is empty and replace it with default values if available
            if (milkType.isEmpty()) {
                milkType = "cow"; // Set default milk type to "cow"
            }

            if (milkPrice.isEmpty()) {
                milkPrice = "27"; // Set default milk price to "27"
            }

            try {
                addFarmer(farmerID, firstName, phone, villageID, milkType, milkPrice, regDate, animalID);
                JOptionPane.showMessageDialog(null, "Farmer added successfully!");
                updateTable(); // Refresh table after adding
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    // Method to get current timestamp
  
    // Methods to retrieve default values from the database if available


    private void showFarmerData() {
        updateTable(); // Call updateTable method to refresh data
    }

    private boolean isInputValid() {
        return !farmerIDField.getText().isEmpty() &&
                !firstNameField.getText().isEmpty() &&
                !phoneField.getText().isEmpty() &&
                !villageIDField.getText().isEmpty() &&
                !milkTypeField.getText().isEmpty() &&
                !milkPriceField.getText().isEmpty() &&
                !regDateField.getText().isEmpty() &&
                !animalIDField.getText().isEmpty();
    }

    private void searchFarmer() {
        try {
            String query = searchField.getText();
            String selectedCriteria = (String) searchCriteriaComboBox.getSelectedItem();
            if (selectedCriteria == null || selectedCriteria.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a search criteria!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String columnName = getColumnName(selectedCriteria);
            if (columnName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid search criteria!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String url = "jdbc:mysql://localhost:3306/milk";
            String username = "root";
            String password = "root@123";

            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM farmer WHERE " + columnName + " LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0); // Clear table before updating

            while (rs.next()) {
                int farmerID = rs.getInt("id");
                String firstName = rs.getString("fname");
                String phone = rs.getString("ph");
                int villageID = rs.getInt("f_vid");
                String milkType = rs.getString("milk_type");
                String milkPrice = rs.getString("milk_price");
                String regDate = rs.getString("reg_date");
                String animalID = rs.getString("animalID");

                tableModel.addRow(new Object[]{farmerID, firstName, phone, villageID, milkType, milkPrice, regDate, animalID, "Delete"});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error searching: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getColumnName(String selectedCriteria) {
        switch (selectedCriteria.toLowerCase()) {
            case "farmer id":
                return "id";
            case "first name":
                return "fname";
            case "phone":
                return "ph";
            case "village id":
                return "f_vid";
            case "milk type":
                return "milk_type";
            case "milk price":
                return "milk_price";
            case "registered date":
                return "reg_date";
            case "animal id":
                return "animalID";
            default:
                return "";
        }
    }

    private void updateFarmer() {
        if (isInputValid()) {
            int farmerID = Integer.parseInt(farmerIDField.getText());
            String firstName = firstNameField.getText();
            String phone = phoneField.getText();
            int villageID = Integer.parseInt(villageIDField.getText());
            String milkType = milkTypeField.getText();
            String milkPrice = milkPriceField.getText();
            String regDate = regDateField.getText();
            String animalID = animalIDField.getText();

            try {
                updateFarmerInDatabase(farmerID, firstName, phone, villageID, milkType, milkPrice, regDate, animalID);
                JOptionPane.showMessageDialog(null, "Farmer updated successfully!");
                updateTable(); // Refresh table after updating
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    private void deleteSelectedFarmer() {
        int selectedRow = farmerTable.getSelectedRow();
        if (selectedRow == -1) { // If no row is selected
            JOptionPane.showMessageDialog(null, "Please select a record to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int farmerID = (int) farmerTable.getValueAt(selectedRow, 0);
            String deletedRecordDetails = "Deleted Record Details:\n" +
                    "Farmer ID: " + farmerID + "\n" +
                    "First Name: " + farmerTable.getValueAt(selectedRow, 1) + "\n" +
                    "Phone: " + farmerTable.getValueAt(selectedRow, 2) + "\n" +
                    "Village ID: " + farmerTable.getValueAt(selectedRow, 3) + "\n" +
                    "Milk Type: " + farmerTable.getValueAt(selectedRow, 4) + "\n" +
                    "Milk Price: " + farmerTable.getValueAt(selectedRow, 5) + "\n" +
                    "Registered Date: " + farmerTable.getValueAt(selectedRow, 6) + "\n" +
                    "Animal ID: " + farmerTable.getValueAt(selectedRow, 7);
            
            int confirmDialog = JOptionPane.showConfirmDialog(null, deletedRecordDetails + "\nAre you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if (confirmDialog == JOptionPane.YES_OPTION) {
                try {
                    deleteFarmerFromDatabase(farmerID);
                    JOptionPane.showMessageDialog(null, "Farmer deleted successfully!");
                    updateTable(); // Refresh table after deletion
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void deleteFarmer(int row) {
        int farmerID = (int) farmerTable.getValueAt(row, 0);
        String deletedRecordDetails = "Deleted Record Details:\n" +
                "Farmer ID: " + farmerID + "\n" +
                "First Name: " + farmerTable.getValueAt(row, 1) + "\n" +
                "Phone: " + farmerTable.getValueAt(row, 2) + "\n" +
                "Village ID: " + farmerTable.getValueAt(row, 3) + "\n" +
                "Milk Type: " + farmerTable.getValueAt(row, 4) + "\n" +
                "Milk Price: " + farmerTable.getValueAt(row, 5) + "\n" +
                "Registered Date: " + farmerTable.getValueAt(row, 6) + "\n" +
                "Animal ID: " + farmerTable.getValueAt(row, 7);
        try {
            JOptionPane.showMessageDialog(null, deletedRecordDetails, "Deleted Record Details", JOptionPane.INFORMATION_MESSAGE);

            int confirmDialog = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmDialog == JOptionPane.YES_OPTION) {
                // Show deleted record details before confirming deletion
                
                // Open the confirmation page after showing deleted record details
                openConfirmationPage();
                
                // Delete the record from the database if confirmed
                deleteFarmerFromDatabase(farmerID);
                
                // Update table after deletion
                updateTable();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to open the confirmation page
    private void openConfirmationPage() {
        // Assuming you want to show a confirmation dialog after deleting the record
        int confirmDialog = JOptionPane.showConfirmDialog(null, "Record deleted successfully!", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        if (confirmDialog == JOptionPane.OK_OPTION) {
            // Logic to handle the confirmation after the record is deleted
            // For example, you can close the confirmation dialog or perform additional actions
        }
    }

    private void deleteFarmerFromDatabase(int farmerID) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/milk";
        String username = "root";
        String password = "root@123";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM farmer WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, farmerID);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Farmer deleted successfully!");
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private void updateFarmerInDatabase(int farmerID, String firstName, String phone, int villageID, String milkType, String milkPrice, String regDate, String animalID) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/milk";
        String username = "root";
        String password = "root@123";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            StringBuilder queryBuilder = new StringBuilder("UPDATE farmer SET ");
            boolean updated = false;

            if (!firstName.isEmpty()) {
                queryBuilder.append("fname=?, ");
                updated = true;
            }
            if (!phone.isEmpty()) {
                queryBuilder.append("ph=?, ");
                updated = true;
            }
            if (villageID > 0) {
                queryBuilder.append("f_vid=?, ");
                updated = true;
            }
            if (!milkType.isEmpty()) {
                queryBuilder.append("milk_type=?, ");
                updated = true;
            }
            if (!milkPrice.isEmpty()) {
                queryBuilder.append("milk_price=?, ");
                updated = true;
            }
            if (!regDate.isEmpty()) {
                queryBuilder.append("reg_date=?, ");
                updated = true;
            }
            if (!animalID.isEmpty()) {
                queryBuilder.append("animalID=?, ");
                updated = true;
            }

            if (updated) {
                // Remove the last comma and space
                queryBuilder.setLength(queryBuilder.length() - 2);
                queryBuilder.append(" WHERE id=?");

                PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
                int parameterIndex = 1;

                if (!firstName.isEmpty()) {
                    statement.setString(parameterIndex++, firstName);
                }
                if (!phone.isEmpty()) {
                    statement.setString(parameterIndex++, phone);
                }
                if (villageID > 0) {
                    statement.setInt(parameterIndex++, villageID);
                }
                if (!milkType.isEmpty()) {
                    statement.setString(parameterIndex++, milkType);
                }
                if (!milkPrice.isEmpty()) {
                    statement.setString(parameterIndex++, milkPrice);
                }
                if (!regDate.isEmpty()) {
                    statement.setString(parameterIndex++, regDate);
                }
                if (!animalID.isEmpty()) {
                    statement.setString(parameterIndex++, animalID);
                }

                statement.setInt(parameterIndex, farmerID);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                  //  System.out.println("Farmer updated successfully!");
                }

                statement.close();
            } else {
                System.out.println("Nothing to update!");
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private void addFarmer(int farmerID, String firstName, String phone, int villageID, String milkType, String milkPrice, String regDate, String animalID) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/milk";
        String username = "root";
        String password = "root@123";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO farmer (id, fname, ph, f_vid, milk_type, milk_price, reg_date, animalID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, farmerID);
            statement.setString(2, firstName);
            statement.setString(3, phone);
            statement.setInt(4, villageID);
            statement.setString(5, milkType);
            statement.setString(6, milkPrice);
            statement.setString(7, regDate);
            statement.setString(8, animalID);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new farmer was inserted successfully!");
            }

            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }


    private void updateTable() {
        try {
            String url = "jdbc:mysql://localhost:3306/milk";
            String username = "root";
            String password = "root@123";

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM farmer");

            tableModel.setRowCount(0); // Clear table before updating

            while (rs.next()) {
                int farmerID = rs.getInt("id");
                String firstName = rs.getString("fname");
                String phone = rs.getString("ph");
                int villageID = rs.getInt("f_vid");
                String milkType = rs.getString("milk_type");
                String milkPrice = rs.getString("milk_price");
                String regDate = rs.getString("reg_date");
                String animalID = rs.getString("animalID");

                tableModel.addRow(new Object[]{farmerID, firstName, phone, villageID, milkType, milkPrice, regDate, animalID, "Delete"});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new dataentry();
    }
}
