package MINI_PROJECT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class performPayment {
    private int farmerId;
    public performPayment(int farmerId) {
        this.farmerId = farmerId;
    }

    public void executePayment() {
        System.out.println("Executing payment for farmer ID: " + farmerId);
        List<Bill> bills = getAllBillsForFarmer(farmerId);
        if (bills.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No bills found for the farmer.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("No bills found for farmer ID: " + farmerId);
            return;
        }

        BillSelectionWindow billSelectionWindow = new BillSelectionWindow(bills);
        billSelectionWindow.setVisible(true);

        billSelectionWindow.addPaymentListener(selectedBills -> {
            for (Bill bill : selectedBills) {
                BigDecimal amount = bill.getAmount();
                boolean updateSuccessful = creditAmountToBalance(farmerId, amount.negate());
                if (updateSuccessful) {
                    System.out.println("Payment successful for bill ID: " + bill.getId() + ", Amount: " + amount);
                    generateReceiptAndDeleteBill(bill.getId(), amount);
                    generateReceiptAndCreditBill(farmerId, amount);
                    boolean creditSuccessful = creditAmountToBalance(farmerId, amount);
                    if (creditSuccessful) {
                        System.out.println("Receipt amount credited to farmer's balance.");
                    } else {
                        System.out.println("Failed to credit receipt amount to farmer's balance.");
                    }
                } else {
                    System.out.println("Failed to update balance for bill ID: " + bill.getId());
                }
            }
        });
    }

    private boolean creditAmountToBalance(int farmerId, BigDecimal amount) {
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement("UPDATE farmer SET balance = balance + ? WHERE id = ?")) {
            stmt.setBigDecimal(1, amount);
            stmt.setInt(2, farmerId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            handleSQLException(e, "Error updating balance for farmer ID: " + farmerId);
            return false;
        }
    }

    private boolean deleteBill(int billId) {
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM bill WHERE bill_id = ?")) {
            stmt.setInt(1, billId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            handleSQLException(e, "Error deleting bill ID: " + billId);
            return false;
        }
    }

    private Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
    }

    private void handleSQLException(SQLException e, String errorMessage) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, errorMessage, "Database Error", JOptionPane.ERROR_MESSAGE);
    }

    private List<Bill> getAllBillsForFarmer(int farmerId) {
        List<Bill> bills = new ArrayList<>();
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement("SELECT bill_id, amount FROM bill WHERE farmer_id = ?")) {
            stmt.setInt(1, farmerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int billId = rs.getInt("bill_id");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    bills.add(new Bill(billId, amount));
                }
            }
        } catch (SQLException e) {
            handleSQLException(e, "Error fetching bills for farmer ID: " + farmerId);
        }
        return bills;
    }

    private void generateReceiptAndDeleteBill(int billId, BigDecimal amount) {
        String receipt = ReceiptGenerator.generateReceipt(amount, farmerId);
        ReceiptGenerator.displayReceipt(receipt); // Display receipt
        if (deleteBill(billId)) {
            System.out.println("Bill ID " + billId + " deleted after payment.");
        } else {
            System.out.println("Failed to delete bill ID " + billId + " after payment.");
        }
    }

    private void generateReceiptAndCreditBill(int farmerid, BigDecimal amount) {
        ReceiptGenerator.generateReceipt(amount, farmerId);
        if (creditAmountToBalance(farmerId, amount)) {
            System.out.println("Farmer ID " + farmerid + " Credited after payment.");
        } else {
            System.out.println("Failed to Credit bill ID " + farmerid + " after payment.");
        }
    }
}

class BillSelectionWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<Bill> billList;
    private JButton confirmButton;
    private PaymentListener paymentListener;

    public BillSelectionWindow(List<Bill> bills) {
        setTitle("Select Bills for Payment");
        setSize(400, 300); // Increased size for better visibility
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Increased border for spacing

        // Set background color to light blue
        panel.setBackground(new Color(173, 216, 230));

        // Use Garamond font for elegance
        Font garamondFont = new Font("Garamond", Font.PLAIN, 14);

        billList = new JList<>(bills.toArray(new Bill[0]));
        billList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(billList);
        panel.add(scrollPane, BorderLayout.CENTER);

        confirmButton = new JButton("Confirm Payment");
        // Set button font to Garamond
        confirmButton.setFont(garamondFont);
        confirmButton.addActionListener(e -> confirmPayment());
        panel.add(confirmButton, BorderLayout.SOUTH);

        add(panel);
    }

    private void confirmPayment() {
        List<Bill> selectedBills = billList.getSelectedValuesList();
        if (selectedBills.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select at least one bill.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (paymentListener != null) {
                paymentListener.onPaymentConfirmed(selectedBills);
                dispose();
            }
        }
    }

    public void addPaymentListener(PaymentListener listener) {
        this.paymentListener = listener;
    }
}

class Bill {
    private int id;
    private BigDecimal amount;

    public Bill(int id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Bill ID: " + id + ", Amount: ₹" + amount;
    }
}

interface PaymentListener {
    void onPaymentConfirmed(List<Bill> selectedBills);
}
