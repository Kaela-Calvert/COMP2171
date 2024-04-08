package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SelectBikeUI extends JFrame {
    private JPanel mainPanel;
    private JLabel imageLabel;

    public SelectBikeUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        setTitle("Select Docking Station");
        setBounds(300, 100, 800, 650);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        add(mainPanel, BorderLayout.CENTER);

        // Image setup
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Images/Logo.jpg")); // Change to your image path
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imageLabel = new JLabel(new ImageIcon(image));
        mainPanel.add(imageLabel, BorderLayout.NORTH);

        // Buttons setup
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(51, 102, 153));

        JButton studentUnionButton = new JButton("Student Union");
        JButton scitechButton = new JButton("SciTech");
        JButton medSciButton = new JButton("Med Sci");
        JButton humanitiesButton = new JButton("Humanities");
        JButton socialScienceButton = new JButton("Social Science");
        JButton backGateButton = new JButton("Back Gate");
        JButton backButton = new JButton("Back");

        studentUnionButton.setPreferredSize(new Dimension(180, 40));
        studentUnionButton.setBackground(new Color(51, 153, 102));
        studentUnionButton.setForeground(Color.WHITE);
        scitechButton.setPreferredSize(new Dimension(180, 40));
        scitechButton.setBackground(new Color(51, 153, 102));
        scitechButton.setForeground(Color.WHITE);
        medSciButton.setPreferredSize(new Dimension(180, 40));
        medSciButton.setBackground(new Color(51, 153, 102));
        medSciButton.setForeground(Color.WHITE);
        humanitiesButton.setPreferredSize(new Dimension(180, 40));
        humanitiesButton.setBackground(new Color(51, 153, 102));
        humanitiesButton.setForeground(Color.WHITE);
        socialScienceButton.setPreferredSize(new Dimension(180, 40));
        socialScienceButton.setBackground(new Color(51, 153, 102));
        socialScienceButton.setForeground(Color.WHITE);
        backGateButton.setPreferredSize(new Dimension(180, 40));
        backGateButton.setBackground(new Color(51, 153, 102));
        backGateButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(180, 40));
        backButton.setBackground(new Color(51, 153, 102));
        backButton.setForeground(Color.WHITE);

        buttonPanel.add(studentUnionButton);
        buttonPanel.add(scitechButton);
        buttonPanel.add(medSciButton);
        buttonPanel.add(humanitiesButton);
        buttonPanel.add(socialScienceButton);
        buttonPanel.add(backGateButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Button listeners
        studentUnionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                BikeListUI bikes = new BikeListUI("StudentUnion", SelectBikeUI.this);
            }
        });

        scitechButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                BikeListUI bikes = new BikeListUI("SciTech", SelectBikeUI.this);
            }
        });

        medSciButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                BikeListUI bikes = new BikeListUI("Humanities", SelectBikeUI.this);
            }
        });

        humanitiesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                BikeListUI bikes = new BikeListUI("MED", SelectBikeUI.this);
            }
        });

        socialScienceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                BikeListUI bikes = new BikeListUI("ScoSci", SelectBikeUI.this);
            }
        });

        backGateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                BikeListUI bikes = new BikeListUI("Backgate", SelectBikeUI.this);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                MainPage main = new MainPage(); 
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SelectBikeUI::new);
    }
}