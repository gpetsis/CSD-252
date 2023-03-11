package view.classes.sidepanel;

import javax.swing.*;
import java.awt.*;

/**
 * Capture class that extends JPanel.
 * This class holds each individual capture a player has and is placed inside
 * the capture panel.
 *
 * @author Ioannis Petsis csd4993
 */
public class Capture extends JPanel{

    /**
     * Label that has the number of pieces of the same kind
     * the player has captured.
     */
    private JLabel label;

    /**
     * Path to the image of the piece.
     */
    private String path;

    /**
     * Constructor.
     *
     * @param path the path to the image of the piece
     */
    public Capture(String path){
        this.path = path;

        JLabel text = new JLabel();

        ImageIcon imageicon = new ImageIcon(path);
        Image image = imageicon.getImage().getScaledInstance(65, 75, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(image);

        label = new JLabel("1");
        label.setIcon(icon);

        this.add(label);
        this.add(text);
    }

    /**
     * Function that returns the label which holds the amount of pieces
     * of the same kind the player has captured.
     *
     * @return the label
     */
    public JLabel getLabel(){ return this.label; }

    /**
     * Function that returns the path to the image of the piece.
     *
     * @return the path to the image of the piece
     */
    public String getPath(){ return this.path; }
}