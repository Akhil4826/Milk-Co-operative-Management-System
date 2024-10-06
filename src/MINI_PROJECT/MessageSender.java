package MINI_PROJECT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MessageSender extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> receiverComboBox;
    private JTextArea messageTextArea;
    private JButton sendButton;
    private JButton displayChatsButton;
    private Map<String, ArrayList<String>> chatHistoryMap;
    private String loggedInUserId;
    private JTextArea chatArea;
    private boolean unreadMessage = false; // Flag for unread message

    public MessageSender(String loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
        setTitle("Modern Messaging Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        chatHistoryMap = new HashMap<>();

        // Set the background image
        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 348911474064643296L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\aug_9_01.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        JLabel receiverLabel = new JLabel("To:");
        receiverComboBox = new JComboBox<>();
        populateComboBoxes();

        topPanel.add(receiverLabel);
        topPanel.add(receiverComboBox);

        panel.add(topPanel, BorderLayout.NORTH);
        topPanel.setForeground(Color.ORANGE); // Set font color to orange
        receiverLabel.setForeground(Color.ORANGE); // Set font color to orange

        // Initialize JTextArea
        messageTextArea = new JTextArea();
        messageTextArea.setForeground(Color.ORANGE); // Set font color to orange
        messageTextArea.setFont(messageTextArea.getFont().deriveFont(Font.BOLD, 16)); // Set bold font with size 14

        // Enable text wrapping
        messageTextArea.setLineWrap(true);

        // Set the number of visible rows manually
        messageTextArea.setRows(2);

        // Set the number of columns
        messageTextArea.setColumns(1); // Set the number of columns as needed

        // Add JScrollPane to enable scrolling
        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);

        chatArea = new JTextArea();
        chatArea.setForeground(Color.ORANGE); // Set font color to orange
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageScrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton, BorderLayout.WEST); // Add the back button to the bottomPanel


        // Set custom icon for send button
        ImageIcon sendIcon = new ImageIcon("send_icon.png");
        sendButton = new JButton("Send", sendIcon);
        sendButton.setFont(sendButton.getFont().deriveFont(Font.BOLD, 12)); // Increase font size
        sendButton.setForeground(Color.ORANGE); // Change font color to orange
        bottomPanel.add(sendButton, BorderLayout.EAST);

        // Set custom icon for display chats button
        ImageIcon chatsIcon = new ImageIcon("chats_icon.png");
        displayChatsButton = new JButton("Chats", chatsIcon);
        displayChatsButton.setFont(displayChatsButton.getFont().deriveFont(Font.BOLD, 12)); // Increase font size
        displayChatsButton.setForeground(Color.ORANGE); // Change font color to orange
        bottomPanel.add(displayChatsButton, BorderLayout.WEST);

        panel.add(chatScrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
        setTransparent(panel); // Make the entire panel and its components transparent except for the background image
        sendButton.setBackground(new Color(255, 255, 255, 150)); // Set semi-transparent background for the sendButton
        displayChatsButton.setBackground(new Color(255, 255, 255, 150)); // Set semi-transparent background for the displayChatsButton
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        displayChatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayChats();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Instantiate and display EmployeeDetailsWindow
            }
        });

        receiverComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedContact = (String) receiverComboBox.getSelectedItem();
                if (selectedContact != null) {
                    String[] receiverInfo = selectedContact.split(" - ");
                    if (receiverInfo.length >= 2) {
                        String receiverId = receiverInfo[0];
                        updateChatHistory(receiverId);
                    }
                }
            }
        });

        setVisible(true);
        sendButton.setPreferredSize(new Dimension(100, 100));
        displayChatsButton.setPreferredSize(new Dimension(100, 100));
    }

    private void populateComboBoxes() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123")) {
            String query = "SELECT id, fname FROM farmer WHERE id != ? UNION SELECT eid, ename FROM employee WHERE eid != ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, loggedInUserId);
            preparedStatement.setString(2, loggedInUserId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                receiverComboBox.addItem(id + " - " + name);
                loadChatHistoryFromDatabase(id, name); // Pass name to loadChatHistoryFromDatabase
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while populating combo boxes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadChatHistoryFromDatabase(String userId, String userName) { // Modified to accept userName
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123")) {
            String query = "SELECT sender_id, receiver_id, message, sent_at FROM chat_history WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, loggedInUserId);
            preparedStatement.setString(2, userId);
            preparedStatement.setString(3, userId);
            preparedStatement.setString(4, loggedInUserId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String senderId = resultSet.getString("sender_id");
                String receiverId = resultSet.getString("receiver_id");
                String message = resultSet.getString("message");
                Timestamp timestamp = resultSet.getTimestamp("sent_at");

                String chatPartnerId = senderId.equals(loggedInUserId) ? receiverId : senderId;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedTimestamp = dateFormat.format(new Date(timestamp.getTime()));

                ArrayList<String> chatHistory = chatHistoryMap.getOrDefault(chatPartnerId, new ArrayList<>());
                chatHistory.add(userName + " (" + formattedTimestamp + "): " + message); // Modified to include userName and timestamp
                chatHistoryMap.put(chatPartnerId, chatHistory);

                // Set unread message flag if the received message is not from the current user
                if (!senderId.equals(loggedInUserId)) {
                    unreadMessage = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading chat history from database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void sendMessage() {
        String receiverDetails = (String) receiverComboBox.getSelectedItem();
        if (receiverDetails == null) {
            JOptionPane.showMessageDialog(this, "No receiver selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] receiverInfo = receiverDetails.split(" - ");
        if (receiverInfo.length < 2) {
            JOptionPane.showMessageDialog(this, "Invalid receiver format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String receiverId = receiverInfo[0];

        String message = messageTextArea.getText();
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a message to send!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/milk", "root", "root@123")) {
            String query = "INSERT INTO chat_history (sender_id, receiver_id, message) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, loggedInUserId);
            preparedStatement.setString(2, receiverId);
            preparedStatement.setString(3, message);

            preparedStatement.executeUpdate();

            ArrayList<String> senderChatHistory = chatHistoryMap.getOrDefault(loggedInUserId, new ArrayList<>());
            senderChatHistory.add("You: " + message); // Changed "Sender" to "You"
            chatHistoryMap.put(loggedInUserId, senderChatHistory);

            ArrayList<String> receiverChatHistory = chatHistoryMap.getOrDefault(receiverId, new ArrayList<>());
            receiverChatHistory.add(receiverInfo[1] + ": " + message); // Modified to include receiver's name
            chatHistoryMap.put(receiverId, receiverChatHistory);

            updateChatHistory(loggedInUserId);
            updateChatHistory(receiverId);
            JOptionPane.showMessageDialog(this, "Message sent successfully!");
            messageTextArea.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error sending message: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateChatHistory(String userId) {
        ArrayList<String> userChatHistory = chatHistoryMap.get(userId);

        chatArea.setText(""); // Clear chatArea before updating with new messages

        if (userChatHistory != null) {
            for (String message : userChatHistory) {
                chatArea.append(message + "\n");
            }
        }
    }

    private void displayChats() {
        JDialog dialog = new JDialog(this, "All Chats", true);
        JPanel panel = new JPanel(new BorderLayout());
        JTextPane allChatsArea = new JTextPane(); // Changed JTextArea to JTextPane for HTML formatting

        // Set the background image for the dialog
        // (You need to implement the background image setting here)

        // Iterate over the chat history map
        for (Map.Entry<String, ArrayList<String>> entry : chatHistoryMap.entrySet()) {
            String contactId = entry.getKey();
            ArrayList<String> chatHistory = entry.getValue();
            // Iterate over the messages for each contact
            for (String message : chatHistory) {
                // Split the message into sender and text parts
                String[] parts = message.split(": ", 2);
                String sender = parts[0];
                String text = parts[1];
                // Check if the message sender is the logged-in user
                if (contactId.equals(loggedInUserId)) {
                    // Format the message for the logged-in user and align right
                    SimpleAttributeSet leftAlign = new SimpleAttributeSet();
                    StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);

                    StyledDocument doc = allChatsArea.getStyledDocument();
                    try {
                        doc.insertString(doc.getLength(), "You: " + text + "\n", leftAlign);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Format the message for other contacts and align left
                    SimpleAttributeSet rightAlign = new SimpleAttributeSet();
                    StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);
                    StyledDocument doc = allChatsArea.getStyledDocument();
                    try {
                        doc.insertString(doc.getLength(), sender + ": " + text + "\n", rightAlign);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Create a scroll pane for the text area
        JScrollPane scrollPane = new JScrollPane(allChatsArea);
        // Add the scroll pane to the panel
        panel.add(scrollPane, BorderLayout.CENTER);
        // Add the panel to the dialog
        dialog.add(panel);
        // Set the size of the dialog
        dialog.setSize(400, 400);
        // Make the dialog visible
        dialog.setVisible(true);

        // Display notification if there is an unread message
        if (unreadMessage) {
            JOptionPane.showMessageDialog(this, "You have unread messages!", "Notification", JOptionPane.INFORMATION_MESSAGE);
            // Reset the flag after displaying the notification
            unreadMessage = false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MessageSender("1")); // Pass the logged in user's ID
    }

    // Function to make components transparent
    private void setTransparent(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JComponent) {
                ((JComponent) component).setOpaque(false);
            }
            if (component instanceof Container) {
                setTransparent((Container) component);
            }
        } 
    }
}
