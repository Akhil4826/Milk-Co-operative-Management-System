package MINI_PROJECT;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ManagersDisplay extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManagersDisplay() {
        setTitle("Managers");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"Manager ID", "Name", "Designation", "Phone Number", "Salary", "Address"};

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        String sql = "SELECT eid, ename, designation, phno, salary, address FROM employee WHERE designation = 'Manager'";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] rowData = {rs.getInt("eid"), rs.getString("ename"), rs.getString("designation"), rs.getString("phno"), rs.getInt("salary"), rs.getString("address")};
                tableModel.addRow(rowData);
            }

            JTable table = new JTable(tableModel);
            table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
            table.getTableHeader().setBackground(new Color(32, 136, 203));
            table.getTableHeader().setForeground(Color.WHITE);
            table.setGridColor(Color.LIGHT_GRAY);
            table.setSelectionBackground(new Color(176, 196, 222));

            JScrollPane scrollPane = new JScrollPane(table);
            getContentPane().add(scrollPane, BorderLayout.CENTER);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ManagersDisplay::new);
    }
}
