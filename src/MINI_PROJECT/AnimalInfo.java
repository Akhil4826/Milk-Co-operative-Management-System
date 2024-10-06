package MINI_PROJECT;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class AnimalInfo extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable animalTable;
    private DefaultTableModel tableModel;
    private float transparency = 0.5f; // Initial transparency value

    public AnimalInfo(int farmerId) {
        setTitle("Animal Info");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600); // Increased size for a bigger and more stylish table
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create a JPanel as the content pane to add background image
        JPanel backgroundPanel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\2.png");
                // Draw the background image
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Connect to the database and retrieve animal info
        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM an_details WHERE fid =" + farmerId);

            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                data.add(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        tableModel = new DefaultTableModel(data, columnNames);
        animalTable = new JTable(tableModel);

        // Set the table's opacity to false to make the background visible
        animalTable.setOpaque(false);
        animalTable.getTableHeader().setOpaque(false); // Make the table header transparent

        // Styling the table
        animalTable.setFont(new Font("Roboto", Font.PLAIN, 16)); // Set font to Roboto with size 16
        animalTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 18)); // Set header font with bold style
        animalTable.setRowHeight(40); // Set row height for a more spacious look

        // Set custom colors for table foreground (text color)
        animalTable.setForeground(Color.BLACK); // Set foreground (text) color to black

        // Create a slider for adjusting transparency
        JSlider transparencySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50); // Range: 0-100, Initial value: 50
        transparencySlider.setMajorTickSpacing(10);
        transparencySlider.setPaintTicks(true);
        transparencySlider.setPaintLabels(true);
        transparencySlider.addChangeListener(e -> {
            transparency = transparencySlider.getValue() / 100f; // Update transparency value
            repaint(); // Repaint the panel to apply transparency changes
        });

        // Add the table and slider to the background panel
        JScrollPane scrollPane = new JScrollPane(animalTable);
        scrollPane.setOpaque(false); // Make the scroll pane translucent
        scrollPane.getViewport().setOpaque(false); // Make the viewport translucent
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        backgroundPanel.add(transparencySlider, BorderLayout.SOUTH);

        // Add a back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose the window when the back button is clicked
            }
        });
        backgroundPanel.add(backButton, BorderLayout.NORTH);

        // Set the background panel as the content pane
        setContentPane(backgroundPanel);

        setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        // Apply transparency to the panel
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnimalInfo(1)); // Pass the farmer ID here
    }
}
