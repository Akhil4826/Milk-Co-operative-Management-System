package MINI_PROJECT;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Staff extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel nameLabel, designationLabel, eidLabel, phnoLabel, salaryLabel, addressLabel, searchLabel;
    private JTextField nameField, designationField, eidField, phnoField, salaryField, addressField, searchField;
    private Connection conn;
    private JTable staffTable;
    private MyTableModel tableModel;
    private JComboBox<String> searchCriteriaComboBox;

    public Staff() {
        setTitle("Staffd Data");
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

        int textFieldWidth = 350;
        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Roboto", Font.BOLD, 12));
        editButton.setBounds(100, 10, 65, 30);
        editButton.setBackground(Color.GREEN);
        editButton.addActionListener(e -> {
            int selectedRow = staffTable.getSelectedRow();
            if (selectedRow != -1) { // Check if a row is selected
                // Retrieve data from the selected row and populate the text fields
                eidField.setText(staffTable.getValueAt(selectedRow, 0).toString());
                nameField.setText(staffTable.getValueAt(selectedRow, 1).toString());
                designationField.setText(staffTable.getValueAt(selectedRow, 2).toString());
                phnoField.setText(staffTable.getValueAt(selectedRow, 3).toString());
                salaryField.setText(staffTable.getValueAt(selectedRow, 4).toString());
                addressField.setText(staffTable.getValueAt(selectedRow, 5).toString());
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        contentPane.add(editButton);

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

        String[] searchCriteria = {"Employee ID", "Name", "Designation", "Phone Number", "Salary", "Address"};

        searchCriteriaComboBox = new JComboBox<>(searchCriteria);
        searchCriteriaComboBox.setFont(textFieldFont);
        searchCriteriaComboBox.setBounds(1265, 350, 150, 30);
        add(searchCriteriaComboBox);

        JButton cancelButton = new JButton("Back");
        cancelButton.setFont(new Font("Roboto", Font.BOLD, 12));

        cancelButton.setBounds(10, 10, 65, 30);
        cancelButton.setBackground(Color.YELLOW);
        cancelButton.addActionListener(e -> dispose());
        contentPane.add(cancelButton);

        
        String[] columnNames = {"Employee ID", "Name", "Designation", "Phone Number", "Salary", "Address"};
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
                tableModel.addRow(new Object[]{eid, name, designation, phno, salary, address});
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
                tableModel.addRow(new Object[]{eid, name, designation, phno, salary, address});
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Staff::new);
    }

    // Custom table model to allow editing for certain columns
    private class MyTableModel extends DefaultTableModel {
        private static final long serialVersionUID = 1L;

        public MyTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            // Allowing editing for certain columns
            return columnIndex == 0 || columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 4 || columnIndex == 5
                    ? String.class : super.getColumnClass(columnIndex);
        }
    }
}
