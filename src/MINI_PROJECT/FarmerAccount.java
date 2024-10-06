package MINI_PROJECT;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class FarmerAccount extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int loggedInFarmerID;
    private JTable receiptTable;
    private DefaultTableModel tableModel;

    public FarmerAccount(int loggedInFarmerID) {
        this.loggedInFarmerID = loggedInFarmerID;
        setTitle("Farmer Account");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Current Balance Panel
        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel balanceLabel = new JLabel("Current Balance: ₹" + getCurrentBalance());
        balancePanel.add(balanceLabel);
        panel.add(balancePanel, BorderLayout.NORTH);

        // Receipt History Panel
        JPanel receiptPanel = new JPanel(new BorderLayout());
        JLabel receiptLabel = new JLabel("Receipt History");
        receiptLabel.setFont(new Font("Arial", Font.BOLD, 16));
        receiptPanel.add(receiptLabel, BorderLayout.NORTH);
        
        // Table Model for Receipt History
        String[] columnNames = {"Receipt ID", "Date", "Amount"};
        tableModel = new DefaultTableModel(columnNames, 0);
        receiptTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(receiptTable);
        receiptPanel.add(scrollPane, BorderLayout.CENTER);
        panel.add(receiptPanel, BorderLayout.CENTER);

        // Fetch Receipts Button
        JButton fetchReceiptsButton = new JButton("Fetch Receipts");
        fetchReceiptsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchReceipts();
            }
        });
        panel.add(fetchReceiptsButton, BorderLayout.SOUTH);

        add(panel);
    }

    private BigDecimal getCurrentBalance() {
        // Fetch and return current balance from database for the logged in farmer
        BigDecimal balance = BigDecimal.ZERO;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
             PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM farmer WHERE id = ?")) {
            stmt.setInt(1, loggedInFarmerID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    balance = rs.getBigDecimal("balance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    private void fetchReceipts() {
        // Fetch and display receipt history from the bills table for the logged in farmer
        tableModel.setRowCount(0); // Clear existing table data
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
             PreparedStatement stmt = conn.prepareStatement("SELECT reciept_id, dt, amount FROM reciept WHERE farmer_id = ?")) {
            stmt.setInt(1, loggedInFarmerID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String receiptID = rs.getString("reciept_id");
                    String date = rs.getString("dt");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    Object[] rowData = {receiptID, date, amount};
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int loggedInFarmerID = 4;
        SwingUtilities.invokeLater(() -> {
            FarmerAccount farmerAccount = new FarmerAccount(loggedInFarmerID);
            farmerAccount.setVisible(true);
        });
    }
}
