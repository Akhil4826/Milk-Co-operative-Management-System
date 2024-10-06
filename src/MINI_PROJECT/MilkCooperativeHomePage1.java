package MINI_PROJECT;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MilkCooperativeHomePage1 extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Declare panel and constraints variables
    private JPanel panel;
    private GridBagConstraints gbc;

    public MilkCooperativeHomePage1() {
        setTitle("Milk Cooperative Homepage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize panel and constraints
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Create a multimedia component for video playback
        JEditorPane multimediaComponent = new JEditorPane();
        multimediaComponent.setEditable(false);

        // Load and play the video from a URL
        try {
            File videoFile = new File("C:\\Users\\akhil\\Downloads\\7671649-hd_1280_720_24fps.mp4"); // Specify the path to your local video file
            URL videoURL = videoFile.toURI().toURL(); // Convert the file path to a URL
            multimediaComponent.setPage(videoURL); // Set the video URL for playback
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Add multimedia component to the panel
        gbc.gridy = 2; // Adjust grid position if necessary
        panel.add(multimediaComponent, gbc);

        // Add the panel to the frame
        add(panel);

        // Pack and set visible
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new MilkCooperativeHomePage1();
    }
}
