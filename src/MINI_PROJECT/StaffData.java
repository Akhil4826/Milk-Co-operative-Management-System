package MINI_PROJECT;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class StaffData extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel nameLabel, designationLabel, eidLabel, phnoLabel, salaryLabel, addressLabel, searchLabel,villageLabel;
    private JTextField nameField, designationField, eidField, phnoField, salaryField, addressField, searchField,villageField;
    private JButton addButton, updateButton, deleteButton, showDataButton, searchButton;
    private Connection conn;
    private JTable staffTable;
    private MyTableModel tableModel;
    private JComboBox<String> searchCriteriaComboBox;
    public StaffData() {
        setTitle("Staff Data");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel() {
            /**
			 * 
			 */
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

        Font labelFont = new Font("Roboto", Font.BOLD, 18);
        Font textFieldFont = new Font("Roboto", Font.PLAIN, 18);
        Font buttonFont = new Font("Roboto", Font.BOLD, 18);
        Font buttonFont1 = new Font("Roboto", Font.BOLD, 12);

        int textFieldWidth = 350;

        
        
        
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(50, 50, 120, 30);
        nameLabel.setForeground(Color.BLUE);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setFont(textFieldFont);
        nameField.setBounds(200, 50, textFieldWidth, 30);
        add(nameField);

        designationLabel = new JLabel("Designation:");
        designationLabel.setFont(labelFont);
        designationLabel.setBounds(50, 100, 120, 30);
        designationLabel.setForeground(Color.BLUE);
        add(designationLabel);
        designationField = new JTextField();
        designationField.setFont(textFieldFont);
        designationField.setBounds(200, 100, textFieldWidth, 30);
        add(designationField);

        eidLabel = new JLabel("Employee ID:");
        eidLabel.setFont(labelFont);
        eidLabel.setBounds(50, 150, 120, 30);
        eidLabel.setForeground(Color.BLUE);
        add(eidLabel);
        eidField = new JTextField();
        eidField.setFont(textFieldFont);
        eidField.setBounds(200, 150, textFieldWidth, 30);
        add(eidField);

        phnoLabel = new JLabel("Phone Number:");
        phnoLabel.setFont(labelFont);
        phnoLabel.setBounds(50, 200, 120, 30);
        phnoLabel.setForeground(Color.BLUE);
        add(phnoLabel);
        phnoField = new JTextField();
        phnoField.setFont(textFieldFont);
        phnoField.setBounds(200, 200, textFieldWidth, 30);
        add(phnoField);

        salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(labelFont);
        salaryLabel.setBounds(50, 250, 120, 30);
        salaryLabel.setForeground(Color.BLUE);
        add(salaryLabel);
        salaryField = new JTextField();
        salaryField.setFont(textFieldFont);
        salaryField.setBounds(200, 250, textFieldWidth, 30);
        add(salaryField);

        addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        addressLabel.setBounds(50, 300, 120, 30);
        addressLabel.setForeground(Color.BLUE);
        add(addressLabel);
        addressField = new JTextField();
        addressField.setFont(textFieldFont);
        addressField.setBounds(200, 300, textFieldWidth, 30);
        add(addressField);

        villageLabel = new JLabel("Village Id:");
        villageLabel.setFont(labelFont);
        villageLabel.setBounds(570, 50, 120, 30);
        villageLabel.setForeground(Color.BLUE);
        add(villageLabel);
        villageField = new JTextField();
        villageField.setFont(textFieldFont);
        villageField.setBounds(680, 50, textFieldWidth, 30);
        add(villageField);

        
        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        addButton.setBounds(50, 350, 120, 40);
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> addStaff());
        add(addButton);

        updateButton = new JButton("Update");
        updateButton.setFont(buttonFont);
        updateButton.setBounds(200, 350, 120, 40);
        updateButton.setBackground(Color.BLUE);
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(e -> updateStaff());
        add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(buttonFont);
        deleteButton.setBounds(350, 350, 120, 40);
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteStaff());
        add(deleteButton);

        showDataButton = new JButton("Show Data");
        showDataButton.setFont(buttonFont);
        showDataButton.setBounds(500, 350, 150, 40);
        showDataButton.setBackground(Color.ORANGE);
        showDataButton.setForeground(Color.WHITE);
        showDataButton.addActionListener(e -> updateTable());
        add(showDataButton);

        
        
        
        searchLabel = new JLabel("Search by:");
        searchLabel.setFont(labelFont);
        searchLabel.setBounds(700, 350, 800, 30);
        searchLabel.setForeground(Color.BLUE);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setFont(textFieldFont);
        searchField.setBounds(800, 350, textFieldWidth, 30);
        add(searchField);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                search();
            }
            public void removeUpdate(DocumentEvent e) {
                search();
            }
            public void changedUpdate(DocumentEvent e) {
                search();
            }
        });

        String[] searchCriteria = {"Employee ID", "Name", "Designation", "Phone Number", "Salary", "Address","Village"};

        searchCriteriaComboBox = new JComboBox<>(searchCriteria);
        searchCriteriaComboBox.setFont(textFieldFont);
        searchCriteriaComboBox.setBounds(1265, 350, 150, 30);
        add(searchCriteriaComboBox);

        searchButton = new JButton("Search");
        searchButton.setFont(buttonFont);
        searchButton.setBounds(1130, 350, 120, 30);
        searchButton.setBackground(Color.YELLOW);
        searchButton.addActionListener(e -> search());
        add(searchButton);

        
        
        JButton cancelButton = new JButton("Back");
        cancelButton.setFont(buttonFont1);

        cancelButton.setBounds(10, 10, 65, 30);
        cancelButton.setBackground(Color.YELLOW);
        cancelButton.addActionListener(e -> dispose());
        contentPane.add(cancelButton);

        
        String[] columnNames = {"Employee ID", "Name", "Designation", "Phone Number", "Salary", "Address","Village Id"};
        tableModel = new MyTableModel(columnNames, 0);
        staffTable = new JTable(tableModel) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                // Make certain columns editable
                return column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5;
            }
        };
        staffTable.setFont(new Font("Roboto", Font.PLAIN, 18));
        staffTable.setGridColor(Color.PINK);
        staffTable.setSelectionBackground(new Color(140, 220, 250));
        JScrollPane scrollPane = new JScrollPane(staffTable);
        scrollPane.setBounds(50, 420, 1200, 390);
        add(scrollPane);

        staffTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = staffTable.getSelectedRow();
                eidField.setText(staffTable.getValueAt(selectedRow, 0).toString());
                nameField.setText(staffTable.getValueAt(selectedRow, 1).toString());
                designationField.setText(staffTable.getValueAt(selectedRow, 2).toString());
                phnoField.setText(staffTable.getValueAt(selectedRow, 3).toString());
                salaryField.setText(staffTable.getValueAt(selectedRow, 4).toString());
                addressField.setText(staffTable.getValueAt(selectedRow, 5).toString());
                villageField.setText(staffTable.getValueAt(selectedRow, 6).toString());
            }
        });

        setLayout(null);
        setVisible(true);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
            updateTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addStaff() {
        String name = nameField.getText();
        String designation = designationField.getText();
        String eidText = eidField.getText();
        String phno = phnoField.getText();
        String salaryText = salaryField.getText();
        String address = addressField.getText();
        String village= villageField.getText();
        
        if (name.isEmpty() || designation.isEmpty() || eidText.isEmpty() || phno.isEmpty() || salaryText.isEmpty() || address.isEmpty()|| village.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Return without adding staff if any field is empty
        }
        try {
            String sql = "INSERT INTO employee (eid, ename, phno, salary, designation, address,e_vid) VALUES (?, ?, ?, ?, ?, ?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, eidText);
            stmt.setString(2, name);
            stmt.setString(3, phno);
            stmt.setString(4, salaryText);
            stmt.setString(5, designation);
            stmt.setString(6, address);
            stmt.setString(6, village);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Staff added successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add staff");
            }
            stmt.close();
            updateTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding staff: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    private void updateStaff() {
        String currentEid = eidField.getText();
        String newEid = JOptionPane.showInputDialog(this, "Enter new Employee ID:");
        if (newEid == null || newEid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a new Employee ID", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Return without updating staff if new Employee ID is empty
        }

        // Build the update query for other fields
        StringBuilder queryBuilder = new StringBuilder("UPDATE employee SET eid = ?, ");
        boolean updated = false;

        if (!nameField.getText().isEmpty()) {
            queryBuilder.append("ename = ?, ");
            updated = true;
        }
        if (!designationField.getText().isEmpty()) {
            queryBuilder.append("designation = ?, ");
            updated = true;
        }
        if (!phnoField.getText().isEmpty()) {
            queryBuilder.append("phno = ?, ");
            updated = true;
        }
        if (!salaryField.getText().isEmpty()) {
            queryBuilder.append("salary = ?, ");
            updated = true;
        }
        if (!addressField.getText().isEmpty()) {
            queryBuilder.append("address = ?, ");
            updated = true;
        }
        if (!villageField.getText().isEmpty()) {
            queryBuilder.append("e_vid = ?, ");
            updated = true;
        }
        

        // Remove the last comma and space
        if (updated) {
            queryBuilder.setLength(queryBuilder.length() - 2);
        } else {
            JOptionPane.showMessageDialog(this, "No fields to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add condition for updating based on current eid
        queryBuilder.append(" WHERE eid = ?");

        try {
            PreparedStatement stmt = conn.prepareStatement(queryBuilder.toString());

            // Set parameters based on the fields that need to be updated
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, newEid); // Set new employee ID

            if (!nameField.getText().isEmpty()) {
                stmt.setString(parameterIndex++, nameField.getText());
            }
            if (!designationField.getText().isEmpty()) {
                stmt.setString(parameterIndex++, designationField.getText());
            }
            if (!phnoField.getText().isEmpty()) {
                stmt.setString(parameterIndex++, phnoField.getText());
            }
            if (!salaryField.getText().isEmpty()) {
                stmt.setInt(parameterIndex++, Integer.parseInt(salaryField.getText()));
            }
            if (!addressField.getText().isEmpty()) {
                stmt.setString(parameterIndex++, addressField.getText());
            }
            if (!villageField.getText().isEmpty()) {
                stmt.setString(parameterIndex++, villageField.getText());
            }

            // Set condition for updating based on current eid
            stmt.setString(parameterIndex++, currentEid);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Staff updated successfully");
                updateTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update staff");
            }
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating staff: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    private void deleteStaff() {
        String eidText = eidField.getText();
        if (eidText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Employee ID", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Return without deleting staff if Employee ID is empty
        }
        
        // Show confirmation dialog
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this staff?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM employee WHERE eid = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, eidText);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Staff deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete staff");
                }
                stmt.close();
                updateTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting staff: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            // Do nothing if user cancels the deletion
        }
    }

    private void updateTable() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            tableModel.setRowCount(0); // Clear the table
            while (rs.next()) {
                int eid = rs.getInt("eid");
                String name = rs.getString("ename");
                String designation = rs.getString("designation");
                String phno = rs.getString("phno");
                int salary = rs.getInt("salary");
                String address = rs.getString("address");
                String village = rs.getString("e_vid");

                tableModel.addRow(new Object[]{eid, name, designation, phno, salary, address,village});
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating table: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void search() {
        try {
            String selectedCriteria = (String) searchCriteriaComboBox.getSelectedItem();
            if(selectedCriteria == null || selectedCriteria.isEmpty()) {
                // If no criteria is selected, return without performing the search
                return;
            }
            
            String searchData = searchField.getText();
            if(searchData == null || searchData.isEmpty()) {
                // If no search data is entered, return without performing the search
                return;
            }

            String sql = "SELECT * FROM employee WHERE ";

            switch(selectedCriteria.toLowerCase()) {
                case "employee id":
                    sql += "eid LIKE ?";
                    break;
                case "name":
                    sql += "ename LIKE ?";
                    break;
                case "designation":
                    sql += "designation LIKE ?";
                    break;
                case "phone number":
                    sql += "phno LIKE ?";
                    break;
                case "salary":
                    sql += "salary LIKE ?";
                    break;
                case "address":
                    sql += "address LIKE ?";
                    break;
                case "village":
                    sql += "e_vid LIKE ?";
                    break;
                default:
                    // Handle the case when invalid criteria is selected
                    JOptionPane.showMessageDialog(this, "Invalid search criteria!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchData + "%");
            ResultSet rs = stmt.executeQuery();
            tableModel.setRowCount(0); // Clear the table
            while (rs.next()) {
                int eid = rs.getInt("eid");
                String name = rs.getString("ename");
                String designation = rs.getString("designation");
                String phno = rs.getString("phno");
                int salary = rs.getInt("salary");
                String address = rs.getString("address");
                String village = rs.getString("e_vid");

                tableModel.addRow(new Object[]{eid, name, designation, phno, salary, address,village});
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
         
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StaffData::new);
    }
    
    // Custom table model to allow editing for certain columns
    private class MyTableModel extends DefaultTableModel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }
        
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            // Allowing editing for certain columns
            return columnIndex == 0 || columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 4 || columnIndex == 5 || columnIndex == 6
                    ? String.class : super.getColumnClass(columnIndex);
        }
    }
}
