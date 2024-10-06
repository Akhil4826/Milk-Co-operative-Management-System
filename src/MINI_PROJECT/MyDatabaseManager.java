package MINI_PROJECT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyDatabaseManager {
    private Connection connection;
    String databaseURL;
    String username;
    String password;

    MyDatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyDatabaseManager dbManager = new MyDatabaseManager();
        dbManager.displayMostvillageMilk();
    }

    private List<Village> getAllVillages() {
        List<Village> villages = new ArrayList<>();
        // Code to fetch villages from the database and populate the list
        // For demonstration purposes, I'm adding some villages
        villages.add(new Village(1, "Village 1"));
        villages.add(new Village(2, "Village 2"));
        villages.add(new Village(3, "Village 3"));
        return villages;
    }

    private int getVillageIndex(List<Village> villages, int villageID) {
        for (int i = 0; i < villages.size(); i++) {
            if (villages.get(i).getID() == villageID) {
                return i;
            }
        }
        return -1; // Village not found
    }

    void displayMostvillageMilk() {
        List<Village> villages = getAllVillages();

        if (villages.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "No villages found.",
                    "Milk Achievements",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int[] totalMilkPerVillage = new int[villages.size()];

        String query = "SELECT f_vid, SUM(quantity) FROM daily_data_entry JOIN farmer ON daily_data_entry.fid = farmer.id GROUP BY f_vid";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int villageID = resultSet.getInt("f_vid");
                int totalMilk = resultSet.getInt("SUM(quantity)");
                int index = getVillageIndex(villages, villageID);
                if (index!= -1) {
                    totalMilkPerVillage[index] += totalMilk;
                }
            }

            int maxMilkIndex = 0;
            int maxMilk = 0;
            for (int i = 0; i < totalMilkPerVillage.length; i++) {
                if (totalMilkPerVillage[i] > maxMilk) {
                    maxMilk = totalMilkPerVillage[i];
                    maxMilkIndex = i;
                }
            }

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Village Name");
            model.addColumn("Total Milk Collected");

            model.addRow(new Object[]{villages.get(maxMilkIndex).getName(), maxMilk});

            JTable table = new JTable(model);
            JFrame frame = new JFrame("Milk Achievements");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(new JScrollPane(table));
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setVisible(boolean b) {
        // TODO Auto-generated method stub
    }
}

class Village {
    private int ID;
    private String name;

    public Village(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}