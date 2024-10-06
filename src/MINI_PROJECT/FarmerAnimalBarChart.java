package MINI_PROJECT;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class FarmerAnimalBarChart extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FarmerAnimalBarChart() {
        setTitle("Farmers vs Animals Data Visualization");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/milk", "root", "root@123");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT YEAR(reg_date) AS year, COUNT(animalID) AS total_animals " +
                    "FROM an_details " +
                    "GROUP BY YEAR(reg_date)");

            while (resultSet.next()) {
                int year = resultSet.getInt("year");
                int numAnimals = resultSet.getInt("total_animals");

                // Add the data to the dataset
                dataset.addValue(numAnimals, "Animals", String.valueOf(year));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to retrieve data from the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Farmers vs Animals",
                "Year",
                "Number of Animals",
                dataset
        );

        CategoryPlot barPlot = (CategoryPlot) barChart.getPlot();

        // Customize the bar renderer
        BarRenderer renderer = (BarRenderer) barPlot.getRenderer();
        renderer.setItemMargin(0.1);

        ChartPanel barChartPanel = new ChartPanel(barChart);
        mainPanel.add(barChartPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

	 public static void showFarmersData() {
		        SwingUtilities.invokeLater(() -> {
		            FarmerAnimalBarChart dataVisualization = new FarmerAnimalBarChart();
		            dataVisualization.setVisible(true);
	        });
	    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FarmerAnimalBarChart dataVisualization = new FarmerAnimalBarChart();
            dataVisualization.setVisible(true);
        });
    }
}