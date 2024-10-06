package MINI_PROJECT;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AnimalInfoData extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/milk";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root@123";

    private JTextField animalIDField;
    private JTextField fidField;
    private JTextField animalTypeField;
    private JTextField regDateField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton backButton; // New back button
    private JTable animalTable;
    private DefaultTableModel tableModel;

    public AnimalInfoData() {
        setTitle("Animal Info Data");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        animalIDField = new JTextField(15);
        fidField = new JTextField(15);
        animalTypeField = new JTextField(25);
        regDateField = new JTextField(15);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back"); // Initialize back button

        addButton.setBackground(Color.GREEN);
        editButton.setBackground(Color.YELLOW);
        deleteButton.setBackground(Color.RED);
        addButton.setForeground(Color.BLACK);
        editButton.setForeground(Color.BLACK);
        deleteButton.setForeground(Color.BLACK);

        String[] columnNames = {"Animal ID", "Farmer ID", "Animal Type", "Registration Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        animalTable = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        animalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        animalTable.setRowHeight(30);
        animalTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        animalTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = animalTable.getSelectedRow();
                animalIDField.setText(tableModel.getValueAt(row, 0).toString());
                fidField.setText(tableModel.getValueAt(row, 1).toString());
                animalTypeField.setText(tableModel.getValueAt(row, 2).toString());
                regDateField.setText(tableModel.getValueAt(row, 3).toString());
            }
        });

        animalTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(173, 216, 230));
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(animalTable);

        JPanel inputPanel = new JPanel(new GridLayout(1, 1));
        inputPanel.add(new JLabel("<html><font color='blue'>Animal ID:</font></html>"));
        inputPanel.add(animalIDField);
        inputPanel.add(new JLabel("<html><font color='blue'>Farmer ID:</font></html>"));
        inputPanel.add(fidField);
        inputPanel.add(new JLabel("<html><font color='blue'>Animal Type:</font></html>"));
        inputPanel.add(animalTypeField);
        inputPanel.add(new JLabel("<html><font color='blue'>Registration Date (YYYY-MM-DD):</font></html>"));
        inputPanel.add(regDateField);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);
        inputPanel.add(backButton); // Add back button to input panel

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> addAnimalToDatabase());
        editButton.addActionListener(e -> editAnimalInDatabase());
        deleteButton.addActionListener(e -> deleteAnimalFromDatabase());
        backButton.addActionListener(e -> dispose()); // Dispose the window on back button click

        setVisible(true);

        updateTable();
    }

    private void addAnimalToDatabase() {
        String animalID = animalIDField.getText();
        String fid = fidField.getText();
        String animalType = animalTypeField.getText();
        String regDate = regDateField.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = conn.prepareStatement("INSERT INTO an_details VALUES (?, ?, ?, ?)");
            statement.setString(1, animalID);
            statement.setString(2, fid);
            statement.setString(3, animalType);
            statement.setString(4, regDate);
            statement.executeUpdate();
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(this, "Animal added successfully!");
            updateTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding animal to database: " + ex.getMessage());
        }
    }

    public void editAnimalInDatabase() {
        String animalID = animalIDField.getText();
        String newAnimalID = JOptionPane.showInputDialog("Enter new Animal ID:");

        if (newAnimalID != null && !newAnimalID.isEmpty()) { // Check if the input is valid
            try {
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement statement = conn.prepareStatement("UPDATE an_details SET animalID = ?, fid = ?, animalTYPE = ?, reg_date = ? WHERE animalID = ?");
                statement.setString(1, newAnimalID); // Use the new Animal ID
                statement.setString(2, fidField.getText());
                statement.setString(3, animalTypeField.getText());
                statement.setString(4, regDateField.getText());
                statement.setString(5, animalID);
                int affectedRows = statement.executeUpdate();
                statement.close();
                conn.close();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Animal details updated successfully!");
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(this, "No animal found with the given ID: " + animalID);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating animal details: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Animal ID!");
        }
    }

    public void deleteAnimalFromDatabase() {
        String animalID = animalIDField.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM an_details WHERE animalID = ?");
            statement.setString(1, animalID);
            int affectedRows = statement.executeUpdate();
            statement.close();
            conn.close();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Animal deleted successfully!");
                updateTable();
            } else {
                JOptionPane.showMessageDialog(this, "No animal found with the given ID: " + animalID);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting animal: " + ex.getMessage());
        }
    }

    private void updateTable() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM an_details");
            tableModel.setRowCount(0);
            while (resultSet.next()) {
                String animalID = resultSet.getString("animalID");
                String fid = resultSet.getString("fid");
                String animalType = resultSet.getString("animalTYPE");
                String regDate = resultSet.getString("reg_date");
                tableModel.addRow(new Object[]{animalID, fid, animalType, regDate});
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating table: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AnimalInfoData::new);
    }
}
