package MINI_PROJECT;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;
import javax.imageio.ImageIO;

public class FarmerTable extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable farmerTable;
    private DefaultTableModel tableModel;
    private float transparency = 0.9f; // Initial transparency value
    private BufferedImage backgroundImage; // Background image
    private JComboBox<SearchCriterion> searchComboBox;
    private JTextField searchText;

    public FarmerTable() {
        setTitle("Farmer Table");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\2.png")); // Load the background image
        } catch (IOException e) {
            e.printStackTrace();
        }

        createTableModel();
        farmerTable = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                // Make the table transparent
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
                super.paintComponent(g2d);
                g2d.dispose();
            }
        };
        farmerTable.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Changed font and font size for table
        farmerTable.setForeground(Color.BLACK); // Changed font color for table
        farmerTable.setBackground(Color.WHITE); // Changed background color for table
        farmerTable.setGridColor(Color.GRAY); // Changed grid color
        farmerTable.setRowHeight(50); // Increased row height for better readability

        // Set column widths
        farmerTable.getColumnModel().getColumn(6).setPreferredWidth(170); // Adjust column width as needed

        // Create a slider for adjusting transparency
        JSlider transparencySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 79); // Range: 0-100, Initial value: 50
        transparencySlider.setMajorTickSpacing(10);
        transparencySlider.setPaintTicks(true);
        transparencySlider.setPaintLabels(true);
        transparencySlider.addChangeListener(e -> {
            transparency = transparencySlider.getValue() / 100f; // Update transparency value
            repaint(); // Repaint the frame to apply transparency changes
        });

        // Initialize searchComboBox with SearchCriterion objects
        searchComboBox = new JComboBox<>(new SearchCriterion[]{
                new SearchCriterion("ID", "id"),
                new SearchCriterion("Farmer Name", "fname"),
                new SearchCriterion("Phone", "ph"),
                new SearchCriterion("Farmer VillageID", "f_vid"),
                new SearchCriterion("Milk Type", "milk_type"),
                new SearchCriterion("Milk Price", "milk_price"),
                new SearchCriterion("Registration Date", "reg_date"),
                new SearchCriterion("Animal ID", "animalID"),
                new SearchCriterion("Balance", "balance")
        });

        searchText = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        JButton backButton = new JButton("Back"); // Added back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose the window when back button is clicked
            }
        });

        // Apply modern UI colors to buttons
        searchButton.setBackground(new Color(51, 153, 255)); // Blue color for search button
        backButton.setBackground(new Color(255, 102, 102)); // Red color for back button
        searchButton.setForeground(Color.WHITE); // White text color for search button
        backButton.setForeground(Color.WHITE); // White text color for back button
        JPanel searchPanel = new JPanel();
        JLabel searchByLabel = new JLabel("Search By:");
        searchByLabel.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Set font size to 18
        searchPanel.add(searchByLabel);
        searchPanel.add(searchComboBox);

        JLabel searchTextLabel = new JLabel("Search Text:");
        searchTextLabel.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Set font size to 18
        searchPanel.add(searchTextLabel);

        searchText = new JTextField(24);
        searchText.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Set font size to 18
        searchPanel.add(searchText);

        searchPanel.add(searchButton);
        searchPanel.add(backButton); // Added back button to the search panel

        JScrollPane scrollPane = new JScrollPane(farmerTable);
        scrollPane.setOpaque(false); // Make the scroll pane translucent
        scrollPane.getViewport().setOpaque(false); // Make the viewport translucent

        JPanel panel = new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = 1L;

            @Override	
            protected void paintComponent(Graphics g) {
                // Draw the background image
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(transparencySlider, BorderLayout.SOUTH);
        panel.add(searchPanel, BorderLayout.NORTH);

        setContentPane(panel);

        setVisible(true);

        // Custom renderer for table header
        JTableHeader header = farmerTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Increased font size for table column names
    }

    private void createTableModel() {
        String[] columnNames = {"ID", "Farmer Name", "Phone", "Farmer VillageID", "Milk Type", "Milk Price", "Registration Date", "Animal ID", "Balance"};

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM farmer");
            ResultSetMetaData metaData = resultSet.getMetaData();

            tableModel = new DefaultTableModel(columnNames, 0);

            Vector<Vector<Object>> rows = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rows.add(row);
            }

            for (Vector<Object> row : rows) {
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void search() {
        try {
            SearchCriterion selectedCriterion = (SearchCriterion) searchComboBox.getSelectedItem();
            if (selectedCriterion == null || selectedCriterion.getColumnName().isEmpty()) {
                // If no criterion is selected, return without performing the search
                return;
            }

            String searchData = searchText.getText();
            if (searchData == null || searchData.isEmpty()) {
                // If no search data is entered, return without performing the search
                return;
            }

            String sql = "SELECT * FROM farmer WHERE ";

            switch (selectedCriterion.getColumnName()) {
                case "id":
                case "f_vid":
                case "animalID":
                case "balance":
                    sql += selectedCriterion.getColumnName() + " = ?";
                    break;
                case "milk_price":
                    sql += "milk_price BETWEEN ? AND ?";
                    break;
                default:
                    sql += selectedCriterion.getColumnName() + " LIKE ?";
                    searchData = "%" + searchData + "%";
                    break;
            }

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
            PreparedStatement stmt = conn.prepareStatement(sql);
            if (selectedCriterion.getColumnName().equals("milk_price")) {
                String[] priceRange = searchData.split("-");
                stmt.setFloat(1, Float.parseFloat(priceRange[0].trim()));
                stmt.setFloat(2, Float.parseFloat(priceRange[1].trim()));
            } else {
                stmt.setString(1, searchData);
            }

            ResultSet rs = stmt.executeQuery();
            tableModel.setRowCount(0); // Clear the table
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getObject(i));
                }
                tableModel.addRow(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FarmerTable();
    }
}

class SearchCriterion {
    private String displayName;
    private String columnName;

    public SearchCriterion(String displayName, String columnName) {
        this.displayName = displayName;
        this.columnName = columnName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getColumnName() {
        return columnName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
