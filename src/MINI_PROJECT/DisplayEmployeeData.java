package MINI_PROJECT;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayEmployeeData extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String url;
    private final String username;
    private final String password;

    public DisplayEmployeeData() {
        this.url = "jdbc:mysql://localhost/milk";
        this.username = "root";
        this.password = "root@123";
    }

    // Method to display employees grouped by address
    public void displayEmployeeDataByVillage() {
        Map<String, List<String>> employeesByAddress = new HashMap<>();
        String query = "SELECT ename, address, designation FROM employee";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("ename");
                String address = resultSet.getString("address");
                String designation = resultSet.getString("designation");

                String employeeInfo = name + " - " + designation;

                if (!employeesByAddress.containsKey(address)) {
                    employeesByAddress.put(address, new ArrayList<>());
                }
                employeesByAddress.get(address).add(employeeInfo);
            }

            // Display employees grouped by address
            JFrame frame = new JFrame("Employee Data by Village");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new GridLayout(0, 1));

            // Array of colors for different villages
            Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN};

            int colorIndex = 0; // Index to select color from the array

            for (Map.Entry<String, List<String>> entry : employeesByAddress.entrySet()) {
                JPanel panel = new JPanel(new BorderLayout());
                JLabel label = new JLabel("Address: " + entry.getKey());
                label.setForeground(Color.WHITE); // Set font color
                panel.add(label, BorderLayout.NORTH);

                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                
                // Set color for the panel
                panel.setBackground(colors[colorIndex % colors.length]);

                for (String employeeInfo : entry.getValue()) {
                    textArea.append(employeeInfo + "\n");
                }
                JScrollPane scrollPane = new JScrollPane(textArea);
                panel.add(scrollPane, BorderLayout.CENTER);

                frame.add(panel);

                colorIndex++; // Move to the next color for the next village
            }

            frame.setSize(1000, 700); // Set manual size of the window
            frame.setLocationRelativeTo(null); // Open the window at the center of the screen
            
            // Adding key listener to close the window on pressing Escape key
            frame.getRootPane().registerKeyboardAction(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); // Close the window
                }
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
            
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DisplayEmployeeData display = new DisplayEmployeeData();
        display.displayEmployeeDataByVillage();
    }
}
