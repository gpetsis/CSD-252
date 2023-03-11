package view.classes;

import model.classes.Utilities;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * EndScreen class that extends JFrame.
 * This is the end screen, when a player has
 * captured the enemy's flag.
 *
 * @author Ioannis Petsis csd4993
 */
public class EndScreen extends JFrame {

    /**
     * Constructor.
     *
     * @param team the winning player
     */
    public EndScreen(String team){
        super("Stratego by Ioannis Petsis");

        this.setSize(Utilities.getWIDTH() + 333, Utilities.getHEIGHT());

        JPanel panel = new JPanel();

        panel.setSize(Utilities.getWIDTH() + 333, Utilities.getHEIGHT());

        if(team.equals("Red")){
            panel.setBackground(Color.RED);
        }else{
            panel.setBackground(Color.BLUE);

        }

        JLabel label = new JLabel(team + " Player Won!");

        label.setFont(new Font("Monospaced", Font.BOLD, 50));
        label.setBorder(new EmptyBorder(375, 0, 0, 0));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);

        panel.add(label);

        this.add(panel);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
}