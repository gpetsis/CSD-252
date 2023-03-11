package view.classes;

import controller.classes.Controller;

import model.classes.Utilities;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalCheckBoxIcon;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class StartingScreen extends JFrame {

    private static JCheckBox reducedArmy;

    private static JCheckBox noFallBack;

    public StartingScreen(){
        super("Stratego by Ioannis Petsis");

        String path = new File(System.getProperty("user.dir")).toString();
        JPanel panel = new JPanel();
        JLabel imageLabel = new JLabel();
        JButton playButton = new JButton();
        JLabel playLabel = new JLabel("Start");

        reducedArmy = new JCheckBox("Μειωμένος Στρατός");
        noFallBack = new JCheckBox("Καμία Υποχώρηση");

        imageLabel.setIcon(new ImageIcon(path + "\\resources\\images\\strategoImage.jpg"));

        playLabel.setFont(new Font("Monospaced", Font.BOLD, 50));

        playButton.setSize(80, 80);
        playButton.setOpaque(false);
        playButton.setFocusPainted(false);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorder(new EmptyBorder(100, 0, 100, 0));
        playButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Thread.sleep(1500);

                    getStartingScreenFrame().setVisible(false);

                    Controller.controllerInitialize();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        playButton.add(playLabel);
        playButton.setMargin(new Insets(35, 80, 35, 80));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        imageLabel.setBorder(new EmptyBorder(200, 0, 0, 0));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        reducedArmy.setAlignmentX(Component.CENTER_ALIGNMENT);
        reducedArmy.setFont(new Font("Monospaced", Font.BOLD, 20));

        reducedArmy.setIcon (new MetalCheckBoxIcon() {
            protected int getControlSize() { return 20; }
        });

        noFallBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        noFallBack.setFont(new Font("Monospaced", Font.BOLD, 20));

        noFallBack.setIcon (new MetalCheckBoxIcon () {
            protected int getControlSize() { return 20; }
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(imageLabel);
        panel.add(playButton);
        panel.add(reducedArmy);
        panel.add(noFallBack);

        this.setBackground(Color.LIGHT_GRAY);
        this.add(panel);
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(Utilities.getWIDTH() + 333, Utilities.getHEIGHT());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static boolean getReducedArmy(){
        return reducedArmy.isSelected();
    }

    public static boolean getNoFallBack(){
        return noFallBack.isSelected();
    }

    public JFrame getStartingScreenFrame(){
        return this;
    }
}