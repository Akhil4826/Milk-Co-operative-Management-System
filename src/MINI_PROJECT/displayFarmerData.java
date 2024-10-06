package MINI_PROJECT;



import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

public class displayFarmerData extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable farmerTable;
    private DefaultTableModel tableModel;
    private float transparency = 0.8f;
    private BufferedImage backgroundImage;
    private int loggedInFarmerId;

    public displayFarmerData(int loggedInFarmerId) {
        this.loggedInFarmerId = loggedInFarmerId;

        setTitle("Farmer Table");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        createTableModel();
        farmerTable = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
                super.paintComponent(g2d);
                g2d.dispose();
            }
        };
        farmerTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        farmerTable.setForeground(Color.BLACK);
        farmerTable.setBackground(Color.WHITE);
        farmerTable.setGridColor(Color.GRAY);
        farmerTable.setRowHeight(30);
        farmerTable.getColumnModel().getColumn(0).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(farmerTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JPanel panel = new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose the window when back button is clicked
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);

        setVisible(true);
    }

    private void createTableModel() {
        String[] columnNames = {"ID", "Farmer Name", "Phone", "Farmer VillageID", "Milk Type", "Milk Price", "Registration Date", "Animal ID", "Balance"};

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123")) {
            String query = "SELECT * FROM farmer WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, loggedInFarmerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            tableModel = new DefaultTableModel(columnNames, 0);

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new displayFarmerData(2); // Example: Pass the logged-in farmer ID here
            }
        });
    }
}
