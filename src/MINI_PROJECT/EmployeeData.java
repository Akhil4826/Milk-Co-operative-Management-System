package MINI_PROJECT;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class EmployeeData {

    public void displayEmployeeData(int employeeId) {
        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3306/milk"; // Change to your database URL
        String username = "root"; // Change to your database username
        String password = "root@123"; // Change to your database password

        // SQL query to retrieve employee data
        String query = "SELECT * FROM employee WHERE eid = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the employee ID parameter
            pstmt.setInt(1, employeeId);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Create a table model
            DefaultTableModel model = new DefaultTableModel() {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public Class<?> getColumnClass(int columnIndex) {
                    return String.class; // Set column class to String
                }
            };

            // Add columns to the table model
            model.addColumn("Employee ID");
            model.addColumn("Employee Name");
            model.addColumn("Phone Number");
            model.addColumn("Salary");
            model.addColumn("Designation");
            model.addColumn("Address");
            model.addColumn("Employee Village ID");

            // Populate the table model with data from the result set
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("eid"),
                        rs.getString("ename"),
                        rs.getString("phno"),
                        rs.getInt("salary"),
                        rs.getString("designation"),
                        rs.getString("address"),
                        rs.getInt("e_vid")
                });
            }

            // Create a JTable with the table model
            JTable table = new JTable(model) {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    Component comp = super.prepareRenderer(renderer, row, column);
                    // Set alternate row background color to light blue
                    if (isRowSelected(row)) {
                        comp.setBackground(Color.YELLOW); // Set selected row background color to yellow
                    } else if (row % 2 == 0) {
                        comp.setBackground(new Color(0xE1F5FE)); // Set even row background color to a light shade of blue
                    } else {
                        comp.setBackground(Color.WHITE); // Set odd row background color to white
                    }
                    return comp;
                }
            };

            // Set table properties
            table.setRowHeight(30); // Set row height
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing of columns

            // Display the table in a scrollable pane
            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Employee Data", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        EmployeeData employeeData = new EmployeeData();
        employeeData.displayEmployeeData(2); // Pass the employee ID to display data
    }

    // Method to handle displaying complete information of the logged-in employee
    public void loggedInEmployeeData() {
        // Implement this method to display employee data
    }
}
