package MINI_PROJECT;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContactInformation {

    private static JLabel qrCodeLabel; // Declare qrCodeLabel as a class variable to make it accessible throughout the class

    public  ContactInformation() {         // Create a new JFrame
        JFrame frame = new JFrame("Contact Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the frame layout to BorderLayout
        frame.setLayout(new BorderLayout());

        // Load the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\istockphoto-1332840539-612x612.jpg");
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(800, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        JLabel imageLabel = new JLabel(imageIcon);

        // Load the QR code image
        ImageIcon qrCodeIcon = new ImageIcon("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\Scan QR CODE to Contact us.png");
        qrCodeLabel = new JLabel(qrCodeIcon); // Initialize qrCodeLabel as a class variable

        // Create a container for the image, QR code, and the upper quarter
        JPanel upperQuarter = new JPanel(new BorderLayout());
        upperQuarter.add(imageLabel, BorderLayout.WEST);
        upperQuarter.add(qrCodeLabel, BorderLayout.EAST); // Add the QR code image
        qrCodeLabel.setVisible(true); // Set QR code visible at all times

        // Load the additional logo image
     // Load the additional logo image
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\akhil\\eclipse-workspace\\MINI_PROJECT\\src\\MINI_PROJECT\\Gallery\\Scan QR CODE to Contact us.png");
        Image logoImage = logoIcon.getImage(); // Get the image
        // Resize the image to fit within a 32x32 area
        Image resizedLogoImage = logoImage.getScaledInstance(192, 192, Image.SCALE_SMOOTH);
        // Create a new ImageIcon with the resized image
        ImageIcon resizedLogoIcon = new ImageIcon(resizedLogoImage);
        JLabel logoLabel = new JLabel(resizedLogoIcon);
        // Add the additional logo to the upper quarter
        upperQuarter.add(logoLabel, BorderLayout.CENTER);


        // Create a container for the icons and the right side of the upper quarter
        JPanel iconsContainer = new JPanel();
        iconsContainer.setBackground(new Color(240, 240, 240));

        // Add icons here
        // For example, add a Facebook icon
        JButton facebookIcon = new JButton(UIManager.getIcon("OptionPane.informationIcon"));
        facebookIcon.setPreferredSize(new Dimension(32, 32));

        // Create a new instance of ClickCounter
        ClickCounter clickCounter = new ClickCounter();
        facebookIcon.addMouseListener(clickCounter);

        iconsContainer.add(facebookIcon);

        // Add other icons here

        // Add the icons container to the upper quarter
        upperQuarter.add(iconsContainer, BorderLayout.EAST);

        // Add the upper quarter to the frame
        frame.add(upperQuarter, BorderLayout.NORTH);

        // Create a container for the left-aligned labels and the details
        JPanel detailsContainer = new JPanel(new GridLayout(4, 2, 10, 10));
        detailsContainer.setPreferredSize(new Dimension(400, 200));

        // Add Phone label
        JLabel phoneLabel = new JLabel("Phone: ");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(5, 10, 5, 0);
        detailsContainer.add(phoneLabel);

        // Add Phone number
        JLabel phoneValue = new JLabel("(953) 577-5545");
        detailsContainer.add(phoneValue);

        // Add Email label
        JLabel emailLabel = new JLabel("Email: ");
        detailsContainer.add(emailLabel);

        // Add Email
        JLabel emailValue = new JLabel("dairyone@milkcooperative.com");
        detailsContainer.add(emailValue);

        // Add Address label
        JLabel addressLabel = new JLabel("Address: ");
        detailsContainer.add(addressLabel);

        // Add Address
        JLabel addressValue = new JLabel("No21 Salagame Rd Rangoli halla Malnad college of Engineering ");
        detailsContainer.add(addressValue);

        // Add the details container to the frame
        frame.add(detailsContainer, BorderLayout.CENTER);

        // Add a container for the lower half
        JPanel lowerHalf = new JPanel(new GridLayout(1, 2, 10, 10));
        frame.add(lowerHalf, BorderLayout.SOUTH);

        // Add information to the lower half
        JButton productFAQs = new JButton("Product FAQs");
        JButton shippingReturns = new JButton("Shipping & Returns");
        JButton warranty = new JButton("Warranty");
        JButton productRegistration = new JButton("Product Registration");
        JButton privacyInquiry = new JButton("Privacy Inquiry");
        JButton corporateQuote = new JButton("Corporate Quote");

        lowerHalf.add(productFAQs);
        lowerHalf.add(shippingReturns);
        lowerHalf.add(warranty);
        lowerHalf.add(productRegistration);
        lowerHalf.add(privacyInquiry);
        lowerHalf.add(corporateQuote);

        // Add a toolbar at the bottom
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        // Add buttons to the toolbar
        JButton homeButton = new JButton(UIManager.getIcon("OptionPane.questionIcon"));
        toolbar.add(homeButton);

        JButton contactButton = new JButton(UIManager.getIcon("OptionPane.warningIcon"));
        toolbar.add(contactButton);

        JButton aboutButton = new JButton(UIManager.getIcon("OptionPane.errorIcon"));
        toolbar.add(aboutButton);

        // Add the toolbar to the frame
        frame.add(toolbar, BorderLayout.SOUTH);

        // Pack the frame to adjust its size
        frame.pack();

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Set the frame visible
        frame.setVisible(true);
    }

    private static class ClickCounter extends MouseAdapter {
        private int clickCount = 0;

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

            // Reset click count
            if (e.getClickCount() == 0) {
                clickCount = 0;
            }

            // Check click count
            if (e.getClickCount() == 2) {
                clickCount = 2;
            }

            // Show or hide the QR code image based on the click count
            if (clickCount == 2) {
                qrCodeLabel.setVisible(!qrCodeLabel.isVisible());
                clickCount = 0;
            }
        }
    }


public static void main(String[] args) {
    new ContactInformation();
}


public void setVisible(boolean b) {
	// TODO Auto-generated method stub
    new ContactInformation();

}
}

