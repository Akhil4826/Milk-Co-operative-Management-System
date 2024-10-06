package MINI_PROJECT;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class ReceiptGenerator {
    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal("100.50"); // Example amount
        int farmerId = 123; // Example farmer ID
        String receipt = generateReceipt(amount, farmerId);
        displayReceipt(receipt);
    }

    public static String generateReceipt(BigDecimal amount, int farmerId) {
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("<html lang=\"en\"><head>");
        receiptBuilder.append("<meta charset=\"UTF-8\">");
        receiptBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        receiptBuilder.append("<title>Receipt</title>");
        receiptBuilder.append("<style>");
        receiptBuilder.append("body {font-family: Arial, sans-serif; background-image: url('https://seeklogo.com/images/D/Dairy_Farmers-logo-1CC042C645-seeklogo.com.png'); background-size: cover; background-repeat: no-repeat; background-position: center;}");
        receiptBuilder.append(".container {max-width: 600px; margin: 0 auto; padding: 20px; background-color: rgba(255, 255, 255, 0.8); border: 1px solid #ccc; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);}");
        receiptBuilder.append("h2 {color: #007bff; text-align: center;}");
        receiptBuilder.append("p {margin-bottom: 10px;}");
        receiptBuilder.append("</style>");
        receiptBuilder.append("</head>");
        receiptBuilder.append("<body>");
        receiptBuilder.append("<div class='container'>");
        receiptBuilder.append("<h2>Receipt</h2>");
        receiptBuilder.append("<p>Date: ").append(getCurrentDate()).append("</p>");
        receiptBuilder.append("<hr>");
        receiptBuilder.append("<p>Farmer ID: ").append(farmerId).append("</p>");
        receiptBuilder.append("<p>Amount: ₹").append(amount).append("</p>");
        receiptBuilder.append("<p>Payment Method: DBT</p>");
        receiptBuilder.append("<hr>");
        receiptBuilder.append("<p style='text-align: center; color: grey;'>Thank you for trusting us!</p>");
        receiptBuilder.append("<p style='text-align: center; color: grey;'>For any inquiries, please contact us at: 953-577-5545</p>");
        receiptBuilder.append("</div>");
        receiptBuilder.append("<br>");
        receiptBuilder.append("</body></html>");
        return receiptBuilder.toString();
    }

    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static void displayReceipt(String htmlContent) {
        JFrame frame = new JFrame("Receipt");
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);
        textPane.setText(htmlContent);

        JScrollPane scrollPane = new JScrollPane(textPane);
        frame.add(scrollPane);

        JButton saveButton = new JButton("Save Receipt");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File htmlFile = saveToHTML(htmlContent);
                    openFile(htmlFile); // Open the saved file
                    JOptionPane.showMessageDialog(null, "Receipt saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving receipt!", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        frame.add(saveButton, BorderLayout.SOUTH);

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private static File saveToHTML(String content) throws IOException {
        File htmlFile = new File("Receipt.html");
        FileWriter writer = new FileWriter(htmlFile);
        writer.write(content);
        writer.close();
        return htmlFile;
    }

    private static void openFile(File file) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) {
            desktop.open(file);
        }
    }
}
