package MINI_PROJECT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class EmployeeVisualization extends JFrame {

    private static final long serialVersionUID = 1L;

    public EmployeeVisualization() {
        // Set window properties
        setTitle("Employees by Village Visualization");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create dataset
        DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();

        // Connect to the database and retrieve data
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/milk", "root", "root@123");

            // Create a statement with a scrollable ResultSet
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Query to get number of employees for each village and total number of employees
            ResultSet resultSet = statement.executeQuery(
                    "SELECT e.e_vid AS village, COUNT(*) AS employee_count FROM employee e GROUP BY e.e_vid");

            // Retrieve the total number of employees
            int totalEmployees = 0;
            while (resultSet.next()) {
                totalEmployees += resultSet.getInt("employee_count");
            }

            // Move the cursor back to before the first row
            resultSet.beforeFirst(); // Reset cursor to the beginning

            // Calculate the percentage of employees for each village and add to the dataset
            while (resultSet.next()) {
                String village = resultSet.getString("village");
                int employeeCount = resultSet.getInt("employee_count");
                double percentage = (double) employeeCount / totalEmployees * 100; // Calculate percentage
                dataset.setValue(village + " - " + getAddressForVillage(village), percentage); // Add village and address
                                                                                                // to label
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create the pie chart
        JFreeChart chart = ChartFactory.createPieChart("Percentage of Employees by Village", // Chart title
                dataset, // Dataset
                true, // Include legend
                true, // Include tooltips
                false // Do not include URLs
        );

        // Customize the plot to set a classy color for each section
        PiePlot<?> plot = (PiePlot<?>) chart.getPlot();
        for (int i = 0; i < dataset.getItemCount(); i++) {
            Color color = Color.getHSBColor((float) i / dataset.getItemCount(), 0.7f, 0.7f);
            plot.setSectionPaint(dataset.getKey(i).toString(), color);
        }

        // Create a chart panel to display the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        mainPanel.add(chartPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(mainPanel);

        // Create and configure a button to open EmployeeVisualization data
        JButton employeeButton = new JButton("Show Employee Data");
        employeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display employee data according to village in a beautiful manner
            	DisplayEmployeeData DisplayEmployeeData = new DisplayEmployeeData();
            	DisplayEmployeeData.displayEmployeeDataByVillage();           }
        });

        // Add the button to the main panel
        mainPanel.add(employeeButton, BorderLayout.SOUTH);
    }

    // Method to get the address for a given village from the database
    private String getAddressForVillage(String village) {
        // Connect to the database and retrieve the address
        String address = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/milk", "root", "root@123");
            Statement statement = connection.createStatement();
            String query = "SELECT address FROM employee WHERE e_vid = '" + village + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                address = resultSet.getString("address");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (address != null) ? address : "Address not found for " + village;
    }

    // Method to show EmployeeVisualization data
    public static void showEmployeeData() {
        SwingUtilities.invokeLater(() -> {
            EmployeeVisualization employeeByVillageVisualization = new EmployeeVisualization();
            employeeByVillageVisualization.setVisible(true);
        });
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeVisualization employeeByVillageVisualization = new EmployeeVisualization();
            employeeByVillageVisualization.setVisible(true);
        });
    }
}
